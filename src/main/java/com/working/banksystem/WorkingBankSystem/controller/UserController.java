package com.working.banksystem.WorkingBankSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.working.banksystem.WorkingBankSystem.entities.User;
import com.working.banksystem.WorkingBankSystem.serviceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@PostMapping("/create")
	public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
		userServiceImpl.createUser(user);
		return ResponseEntity.ok("User created!!");
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> retrieveUser(@PathVariable("id") Integer id) {
		User user = userServiceImpl.retrieveUserById(id);
		if(user==null) {
			return ResponseEntity.ok("User ID  does Not Exist.");
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/getmob/{mob}")
	public ResponseEntity<?> retrieveUsermob(@PathVariable("mob") String mob) {
		User user = userServiceImpl.retrieveUserByMobileNo(mob);
		if(user == null) {
			return ResponseEntity.ok("Mobile no. does not exists.");
		}
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody @Valid User user,@PathVariable("id") Integer id) {
		User updatedUser = userServiceImpl.updateUser(id, user);
		if(updatedUser == null) {
			return ResponseEntity.ok("User ID does not exists. So you cannot make any changes.");
		}
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
		boolean ans = userServiceImpl.deleteUser(id);
		if(ans==false) {
			return ResponseEntity.ok("User does not Exists!!");
		}
		return ResponseEntity.ok("User is now inactive.!!!!!");
		
	}
	
	@PutMapping("/withdraw/{id}/{amt}")
	public ResponseEntity<?> withdraw(@PathVariable("id") Integer id, @PathVariable("amt") Integer amt){
		boolean ans = userServiceImpl.withdrawalAmt(id, amt);
		if(ans==false) {
			return ResponseEntity.ok("MayBe User does not existed in the database.");
		}
		return ResponseEntity.ok("Amount debited successfully.!!!!");
	}
		
	@PutMapping("/deposit/{id}/{amt}")
	public ResponseEntity<?> deposit(@PathVariable("id") Integer id, @PathVariable("amt") Integer amt){
		boolean ans=userServiceImpl.depositAmt(id, amt);
		if(ans==false) {
			return ResponseEntity.ok("MayBe User does not existed in the database.");
		}
		return ResponseEntity.ok("Amount credited successfully.!!!!");
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers(){
		List<User> user = userServiceImpl.getAllUser();
		if(user.size()<1) {
			return ResponseEntity.ok("There are no users in the database.");
		}
		return ResponseEntity.ok(user);
	}
	
	
}
