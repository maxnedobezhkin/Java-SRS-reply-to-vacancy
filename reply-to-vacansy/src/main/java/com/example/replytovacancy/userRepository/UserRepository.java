package com.example.replytovacancy.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.replytovacancy.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

}
