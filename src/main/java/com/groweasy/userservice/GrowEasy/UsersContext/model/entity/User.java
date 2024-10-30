package com.groweasy.userservice.GrowEasy.UsersContext.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", length =150, nullable=false)
    private String firstName;

    @Column(name="last_name", length =150, nullable=false)
    private String lastName;

    @Column(name="email", length =150, nullable=false)
    private String email;

    @Column(name="password", length =150, nullable=false)
    private String password;

}
