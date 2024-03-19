package gregl.opticuswebshop.service.impl;


import gregl.opticuswebshop.service.PayPalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class PayPalServiceImpl implements PayPalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.api.base.url}")
    private String apiBaseUrl;

    @Value("${paypal.return.url}")
    private String paypalReturnUrl;

    @Value("${paypal.cancel.url}")
    private String paypalCancelUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PayPalServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getAccessToken() {
        HttpHeaders headers = createBasicAuthHeaders(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String tokenEndpoint = apiBaseUrl + "/v1/oauth2/token";

        ResponseEntity<HashMap> response = restTemplate.exchange(
                tokenEndpoint,
                HttpMethod.POST,
                entity,
                HashMap.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            HashMap<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("access_token")) {
                return (String) responseBody.get("access_token");
            }
        }

        throw new RuntimeException("Failed to retrieve access token from PayPal");
    }

    @Override
    public String createOrder(Double total, String currency) {
        String accessToken = getAccessToken();

        HttpHeaders headers = createBasicAuthHeaders(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        ObjectNode orderPayload = createOrderPayload(total, currency, paypalReturnUrl, paypalCancelUrl);

        HttpEntity<String> entity = new HttpEntity<>(orderPayload.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                apiBaseUrl + "/v2/checkout/orders",
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED && response.getBody() != null) {
            ObjectNode responseBody;
            try {
                responseBody = objectMapper.readValue(response.getBody(), ObjectNode.class);
                return responseBody.get("links").get(1).get("href").asText();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse the PayPal order creation response", e);
            }
        } else {
            throw new RuntimeException("Failed to create order in PayPal. Response: " + response.getBody());
        }
    }

    @Override
    public Void captureOrder(String orderId) {
        String accessToken = getAccessToken();

        HttpHeaders headers = createBasicAuthHeaders(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiBaseUrl + "/v2/checkout/orders/" + orderId + "/capture",
                HttpMethod.POST,
                entity,
                String.class
        );

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to capture order in PayPal. Response: " + response.getBody());
        }

        return null;
    }

    private ObjectNode createOrderPayload(Double total, String currency, String returnUrl, String cancelUrl) {
        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("intent", "CAPTURE");
        ObjectNode purchaseUnit = objectMapper.createObjectNode();
        purchaseUnit.put("reference_id", "PUHF");
        ObjectNode amount = objectMapper.createObjectNode();
        amount.put("currency_code", currency);
        amount.put("value", total.toString());
        purchaseUnit.set("amount", amount);
        payload.set("purchase_units", objectMapper.createArrayNode().add(purchaseUnit));
        ObjectNode applicationContext = objectMapper.createObjectNode();
        applicationContext.put("return_url", returnUrl);
        applicationContext.put("cancel_url", cancelUrl);
        payload.set("application_context", applicationContext);
        return payload;
    }

    private HttpHeaders createBasicAuthHeaders(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        if (mediaType.equals(MediaType.APPLICATION_FORM_URLENCODED)) {
            String credentials = clientId + ":" + clientSecret;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
            headers.add("Authorization", "Basic " + encodedCredentials);
        }
        return headers;
    }


}