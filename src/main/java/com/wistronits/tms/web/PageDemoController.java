package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wistronits.tms.entity.RequestDemo;

@Controller
@RequestMapping("/PageDemo")
public class PageDemoController {

	@RequestMapping("/page1")
	public String firstDemoPage(RequestDemo request, Model model) {
		model.addAttribute("msg", "processed");
		return "demo/demo";
	}
	
	@RequestMapping("/submit")
	public String submitDemoPage(RequestDemo request, Model model) {
		model.addAttribute("msg", "processed");
		return "demo/demoResult";
	}	
}
