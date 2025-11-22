package com.Expenses.AuthService.Controllers;


import com.Expenses.AuthService.Dto.AuthRequestDto;
import com.Expenses.AuthService.Dto.JwtResponseTokenDto;
import com.Expenses.AuthService.Dto.RefreshTokenRequestDto;
import com.Expenses.AuthService.Enitity.Tokens;
import com.Expenses.AuthService.Enitity.UserInfo;
import com.Expenses.AuthService.Repository.UserInfoRepository;
import com.Expenses.AuthService.Service.JwtTokenService;
import com.Expenses.AuthService.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/v1")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtTokenService jwtService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ LOGIN ENDPOINT
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndFetchToken(@RequestBody AuthRequestDto authRequestDTO) {
        UserInfo user = userInfoRepository.findByUsername(authRequestDTO.getUsername());
        if (user != null) {
            System.out.println("Raw password login: " + authRequestDTO.getPassword());
            System.out.println("Encoded password from DB: " + user.getPassword());
            System.out.println("Matches? " + passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword()));
        }

        try {
            System.out.println(authRequestDTO.getUsername()+"auth request dto"+authRequestDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );
            System.out.println(authentication+"dsfjhashdkj");
            if (authentication.isAuthenticated()) {
                System.out.println(authentication.isAuthenticated());
                Tokens refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                String jwtToken = jwtService.generateToken(authRequestDTO.getUsername());

               JwtResponseTokenDto jwtResponse = JwtResponseTokenDto.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken.getToken())
                        .build();

                return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Authentication failed: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(Tokens:: getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    JwtResponseTokenDto jwtResponse = JwtResponseTokenDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getRefreshToken()  )
                            .build();
                    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found in DB!"));
    }
}
