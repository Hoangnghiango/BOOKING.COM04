package com.casestudy_module4.repository;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation,Long> {
    List<Accommodation> findByUser(User user);
    Accommodation findByRoomList(Room room);
}
