package com.chabiaminesifeddine.userProvisioning.controller;

import com.chabiaminesifeddine.userProvisioning.model.UserDTO;
import com.chabiaminesifeddine.userProvisioning.service.IASprovisioningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IASprovisioningService provisioningService;

    public UserController(IASprovisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    @PostMapping
    public ResponseEntity<String> provisionUser(@RequestBody UserDTO user) {
        provisioningService.provision(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User provisioned");
    }

}
