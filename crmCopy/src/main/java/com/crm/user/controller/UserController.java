package com.crm.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.bean.SysRight;
import com.crm.bean.SysRole;
import com.crm.bean.SysUser;
import com.crm.role.service.IRoleService;
import com.crm.role.service.impl.RoleServiceImpl;
import com.crm.support.CrmResult;
import com.crm.user.service.IUserService;

@RestController("/sysUser")
public class UserController {

	@Resource
	private IUserService userService;

	@Resource
	private IRoleService roleService;
	
	private CrmResult result;

	@RequestMapping("/login")
	public String login(SysUser sysUser, HttpSession session) {
		SysUser user = userService.selectUser(sysUser);
		if (user != null) {
			
			session.setAttribute("user", user);
			session.setAttribute("lists", buildMenu(user));
			return "redirect:/index.html";
		}
		return "redirect:/login.html";
	}


	public String buildMenu(SysUser user) {
		// List<SysRight> rights = user.getRole().getRights();
		List<SysRight> rights = userService.selectUserRights(user);
		StringBuffer sbu = new StringBuffer();
		for (SysRight sysRight : rights) {
			sbu.append("\r\n").append(sysRight.getRightCode()).append(" = theMenu.addChild(")
					.append(sysRight.getRightParentCode()).append(", \"").append(sysRight.getRightType())
					.append("\", \"").append(sysRight.getRightText()).append("\", \"").append(sysRight.getRightUrl())
					.append("\", \"").append(sysRight.getRightTip()).append("\");").append("\r\n");
		}
		System.out.println(sbu.toString());
		return sbu.toString();
	}



}

