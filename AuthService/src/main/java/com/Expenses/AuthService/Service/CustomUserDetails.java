package com.Expenses.AuthService.Service;

import com.Expenses.AuthService.Enitity.UserInfo;
import com.Expenses.AuthService.Enitity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetails extends UserInfo implements UserDetails {

//fields

    private String username;

    private  String password;

    Collection<? extends GrantedAuthority> authorities;


// constructor
    public CustomUserDetails(UserInfo userInfo) {
       this.username = userInfo.getUsername();
       this.password = userInfo.getPassword();

        List<GrantedAuthority> auths =new ArrayList<>();

        for(UserRole role :userInfo.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getRoleTypeName().name().toUpperCase()));
        }
        this.authorities =auths;
    }




    //no use of below we create customer

    //these are gettter and setters

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // You can return a real field or just true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
