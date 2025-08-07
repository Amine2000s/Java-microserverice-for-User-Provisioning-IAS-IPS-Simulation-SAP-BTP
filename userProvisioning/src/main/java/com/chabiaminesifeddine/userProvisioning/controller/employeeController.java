package com.chabiaminesifeddine.userProvisioning.controller;

import com.chabiaminesifeddine.userProvisioning.model.employeeDTO;
import com.chabiaminesifeddine.userProvisioning.model.employee;
import com.chabiaminesifeddine.userProvisioning.service.IASprovisioningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class employeeController {

    private final IASprovisioningService provisioningService;



    private static final Logger logger = LoggerFactory.getLogger(employeeController.class);

    public employeeController(IASprovisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }



    @PostMapping
    //@PreAuthorize("hasAuthority('UserProvisioner')") soon
    public ResponseEntity<String> provisionUser(@RequestBody employeeDTO USER,
                                                @AuthenticationPrincipal Jwt principal) {

        String requestedBy = principal.getClaimAsString("email");

        logger.info("🔐 Provisioning user: {} | Requested by: {}", USER.getEmail(), requestedBy);
        try {
            provisioningService.provision(USER,requestedBy);
            logger.info("✅ Provisioning successful for user: {}", USER.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body("User provisioned");
        } catch (Exception e) {
            logger.error("❌ Provisioning failed for user: {} | Error: {}", USER.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

    //lets ignore security for a moment
    @GetMapping
    //@PreAuthorize("hasAuthority('UserProvisioner')") soon
    public ResponseEntity<List<employee>> all(@AuthenticationPrincipal Jwt principal) {

        String requestedBy = principal.getClaimAsString("email");

        try {
            logger.info("✅  successful retreival for users list by : {}", requestedBy);
            List<employee>  usersList=provisioningService.getUsers();

            return ResponseEntity.status(HttpStatus.CREATED).body(usersList);
        } catch (Exception e) {
            logger.error("❌ Provisioning failed for : {} | Error: {}", requestedBy, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
