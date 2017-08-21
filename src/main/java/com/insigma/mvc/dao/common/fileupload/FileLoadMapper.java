package com.insigma.mvc.dao.common.fileupload;

import java.util.List;

import com.insigma.mvc.model.SFileRecord;



/**
 * 文件记录保存
 * @author wengsh
 *
 */
public interface FileLoadMapper {
	
	/**
	 * 保存文件上传路径
	 * @param sfilerecord
	 */
	public void saveFileUploadRecord( SFileRecord sfilerecord);
	/**
	 * 通过文件md5查询文件
	 * @param file_md5
	 * @return
	 */
	public SFileRecord getFileUploadRecordByFileMd5(String file_md5);
	
	/**
	 * 通过文件id查询文件
	 * @param file_uuid
	 * @return
	 */
	public SFileRecord getFileUploadRecordByFileUUid(String file_uuid);
	
	/**
	 * 通过 
	 * @param sFileRecord
	 * @return
	 */
	public List<SFileRecord> getFileListByBusId(SFileRecord sFileRecord);
	
	/**
	 * 通过文件编号删除文件
	 * @param fileuuid
	 * @return
	 */
	public int deleteFileByFileUuid(String fileuuid);
	
	/**
	 * 批量删除数据
	 * @param ids
	 * @return
	 */
	public int batDeleteData(String[] ids);
}
