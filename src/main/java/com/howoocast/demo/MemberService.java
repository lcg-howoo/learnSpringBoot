package com.howoocast.demo;

import java.util.List;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.UniqueViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	public void create(Member member) throws EmptyValueException, UniqueViolationException {
		isInvalid(member.getId());
		isInvalid(member.getName());
		isInvalid(member.getPassword());
		isInvalid(member.getPhone());

		List<Member> memberList = this.findAll();

		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(member.getId())) {
				throw new UniqueViolationException();
			}
		}

		memberDAO.create(member);
	}

	public Member findById(String id) throws EmptyValueException, DataNotFoundException {

		isInvalid(id);
		Member member = memberDAO.findById(id);
		if (member == null) {
			throw new DataNotFoundException();
		}
		return member;
	}

	public List<Member> findAll() {
		return memberDAO.findAll();
	}

	public void update(Member member) throws EmptyValueException, DataNotFoundException {
		isInvalid(member.getId());
		isInvalid(member.getName());
		isInvalid(member.getPassword());
		isInvalid(member.getPhone());

		if (memberDAO.update(member) == false) {
			throw new DataNotFoundException();
		}
	}

	public void delete(String id) throws EmptyValueException, DataNotFoundException {
		isInvalid(id);
		if (memberDAO.delete(id) == false) {
			throw new DataNotFoundException();
		}
	}

	private void isInvalid(String value) throws EmptyValueException {
		if (value == null || value.trim().isEmpty()) {
			throw new EmptyValueException();
		}
	}

}
