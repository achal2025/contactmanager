package com.nawaz2000.contactmanager.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nawaz2000.contactmanager.entity.ContactDetails;

@Repository
public interface ContactRepository extends JpaRepository<ContactDetails, Integer> {
	
	// Find contacts by user ID and order by name ascending
	public List<ContactDetails> findByUseridOrderByNameAsc(int userid);
	
	// Corrected query for searching contact details by name and user ID
	@Query(value = "select * from contactdetails where lower(name) LIKE CONCAT('%', ?1, '%') and userid = ?2 order by name",
			nativeQuery = true)
	public Page<ContactDetails> search(String search, int userId, Pageable pageable);
	
	// Find contacts by favourite status and order by name ascending
	public List<ContactDetails> findByFavouriteOrderByNameAsc(String favourite);
	
	// Find contacts by user ID and order by name with pagination
	@Query(value = "select * from contactdetails where userid = ?1 order by name", nativeQuery = true)
	public Page<ContactDetails> findByUserid(int userId, Pageable pageable);
	
}
