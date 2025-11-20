package com.Expenses.AuthService.Dto;


import com.Expenses.AuthService.Enitity.Enum.RoleType;
import com.Expenses.AuthService.Enitity.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy .class)
public class UserInfoDto  {
    private String username;
    private String password;
    private Set<RoleType> roles;
}
