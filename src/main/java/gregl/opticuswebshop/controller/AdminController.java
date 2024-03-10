package gregl.opticuswebshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin.html")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public String adminDashboard() {
        return "admin";
    }
}
