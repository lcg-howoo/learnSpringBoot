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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService memberService;

	private static final String Key_SESSION_ID = "sessionId";

	@GetMapping("/api/member/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") Long id){
		Optional<Member> member = memberService.findbyId(id);
		return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
	}


	// @GetMapping("/api/member")
	// public ResponseEntity<?> getList() {

	// 	return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
	// }

	// @GetMapping("/api/member/{username}")
	// public ResponseEntity<?> get(@PathVariable String username) {
	// 	try {
	// 		return new ResponseEntity<>(memberService.findById(username), HttpStatus.OK);
	// 	} catch (DataNotFoundException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	}
	// }

	// @PostMapping("/api/member")
	// public ResponseEntity<?> post(Member member, HttpSession session) {
	// 	// try {
	// 	// 	this.getLoginMember(session);
	// 	// 	throw new LogoutException();

	// 	// } catch (UniqueViolationException e) {
	// 	// 	return e.getResponse();
	// 	// } catch (EmptyValueException e) {
	// 	// 	return e.getResponse();
	// 	// } catch (LogoutException e) {
	// 	// 	return e.getResponse();
	// 	// } catch (WrongAccessException e) {
	// 	// 	memberService.create(member);
	// 	// 	return new ResponseEntity<>(HttpStatus.CREATED);
	// 	// }

	// 	try {
	// 		if (session.getAttribute(Key_SESSION_ID) == null) {
	// 			memberService.create(member);
	// 			return new ResponseEntity<>(HttpStatus.CREATED);
	// 		}
	// 		throw new WrongAccessException("로그아웃이 필요합니다.");
	// 	} catch (UniqueViolationException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	} catch (WrongAccessException e) {
	// 		return e.getResponse();
	// 	}
	// }

	// @PatchMapping("/api/member")
	// public ResponseEntity<?> patch(Member member, HttpSession session) {
	// 	try {
	// 		Member loginMember = this.getLoginMember(session);
	// 		if (loginMember.getUsername().equals(member.getUsername())) {
	// 			memberService.update(member);
	// 			return new ResponseEntity<>("업데이트가 완료 됐습니다", HttpStatus.OK);
	// 		}

	// 		throw new WrongAccessException();
	// 	} catch (DataNotFoundException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	} catch (WrongAccessException e) {
	// 		return e.getResponse();
	// 	}
	// }

	// @DeleteMapping("/api/member")
	// public ResponseEntity<?> delete(@RequestParam String username, HttpSession session) {
	// 	try {
	// 		Member loginMember = this.getLoginMember(session);
	// 		if (loginMember.getUsername().equals(username)) {
	// 			memberService.delete(username);
	// 			return new ResponseEntity<>("삭제가 완료 됐습니다.", HttpStatus.OK);
	// 		}
	// 		throw new WrongAccessException();
	// 	} catch (DataNotFoundException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	} catch (WrongAccessException e) {
	// 		return e.getResponse();
	// 	}
	// }

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
