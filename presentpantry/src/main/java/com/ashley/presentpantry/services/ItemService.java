package com.ashley.presentpantry.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.presentpantry.models.Item;
import com.ashley.presentpantry.models.User;
import com.ashley.presentpantry.repositories.ItemRepository;


@Service
public class ItemService {
	@Autowired
	private ItemRepository iRepo;
	
	public Item createItem(Item item) {
		return this.iRepo.save(item);
	}
	
	public List<Item> getAllItems(){
		return this.iRepo.findAll();
	}
	
	public Item findOneItem(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	
	public Item updateItem(Item updatedItem) {
		return this.iRepo.save(updatedItem);
	}
	
	public void deleteItem(Long id) {
		this.iRepo.deleteById(id);
	}
	
	//Staple Item
	public void stapleItem(User user, Item item) {
		List<User> usersWhoStapled = item.getStaplers();
		usersWhoStapled.add(user);
		this.iRepo.save(item);
	}
	
	//UnStaple
	public void unstapleItem(User user, Item item) {
		List<User> usersWhoStapled = item.getStaplers();
		usersWhoStapled.remove(user);
		this.iRepo.save(item);
	}
	
}
