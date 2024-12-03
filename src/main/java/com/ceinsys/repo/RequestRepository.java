package com.ceinsys.repo;

import com.ceinsys.model.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<ItemRequest, Long> {
    Optional<ItemRequest> findById(Long id);
}
