package com.working.banksystem.WorkingBankSystem.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.working.banksystem.WorkingBankSystem.Repository.UserRepository;
import com.working.banksystem.WorkingBankSystem.entities.User;
import com.working.banksystem.WorkingBankSystem.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User retrieveUserById(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if(user!=null) {
		    return user;
		}
		return null;
	}

	@Override
	public User retrieveUserByMobileNo(String mob) {
		User user = userRepository.findByMobileNo(mob);
		if(user!=null) {
		    return user;
		}
		return null;
	}

	@Override
	public User updateUser(Integer id, User user) {
		User userDb = userRepository.findById(id).get();
		if(userDb==null) {
			return null;
		}
		userDb.setUsername(user.getUsername());
		user.setBalance(user.getBalance());
		userDb.setEmail(user.getEmail());
		userDb.setMobileNo(user.getMobileNo());
		userRepository.save(userDb);
		return userDb;
	}

	@Override
	public boolean deleteUser(Integer id) {
		User user = userRepository.findById(id).get();
		if(user==null) {
			return false;
		}
		userRepository.softDeletion(id);
		return true;
	}

	@Override
	public boolean withdrawalAmt(Integer id, Integer amt) {
		User user = userRepository.findById(id).get();
		if(user==null) {
			return false;
		}
		userRepository.withdrawalAmtDTO(id, amt);
		return true;
	}

	@Override
	public boolean depositAmt(Integer id, Integer amt) {
		User user = userRepository.findById(id).get();
		if(user==null) {
			return false;
		}
		userRepository.depositAmtDTO(id, amt);
		return true;
	}

	@Override
	public List<User> getAllUser() {
		List<User> userDto= userRepository.findAll();
		return userDto;
	}

}
