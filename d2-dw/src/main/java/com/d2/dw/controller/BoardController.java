package com.d2.dw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

	@GetMapping("/hello")
	public String hello() {
		return "{'test':'hello test'}";
	}
}
