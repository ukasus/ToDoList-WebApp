package com.ujjawal.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ujjawal.project.entity.ToDoItems;
import com.ujjawal.project.repo.ToDoItemsRepository;
import com.ujjawal.project.service.NextSequenceService;

@Controller()

public class ToDoController {
	
	@Autowired
	NextSequenceService nextSequenceService;
	@Autowired
	ToDoItemsRepository toDoItemRepo;
	
	
	@RequestMapping("/additem")
	public String addItemPage()
	{
		return "additem";
	}
	
	
	@PostMapping("/additem")
	public String addItemPage(@RequestParam("item") String item)
	{
		
		String useremail=SecurityContextHolder.getContext().getAuthentication().getName();
		ToDoItems newitem=new ToDoItems();
		newitem.setDescription(item);
		newitem.setUserName(useremail);
		newitem.setId(nextSequenceService.getNextSequence("customSequences"));
		
		
		toDoItemRepo.save(newitem);
		return "redirect:/";
	}
	
	@RequestMapping("/deleteitem")
	public String addItemPage(@RequestParam("id") int id)
	{
		ToDoItems item=toDoItemRepo.findById(id);
		toDoItemRepo.delete(item);
		return "redirect:/";
	}

}
