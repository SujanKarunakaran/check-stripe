package com.sujan.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sujan.demo.model.Jewellry;
import com.sujan.demo.services.JewellryService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/jewellry")
public class JewellryController {

	@Autowired
    private JewellryService jewellryService;
	
	@PostMapping
    public ResponseEntity<Jewellry> createJewellry(@RequestBody Jewellry jewellry){
		return jewellryService.createJewellry(jewellry);
	}
	
	@GetMapping
    public ResponseEntity<List<Jewellry>> listJewellries(){
		return jewellryService.listJewellries();
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Jewellry> getJewellryById(@PathVariable Integer id){
	    return jewellryService.getJewellryById(id);
	}
	
	@GetMapping(params = "userName")
	public ResponseEntity<Jewellry> getJewellryByName(@RequestParam String Name){
	    return jewellryService.getJewellryByName(Name);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Jewellry> updateJewellry(@PathVariable("id") Integer id, @RequestBody Jewellry jewellry) {
        return jewellryService.updateJewellry(id, jewellry);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteJewellry(@PathVariable int id) {
	    return jewellryService.deleteJewellry(id);
	}
	
	@GetMapping("/search/{search}")
	public ResponseEntity<?> getBooks(@PathVariable String search, @RequestParam(value="pageNo",defaultValue="0") int pageNo,
	@RequestParam(value="pageSize",defaultValue="0") int pageSize,@RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
	return jewellryService.getBookBySearch(search,pageNo,pageSize,sortBy);
	} 
	
	@GetMapping("/page")
	public ResponseEntity<Map<String, Object>> getBooks(@RequestParam(value="pageNo",defaultValue="0") int pageNo,
	@RequestParam(value="pageSize",defaultValue="0") int pageSize,@RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
	return jewellryService.getBooks(pageNo,pageSize,sortBy);
	}

}
