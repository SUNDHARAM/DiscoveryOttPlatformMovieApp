package com.example.movie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}
