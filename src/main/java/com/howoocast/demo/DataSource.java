package com.howoocast.demo;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

	public static final List<Member> data;

	static {
		data = new ArrayList<>();
		data.add(new Member(1, "lcg", "123", "이창근", "01012341234"));
		data.add(new Member(2, "young", "qwe", "양영오", "01056785678"));
	}
}
