package com.howoocast.demo;

import com.howoocast.demo.exception.EmptyValueException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
	private String id;
	private String password;
	private String name;
	private String phone;

	public Member(String id, String password, String name, String phone) {
		if (id == null || id.trim().isEmpty()) {
			throw new EmptyValueException("아이디");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new EmptyValueException("비밀번호");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new EmptyValueException("이름");
		}
		if (phone == null || phone.trim().isEmpty()) {
			throw new EmptyValueException("전화번호");
		}

		this.id = id.trim();
		this.password = password.trim();
		this.name = name.trim();
		this.phone = phone.trim();
	}
	
	public Member(String id, String password) {
		if (id == null || id.trim().isEmpty()) {
			throw new EmptyValueException("아이디");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new EmptyValueException("비밀번호");
		}

		this.id = id.trim();
		this.password = password.trim();
	}
}
