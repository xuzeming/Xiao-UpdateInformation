package com.zl.SecuritiesSystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="card", method={RequestMethod.GET})
/**
 * 主要用来解析路由并转发到相应的html中
 * @author 王徐
 *
 */
public class CardController {
	
	/**
	 * 转发到bankcard.html页面中去
	 * @return
	 */
	@RequestMapping("/mycard")
	public String CardList() {
		return "/html/bankcard.html";
	}
	
	/**
	 * 转发到bindling.html页面中去
	 * @return
	 */
	@RequestMapping("/binding")
	public String bindingCard() {
		return "/html/binding.html";
	}
	
	/**
	 * 转发到unbundling.html页面中去
	 * @return
	 */
	@RequestMapping("/unbundling")
	public String unbundling() {
		return "/html/unbundling.html";
	}
	
	/**
	 * 转发到changeUserPassword.html页面中去
	 * @return
	 */
	@RequestMapping("/changeuserdetail")
	public String changAccount() {
		return "/html/changeUserPassword.html";
	}
	
	/**
	 * 转发到changeAccountPassword.html页面中去
	 * @return
	 */
	@RequestMapping("/changedealdetail")
	public String changDealDetail() {
		return "/html/changeDealPassword.html";
	}

}
