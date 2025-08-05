package com.chabiaminesifeddine.userProvisioning.service;

import com.chabiaminesifeddine.userProvisioning.model.UserDTO;

public class IASprovisioningService {


    public void provision(UserDTO user) {
        // Simulation of  provisioning (mock IAS)
        System.out.printf("Provisioning user %s with role %s%n", user.getEmail(), user.getRole());

        // storage simulation (saving in a db later on )
    }


}
