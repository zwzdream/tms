package com.wistronits.tms.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wistronits.tms.entity.RequestDemo;

@RequestMapping({"/RestDemo","/RestDemo2"})
@RestController
public class RestDemoController {

	@RequestMapping("{id}")
	public RequestDemo handleRequest(RequestDemo request, Model model) {
		return request;
	}
}
