package com.crm.support;

import java.io.Serializable;

/**
 * 信息结果类，可以封装返回的信息
 * @author Lenovo
 *
 */
public class CrmResult implements Serializable{

	//状态码
	private String errCode;
	//可以是String型的=》Json 转换
	//信息
	private Object errMsg;
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public Object getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(Object errMsg) {
		this.errMsg = errMsg;
	}
	
	public CrmResult(String errCode, Object errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public CrmResult() {
		super();
	}
	
	
}
