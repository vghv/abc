package com.crm.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.base.service.impl.BaseServiceImpl;
import com.crm.bean.SysRole;
import com.crm.bean.SysRoleRightExample;
import com.crm.bean.SysUserExample;
import com.crm.mapper.SysRoleMapper;
import com.crm.mapper.SysRoleRightMapper;
import com.crm.mapper.SysUserMapper;
import com.crm.role.service.IRoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements IRoleService{

    private SysRoleMapper roleMapper;
	
	@Resource
	private SysUserMapper userMapper;
	
	@Resource
	private SysRoleRightMapper roleRightMapper;
	
	@Resource
	public void setSuperMapper(SysRoleMapper mapper) {
		super.setBaseMapper(mapper);
		this.roleMapper = mapper;
	}
	@Override
	public List<SysRole> findAllRoles() {

		return roleMapper.selectByExample(null);
	}
	@Override
	public void deleteAllByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		SysUserExample userExample = new SysUserExample();
		userExample.createCriteria().andUsrRoleIdEqualTo(roleId);
		userMapper.deleteByExample(userExample);
		//角色权限的删除
		SysRoleRightExample rrExample = new SysRoleRightExample();
		rrExample.createCriteria().andRfRoleIdEqualTo(roleId);
		roleRightMapper.deleteByExample(rrExample);
		//角色的删除
		roleMapper.deleteByPrimaryKey(roleId);
		
	}

}
