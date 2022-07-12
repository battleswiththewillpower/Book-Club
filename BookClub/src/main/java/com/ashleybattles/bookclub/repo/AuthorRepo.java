package com.ashleybattles.bookclub.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ashleybattles.bookclub.models.Author;

public interface AuthorRepo extends CrudRepository<Author, Long> {
	List<Author> findAll();

}
