package com.insigma.mvc.serviceimp.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.demo.DemoAc01Mapper;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.service.demo.DemoAc01Service;
import com.insigma.shiro.realm.SysUserUtil;


/**
 * demo ac01 service impl
 * @author wengsh
 *
 */

@Service
public class DemoAc01ServiceImpl extends MvcHelper implements DemoAc01Service {

	@Resource
	
	private DemoAc01Mapper demoAc01Mapper;
	
	/**
	 * 分页查询
	 */
	
	@Override
	public HashMap<String, Object> getAc01List(Ac01 ac01) {
		PageHelper.offsetPage(ac01.getOffset(), ac01.getLimit());
		if(StringUtils.isNotEmpty(ac01.getAac011())){
			ac01.setA_aac011(ac01.getAac011().split(","));
		}
		List<Ac01> list=demoAc01Mapper.getAc01List(ac01);
		PageInfo<Ac01> pageinfo = new PageInfo<Ac01>(list);
		return this.success_hashmap_response(pageinfo);
	}

	/**
	 * 通过id删除demo数据
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deleteDemoById(String aac001) {
		int deletenum=demoAc01Mapper.deleteByPrimaryKey(aac001);
		if(deletenum==1){
			return this.success("删除成功");
		}else{
			return this.error("删除失败,请确定此人已经被删除了");
		}
	
	}

	
	/**
	 * 批量删除
	 */
	@Override
	@Transactional
	public AjaxReturnMsg batDeleteDemoData(Ac01 ac01) {
		String [] ids=ac01.getSelectnodes().split(",");
		int batdeletenum=demoAc01Mapper.batDeleteData(ids);
		if(batdeletenum==ids.length){
			return this.success("批量删除成功");
		}else{
			return this.success("批量删除成功,但存在失败的数据,请检查");
		}
	    
	}

	/**
	 * 通过个人编号获取信息
	 */
	@Override
	public Ac01 getDemoById(String aac001) {
		return demoAc01Mapper.selectByPrimaryKey(aac001);
	}

	/**
	 * 保存
	 */
	@Override
	public AjaxReturnMsg saveDemoData(Ac01 ac01) {
		ac01.setAae011(SysUserUtil.getCurrentUser().getUserid());//经办人编号
		ac01.setAae010(SysUserUtil.getCurrentUser().getCnname());//经办人姓名
		ac01.setAaf011(SysUserUtil.getCurrentUser().getGroupid());//经办机构编号
		ac01.setAae009(SysUserUtil.getCurrentUser().getGroupname());//经办机构编号
		ac01.setAae036(new Date());//经办时间
		//判断身份证号码是否重复
		int aac002num=demoAc01Mapper.selectByAac002(ac01);
		if(aac002num>0){
			return this.error("此身份证号码"+ac01.getAac002()+"已经存在，不能重复,请输入正确的号码");
		}
				
		//判断是否是更新
		if(StringUtils.isEmpty(ac01.getAac001())){
			int insertnum= demoAc01Mapper.insertSelective (ac01);
			if(insertnum==1){
				return this.success("新增成功");
			}else{
				return this.error("更新失败,请确认此人已经被删除");
			}
		}else{
			int updatenum=demoAc01Mapper.updateByPrimaryKeySelective(ac01);
			if(updatenum==1){
				return this.success("更新成功");
			}else{
				return this.error("更新失败,请确认此人已经被删除");
			}
		}
	}

	@Override
	public Ac01 getDemoNameById(String aac001) {
		return demoAc01Mapper.selectNameByPrimaryKey(aac001);
	}

}
