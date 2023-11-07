package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Room;
import com.casestudy_module4.model.User;
import com.casestudy_module4.repository.AccommodationRepository;
import com.casestudy_module4.service.IAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService  implements IAccommodationService {
    @Autowired
    private AccommodationRepository accommodationRepository;
    @Override
    public List<Accommodation> findAll() {
        return (List<Accommodation>) accommodationRepository.findAll();
    }

    @Override
    public Accommodation save(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation update(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);

    }

    public Optional<Accommodation> getAccommodationById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public List<Accommodation> findAccommodationListByUser(User user) {
        return accommodationRepository.findByUser(user);
    }

    @Override
    public Accommodation findAccommodationById(Long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found with ID: " + accommodationId));
    }

    @Override
    public Accommodation findAccommodationByRoom(Room room) {
        return accommodationRepository.findByRoomList(room);
    }
}
