package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> findAll();
    Accommodation save(Cart cart);
    Accommodation update(Cart cart);
    void deleteById(Long id);
}
