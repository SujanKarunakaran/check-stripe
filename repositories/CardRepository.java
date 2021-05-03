package com.sujan.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sujan.demo.model.PaymentRequest;

public interface CardRepository extends MongoRepository<PaymentRequest,String>{
	

	}


