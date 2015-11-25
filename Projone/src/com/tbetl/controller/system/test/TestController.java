package com.tbetl.controller.system.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbetl.controller.base.BaseController;


@Controller
public class TestController extends BaseController {

	
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value="/Test_defausslt")
	public String defaultPage(){
		return "test/error";
	}
	
	
}
