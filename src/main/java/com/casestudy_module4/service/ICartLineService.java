package com.casestudy_module4.service;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.CartLine;

import java.util.List;

public interface ICartLineService {
    List<CartLine> findAll();
    CartLine save(CartLine cartLine);
    CartLine update(CartLine cartLine);
    void deleteById(Long id);
}
