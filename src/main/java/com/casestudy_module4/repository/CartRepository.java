package com.casestudy_module4.repository;

import com.casestudy_module4.model.Accommodation;
import com.casestudy_module4.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartRepository extends CrudRepository<Cart,Long> {
}
