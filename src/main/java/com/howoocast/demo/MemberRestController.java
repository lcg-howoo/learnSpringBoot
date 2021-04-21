package com.howoocast.demo;

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
		return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/api/member")
	public ResponseEntity<?> post(Member member) {
		boolean result = memberService.create(member);

		if (result) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
	}

	@PatchMapping("/api/member")
	public ResponseEntity<?> patch(Member member) {
		boolean result = memberService.update(member);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@DeleteMapping("/api/member")
	public ResponseEntity<?> delete(@RequestParam String id) {
		boolean result = memberService.delete(id);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
