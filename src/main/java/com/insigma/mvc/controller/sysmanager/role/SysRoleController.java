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
 * ��ɫ������ɫȨ�޷������
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
	
	
	@Resource
	private SysPermService sysPermService;
	/**
	 * ҳ���ʼ��
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
	 * Ȩ��������
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
	 * ͨ��Ȩ�ޱ�Ż�ȡȨ������
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
	 * ���»򱣴�Ȩ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	@Transactional
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SPermission spermission,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		SPermission ispermsionexist=sysPermService.isPermCodeExist(spermission);
		   if(ispermsionexist!=null){
			   return this.error("��Ȩ��"+spermission.getCode()+"����Ѿ�����,����������һ���µ�Ȩ�ޱ��");
		   }else{
			//�ж��Ƿ���²���
			if(StringUtils.isNullOrEmpty(spermission.getPermissionid())){
			     sysPermService.savePermissionData(spermission);
				 return this.success("�����ɹ�");
			}else{
				 sysPermService.updatePermissionData(spermission);
				return this.success("���³ɹ�");
			}
		}
	}
	
	/**
	 * ɾ��Ȩ���������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePermDataById/{id}")
	@ResponseBody
	@Transactional
	public AjaxReturnMsg deletePermDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		if(sysPermService.getPermListDataByParentid(id).size()>0){
			return this.error("��ǰȨ�޻�������Ȩ������,����ɾ����Ȩ������");
		}else{
			sysPermService.deletePermDataById(id);
			return this.success("�����ɹ�");
		}
	}
}
