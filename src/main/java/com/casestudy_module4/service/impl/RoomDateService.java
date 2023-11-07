package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.RoomDate;
import com.casestudy_module4.repository.AccommodationRepository;
import com.casestudy_module4.repository.RoomRepository;
import com.casestudy_module4.service.IRoomDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoomDateService  implements IRoomDateService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<RoomDate> findAll() {
        return null;
    }

    @Override
    public RoomDate save(RoomDate RoomDate) {
        return null;
    }

    @Override
    public RoomDate update(RoomDate RoomDate) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
