package com.upc.ittrainer.repository.client;

import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientJPARepository extends JpaRepository<Client, Integer> {

    List<Client> findByAppUser(AppUser appUser);

    Optional<Client> findById(Integer id);

}


