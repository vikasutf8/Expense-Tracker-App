package com.Expenses.AuthService.Enitity;

import com.Expenses.AuthService.Enitity.Enum.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@Entity
@Table(name = "roles_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private  Long roleId;


    @Column(name = "role_name")
    private String roleTypeName;
}
