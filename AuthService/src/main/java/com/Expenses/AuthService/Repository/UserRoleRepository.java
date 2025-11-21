package com.Expenses.AuthService.Repository;

import com.Expenses.AuthService.Enitity.Enum.RoleType;
import com.Expenses.AuthService.Enitity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Set<UserRole> findByRoleTypeNameIn(Set<RoleType> roleNames);
}

