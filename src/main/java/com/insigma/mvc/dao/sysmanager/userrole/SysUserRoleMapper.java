package com.insigma.mvc.dao.sysmanager.userrole;

import java.util.List;

import com.insigma.mvc.model.SGroup;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;


/**
 * ������֮�������û���ɫ����
 * @author wengsh
 *
 */
public interface SysUserRoleMapper {
	
	public List<SGroup> getAllGroupList(String parentid);
	
	public SGroup getGroupDataById(String groupid);
	
	public List<SUser> getUserListByGroupid(String groupid);
	
	public List<SRole> getUserRoleByUserId(String userid);
	
	public void deleteUserRoleByUserId(String userid);
	
	public void saveUserRole(SRole srole);
}
