package com.Expenses.AuthService.Service;

import com.Expenses.AuthService.Dto.UserInfoDto;
import com.Expenses.AuthService.Enitity.UserInfo;
import com.Expenses.AuthService.Repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserInfoRepository userInfoRepository;

    private  final PasswordEncoder passwordEncoder; 

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not found user"+username);
        }

        return new CustomUserDetails(user); // assuming you created CustomUserDetails earlier

    }


    public  UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userInfoRepository.findByUsername(userInfoDto.getUsername()
        );
    }

    public  boolean signUp(UserInfoDto dto){

        // encode password
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        // check duplicate
        if (checkIfUserAlreadyExist(dto) != null) {
            return false;
        }

        // map DTO → Entity
        UserInfo user = new UserInfo();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());

        userInfoRepository.save(user);

        return true;

    }
}
