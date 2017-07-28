package com.insigma.mvc.controller.sysmanager.userrole;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.SGroup;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.service.sysmanager.userrole.SysUserRoleService;

/**
 * 用户组与用户管理
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/userrole")
public class SysUserRoleController extends MvcHelper {
	
	@Resource
	private SysUserRoleService sysUserRoleService;
	/**
	 * 页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@RequiresRoles("admin")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/userrole/sysUserRoleIndex");
        return modelAndView;
	}
	
	
	/**
	 * 机构树数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	@RequiresRoles("admin")
	@ResponseBody
	public String getGroupTreeData(HttpServletRequest request,Model model) throws Exception {
		String parentid=request.getParameter("id");
		if(parentid.equals("")){
			parentid="G001";
		}
		return sysUserRoleService.getAllGroupList(parentid);
	}
	
	
	/**
	 * 通过机构编号获取机构信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getgroupdatabyid/{id}")
	@RequiresRoles("admin")
	@ResponseBody
	public AjaxReturnMsg getgroupdata(HttpServletRequest request,Model model,@PathVariable String id ) throws Exception {
		return sysUserRoleService.getGroupDataById(id);
	}
	
	/**
	 * 获取人员信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserListDataByid")
	@RequiresRoles("admin")
	@ResponseBody
	public HashMap<String,Object> getUserListByGroupid(HttpServletRequest request,Model model,SGroup sgroup ) throws Exception {
		if(StringUtils.isEmpty(sgroup.getGroupid())){
			sgroup.setGroupid("G001");
		}
		return sysUserRoleService.getUserListByGroupid(sgroup);
	}
	
	
	/**
	 * 通用用户id获取角色列表及选中状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleByUserId")
	@RequiresRoles("admin")
	@ResponseBody
	public HashMap<String,Object> getRoleByUserId(HttpServletRequest request,Model model,SRole srole ) throws Exception {
		if(StringUtils.isEmpty(srole.getUserid())){
			srole.setUserid("");
		}
		return sysUserRoleService.getUserRoleByUserId(srole);
	}
	
	
	/**
	 * 保存用户对应角色
	 * @param request
	 * @param model
	 * @param srole
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUserRole")
	@RequiresRoles("admin")
	@ResponseBody
	public AjaxReturnMsg saveUserRole(HttpServletRequest request,Model model,SRole srole ) throws Exception {
		return sysUserRoleService.saveUserRole(srole);
	}
}
