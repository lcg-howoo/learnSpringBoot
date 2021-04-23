package com.howoocast.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, Long>{ // 데이터를 DB에 전달하는 게 목적
	// CRUD
	// Create

	public List<Member> findByid(String id);
	 

	// public void create(Member member) {
	// 	// DB 호출 로직
	// 	DataSource.data.add(member);
	// }

	// // List
	// public List<Member> findAll() {
	// 	return DataSource.data;
	// }

	// // find one by username
	// public Member findById(String username) {
	// 	List<Member> memberList = DataSource.data;
	// 	for (int i = 0; i < memberList.size(); i++) {
	// 		if (memberList.get(i).getUsername().equals(username)) {
	// 			return memberList.get(i);
	// 		}
	// 	}
	// 	return null;
	// }

	// // update
	// public boolean update(Member member) {
	// 	List<Member> memberList = DataSource.data;
	// 	for (int i = 0; i < memberList.size(); i++) {
	// 		System.out.println(member.getUsername());
	// 		if (memberList.get(i).getUsername().equals(member.getUsername())) {
	// 			System.out.println(member.getUsername());
	// 			memberList.remove(i);
	// 			memberList.add(i, member);
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }

	// // delete
	// public boolean delete(String username) {
	// 	List<Member> memberList = DataSource.data;
	// 	for (int i = 0; i < memberList.size(); i++) {
	// 		if (memberList.get(i).getUsername().equals(username)) {
	// 			memberList.remove(i);
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }
}