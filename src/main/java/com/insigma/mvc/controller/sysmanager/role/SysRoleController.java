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
 * ��ɫ������ɫ��ɫ�������
 * @author wengsh
 *
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
	
	
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
		ModelAndView modelAndView=new ModelAndView("sysmanager/role/sysroleindex");
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
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,SRole srole) throws Exception {
		PageInfo<SRole> pageinfo =sysRoleService.getAllRoleList(srole);
		return this.success(pageinfo);
	}
	
	
	/**
	 * ͨ����ɫ��Ż�ȡ��ɫ����
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
	 * ɾ����ɫ�������
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoleDataById/{id}")
	@ResponseBody
	@Transactional
	@RequiresRoles("admin")
	public AjaxReturnMsg deleteRoleDataById(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		if(sysRoleService.isRoleUsedbyUser(id)!=null){
			return this.error("��ǰ��ɫ�Ѿ����û���ʹ�ã�������ɾ��,��ȷ��");
		}else{
			sysRoleService.deleteRoleDataById(id);
			return this.success("�����ɹ�");
		}
	}
	
	/**
	 * ���»򱣴��ɫ
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveorupdate")
	@ResponseBody
	@Transactional
	@RequiresRoles("admin")
	public AjaxReturnMsg saveorupdate(HttpServletRequest request,Model model,@Valid SRole srole,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		SRole ispermsionexist=sysRoleService.isRoleCodeExist(srole);
		if(ispermsionexist!=null){
			   return this.error("�˽�ɫ"+srole.getCode()+"����Ѿ�����,����������һ���µĽ�ɫ���");
		}else{
			//�ж��Ƿ���²���
			if(StringUtils.isNullOrEmpty(srole.getRoleid())){
				sysRoleService.saveRoleData(srole);
				 return this.success("�����ɹ�");
			}else{
				sysRoleService.updateRoleData(srole);
				return this.success("���³ɹ�");
			}
		}
	}
}
