package com.sujan.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Jewellry {
	@Id
	private Integer id;
	private String image;
	private String imageurl;
	private String name;
	private String description;
	private long price;
	
	
	public Jewellry(Integer id, String image, String imageurl, String name, String description, long price) {
		super();
		this.id = id;
		this.image = image;
		this.imageurl = imageurl;
		this.name = name;
		this.description = description;
		this.price = price;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getImageurl() {
		return imageurl;
	}


	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}
		
	

}
