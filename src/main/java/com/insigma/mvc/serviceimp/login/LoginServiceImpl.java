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


/**
 * µÇÂ¼service½Ó¿Ú
 * @author wengsh
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	//µÇÂ¼dao
	@Resource
	private LoginMapper loginmapper;
	
	@Override
	public SUser getUserAndGroupInfo(String loginname) {
		try {
			return loginmapper.getUserAndGroupInfo(loginname);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SRole> findRolesStr(String loginname){
		List<SRole> list= loginmapper.findRolesStr(loginname);
		return list;
	}

	@Override
	public List<SPermission>  findPermissionStr(String loginname)  {
		List<SPermission> list=loginmapper.findPermissionStr(loginname);
		return list;
	}

	@Override
	public void saveLoginHashInfo(LoginInf inf) {
		loginmapper.saveLoginHashInfo(inf);
	}

	@Override
	public List<LoginInf> findLoginInfoByhashcode(String loginhash) {
		return loginmapper.findLoginInfoByhashcode(loginhash);
	}


}
