package com.expense_tracker.UserService.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserInfo {

    @Id
    private String id;   // MongoDB _id

    private String userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String profilePic;
}
