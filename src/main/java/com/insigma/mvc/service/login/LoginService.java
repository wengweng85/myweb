package com.insigma.mvc.service.login;


import java.util.List;

import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.resolver.AppException;


/**
 * ��¼service�ӿ�
 * @author wengsh
 *
 */
public interface LoginService {
	/**
	 * ͨ���û�����ȡ��Ա����Ϣ
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public SUser getUser(String loginname);
	
	/**
	 * ͨ���û�id��ȡ�û���ɫ����
	 * @param userid
	 * @return ��ɫ����
	 * @throws Exception 
	 */
	public List<SRole> findRolesStr(String loginname) throws AppException, Exception;
	
	/**
	 * ͨ���û�id��ȡ�û�Ȩ�޼���
	 * @param userid
	 * @return Ȩ�޼���
	 * @throws Exception 
	 */
	public List<SPermission> findPermissionStr(String loginname) throws AppException, Exception;
	
	
	public void saveLoginHashInfo(LoginInf inf);
	
	
	public LoginInf findLoginInfoByhashcode(String loginhash);
	

}
