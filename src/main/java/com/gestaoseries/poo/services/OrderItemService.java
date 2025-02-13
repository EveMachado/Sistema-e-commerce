package com.gestaoseries.poo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestaoseries.poo.entities.OrderItem;
import com.gestaoseries.poo.repositories.OrderItemRepository;


@Service
public class OrderItemService {
	
	
	@Autowired
	private OrderItemRepository repository;
	
	
	public List<OrderItem> findAll(){
		return repository.findAll();
		
	}
	
	public OrderItem findById(Long id) {
		Optional<OrderItem> obj = Optional.empty();
		return obj.get();
		
	}

}
