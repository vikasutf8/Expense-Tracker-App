package com.Expenses.AuthService.Service;

import com.Expenses.AuthService.Dto.UserInfoDto;
import com.Expenses.AuthService.Enitity.Enum.RoleType;
import com.Expenses.AuthService.Enitity.UserInfo;
import com.Expenses.AuthService.Enitity.UserRole;
import com.Expenses.AuthService.Repository.UserInfoRepository;
import com.Expenses.AuthService.Repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserInfoRepository userInfoRepository;

    private  final PasswordEncoder passwordEncoder;

    private  final UserRoleRepository userRoleRepository;

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
//        Set<UserRole> roles = userRoleRepository.findByRoleTypeNameIn(dto.getRoles());

        Set<RoleType> roles = dto.getRoles();

        // check duplicate
        if (checkIfUserAlreadyExist(dto) != null) {
            return false;
        }

        if (roles.isEmpty()) {
            throw new RuntimeException("Invalid roles provided");
        }

        // create user
        UserInfo user = UserInfo.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(roles)
                .build();
        userInfoRepository.save(user);

        return true;

    }
}
