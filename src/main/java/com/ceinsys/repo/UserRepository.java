package com.ceinsys.repo;

import com.ceinsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByEmail(String email);


        @Query(value = "SELECT * FROM user ;", nativeQuery = true)
        List<User> getAllUser();

        @Query(value = "SELECT * FROM user WHERE role = 'ADMIN' ;", nativeQuery = true)
        List<User> getAllAdmins();

        @Query(value = "SELECT * FROM user WHERE role = 'SUPER_ADMIN' ;", nativeQuery = true)
        List<User> getAllSuperAdmin();



}
