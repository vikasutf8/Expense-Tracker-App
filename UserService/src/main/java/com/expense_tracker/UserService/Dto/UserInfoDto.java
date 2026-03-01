package com.expense_tracker.UserService.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {

        private String userId;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private String profilePic;


}
