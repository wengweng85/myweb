package com.insigma.mvc.controller.sysmanager.role;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;
import com.mysql.jdbc.StringUtils;

/**
 * 角色管理及角色权限分配管理
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
	
	
	@Resource
	private SysPermService sysPermService;
	/**
	 * 页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@RequiresRoles("admin")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/perm/syspermindx");
        return modelAndView;
	}
	
	
	/**
	 * 权限树数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	@RequiresRoles("admin")
	public void treedata(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		List<SPermission> permlist =sysPermService.getPermTreeList();
		this.success_native_response(response,permlist);
	}
	
	
	/**
	 * 通过权限编号获取权限数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPermData/{id}")
	@RequiresRoles("admin")
	@ResponseBody
	public AjaxReturnMsg getPermDataByid(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable String id) throws Exception {
		SPermission spermission= sysPermService.getPermDataById(id);
		return this.success(spermission);
	}
	
	
	/**
	 * 更新或保存权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	@Transactional
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SPermission spermission,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		SPermission ispermsionexist=sysPermService.isPermCodeExist(spermission);
		   if(ispermsionexist!=null){
			   return this.error("此权限"+spermission.getCode()+"编号已经存在,请重新输入一个新的权限编号");
		   }else{
			//判断是否更新操作
			if(StringUtils.isNullOrEmpty(spermission.getPermissionid())){
			     sysPermService.savePermissionData(spermission);
				 return this.success("新增成功");
			}else{
				 sysPermService.updatePermissionData(spermission);
				return this.success("更新成功");
			}
		}
	}
	
	/**
	 * 删除权限相关数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePermDataById/{id}")
	@ResponseBody
	@Transactional
	public AjaxReturnMsg deletePermDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		if(sysPermService.getPermListDataByParentid(id).size()>0){
			return this.error("当前权限还存在子权限数据,请先删除子权限数据");
		}else{
			sysPermService.deletePermDataById(id);
			return this.success("操作成功");
		}
	}
}
