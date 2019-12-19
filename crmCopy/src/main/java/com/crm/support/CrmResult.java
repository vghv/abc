package com.crm.support;

import java.io.Serializable;

/**
 * ��Ϣ����࣬���Է�װ���ص���Ϣ
 * @author Lenovo
 *
 */
public class CrmResult implements Serializable{

	//״̬��
	private String errCode;
	//������String�͵�=��Json ת��
	//��Ϣ
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
