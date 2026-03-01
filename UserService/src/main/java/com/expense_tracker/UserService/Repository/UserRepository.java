package com.expense_tracker.UserService.Repository;

import com.expense_tracker.UserService.Entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo,String> {

    UserInfo findByUserId(String userId);
}
