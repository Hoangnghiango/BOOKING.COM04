package com.casestudy_module4.repository;

import com.casestudy_module4.model.RoomDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomDateRepository extends CrudRepository<RoomDate, Long> {
}
