package com.Expenses.AuthService.Controllers;

import com.Expenses.AuthService.Dto.JwtResponseTokenDto;
import com.Expenses.AuthService.Dto.UserInfoDto;
import com.Expenses.AuthService.Enitity.Tokens;

import com.Expenses.AuthService.Service.JwtTokenService;
import com.Expenses.AuthService.Service.RefreshTokenService;
import com.Expenses.AuthService.Service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    @Autowired
    private JwtTokenService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto) {
        System.out.println(userInfoDto+"userInfo");
        try {
            Boolean isSignedUp = userDetailsService.signUp(userInfoDto);

            if (Boolean.FALSE.equals(isSignedUp)) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }


        Tokens refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());
            System.out.println("jwt token generate"+jwtToken);
            // ✅ Build and return response
            JwtResponseTokenDto jwtResponse = JwtResponseTokenDto.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken.getToken())
                    .build();

            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Exception in User Service: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
