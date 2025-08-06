package com.chabiaminesifeddine.userProvisioning.controller;

import com.chabiaminesifeddine.userProvisioning.model.UserDTO;
import com.chabiaminesifeddine.userProvisioning.service.IASprovisioningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IASprovisioningService provisioningService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IASprovisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }



    @PostMapping
    //@PreAuthorize("hasAuthority('UserProvisioner')") soon
    public ResponseEntity<String> provisionUser(@RequestBody UserDTO USER,
                                                @AuthenticationPrincipal Jwt principal) {

        String requestedBy = principal.getClaimAsString("email");

        logger.info("🔐 Provisioning user: {} | Requested by: {}", USER.getEmail(), requestedBy);
        try {
            provisioningService.provision(USER);
            logger.info("✅ Provisioning successful for user: {}", USER.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body("User provisioned");
        } catch (Exception e) {
            logger.error("❌ Provisioning failed for user: {} | Error: {}", USER.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

}
