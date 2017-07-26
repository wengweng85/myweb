package com.insigma.mvc.serviceimp.sysmanager.perm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insigma.dto.AjaxReturnMsg;
import com.insigma.mvc.MvcHelper;
import com.insigma.mvc.dao.sysmanager.perm.SysPermMapper;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.service.sysmanager.perm.SysPermService;
import com.mysql.jdbc.StringUtils;


/**
 * ������֮Ȩ�޹���service impl 
 * @author wengsh
 *
 */

@Service
public class SysPermServiceImpl extends MvcHelper implements SysPermService {

	@Resource
	private SysPermMapper sysPermMapper;
	
	
	/**
	 * ��ȡȨ������
	 */
	@Override
	public String getPermTreeList() {
		// TODO Auto-generated method stub
		return this.success_string_response (sysPermMapper.getPermTreeList());
	}

	/**
	 * ͨ��Ȩ��id��ȡȨ������
	 */
	@Override
	public AjaxReturnMsg getPermDataById(String id) {
		return this.success(sysPermMapper.getPermDataById(id));
	}

	
    /**
     * ��������Ȩ������
     */
	@Override
	@Transactional
	public AjaxReturnMsg saveOrUpdatePermData(SPermission spermission) {
		  SPermission ispermsionexist=sysPermMapper.isPermCodeExist(spermission);
		   if(ispermsionexist!=null){
			   return this.error("��Ȩ��"+spermission.getCode()+"����Ѿ�����,����������һ���µ�Ȩ�ޱ��");
		   }else{
			//�ж��Ƿ���²���
			if(StringUtils.isNullOrEmpty(spermission.getPermissionid())){
				sysPermMapper.savePermissionData(spermission);
				 return this.success("�����ɹ�");
			}else{
				sysPermMapper.updatePermissionData(spermission);
				return this.success("���³ɹ�");
			}
		}
		   
	}

	/**
	 * ͨ�����ڵ��ȡȨ���ӽڵ�����
	 */
	@Override
	public AjaxReturnMsg getPermListDataByParentid(String parentid) {
		return this.success( sysPermMapper.getPermListDataByParentid(parentid));
	}

	
	/**
	 * ͨ��Ȩ��idɾ��Ȩ���������
	 */
	@Override
	@Transactional
	public AjaxReturnMsg deletePermDataById(String id) {
		if(sysPermMapper.getPermListDataByParentid(id).size()>0){
			return this.error("��ǰȨ�޻�������Ȩ������,����ɾ����Ȩ������");
		}else{
			sysPermMapper.deletePermDataById(id);
			return this.success("�����ɹ�");
		}
	}

}
