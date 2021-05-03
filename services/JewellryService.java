package com.sujan.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sujan.demo.model.Jewellry;
import com.sujan.demo.repositories.JewellryDao;
import com.sujan.demo.repositories.JewellryRepositoryCustom;

@Service
public class JewellryService {

	@Autowired
	private JewellryDao jewellryDao;
	
	@Autowired
	private JewellryRepositoryCustom jewellryRepositoryCustom;
	
	public ResponseEntity<Jewellry> createJewellry(Jewellry jewellry) {
		try {
			Integer id = jewellryRepositoryCustom.getMaxJewellryId() + 1;
			Jewellry jewellryNew = jewellryDao.save(new Jewellry(id, jewellry.getImage(), jewellry.getImageurl(), jewellry.getName(), jewellry.getDescription(), 
					jewellry.getPrice()));
		    return new ResponseEntity<>(jewellryNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<List<Jewellry>> listJewellries() {
		try {
		    List<Jewellry> jewellries = new ArrayList<Jewellry>();
		    jewellryDao.findAll().forEach(jewellries::add);
		    if (jewellries.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(jewellries, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Jewellry> getJewellryById(Integer id) {
		Optional<Jewellry> jewellry = jewellryDao.findById(id);
		if (jewellry.isPresent()) {
			return new ResponseEntity<>(jewellry.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Jewellry> updateJewellry(Integer id, Jewellry jewellry) {
		Optional<Jewellry> jewellryData = jewellryDao.findById(id);

		if (jewellryData.isPresent()) {
			Jewellry jewellryOld = jewellryData.get();
			jewellryOld.setName(jewellry.getName());
			jewellryOld.setImage(jewellry.getImage());
			jewellryOld.setImageurl(jewellry.getImageurl());
			jewellryOld.setDescription(jewellry.getDescription());
			jewellryOld.setPrice(jewellry.getPrice());
		    return new ResponseEntity<>(jewellryDao.save(jewellryOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<HttpStatus> deleteJewellry(int id) {
		Optional<Jewellry> jewellry = jewellryDao.findById(id);
		if (jewellry.isPresent()) {
			jewellryDao.delete(jewellry.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Jewellry> getJewellryByName(String name) {
		Optional<Jewellry> jewellry = jewellryDao.findByname(name);
		if (jewellry.isPresent()) {
			return new ResponseEntity<>(jewellry.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Page<Jewellry>> getBookBySearch(String searchText, int pageNo, int pageSize, String sortBy) {
		try {
		// List<BookModel> book = bookRepository.findByTitleContaining(title);

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Jewellry> bookPages = jewellryDao.searchBooks(pageable, ".*" + searchText + ".*");
		// if (bookPages.isEmpty()) {
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// }
		return new ResponseEntity<>(bookPages, HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	
	public ResponseEntity<Map<String, Object>> getBooks(int pageNo, int pageSize, String sortBy) {
	  try {System.out.println(pageNo);
	  System.out.println(pageSize);
	  System.out.println(sortBy);
	  Map<String, Object> response = new HashMap<>();
	    Sort sort = Sort.by(sortBy);
	    System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		 System.out.println(pageable);
	    Page<Jewellry> page = jewellryDao.findAll(pageable);
	    System.out.println(page);
	    response.put("data", page.getContent());
	    System.out.println(response);
	    response.put("Total no of pages", page.getTotalPages());
	    response.put("Total no of elements", page.getTotalElements());
	    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
