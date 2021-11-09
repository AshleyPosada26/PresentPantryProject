package com.ashley.presentpantry.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.presentpantry.models.Item;
import com.ashley.presentpantry.models.User;
import com.ashley.presentpantry.repositories.UserRepository;

	@Service
	public class UserService {
		@Autowired
		private UserRepository uRepo;
		
		// Get one user 
		public User findById(Long id) {
			return this.uRepo.findById(id).orElse(null);
		}
		
		// Get all users
		public List<User> getAllUsers() {
			return this.uRepo.findAll();
		}
		
		public User registerUser(User user) {
			// Generate the hash
			String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			
			// Set the hashed password on the users password field
			user.setPassword(hash);
			
			// Save that new user password and the user object to the database
			return this.uRepo.save(user);
		}
		
		public boolean authenticateUser(String email, String password) {
			// Query the user by email
			User user = this.uRepo.findByEmail(email);
			
			if(user == null) {
				return false;
			}
			
			// step 2 check provided email against email in database
			return BCrypt.checkpw(password, user.getPassword());
		}
		
		public User getUserByEmail(String email) {
			return this.uRepo.findByEmail(email);
		}
		
		public void itemsStapled(User user, Item item) {
			List<Item> stapledItems = user.getItemStapled();
			stapledItems.add(item);
			this.uRepo.save(user);
		}
}
