package com.insigma.mvc.dao.common.fileupload;

import java.util.List;

import com.insigma.mvc.model.SFileRecord;



/**
 * �ļ���¼����
 * @author wengsh
 *
 */
public interface FileLoadMapper {
	
	/**
	 * �����ļ��ϴ�·��
	 * @param sfilerecord
	 */
	public void saveFileUploadRecord( SFileRecord sfilerecord);
	/**
	 * ͨ���ļ�md5��ѯ�ļ�
	 * @param file_md5
	 * @return
	 */
	public SFileRecord getFileUploadRecordByFileMd5(String file_md5);
	
	/**
	 * ͨ���ļ�id��ѯ�ļ�
	 * @param file_uuid
	 * @return
	 */
	public SFileRecord getFileUploadRecordByFileUUid(String file_uuid);
	
	/**
	 * ͨ�� 
	 * @param sFileRecord
	 * @return
	 */
	public List<SFileRecord> getFileListByBusId(SFileRecord sFileRecord);
	
	/**
	 * ͨ���ļ����ɾ���ļ�
	 * @param fileuuid
	 * @return
	 */
	public int deleteFileByFileUuid(String fileuuid);
	
	/**
	 * ����ɾ������
	 * @param ids
	 * @return
	 */
	public int batDeleteData(String[] ids);
}
