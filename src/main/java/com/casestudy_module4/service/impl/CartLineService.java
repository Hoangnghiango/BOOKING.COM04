package com.casestudy_module4.service.impl;

import com.casestudy_module4.model.CartLine;
import com.casestudy_module4.service.ICartLineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CartLineService implements ICartLineService {
    @Override
    public List<CartLine> findAll() {
        return null;
    }

    @Override
    public CartLine save(CartLine cartLine) {
        return null;
    }

    @Override
    public CartLine update(CartLine cartLine) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
