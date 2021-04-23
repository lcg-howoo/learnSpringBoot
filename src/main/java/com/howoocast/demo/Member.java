package com.howoocast.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.howoocast.demo.exception.EmptyValueException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	private String username;

	@Column(length = 512, nullable = false)
	private String password;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 16, nullable = false)
	private String phone;

	public Member(String username, String password, String name, String phone) {
		if (username == null || username.trim().isEmpty()) {
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
		this.username = username.trim();
		this.password = password.trim();
		this.name = name.trim();
		this.phone = phone.trim();
	}

	public Member(String username, String password) {
		if (username == null || username.trim().isEmpty()) {
			throw new EmptyValueException("아이디");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new EmptyValueException("비밀번호");
		}

		this.username = username.trim();
		this.password = password.trim();
	}
}
