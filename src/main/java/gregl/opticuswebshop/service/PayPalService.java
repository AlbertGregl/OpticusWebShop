package gregl.opticuswebshop.service;

public interface PayPalService {
    String getAccessToken();
    String createOrder(Double total, String currency);
}
