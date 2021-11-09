package com.ashley.presentpantry.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashley.presentpantry.models.Item;
import com.ashley.presentpantry.models.User;
import com.ashley.presentpantry.services.ItemService;
import com.ashley.presentpantry.services.UserService;
import com.ashley.presentpantry.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	@Autowired
	private ItemService iService;

	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("user_id") == null)
			return null;
		return (Long)session.getAttribute("user_id");
	}
	
	@GetMapping("/")
	public String registerlanding(@ModelAttribute("user") User user) {
		return "registration.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "registration.jsp";
		}
		User newUser = this.uService.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/pantry";
	}
	
	@GetMapping("/pantry") 
	public String index(Model viewModel, HttpSession session) {
		if(session.getAttribute("user_id") == null)
			return "redirect:/";
	
		viewModel.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		viewModel.addAttribute("allUsers", this.uService.getAllUsers());
		viewModel.addAttribute("allItems", this.iService.getAllItems());
		return "pantry.jsp";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user) {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam("lemail") String email, @RequestParam("lpassword") String password, RedirectAttributes redirectAttr) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttr.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/login";
		}
		User userToBeLoggedIn = this.uService.getUserByEmail(email);
		session.setAttribute("user_id", userToBeLoggedIn.getId());
//		session.setAttribute("user_name", userToBeLoggedIn.getName());
		return "redirect:/pantry";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/items/new")
	public String newItem(@ModelAttribute("item") Item item, Model model, HttpSession session) {
//		Long userId = this.userSessionId(session);
//		if(userId == null)
//			return "redirect:/";
		
//		User user = this.uService.findById(userId);
//		model.addAttribute("user", user);
			
		model.addAttribute("user", this.uService.getAllUsers());
		model.addAttribute("user_name", this.uService.findById((Long)session.getAttribute("user_id")));
		return "newitem.jsp";
	}
	
	@PostMapping("/newItem")
	public String addItem(@Valid @ModelAttribute("item") Item item, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
//			Long userId = this.userSessionId(session);
//			User user = this.uService.findById(userId);
//			
//			model.addAttribute("user", user);
			model.addAttribute("user_name", this.uService.findById((Long)session.getAttribute("user_id")));
			model.addAttribute("user", this.uService.getAllUsers());
			model.addAttribute("name");
			return "newitem.jsp";
		}
		this.iService.createItem(item);
		return "redirect:/pantry";
	}
	
	@GetMapping("/shoppingList")
	public String shoppingList(Model viewModel, HttpSession session) {
		if(session.getAttribute("user_id") == null)
			return "redirect:/";
		
		viewModel.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		viewModel.addAttribute("allUsers", this.uService.getAllUsers());
		viewModel.addAttribute("allItems", this.iService.getAllItems());
		return "shoppinglist.jsp";
	}
	
	
	@PostMapping("/updatedShoppingList")
	public String updatedShoppingList(@Valid @ModelAttribute("item") Item item, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
//			Long userId = this.userSessionId(session);
//			User user = this.uService.findById(userId);
//			
//			model.addAttribute("user", user);
			model.addAttribute("user_name", this.uService.findById((Long)session.getAttribute("user_id")));
			model.addAttribute("user", this.uService.getAllUsers());
			model.addAttribute("name");
			return "newitem.jsp";
		}
		this.iService.createItem(item);
		return "redirect:/shoppingList";
	}
	
	
	@GetMapping("/items/{id}/edit")
	public String editItem(Model model, @PathVariable("id") Long id, HttpSession session) {
		
//		If I wanted only the creator of the item to be able to edit it. 
		
//		Long user_id = this.userSessionId(session);
//		Item item = this.iService.findOneItem(id);	
		
//		if(item == null || !item.getCreator().getId().equals(user_id))
//			return "redirect:/pantry";
//	
//		I want anyone to be able to edit the item in the pantry which is shown below. 
		
		model.addAttribute("item", this.iService.findOneItem(id));
		model.addAttribute("user", this.uService.getAllUsers());
		model.addAttribute("allItems", this.iService.getAllItems());
		model.addAttribute("user_name", this.uService.findById((Long)session.getAttribute("user_id")));
		return "edit.jsp";
	}
	
	@PutMapping("/items/{id}/edit")
	public String update(@Valid @ModelAttribute("item") Item item, BindingResult result, Model model, @PathVariable("id") Long id, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("item", this.iService.findOneItem(id));
			model.addAttribute("user", this.uService.getAllUsers());
			model.addAttribute("allItems", this.iService.getAllItems());
			model.addAttribute("user_name", this.uService.findById((Long)session.getAttribute("user_id")));
			return "edit.jsp";
		}
		this.iService.updateItem(item);
		return "redirect:/pantry";
	}	
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		this.iService.deleteItem(id);
		return "redirect:/pantry";
	}
	
	@GetMapping("/{id}/staple")
	public String stapleItem(HttpSession session, @PathVariable("id") Long id) {
		Long user_id = this.userSessionId(session);
		Long itemId = id;
		User stapler = this.uService.findById(user_id);
//		User user = this.uService.findById((Long)session.getAttribute("user_id"));
		Item item = this.iService.findOneItem(itemId);
		this.iService.stapleItem(stapler, item);
		return "redirect:/pantry";
	}
	
	@GetMapping("/{id}/unstaple")
	public String unstapleItem(HttpSession session, @PathVariable("id") Long id) {
		Long user_id = this.userSessionId(session);
		Long itemId = id;
		User stapler = this.uService.findById(user_id);
//		User user = this.uService.findById((Long)session.getAttribute("user_id"));
		Item item = this.iService.findOneItem(itemId);
		this.iService.unstapleItem(stapler, item);
		return "redirect:/pantry";
	}
}
