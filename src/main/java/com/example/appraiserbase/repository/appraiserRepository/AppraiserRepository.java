package com.example.appraiserbase.repository.appraiserRepository;

import com.example.appraiserbase.model.Appraiser;

import com.example.appraiserbase.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppraiserRepository extends JpaRepository<Appraiser, Long>, AppraiserRepositoryCustom {

        Optional<Appraiser> findByLogin(String login);

        @Query("select r from Role r")
        List<Role> getAllRolesForAppraiser();
}
