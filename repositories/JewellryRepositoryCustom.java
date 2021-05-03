package com.sujan.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sujan.demo.model.Jewellry;

@Repository
public class JewellryRepositoryCustom {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Integer getMaxJewellryId() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "id"));
		query.limit(1);
		Jewellry maxObject = mongoTemplate.findOne(query, Jewellry.class);
		if (maxObject == null) {
		    return 0;
		}
		return maxObject.getId();
    }


}
