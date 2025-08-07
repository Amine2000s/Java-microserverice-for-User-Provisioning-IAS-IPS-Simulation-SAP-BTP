package com.chabiaminesifeddine.userProvisioning.service;

import com.chabiaminesifeddine.userProvisioning.model.employeeDTO;
import com.chabiaminesifeddine.userProvisioning.repositories.usersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chabiaminesifeddine.userProvisioning.model.employee;

import java.util.List;

@Service
public class IASprovisioningService {

    @Autowired
    usersRepo usersRepo;


    public void provision(employeeDTO user, String requestby) {

        employee newemployee = employee.builder()
                .name(user.getName()).
                email(user.getEmail()).
                role(user.getRole()).
                provisionedBy(requestby)
                .build();


        usersRepo.save(newemployee);
        System.out.printf("Provisioning user %s with role %s%n", user.getEmail(), user.getRole());

    }

    public List<employee> getUsers(){
        return usersRepo.findAll();
    }

}
