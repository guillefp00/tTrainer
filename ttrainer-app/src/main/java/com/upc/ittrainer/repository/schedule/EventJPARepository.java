package com.upc.ittrainer.repository.schedule;

import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.schedule.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventJPARepository extends JpaRepository<Event, Integer> {

    List<Event> findByClientAppUser(AppUser appUser);

}


