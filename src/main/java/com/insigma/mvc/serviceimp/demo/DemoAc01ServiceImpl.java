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
	 * ��ҳ��ѯ
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
	 * ͨ��idɾ��demo����
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deleteDemoById(String aac001) {
		int deletenum=demoAc01Mapper.deleteByPrimaryKey(aac001);
		if(deletenum==1){
			return this.success("ɾ���ɹ�");
		}else{
			return this.error("ɾ��ʧ��,��ȷ�������Ѿ���ɾ����");
		}
	
	}

	
	/**
	 * ����ɾ��
	 */
	@Override
	@Transactional
	public AjaxReturnMsg batDeleteDemoData(Ac01 ac01) {
		String [] ids=ac01.getSelectnodes().split(",");
		int batdeletenum=demoAc01Mapper.batDeleteData(ids);
		if(batdeletenum==ids.length){
			return this.success("����ɾ���ɹ�");
		}else{
			return this.success("����ɾ���ɹ�,������ʧ�ܵ�����,����");
		}
	    
	}

	/**
	 * ͨ�����˱�Ż�ȡ��Ϣ
	 */
	@Override
	public Ac01 getDemoById(String aac001) {
		return demoAc01Mapper.selectByPrimaryKey(aac001);
	}

	/**
	 * ����
	 */
	@Override
	public AjaxReturnMsg saveDemoData(Ac01 ac01) {
		ac01.setAae011(SysUserUtil.getCurrentUser().getUserid());//�����˱��
		ac01.setAae010(SysUserUtil.getCurrentUser().getCnname());//����������
		ac01.setAaf011(SysUserUtil.getCurrentUser().getGroupid());//����������
		ac01.setAae009(SysUserUtil.getCurrentUser().getGroupname());//����������
		ac01.setAae036(new Date());//����ʱ��
		//�ж����֤�����Ƿ��ظ�
		int aac002num=demoAc01Mapper.selectByAac002(ac01);
		if(aac002num>0){
			return this.error("�����֤����"+ac01.getAac002()+"�Ѿ����ڣ������ظ�,��������ȷ�ĺ���");
		}
				
		//�ж��Ƿ��Ǹ���
		if(StringUtils.isEmpty(ac01.getAac001())){
			int insertnum= demoAc01Mapper.insertSelective (ac01);
			if(insertnum==1){
				return this.success("�����ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ�ϴ����Ѿ���ɾ��");
			}
		}else{
			int updatenum=demoAc01Mapper.updateByPrimaryKeySelective(ac01);
			if(updatenum==1){
				return this.success("���³ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ�ϴ����Ѿ���ɾ��");
			}
		}
	}

	@Override
	public Ac01 getDemoNameById(String aac001) {
		return demoAc01Mapper.selectNameByPrimaryKey(aac001);
	}

}
