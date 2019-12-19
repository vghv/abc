package com.crm.role.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.bean.SysRight;
import com.crm.bean.SysRole;
import com.crm.bean.SysUser;
import com.crm.role.service.IRightService;
import com.crm.role.service.IRoleRightService;
import com.crm.role.service.IRoleService;
import com.crm.user.service.IUserService;

@RestController("role")
public class RoleController {
 
	@Resource
	private IRoleService roleService ;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRightService rightService;
	
	@Resource
	private IRoleRightService roleRightService;
	
	@RequestMapping("userList") // role/userList.action
	public String userList(Model model) {
		//��ѯ�û�Ӧ����userService
		List<SysUser> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		return "role/user/list";
	}
	@RequestMapping("toUserAdd") // role/userList.action
	public String toUserAdd(Model model) {
		//��ѯ��ɫӦ����roleService
		List<SysRole> roleList = roleService.findAllRoles();
		model.addAttribute("roleList", roleList);
		return "role/user/add";
	}
	@RequestMapping("userAdd") // role/userList.action
	public String userAdd(SysUser user) {
		//ʹ��ͨ�õķ��������û����=>��IUserService�Լ�ʵ�������ͨ��ģ���������
		userService.saveEntity(user);
		//������֮���ض����б��action
		return "redirect:../role/userList.action";
	}
	@RequestMapping("toUserEdit") // role/userList.action
	public String toUserEdit(Long usrId,Model model) {
		
		//��ѯ��ɫӦ����roleService
		List<SysRole> roleList = roleService.findAllRoles();
		model.addAttribute("roleList", roleList);
		model.addAttribute("sysUser", userService.findEntityById(usrId));
		return "role/user/edit";
	}
	@RequestMapping("userEdit") // role/userList.action
	public String userEdit(SysUser user) {
		//ʹ��ͨ�õķ��������û����=>��IUserService�Լ�ʵ�������ͨ��ģ���������
		userService.updateEntity(user);
		//������֮���ض����б��action
		return "redirect:../role/userList.action";
	}
	@RequestMapping("userDelete") // role/userList.action
	public String userDelete(Long usrId) {
		//ʹ��ͨ�õķ��������û�ɾ��=>��IUserService�Լ�ʵ�������ͨ��ģ���������
		userService.deleteByPrimaryKey(usrId);
		//������֮���ض����б��action
		return "redirect:../role/userList.action";
	}
	@RequestMapping("roleList") // role/roleList.action
	public String roleList(Model model) {
		//��ѯ��ɫӦ����roleService
		List<SysRole> roleList = roleService.findAllRoles();
		model.addAttribute("roleList", roleList);
		return "role/role/list";
	}
	@RequestMapping("toRoleAdd") // role/userList.action
	public String toRoleAdd(Model model) {
		return "role/role/add";
	}
	@RequestMapping("roleAdd") // role/userList.action
	public String roleAdd(SysRole role) {
		//ʹ��ͨ�õķ������н�ɫ���=>��IRoleService�Լ�ʵ�������ͨ��ģ���������
		roleService.saveEntity(role);
		//������֮���ض����б��action
		return "redirect:../role/roleList.action";
	}
	@RequestMapping("toRoleEdit") // role/userList.action
	public String toRoleEdit(Long roleId,Model model) {	
		model.addAttribute("sysRole", roleService.findEntityById(roleId));
		return "role/role/edit";
	}
	@RequestMapping("roleEdit") // role/userList.action
	public String roleEdit(SysRole role) {
		//ʹ��ͨ�õķ������н�ɫ�޸�=>��IUserService�Լ�ʵ�������ͨ��ģ���������
		roleService.updateEntity(role);
		//������֮���ض����б��action
		return "redirect:../role/roleList.action";
	}
	@RequestMapping("toRoleManage") // role/userList.action
	public String toRoleManage(Long roleId,Model model) {	
		model.addAttribute("sysRole", roleService.findEntityById(roleId));
		//��ѯ���е�Ȩ�� sysRight
		model.addAttribute("sysRightList", rightService.findAllRights());
		//��ѯroleId��sys_role_right���ж�Ӧ��Ȩ��=����Ӧ��Ȩ�ޱ�ѡ��=��SELECT * FROM `sys_role_right` WHERE rf_role_id = 5
		model.addAttribute("sysRoleRightList", roleRightService.findRightsByRoleId(roleId));
		return "role/role/manage";
	}
	@RequestMapping("roleManage") // role/userList.action
	public String roleManage(Long roleId,String[] selectedRights,Model model) {	
		roleRightService.manage(roleId,selectedRights);
		return "redirect:../role/roleList.action";
	}

	@RequestMapping("roleDelete") // role/userList.action
	public String roleDelete(Long roleId) {	
		roleService.deleteAllByRoleId(roleId);
		return "redirect:../role/roleList.action";
	}
	//=================================================
	@RequestMapping("rightList") 
	public String rightList(Model model) {
		//��ѯȨ��Ӧ����rightService
		List<SysRight> rightList = rightService.findAllRights();
		model.addAttribute("rightList", rightList);
		return "role/right/list";
	}
	
	@RequestMapping("toRightAdd") 
	public String toRightAdd(SysRight right,Model model) {
		//��ѯȨ��Ӧ����rightService
		model.addAttribute("right",right);
		return "role/right/add";
	}
	
	/**
	 * ������ӵ�sysRight,��ӳɹ�֮��ֱ����ת���б�ҳ��չʾ
	 *  rightType: Document
		rightText: ����
		rightParentCode: L01
		rightUrl: sale/hah.action
		��ʣ�� rightCode��rightTip
	 * @param right
	 * @param model
	 * @return
	 */
	@RequestMapping("rightAdd") 
	public String rightAdd(SysRight right,Model model) {
		//����L01,����L0103,���߼����뵽service����д
		String code = rightService.createCode(right.getRightParentCode());
		right.setRightCode(code);
		right.setRightTip(right.getRightText());
		rightService.saveEntity(right);
		return "redirect:../role/rightList.action";
	}
	//================================================
	@RequestMapping("toRightEdit") // role/userList.action
	public String toRightEdit(String rightCode,Model model) {
		SysRight right = rightService.findEntityById(rightCode);
		model.addAttribute("right", right);
		//����L0101=>L01=>L01��Ӧ������
		model.addAttribute("fatherRightText",rightService.findEntityById(right.getRightParentCode()).getRightText());
		return "role/right/edit";
	}
	@RequestMapping("rightEdit") // role/userList.action
	public String rightEdit(SysRight right,SysUser user) {
		rightService.updateEntity(right);
		return "redirect:../role/rightList.action";
	}
	
	@RequestMapping("rightDelete") // role/userList.action
	public String rightdelete(SysRight rightCode) {
		rightService.deleteByPrimaryKey(rightCode);
		return "redirect:../role/rightList.action";
	}
}
