package com.howoocast.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	
	public List<Member> findAll();

	public Page<Member> findAll(Pageable pageable);

	public Page<Member> findByUsernameContaining(String username, Pageable pageable);

	public Page<Member> findByPhoneContaining(String phone, Pageable pageable);
	
	public Page<Member> findByUsernameContainingAndPhoneContaining(String username, String phone, Pageable pageable);

}