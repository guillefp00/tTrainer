package com.upc.ittrainer.repository.admin;

import com.upc.ittrainer.model.admin.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserJPARepository extends JpaRepository<AppUser, String> {

}


