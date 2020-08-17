package com.ujjawal.project.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.ujjawal.project.entity.ToDoItems;

public interface ToDoItemsRepository extends MongoRepository<ToDoItems, String> {

	List<ToDoItems> findByUserName(String userName);

	ToDoItems findById(int id);
}
