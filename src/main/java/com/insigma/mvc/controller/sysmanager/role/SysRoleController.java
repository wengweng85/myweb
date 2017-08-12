package com.insigma.mvc.controller.sysmanager.role;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.service.sysmanager.role.SysRoleService;

/**
 * ��ɫ������ɫ��ɫ�������
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends MvcHelper<SRole>  {
	
	@Resource
	private SysRoleService sysRoleService;
	/**
	 * ҳ���ʼ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	@RequiresRoles("admin")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/role/sysRoleIndex");
        return modelAndView;
	}
	
	/**
	 * ��ɫ�б��ѯ
	 * @param request
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	@RequiresRoles("admin")
	public HashMap<String,Object> querylist(HttpServletRequest request,Model model,SRole srole) throws Exception {
		return sysRoleService.getAllRoleList(srole);
	}
	
	
	/**
	 * ͨ����ɫ��Ż�ȡ��ɫ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleData/{id}")
	@RequiresRoles("admin")
	@ResponseBody
	public AjaxReturnMsg<SRole> getPermDataByid(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable String id) throws Exception {
		return sysRoleService.getRoleDataById(id);
	}
	
	
	/**
	 * ҳ���ʼ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRoleEdit/{id}")
	public ModelAndView toRoleEdit(HttpServletRequest request,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("sysmanager/role/sysRoleEdit");
		SRole srole= sysRoleService.getRoleDataById(id).getObj();
		modelAndView.addObject("srole",srole);  
        return modelAndView;
	}
	/**
	 * ɾ����ɫ�������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoleDataById/{id}")
	@ResponseBody
	@RequiresRoles("admin")
	public AjaxReturnMsg<String> deleteRoleDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		return  sysRoleService.deleteRoleDataById(id);
	}
	
	/**
	 * ���»򱣴��ɫ
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	@RequiresRoles("admin")
	public AjaxReturnMsg<String> saveorupdate(HttpServletRequest request,Model model,@Valid SRole srole,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		return sysRoleService.saveOrUpdateRoleData(srole);
	}
	
	/**
	 * ��ɫ-Ȩ��������
	 * @param request
	 * @return
	 */
	@RequestMapping("/treedata")
	@RequiresRoles("admin")
	@ResponseBody
	public  List<SRole> treedata(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String id=request.getParameter("id");
		return sysRoleService.getRolePermTreeData(id);
	}
	
	
	/**
	 * ���»򱣴�Ȩ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveroleperm")
	@ResponseBody
	@RequiresRoles("admin")
	public AjaxReturnMsg<String> saveroleperm(HttpServletRequest request,Model model,SRole srole) throws Exception {
		return sysRoleService.saveRolePermData(srole);
	}
}
