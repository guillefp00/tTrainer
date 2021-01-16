package com.upc.ittrainer.repository.progression;

import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.progression.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressionJPARepository extends JpaRepository<Progression, Integer> {

    List<Progression> findByClientAppUser(AppUser appUser);

}


