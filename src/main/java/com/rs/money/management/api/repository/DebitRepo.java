package com.rs.money.management.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rs.money.management.api.entities.Debits;
import com.rs.money.management.api.entities.User;

public interface DebitRepo extends JpaRepository<Debits, Long> {
	
	List<Debits> findByUser(User user);
	
	@Query("from Debits where user=?1 and createdDate >= ?2 order by createdDate asc")
	List<Debits> getWeeklyData(User user,Date date);
}
