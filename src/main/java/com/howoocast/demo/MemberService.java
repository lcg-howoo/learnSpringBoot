package com.howoocast.demo;

import java.util.List;
import java.util.Optional;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.WrongPassowrdException;
import com.howoocast.demo.exception.UniqueViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService { // validation check

	@Autowired
	private MemberDAO memberDAO;

	public Optional<Member> findbyId(Long id){
		Optional<Member> member = memberDAO.findById(id);
		return member;
	}

	// public void create(Member member) {
	// 	List<Member> memberList = this.findAll();

	// 	for (int i = 0; i < memberList.size(); i++) {
	// 		if (memberList.get(i).getId().equals(member.getId())) {
	// 			throw new UniqueViolationException();
	// 		}
	// 	}
	// 	memberDAO.create(member);
	// }

	// public Member findById(String id) {
	// 	isInvalidId(id);
	// 	Member member = memberDAO.findById(id);
	// 	if (member == null) {
	// 		throw new DataNotFoundException();
	// 	}
	// 	return member;
	// }

	// public List<Member> findAll() {
	// 	return memberDAO.findAll();
	// }

	// public void update(Member member) {

	// 	if (memberDAO.update(member) == false) {
	// 		throw new DataNotFoundException();
	// 	}
	// }

	// public void delete(String id) {
	// 	isInvalidId(id);
	// 	if (memberDAO.delete(id) == false) {
	// 		throw new DataNotFoundException();
	// 	}
	// }

	// public Member login(String id, String rawPassword) {
	// 	Member member = this.findById(id);
	// 	if (member.getPassword().equals(rawPassword)) {
	// 		return member;
	// 	}
	// 	throw new WrongPassowrdException();
	// }

	// private void isInvalidId(String id) {
	// 	if (id == null || id.trim().isEmpty()) {
	// 		throw new EmptyValueException("아이디");
	// 	}
	// }
}