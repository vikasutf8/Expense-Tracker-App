package com.Expenses.AuthService.Repository;

import com.Expenses.AuthService.Enitity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

   public UserInfo findByUsername(String username);
}
