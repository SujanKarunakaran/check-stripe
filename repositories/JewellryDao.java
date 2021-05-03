package com.sujan.demo.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sujan.demo.model.Jewellry;

@Repository
public interface JewellryDao extends MongoRepository<Jewellry, Integer> {
	Optional<Jewellry> findByname(String name);
	
	@Query("{'$or':[ {'name': {$regex : ?0, $options: 'i'}}, {'description': {$regex : ?0, $options: 'i'}}, {'language': {$regex : ?0, $options: 'i'}}, {'genre': {$regex : ?0, $options: 'i'}}]}")
	Page<Jewellry> searchBooks(Pageable pageable, String searchText); 
}
