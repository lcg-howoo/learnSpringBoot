package com.howoocast.demo;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
	
	public static final List<Member> data;

	static {
		data = new ArrayList<>();
		Member m1 = new Member();
		m1.setId("lcg");
		m1.setPassword("123");
		m1.setName("이창근");
		m1.setPhone("01012341234");
		data.add(m1);
		Member m2 = new Member();
		m2.setId("young");
		m2.setPassword("qwe");
		m2.setName("양영오");
		m2.setPhone("01056785678");
		data.add(m2);
	}
}
