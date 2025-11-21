package com.Expenses.AuthService.Service;


import com.Expenses.AuthService.Enitity.Tokens;
import com.Expenses.AuthService.Enitity.UserInfo;
import com.Expenses.AuthService.Repository.TokensRepository;
import com.Expenses.AuthService.Repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

//autowired is field injection
    private  final  UserInfoRepository userInfoRepository;
    private  final TokensRepository tokensRepository;

    public Tokens createRefreshToken(String Username){
        UserInfo userInfoExtract = userInfoRepository.findByUsername(Username);

        System.out.println("userInfo on find by username"+userInfoExtract);

//        Instead of calling a constructor directly, it lets you set properties by name and then build the object.
         Tokens refreshToken = Tokens
                 .builder()
                 .userInfo(userInfoExtract)
                 .expiryDate(Instant.now().plusMillis(6000000))
                 .build();

        System.out.println("userInfo on referesh token"+refreshToken);


        return tokensRepository.save(refreshToken);

    }

    public Tokens verifyExpiration(Tokens token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            tokensRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please sign in again.");
        }
        return token;
    }

    public Optional<Tokens> findByToken(String token) {
        return tokensRepository.findByToken(token);
    }
}
