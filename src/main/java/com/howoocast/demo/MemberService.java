package com.howoocast.demo;

import java.util.List;
import java.util.Optional;

import com.howoocast.demo.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService { // validation check

	@Autowired
	private MemberRepository memberRepository;

	public Page<Member> findByFilter(Member filter, Pageable pageable) {
		if (filter.getUsername() == null) {
			if (filter.getPhone() == null) {
				return memberRepository.findAll(pageable);
			} else {
				return memberRepository.findByPhoneContaining(filter.getPhone(), pageable);
			}
		} else {
			if (filter.getPhone() == null) {
				return memberRepository.findByUsernameContaining(filter.getUsername(), pageable);
			} else {
				return memberRepository.findByUsernameContainingAndPhoneContaining(filter.getUsername(),
						filter.getPhone(), pageable);
			}
		}
	}

	public Page<Member> findByUsername(String username, Pageable pageable) {
		return memberRepository.findByUsernameContaining(username, pageable);
	}

	public Member findById(Long id) throws Exception {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			return member.get();
		}
		throw new DataNotFoundException();
	}

	public void insert(Member member) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member); // try update, however Id is null go to insert (upsert)
	}

	public void update(Member member) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}

	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

}