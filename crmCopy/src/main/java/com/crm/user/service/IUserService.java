package com.crm.user.service;

import java.util.List;

import com.crm.base.service.IBaseService;
import com.crm.bean.SysRight;

import com.crm.bean.SysUser;

public interface IUserService extends IBaseService<SysUser> {

	SysUser selectUser(SysUser user);
	
	//��ӷ��������û���ѯ�û���Ӧ��Ȩ��List<SysRole>
	List<SysRight> selectUserRights(SysUser user);

	List<SysUser> findAllUsers();
	
	SysUser checkLogin(SysUser sysUser);

	SysUser findUserDetail(Long usrId);

	
}
