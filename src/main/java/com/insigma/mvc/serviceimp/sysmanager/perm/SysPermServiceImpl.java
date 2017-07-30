package com.insigma.mvc.serviceimp.sysmanager.perm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.perm.SysPermMapper;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;
import com.mysql.jdbc.StringUtils;


/**
 * 管理功能之权限管理service impl 
 * @author wengsh
 *
 */

@Service
public class SysPermServiceImpl extends MvcHelper implements SysPermService {

	@Resource
	private SysPermMapper sysPermMapper;
	
	
	/**
	 * 获取权限数据
	 */
	@Override
	public String getPermTreeList() {
		// TODO Auto-generated method stub
		return this.success_string_response (sysPermMapper.getPermTreeList());
	}

	/**
	 * 通过权限id获取权限数据
	 */
	@Override
	public AjaxReturnMsg getPermDataById(String id) {
		return this.success(sysPermMapper.getPermDataById(id));
	}

	
    /**
     * 保存或更新权限数据
     */
	@Override
	@Transactional
	public AjaxReturnMsg saveOrUpdatePermData(SPermission spermission) {
		  SPermission ispermsionexist=sysPermMapper.isPermCodeExist(spermission);
		   if(ispermsionexist!=null){
			   return this.error("此权限"+spermission.getCode()+"编号已经存在,请重新输入一个新的权限编号");
		   }else{
			//判断是否更新操作
			if(StringUtils.isNullOrEmpty(spermission.getPermissionid())){
				 int insertnum=sysPermMapper.savePermissionData(spermission);
				 if(insertnum==1){
					 return this.success("新增成功");
				 }else{
					 return this.error("新增失败");
				 }
			}else{
				 int updatenum=sysPermMapper.updatePermissionData(spermission);
				 if(updatenum==1){
					 return this.success("更新成功");
				 }else{
					 return this.error("更新失败");
				 }
			}
		}
		   
	}

	/**
	 * 通过父节点获取权限子节点数据
	 */
	@Override
	public AjaxReturnMsg getPermListDataByParentid(String parentid) {
		return this.success( sysPermMapper.getPermListDataByParentid(parentid));
	}

	
	/**
	 * 通过权限id删除权限相关数据
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deletePermDataById(String id) {
		if(sysPermMapper.getPermListDataByParentid(id).size()>0){
			return this.error("当前权限还存在子权限数据,请先删除子权限数据");
		}else{
			int deletenum=sysPermMapper.deletePermDataById(id);
			if(deletenum==1){
				return this.success("删除成功");
			}else{
				return this.success("删除失败");
			}
			
		}
	}

}
