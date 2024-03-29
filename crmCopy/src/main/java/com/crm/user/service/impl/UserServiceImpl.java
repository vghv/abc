package com.crm.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.base.service.impl.BaseServiceImpl;
import com.crm.bean.SysRight;
import com.crm.bean.SysUser;
import com.crm.bean.SysUserExample;
import com.crm.bean.SysUserExample.Criteria;
import com.crm.mapper.SysUserMapper;
import com.crm.user.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements IUserService {

	private SysUserMapper userMapper;
	
	@Resource
	public void setSuperMapper(SysUserMapper mapper) {
		super.setBaseMapper(mapper);
		this.userMapper = mapper;
	}
	@Override
	public SysUser selectUser(SysUser user) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(user);
	}

	@Override
	public List<SysRight> selectUserRights(SysUser user) {
		
		return userMapper.selectUserRights(user);
	}
	
	@Override
	public List<SysUser> findAllUsers() {
		// TODO Auto-generated method stub
		return userMapper.findAllUsers();
	}
	@Override
	public SysUser checkLogin(SysUser sysUser) {
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsrNameEqualTo(sysUser.getUsrName());
		criteria.andUsrPasswordEqualTo(sysUser.getUsrPassword());
		List<SysUser> userList = userMapper.selectByExample(example);
		if(userList.size()>0) {
			return userList.get(0);
		}
		return null;
	}
	@Override
	public SysUser findUserDetail(Long usrId) {
		// TODO Auto-generated method stub
		return userMapper.findUserDetail(usrId);
	}
	
	
}
