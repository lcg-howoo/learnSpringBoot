package com.howoocast.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.LogoutException;
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

	@GetMapping("/api/member")
	public ResponseEntity<?> getList() {
		return new ResponseEntity<>(memberService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/api/member/{id}")
	public ResponseEntity<?> get(@PathVariable String id) {
		try {
			return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		}
	}

	@PostMapping("/api/member")
	public ResponseEntity<?> post(Member member, HttpSession session) {
		try {
			memberService.create(member, session);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (UniqueViolationException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		} catch (LogoutException e) {
			return e.getResponse();
		}
	}

	@PatchMapping("/api/member")
	public ResponseEntity<?> patch(Member member, HttpSession session) {
		try {
			memberService.update(member, session);
			return new ResponseEntity<>("업데이트가 완료 됐습니다", HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		} catch (WrongAccessException e) {
			return e.getResponse();
		}
	}

	@DeleteMapping("/api/member")
	public ResponseEntity<?> delete(@RequestParam String id, HttpSession session) {
		try {
			memberService.delete(id, session);
			return new ResponseEntity<>("삭제가 완료 됐습니다.", HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		} catch (WrongAccessException e) {
			return e.getResponse();
		}
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> login(Member member, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			memberService.login(member, session);
			session.setAttribute("sessionId", member);
			session = request.getSession(true);
			return new ResponseEntity<>("로그인 완료", HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		} catch (WrongPassowrdException e) {
			return e.getResponse();
		} catch (WrongLoginException e) {
			return e.getResponse();
		} catch (WrongSessionException e) {
			return e.getResponse();
		}
	}

	@PostMapping("api/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.removeAttribute("sessionId");
		return new ResponseEntity<>("로그아웃 완료", HttpStatus.OK);
	}

}
