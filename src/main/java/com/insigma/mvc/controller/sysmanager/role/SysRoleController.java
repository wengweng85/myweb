package com.insigma.mvc.controller.sysmanager.role;

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

import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.service.sysmanager.role.SysRoleService;
import com.mysql.jdbc.StringUtils;

/**
 * 角色管理及角色角色分配管理
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
	
	
	@Resource
	private SysRoleService sysRoleService;
	/**
	 * 页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@RequiresRoles("admin")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/role/sysroleindex");
        return modelAndView;
	}
	
	/**
	 * 角色列表查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	@RequiresRoles("admin")
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,SRole srole) throws Exception {
		PageInfo<SRole> pageinfo =sysRoleService.getAllRoleList(srole);
		return this.success(pageinfo);
	}
	
	
	/**
	 * 通过角色编号获取角色数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleData/{id}")
	@RequiresRoles("admin")
	@ResponseBody
	public AjaxReturnMsg getPermDataByid(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable String id) throws Exception {
		SRole srole= sysRoleService.getRoleDataById(id);
		return this.success(srole);
	}
	
	/**
	 * 删除角色相关数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoleDataById/{id}")
	@ResponseBody
	@Transactional
	@RequiresRoles("admin")
	public AjaxReturnMsg deleteRoleDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		if(sysRoleService.isRoleUsedbyUser(id)!=null){
			return this.error("当前角色已经被用户绑定使用，不允许删除,请确认");
		}else{
			sysRoleService.deleteRoleDataById(id);
			return this.success("操作成功");
		}
	}
	
	/**
	 * 更新或保存角色
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	@Transactional
	@RequiresRoles("admin")
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SRole srole,BindingResult result) throws Exception {
		//检验输入
		if (result.hasErrors()){
			return validate(result);
		}
		SRole ispermsionexist=sysRoleService.isRoleCodeExist(srole);
		if(ispermsionexist!=null){
			   return this.error("此角色"+srole.getCode()+"编号已经存在,请重新输入一个新的角色编号");
		}else{
			//判断是否更新操作
			if(StringUtils.isNullOrEmpty(srole.getRoleid())){
				sysRoleService.saveRoleData(srole);
				 return this.success("新增成功");
			}else{
				sysRoleService.updateRoleData(srole);
				return this.success("更新成功");
			}
		}
	}
}
