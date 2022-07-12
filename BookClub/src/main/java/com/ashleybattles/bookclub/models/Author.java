package com.ashleybattles.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="authors")
public class Author {
	
	//primary keys
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    
    //member variables
  
    @Size(min = 2, message = "Must be at least 2 characters!")
    private String name;
    @Size(min = 2, message = "Must be at least 2 characters!")
    private String title;
    @Size(min = 2, message = "Must be at least 2 characters!")
    private String thoughts;

	//Data Creation Trackers
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    
    //constructor
    public Author() {
        
    }
    
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}
	
    
  public String getThoughts() {
		return thoughts;
	}


	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}


	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


	//data creation event
    @PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
