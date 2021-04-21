package com.howoocast.demo;

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
}
