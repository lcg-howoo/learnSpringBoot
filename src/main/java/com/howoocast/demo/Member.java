package com.howoocast.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.howoocast.demo.exception.EmptyValueException;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member implements Serializable {
	// private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;

	@Builder
	public Member(Long id, String username, String password, String name, String phone) {
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
		this.id = id;
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
