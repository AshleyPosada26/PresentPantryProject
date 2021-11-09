package com.ashley.presentpantry.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




	@Entity
	@Table(name="users")
	public class User {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@NotBlank
		@Size(max=45)
		private String firstName;
		@NotBlank
		@Size(max=45)
		private String lastName;
		@Email
		@NotBlank
		private String email;
		@NotBlank
		@Size(min=8)
		private String password;
		@Transient
		private String confirmPassword;
		
		@OneToMany(fetch=FetchType.LAZY, mappedBy="creator")
		private List<Item> itemsCreated;
		
		@ManyToMany(fetch=FetchType.LAZY)
		@JoinTable(
				name="stapleditem",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="item_id")
			)
		private List<Item> itemStapled;

		public User() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		public List<Item> getItemsCreated() {
			return itemsCreated;
		}

		public void setItemsCreated(List<Item> itemsCreated) {
			this.itemsCreated = itemsCreated;
		}

		public List<Item> getItemStapled() {
			return itemStapled;
		}

		public void setItemStapled(List<Item> itemStapled) {
			this.itemStapled = itemStapled;
		}
		
}
