package com.sprinboot_bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprinboot_bookstore.entity.MyBookList;
@Repository
public interface MyBookRepository extends JpaRepository<MyBookList,Integer>{

	
}
