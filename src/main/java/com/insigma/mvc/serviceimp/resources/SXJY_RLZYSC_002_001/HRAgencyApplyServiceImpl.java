package com.insigma.mvc.serviceimp.resources.SXJY_RLZYSC_002_001;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.demo.DemoAc01Mapper;
import com.insigma.mvc.dao.resources.Ef11Mapper;
import com.insigma.mvc.dao.resources.SXJY_RLZYSC_002_001.HRAgencyApplyMapper;
import com.insigma.mvc.model.Ac01;
import com.insigma.mvc.model.Ef11;
import com.insigma.mvc.service.resources.SXJY_RLZYSC_002_001.HRAgencyApplyService;
import com.insigma.shiro.realm.SysUserUtil;


/**
 * ef11 service impl
 * @author pangyy
 *
 */

@Service
public class HRAgencyApplyServiceImpl extends MvcHelper implements HRAgencyApplyService {

	@Resource
	private DemoAc01Mapper demoAc01Mapper;
	
	@Resource
	private Ef11Mapper ef11Mapper;
	
	@Resource
	private HRAgencyApplyMapper hragencyApplyMapper;

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
	public AjaxReturnMsg getDemoById(String aac001) {
		return this.success(demoAc01Mapper.selectByPrimaryKey(aac001));
	}

	/**
	 * 保存
	 */
	@Override
	public AjaxReturnMsg saveEf11Data(Ef11 ef11) {
		ef11.setAae011(SysUserUtil.getCurrentUser().getUserid());//经办人编号
		ef11.setAae010(SysUserUtil.getCurrentUser().getCnname());//经办人姓名
		ef11.setAaf011(SysUserUtil.getCurrentUser().getGroupid());//经办机构编号
		ef11.setAae009(SysUserUtil.getCurrentUser().getGroupname());//经办机构编号
		ef11.setAae036(new Date());//经办时间
		//判断身份证号码是否重复
		int aef104num=hragencyApplyMapper.selectByAef104(ef11);
		if(aef104num>0){
			return this.error("此机构名称"+ef11.getAef104()+"已经存在，不能重复,请输入正确的机构名称");
		}
				
		//判断是否是更新
		if(StringUtils.isEmpty(ef11.getAef101())){
			int insertnum= hragencyApplyMapper.insertSelective (ef11);
			if(insertnum==1){
				return this.success("新增成功");
				
			}else{
				return this.error("更新失败,请确认此机构是否存在！");
			}
		}else{
			int updatenum=hragencyApplyMapper.updateByPrimaryKeySelective(ef11);
			if(updatenum==1){
				return this.success("更新成功");
			}else{
				return this.error("更新失败,请确认此人已经被删除");
			}
		}
	}

}
