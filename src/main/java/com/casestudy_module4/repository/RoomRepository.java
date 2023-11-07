package com.casestudy_module4.repository;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findByAccommodation(Accommodation accommodation);
    List<Room> findByAccommodationId(Long accId);


}
