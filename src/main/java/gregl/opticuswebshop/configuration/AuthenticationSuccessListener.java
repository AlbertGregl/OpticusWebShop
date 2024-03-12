package gregl.opticuswebshop.configuration;

import gregl.opticuswebshop.DTO.model.LoginHistory;
import gregl.opticuswebshop.DTO.model.User;
import gregl.opticuswebshop.service.LoginHistoryService;
import gregl.opticuswebshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final LoginHistoryService loginHistoryService;
    private final HttpServletRequest request;
    private UserService userService;


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((org.springframework.security.core.userdetails.User) event.getAuthentication().getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            String ipAddress = getClientIP();
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setUser(user);
            loginHistory.setIpAddress(ipAddress);
            loginHistory.setLoginTime(java.time.LocalDateTime.now());
            loginHistoryService.saveLoginHistory(loginHistory);
        }
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
