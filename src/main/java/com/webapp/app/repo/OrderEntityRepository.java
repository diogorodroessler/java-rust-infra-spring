package com.webapp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.app.entity.OrderEntity;

public interface OrderEntityRepository 
    extends JpaRepository<OrderEntity, Long> {

}
