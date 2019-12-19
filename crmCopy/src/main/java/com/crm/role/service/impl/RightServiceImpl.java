package com.crm.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crm.base.service.impl.BaseServiceImpl;
import com.crm.bean.SysRight;
import com.crm.bean.SysRightExample;
import com.crm.mapper.SysRightMapper;
import com.crm.role.service.IRightService;
import com.crm.support.StrUtil;


@Service
public class RightServiceImpl extends BaseServiceImpl<SysRight> implements IRightService {

	private SysRightMapper rightMapper;
	
	@Resource
	public void setSuperMapper(SysRightMapper mapper) {
		super.setBaseMapper(mapper);
		this.rightMapper = mapper;
	}

	/**
	 * 可以用逆向工程自带的查询来查询列表 roleMapper.selectByExample(null)
	 */
	@Override
	public List<SysRight> findAllRights() {
		// TODO Auto-generated method stub
		return rightMapper.selectByExample(null);
	}
	@Override
	public String createCode(String rightParentCode) {
		SysRightExample example = new SysRightExample();
		example.createCriteria().andRightParentCodeEqualTo(rightParentCode);
		int num = rightMapper.selectByExample(example).size()+1;
		return StrUtil.createId(rightParentCode, num);
	}
	
	

}
