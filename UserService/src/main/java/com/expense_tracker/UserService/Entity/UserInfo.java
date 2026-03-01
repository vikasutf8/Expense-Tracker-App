package com.expense_tracker.UserService.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.PropertyNamingStrategy;
import tools.jackson.databind.annotation.JsonNaming;

/**
 *
 * here we deserialized
 *  auth producer --> userInfo consumer userService
 *
 */

@Data
@Document(collection = "users")
@JsonNaming(PropertyNamingStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // if any thing comes null  -- dont de-serialize or serialize it
public class UserInfo {

    private String id;   // MongoDB _id

    @Id
    private String userId;
    private String firstName; // internally should be consider as first_name
    private String lastName;
    private String phoneNumber;
    private String email;
    private String profilePic;
}
