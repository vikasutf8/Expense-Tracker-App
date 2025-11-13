package com.Expenses.AuthService.Dto;


import com.Expenses.AuthService.Enitity.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy .class)
public class UserInfoDto extends UserInfo {
    private String userName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
