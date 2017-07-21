package com.insigma.mvc.serviceimp.login;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.mvc.dao.login.LoginMapper;
import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.login.LoginService;
import com.insigma.resolver.AppException;


/**
 * ��¼service�ӿ�
 * @author wengsh
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	//��¼dao
	@Resource
	private LoginMapper loginmapper;
	
	@Override
	public SUser getUser(String loginname) {
		try {
			return loginmapper.getUser(loginname);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SRole> findRolesStr(String loginname) throws Exception {
		List<SRole> list= loginmapper.findRolesStr(loginname);
		if(list.size()>0){
			return list;
		}else{
			throw new AppException("�û���ɫ��Ϣ������!");
		}
	}

	@Override
	public List<SPermission>  findPermissionStr(String loginname) throws Exception {
		List<SPermission> list=loginmapper.findPermissionStr(loginname);
		if(list.size()>0){
			return list;
		}else{
			throw new AppException("�û���ɫ��Ϣ������!");
		}
	}

	@Override
	public void saveLoginHashInfo(LoginInf inf) {
		loginmapper.saveLoginHashInfo(inf);
	}

	@Override
	public LoginInf findLoginInfoByhashcode(String loginhash) {
		return loginmapper.findLoginInfoByhashcode(loginhash);
	}

}
