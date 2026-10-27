package com.webapp.app.services;

import org.springframework.stereotype.Service;

import com.webapp.app.entity.OrderEntity;
import com.webapp.app.repo.OrderEntityRepository;

import jakarta.transaction.Transactional;

@Service 
public class OrderService {

    private final OrderEntityRepository repository;

    public OrderService(OrderEntityRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(String message) {
        repository.save(new OrderEntity(message));
    }
}
