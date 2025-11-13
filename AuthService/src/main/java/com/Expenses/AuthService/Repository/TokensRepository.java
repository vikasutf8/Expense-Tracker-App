package com.Expenses.AuthService.Repository;


import com.Expenses.AuthService.Enitity.Tokens;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokensRepository extends CrudRepository<Tokens,Integer> {

    Optional<Tokens> findByToken(String token);
}
