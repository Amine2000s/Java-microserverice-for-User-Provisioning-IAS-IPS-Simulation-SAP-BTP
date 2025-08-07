package com.chabiaminesifeddine.userProvisioning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class employee {


        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;

        private String name;
        private String email;
        private String role;

        @CreatedDate
        private LocalDateTime createdAt;
        private String provisionedBy;


    public employee() {

    }
}
