package com.rs.money.management.api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rs.money.management.api.entities.Credits;
import com.rs.money.management.api.entities.User;

public interface CreditRepo extends JpaRepository<Credits, Long> {
	
	List<Credits> findByUser(User user);
	
	@Query("from Credits where user= ?1 and createdDate >= ?2 order by createdDate asc")
	List<Credits> getWeeklyCredit(User user,Date date);
	
	
	
	

}
