package com.working.banksystem.WorkingBankSystem.Repository;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.working.banksystem.WorkingBankSystem.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	User findByMobileNo(String mob);
	
	@Modifying
	@Transactional
	@Query("update User u set u.balance = u.balance- :amt where u.id = :Id ")
	void withdrawalAmtDTO(@Param("Id") Integer Id, @Param("amt") Integer amt);
	
	
	@Modifying
	@Transactional
	@Query("update User u set u.balance = u.balance+ :amt where u.id = :Id ")
	void depositAmtDTO(Integer Id, Integer amt);
	
	@Modifying
	@Transactional
	@Query("UPDATE User SET activityStatus = false WHERE id= :id")
	void softDeletion(Integer id);
	
	
}
