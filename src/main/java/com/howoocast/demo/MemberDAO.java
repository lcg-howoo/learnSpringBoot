package com.howoocast.demo;

import java.util.List;

import javax.xml.bind.DataBindingException;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.UniqueViolationException;

import org.springframework.stereotype.Component;

@Component
public class MemberDAO { //데이터를 DB에 전달하는 게 목적

	// CRUD

	// Create
	public void create(Member member) {
		// DB 호출 로직
		List<Member> memberList = DataSource.data;
		for(int i = 0; i < memberList.size(); i++){
			if(memberList.get(i).getId().equals(member.getId())){
				// System.out.println("같은 아이디 입니다.");
				throw new UniqueViolationException();
			}
		}
		DataSource.data.add(member);
	}

	// List
	public List<Member> findAll() {
		return DataSource.data;
	}

	// find one by id
	public Member findById(String id) {
		List<Member> memberList = DataSource.data;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				return memberList.get(i);
			}
		}
		throw new DataNotFoundException();
	}

	// update
	public void update(Member member) {
		List<Member> memberList = DataSource.data;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(member.getId())) {
				memberList.remove(i);
				memberList.add(i, member);
			}
		}
	}

	// delete
	public void delete(String id) {
		List<Member> memberList = DataSource.data;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getId().equals(id)) {
				memberList.remove(i);
			}
		}
	}
}
