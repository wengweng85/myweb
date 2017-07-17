package com.insigma.mvc.serviceimp.login;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insigma.mvc.dao.login.LoginMapper;
import com.insigma.mvc.model.LoginInf;
import com.insigma.mvc.model.SUser;
import com.insigma.mvc.service.login.LoginService;
import com.insigma.resolver.AppException;


/**
 * 登录service接口
 * @author wengsh
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	//登录dao
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
	public Set<String> findRolesStr(String loginname) throws Exception {
		List<SUser> list= loginmapper.findRolesStr(loginname);
		if(list.size()>0){
			Set<String> set=new HashSet<String>();
			for(int i=0;i<list.size();i++){
				set.add(list.get(i).toString());
			}
			return set;
		}else{
			throw new AppException("用户角色信息不存在!");
		}
	}

	@Override
	public Set<String> findPermissionStr(String loginname) throws Exception {
		List<SUser> list=loginmapper.findPermissionStr(loginname);
		if(list.size()>0){
			Set<String> set=new HashSet<String>();
			for(int i=0;i<list.size();i++){
				set.add(list.get(i).toString());
			}
			return set;
		}else{
			throw new AppException("用户角色信息不存在!");
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
