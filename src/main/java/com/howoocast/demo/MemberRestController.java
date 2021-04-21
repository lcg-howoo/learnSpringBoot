package com.howoocast.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.howoocast.demo.exception.DataNotFoundException;
import com.howoocast.demo.exception.EmptyValueException;
import com.howoocast.demo.exception.WrongPassowrdException;
import com.howoocast.demo.exception.UniqueViolationException;

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
	public ResponseEntity<?> post(Member member) {
		try {
			memberService.create(member);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (UniqueViolationException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			
			return e.getResponse();
		}
	}

	@PatchMapping("/api/member")
	public ResponseEntity<?> patch(Member member) {
		try {
			memberService.update(member);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		}
	}

	@DeleteMapping("/api/member")
	public ResponseEntity<?> delete(@RequestParam String id) {
		try {
			memberService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		}
	}

	@PostMapping("/api/login")
	public ResponseEntity<?> login(Member member, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		try {
			// id가 있으면
			memberService.login(member);
			session = request.getSession(true); //세션을 추가했다.
			session.setAttribute("sessionId", member.getId());
			System.out.println(session.getAttribute("sessionId"));
			return new ResponseEntity<>("로그인 완료", HttpStatus.OK);
		} catch (DataNotFoundException e) {
			return e.getResponse();
		} catch (EmptyValueException e) {
			return e.getResponse();
		}catch (WrongPassowrdException e){
			return e.getResponse();
		}
	}
	// @GetMapping("/api/member/{id}")
	// public ResponseEntity<?> getUserInfo(@PathVariable String id) {
	// 	try {
	// 		return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
	// 	} catch (DataNotFoundException e) {
	// 		return e.getResponse();
	// 	} catch (EmptyValueException e) {
	// 		return e.getResponse();
	// 	}
	// }
}
