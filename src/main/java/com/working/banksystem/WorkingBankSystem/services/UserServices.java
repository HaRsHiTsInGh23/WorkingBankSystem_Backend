package com.working.banksystem.WorkingBankSystem.services;

import java.util.List;

import com.working.banksystem.WorkingBankSystem.entities.User;


public interface UserServices {
	
	 void createUser(User user) ;
	 User retrieveUserById(Integer id);
	 User retrieveUserByMobileNo(String mob); 
	 User updateUser(Integer id, User user);
	 boolean deleteUser(Integer id);
	 boolean withdrawalAmt(Integer id, Integer amt);
	 boolean depositAmt(Integer id, Integer amt);
	List<User> getAllUser();

}
