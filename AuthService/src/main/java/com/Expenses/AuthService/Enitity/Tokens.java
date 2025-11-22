package com.Expenses.AuthService.Enitity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "tokens")
@Builder
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    private String token;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "token_id",referencedColumnName = "user_id")   // FK in tokens table
    private UserInfo userInfo;
}