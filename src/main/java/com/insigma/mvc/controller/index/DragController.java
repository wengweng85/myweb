package com.insigma.mvc.controller.index;


import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.insigma.common.dwr.DwrDragView;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.controller.BaseController;
import com.insigma.mvc.model.PageDesign;
import com.insigma.mvc.model.SLog;
import com.insigma.mvc.service.drag.DragService;
import com.insigma.mvc.service.log.LogService;



/**
 * wegditҳ�����
 * @author wengsh
 *
 */
@Controller
public class DragController extends BaseController {
	
	@Resource
	private DragService dragservice;
	
	@Resource
	private LogService logservice;
	
   //private JmsProducerService jmsProducerService;
	
	//@Resource
	//private MongoDbUserDao mongoDbUserDao;
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/list")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/draglist");
		request.setAttribute("v", 1);
		//SLog slog=new SLog();
		//slog.setContent("��ת���б�");
		//logservice.saveLogInfo(slog);
		//jmsProducerService.sendMessage("��ת���б�");
		
		/*User user = new User();  
        user.setUsername("skyLine2");  
        user.setPassword("7777777");  
        mongoDbUserDao.store(user);  
  
        User user2 = mongoDbUserDao.findOneByUsername("skyLine2");  
        System.out.println("-------��ȡ�˻�����:-------" + user2.getPassword());  */
        return modelAndView;
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/dd/test")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
         AjaxReturnMsg dto = new AjaxReturnMsg();
         dto.setSuccess(true);
         dto.setMessage("111");
         JSONObject jsonObject=JSONObject.fromObject(dto);
         PrintWriter out = response.getWriter();
         out.write(jsonObject.toString());
         out.flush();
         out.close();
		
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/querylist")
	@ResponseBody
	public AjaxReturnMsg querylist(HttpServletRequest request,Model model,PageDesign pagedesign) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		String principal= (String)subject.getPrincipal();
		pagedesign.setUserid(principal);
		PageInfo<PageDesign> pageinfo =dragservice.queryDesignPageList(pagedesign);
		return this.success(pageinfo);
	}
	
	
	/**
	 * �༭
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/gotoedit/{id}")
	public ModelAndView dragedit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragedit");
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ��Զ�Ӧ��ҳ����Ϣ����ȷ���Ƿ���ڻ��Ѿ���ɾ��!");
		}
	}
	
	/**
	 * delete
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/delete/{id}")
	@ResponseBody
	public AjaxReturnMsg dragdelete(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		dragservice.deletePageDesignById(id);
		return this.success("ɾ���ɹ�");
	}
	
	
	/**
	 * Ԥ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view/{id}")
	public ModelAndView dragviewbyid(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		PageDesign design=dragservice.queryDesignPageById(id);
		if(design!=null){
			model.addAttribute("design", design);
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ��Զ�Ӧ��ҳ����Ϣ����ȷ���Ƿ���ڻ��Ѿ���ɾ��!");
		}
	}
	

	/**
	 * Ԥ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view")
	public ModelAndView dragviewlatest(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		//���÷�ҳ����Ϣ
		PageDesign pagedesign=new PageDesign();
		pagedesign.setCurpage(1);
		pagedesign.setLimit(1);
		List<PageDesign> list=dragservice.getLatestDesignPage(pagedesign);
		if(list.size()>0){
			model.addAttribute("design", list.get(0));
	        return modelAndView;
		}else{
			throw new Exception("�Ҳ������ҳ������,��ȷ��");
		}
	}
	
	
	/**
	 * ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/savedata")
	@ResponseBody
	public AjaxReturnMsg savedata(HttpServletRequest request,Model model) throws Exception {
		String id=request.getParameter("id");
		String serialized_data=request.getParameter("serialized_data");
		
		
		
		PageDesign design=new PageDesign();
		design.setId(id);
		design.setSerialized_data(serialized_data);
		dragservice.updateserializedData(design);
		
		
		
		
		try{
			DwrDragView.sendMsg("���¼���");
		}catch(Exception e){
		}
	
		return this.success("���³ɹ�");
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		return modelAndView;
	}
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toEdit/{id}")
	public ModelAndView toEdit(HttpServletRequest request,Model model,@PathVariable String id) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		PageDesign design=dragservice.queryDesignPageById(id);
		model.addAttribute("design", design);
		return modelAndView;
	}
	
	
	/**
	 * ��ת���б�ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate( HttpServletRequest request,Model model,@Valid PageDesign design,BindingResult result) throws Exception {
		//��������
		if (result.hasErrors()){
			return validate(result);
		}
		 
		design.setIsvalid("1");
		if(null==design.getSerialized_data()||  design.getSerialized_data().equals("")){
			design.setSerialized_data("[]");
		}
		try{
			JSONArray.fromObject(design.getSerialized_data());
		}catch(Exception ex){
			ex.printStackTrace();
			  return this.error("ҳ�����ݸ�ʽ����,��ο���ʽ˵����д");
	    }
		
		SLog slog=new SLog();
		slog.setContent("����ع�����");
		logservice.saveLogInfo(slog);
		
		
		//����
		if(design.getId().equals("")){
			  String id=dragservice.savePageDesign(design);
			  System.out.println("id"+id);
			  return this.success("�����ɹ�");
		}
		//�޸�
		else{
			 dragservice.updatePageDesign(design);
			 return this.success("���³ɹ�");
		}
	}
}
