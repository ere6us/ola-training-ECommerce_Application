package com.training.ecommerce.repository;

import com.training.ecommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String query);
}
