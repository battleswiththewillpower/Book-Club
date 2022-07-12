package com.ashleybattles.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashleybattles.bookclub.models.Author;
import com.ashleybattles.bookclub.repo.AuthorRepo;


@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepo authorRepo;
	
	public Author save(Author a) {
		return authorRepo.save(a);
	}
	
	public List<Author> getAll(){
		return authorRepo.findAll();
	}
	
	public Author getOne(Long id) {
		Optional<Author> a = authorRepo.findById(id);
		
		if(a.isPresent()) {
			return a.get();
		}else {
			return null;
		}
	}
	
	public void delete(Long id) {
		authorRepo.deleteById(id);
	}

}
