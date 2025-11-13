package com.Expenses.AuthService.Repository;

import com.Expenses.AuthService.Enitity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

   public UserInfo findByUsername(String username);
}
