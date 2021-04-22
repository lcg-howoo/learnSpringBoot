package com.howoocast.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.LogoutException;
import com.howoocast.demo.exception.WrongPassowrdException;
import com.howoocast.demo.exception.WrongSessionException;
import com.howoocast.demo.exception.UniqueViolationException;
import com.howoocast.demo.exception.WrongAccessException;
import com.howoocast.demo.exception.WrongLoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService { // validation check

	@Autowired
	private MemberDAO memberDAO;

	public void create(Member member, HttpSession session)
			throws EmptyValueException, UniqueViolationException, LogoutException {
		if (this.access(session)) { // false = 로그인 되어있다.
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
		throw new LogoutException();
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

	public void update(Member member, HttpSession session)
			throws EmptyValueException, DataNotFoundException, WrongAccessException {
		if (this.access(session)) {
			throw new WrongAccessException();
		}
		Member user = (Member) session.getAttribute("sessionId");
		if (member.getId().equals(user.getId())) {
			isInvalid(member.getId());
			isInvalid(member.getName());
			isInvalid(member.getPassword());
			isInvalid(member.getPhone());

			if (memberDAO.update(user) == false) {
				throw new DataNotFoundException();
			}
		} else {
			throw new WrongAccessException();
		}
	}

	public void delete(String id, HttpSession session)
			throws EmptyValueException, DataNotFoundException, WrongAccessException {
		if (this.access(session)) {
			throw new WrongAccessException();
		}
		isInvalid(id);
		Member user = (Member) session.getAttribute("sessionId");
		if (id.equals(user.getId())) {
			if (memberDAO.delete(id) == false) {
				throw new DataNotFoundException();
			}
		} else {
			throw new WrongAccessException();
		}
	}

	public void login(Member member, HttpSession session)
			throws WrongPassowrdException, WrongLoginException, WrongSessionException {
		this.getLogin(session);
		if (this.findById(member.getId()).getPassword().equals(member.getPassword()) == false) {
			throw new WrongPassowrdException();
		}

	}

	private boolean getSession(HttpSession session) throws WrongSessionException {
		if (session.getAttribute("sessionId") == null) {
			return true;
		}
		throw new WrongSessionException();
	}

	private boolean getLogin(HttpSession session) throws WrongLoginException {
		if (this.getSession(session)) {
			return true;
		}
		throw new WrongLoginException();
	}

	private boolean access(HttpSession session) {
		if (session.getAttribute("sessionId") == null) {
			return true;
		}
		return false;
	}

	private void isInvalid(String value) throws EmptyValueException {
		if (value == null || value.trim().isEmpty()) {
			throw new EmptyValueException();
		}
	}
}