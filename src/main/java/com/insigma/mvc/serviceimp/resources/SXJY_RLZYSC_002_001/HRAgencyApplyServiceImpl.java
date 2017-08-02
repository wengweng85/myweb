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
	public AjaxReturnMsg getDemoById(String aac001) {
		return this.success(demoAc01Mapper.selectByPrimaryKey(aac001));
	}

	/**
	 * ����
	 */
	@Override
	public AjaxReturnMsg saveEf11Data(Ef11 ef11) {
		ef11.setAae011(SysUserUtil.getCurrentUser().getUserid());//�����˱��
		ef11.setAae010(SysUserUtil.getCurrentUser().getCnname());//����������
		ef11.setAaf011(SysUserUtil.getCurrentUser().getGroupid());//����������
		ef11.setAae009(SysUserUtil.getCurrentUser().getGroupname());//����������
		ef11.setAae036(new Date());//����ʱ��
		//�ж����֤�����Ƿ��ظ�
		int aef104num=hragencyApplyMapper.selectByAef104(ef11);
		if(aef104num>0){
			return this.error("�˻�������"+ef11.getAef104()+"�Ѿ����ڣ������ظ�,��������ȷ�Ļ�������");
		}
				
		//�ж��Ƿ��Ǹ���
		if(StringUtils.isEmpty(ef11.getAef101())){
			int insertnum= hragencyApplyMapper.insertSelective (ef11);
			if(insertnum==1){
				return this.success("�����ɹ�");
				
			}else{
				return this.error("����ʧ��,��ȷ�ϴ˻����Ƿ���ڣ�");
			}
		}else{
			int updatenum=hragencyApplyMapper.updateByPrimaryKeySelective(ef11);
			if(updatenum==1){
				return this.success("���³ɹ�");
			}else{
				return this.error("����ʧ��,��ȷ�ϴ����Ѿ���ɾ��");
			}
		}
	}

}
