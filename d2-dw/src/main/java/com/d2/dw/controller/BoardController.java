package com.d2.dw.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class BoardController {

	@GetMapping("/hello")
	public String hello() {
		return "{'test':'hello test'}";
	}
}
