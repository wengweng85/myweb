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
 * wegdit页面设计
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
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/list")
	public ModelAndView draglist(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/draglist");
		request.setAttribute("v", 1);
		//SLog slog=new SLog();
		//slog.setContent("跳转至列表");
		//logservice.saveLogInfo(slog);
		//jmsProducerService.sendMessage("跳转至列表");
		
		/*User user = new User();  
        user.setUsername("skyLine2");  
        user.setPassword("7777777");  
        mongoDbUserDao.store(user);  
  
        User user2 = mongoDbUserDao.findOneByUsername("skyLine2");  
        System.out.println("-------获取账户密码:-------" + user2.getPassword());  */
        return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
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
	 * 跳转至列表页面
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
	 * 编辑
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
			throw new Exception("找不对对应的页面信息，请确认是否存在或已经被删除!");
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
		return this.success("删除成功");
	}
	
	
	/**
	 * 预览
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
			throw new Exception("找不对对应的页面信息，请确认是否存在或已经被删除!");
		}
	}
	

	/**
	 * 预览
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/view")
	public ModelAndView dragviewlatest(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/dragview");
		//设置分页面信息
		PageDesign pagedesign=new PageDesign();
		pagedesign.setCurpage(1);
		pagedesign.setLimit(1);
		List<PageDesign> list=dragservice.getLatestDesignPage(pagedesign);
		if(list.size()>0){
			model.addAttribute("design", list.get(0));
	        return modelAndView;
		}else{
			throw new Exception("找不到相关页面配置,请确认");
		}
	}
	
	
	/**
	 * 保存
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
			DwrDragView.sendMsg("重新加载");
		}catch(Exception e){
		}
	
		return this.success("更新成功");
	}
	
	/**
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/toadd")
	public ModelAndView toadd(HttpServletRequest request,Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView("drag/adddrag");
		return modelAndView;
	}
	
	/**
	 * 跳转至列表页面
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
	 * 跳转至列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/drag/saveorupdate")
	@ResponseBody
	public AjaxReturnMsg saveorupdate( HttpServletRequest request,Model model,@Valid PageDesign design,BindingResult result) throws Exception {
		//检验输入
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
			  return this.error("页面数据格式错误,请参考格式说明编写");
	    }
		
		SLog slog=new SLog();
		slog.setContent("事务回滚测试");
		logservice.saveLogInfo(slog);
		
		
		//新增
		if(design.getId().equals("")){
			  String id=dragservice.savePageDesign(design);
			  System.out.println("id"+id);
			  return this.success("新增成功");
		}
		//修改
		else{
			 dragservice.updatePageDesign(design);
			 return this.success("更新成功");
		}
	}
}
