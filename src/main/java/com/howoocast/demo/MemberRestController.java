package com.howoocast.demo;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.WrongPassowrdException;
import com.howoocast.demo.exception.WrongSessionException;
import com.howoocast.demo.exception.WrongLoginException;
import com.howoocast.demo.exception.UniqueViolationException;
import com.howoocast.demo.exception.WrongAccessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MemberRestController {
	@Autowired
	private MemberService memberService;

	private static final String Key_SESSION_ID = "sessionId";

	@GetMapping("/api/member/init")
	public ResponseEntity<?> init() {

		for (int i = 0; i < 150; i++) {
			try {
				memberService.insert(new Member("lcg" + i, "123", String.format("이창근%d호", i + 1),
						String.format("010%04d%04d", (int) (Math.random() * 10000), (int) (Math.random() * 10000))));
			} catch (Exception e) {
				// nothing to do
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/api/member/username")
	public ResponseEntity<?> findByUsername(@RequestParam String username, Pageable pageable) {
		log.debug("[Member Rest Controller] username: {}", username);
		return new ResponseEntity<>(memberService.findByUsername(username, pageable), HttpStatus.OK);
	}

	@GetMapping("/api/member/search")
	public ResponseEntity<?> search(Member filter, Pageable pageable) {
		return new ResponseEntity<>(memberService.findByFilter(filter, pageable), HttpStatus.OK);
	}

	@PostMapping("/api/member")
	public ResponseEntity<?> post(Member member) {
		memberService.insert(member);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/api/member")
	public ResponseEntity<?> patch(Member member) throws Exception {
		if (member.getId() == null) {
			throw new EmptyValueException("고유번호");
		}
		Member targetMember = memberService.findById(member.getId());
		if (member.getName() != null) {
			targetMember.setName(member.getName());
		}
		if (member.getPassword() != null) {
			targetMember.setPassword(member.getPassword());
		}
		if (member.getPhone() != null) {
			targetMember.setPhone(member.getPhone());
		}
		memberService.update(targetMember);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/api/member")
	public ResponseEntity<?> delete(@RequestParam Long id) {
		memberService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// @PostMapping("/api/login")
	// public ResponseEntity<?> login(Member member, HttpSession session) {
	// 	try {
	// 		if (session.getAttribute(Key_SESSION_ID) != null) {
	// 			throw new WrongLoginException();
	// 		}

	// 		Member loginMember = memberService.login(member.getUsername(), member.getPassword());
	// 		session.setAttribute(Key_SESSION_ID, loginMember);
	// 		return new ResponseEntity<>("로그인 완료", HttpStatus.OK);
	// 	} catch (DataNotFoundException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	} catch (WrongPassowrdException e) {
	// 		return e.getResponse();
	// 	} catch (WrongLoginException e) {
	// 		return e.getResponse();
	// 	} catch (WrongSessionException e) {
	// 		return e.getResponse();
	// 	}
	// }

	// @PostMapping("api/logout")
	// public ResponseEntity<?> logout(HttpSession session) {
	// 	// TODO: 로그인도 안 했는데 감히 로그아웃을 해?
	// 	session.removeAttribute(Key_SESSION_ID);
	// 	return new ResponseEntity<>("로그아웃 완료", HttpStatus.OK);
	// }

	// private Member getLoginMember(HttpSession session) {
	// 	if (session.getAttribute(Key_SESSION_ID) != null) {
	// 		return (Member) session.getAttribute("sessionId");
	// 	}
	// 	throw new WrongAccessException();
	// }

}
