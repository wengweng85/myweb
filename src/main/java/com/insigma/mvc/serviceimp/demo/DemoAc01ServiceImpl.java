package com.insigma.mvc.serviceimp.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.demo.DemoAc01Mapper;
import com.insigma.mvc.model.DemoAc01;
import com.insigma.mvc.service.common.fileupload.FileLoadService;
import com.insigma.mvc.service.demo.DemoAc01Service;
import com.insigma.shiro.realm.SysUserUtil;


/**
 * demo ac01 service impl
 * @author wengsh
 *
 */

@Service
public class DemoAc01ServiceImpl extends MvcHelper<DemoAc01> implements DemoAc01Service {

	@Resource
	private DemoAc01Mapper demoDemoAc01Mapper;
	
	@Resource
	private FileLoadService fileLoadService;
	
	/**
	 * 分页查询
	 */
	
	@Override
	public HashMap<String, Object> getDemoAc01List(DemoAc01 ac01) {
		System.out.println(SysUserUtil.getCurrentUser().getUserid());
		System.out.println(SysUserUtil.getCurrentUser().getUserid());
		System.out.println(SysUserUtil.getCurrentUser().getUserid());
		System.out.println(SysUserUtil.getCurrentUser().getUserid());
		PageHelper.offsetPage(ac01.getOffset(), ac01.getLimit());
		if(StringUtils.isNotEmpty(ac01.getAac011())){
			ac01.setA_aac011(ac01.getAac011().split(","));
		}
		List<DemoAc01> list=demoDemoAc01Mapper.getDemoAc01List(ac01);
		PageInfo<DemoAc01> pageinfo = new PageInfo<DemoAc01>(list);
		return this.success_hashmap_response(pageinfo);
	}

	/**
	 * 通过id删除demo数据
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> deleteDemoById(String aac001) {
		int deletenum=demoDemoAc01Mapper.deleteByPrimaryKey(aac001);
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
	public AjaxReturnMsg<String> batDeleteDemoData(DemoAc01 ac01) {
		String [] ids=ac01.getSelectnodes().split(",");
		int batdeletenum=demoDemoAc01Mapper.batDeleteData(ids);
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
	public DemoAc01 getDemoById(String aac001) {
		return demoDemoAc01Mapper.selectByPrimaryKey(aac001);
	}

	/**
	 * 保存
	 */
	@Override
	@Transactional
	public AjaxReturnMsg<String> saveDemoData(DemoAc01 ac01) {
		ac01.setAae011(SysUserUtil.getCurrentUser().getUserid());//经办人编号
		ac01.setAae010(SysUserUtil.getCurrentUser().getCnname());//经办人姓名
		ac01.setAaf011(SysUserUtil.getCurrentUser().getGroupid());//经办机构编号
		ac01.setAae009(SysUserUtil.getCurrentUser().getGroupname());//经办机构编号
		ac01.setAae036(new Date());//经办时间
		//判断身份证号码是否重复
		int aac002num=demoDemoAc01Mapper.selectByAac002(ac01);
		if(aac002num>0){
			return this.error("此身份证号码"+ac01.getAac002()+"已经存在，不能重复,请输入正确的号码");
		}
				
		//判断是否是更新
		if(StringUtils.isEmpty(ac01.getAac001())){
			int insertnum= demoDemoAc01Mapper.insertSelective (ac01);
			if(insertnum==1){
				return this.success("新增成功");
			}else{
				return this.error("更新失败,请确认此人已经被删除");
			}
		}else{
			int updatenum=demoDemoAc01Mapper.updateByPrimaryKeySelective(ac01);
			//更新个人附件文件信息
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("file_bus_id", ac01.getAac001());
			map.put("bus_uuids",ac01.getSelectnodes().split(","));
			fileLoadService.batupdateBusIdByBusUuidArray(map);
			if(updatenum==1){
				return this.success("更新成功");
			}else{
				return this.error("更新失败,请确认此人已经被删除");
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public DemoAc01 getDemoNameById(String aac001) {
		return demoDemoAc01Mapper.selectNameByPrimaryKey(aac001);
	}

	
	/**
	 * 通过业务id和文件id更新
	 */
	@Override
	public AjaxReturnMsg<String> updateDemoAc01DemBusUuid(DemoAc01 ac01) {
		int updatenum= demoDemoAc01Mapper.updateDemoAc01DemBusUuid(ac01);
		if(updatenum==1){
			return this.success("更新成功");
		}else{
			return this.error("更新失败");
		}
	}

	
	/**
	 * 通过业务id及文件id上传文件记录
	 */
	@Override
	public AjaxReturnMsg<String> deletefile(DemoAc01 ac01) {
		fileLoadService.deleteFileByBusUuid(ac01.getBus_uuid());		
		ac01.setBus_uuid("");
		int updatenum=demoDemoAc01Mapper.updateDemoAc01DemBusUuid(ac01);
		if(updatenum==1){
			return this.success("删除成功");
		}else{
			return this.error("删除失败");
		}
	}

}
