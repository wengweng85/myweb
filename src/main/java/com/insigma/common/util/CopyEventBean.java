package com.insigma.common.util;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;

import com.insigma.resolver.AppException;



/**
 * 横纵表值的互相拷贝(横表即基本表，纵表即事件表)
 * @author 周兆巍
 */

public class CopyEventBean 
{

	/**
	 * @$comment 判断是否是基础类型
	 * @param cls 
	 * @return true 基础类型 false 不是基础类型
	 */
	@SuppressWarnings("rawtypes" )
	public static boolean isBasicClass(Class cls) {
		if (cls == String.class) {
			return true;
		} else if (cls == Integer.class || cls == int.class) {
			return true;
		} else if (cls == Short.class || cls == short.class) {
			return true;
		} else if (cls == Float.class || cls == float.class) {
			return true;
		} else if (cls == Double.class || cls == double.class) {
			return true;
		} else if (cls == Long.class || cls == long.class) {
			return true;
		} else if (cls == Date.class) {
			return true;
		} else if (cls == Boolean.class || cls ==boolean.class) {
			return true;
		}

		return false;
	}
	/**
	 * @$comment 实现了事件表拷贝到DTO或者到其它的实体(适合所有对像的最终版本)
	 * @param basicTable 目标对象
	 * @param property 属性名
	 * @param value 属性值
	 * @throws AppException
	 * @code
	 *  Ab01 ab01=new Ab01();
	    for (int i = 0; i < ae53Lst.size(); i++) {
			try {
				CopyEventBean.eventCopyToBasicFinally(ab01,
						((Ae53) ae53Lst.get(i)).getAaz312(),(Ae53) ae53Lst.get(i).getAaz313());
			} catch (Exception e) {
				throw new AppException("事件表保存到单位表出错!", e);
			}
		}
	 */
	public static void eventCopyToBasicFinally(Object basicTable,String property,String value) throws AppException 
	{
		BeanInfo toBeanInfo = null;	
		try {
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();

					for (PropertyDescriptor toPropertie:toProperties)
					{
						String newProperty= toPropertie.getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(value!=null){
								if(value.equals("")){
									value="331023413400";//数字标记证明它在事件表中以""形式保存
								}
								if(isBasicClass(toPropertie.getPropertyType())&& toPropertie.getPropertyType()==value.getClass())
									{
										toPropertie.getWriteMethod().invoke(basicTable,new Object[] { value });
									    return;
									}
									
									if(toPropertie.getPropertyType()== Long.class||toPropertie.getPropertyType()== long.class)
								    {
								    	
									    if(value.indexOf("-")>0)
									    {
									    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum(value) });
		                                    return;
									    }
									    toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf(value) });
								    }
								    if(toPropertie.getPropertyType()== Integer.class||toPropertie.getPropertyType()== int.class)
							     	{
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf(value) });
								    	return;
							     	}
								    if(toPropertie.getPropertyType()== Float.class||toPropertie.getPropertyType()== float.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf(value) });
								    	return;
								    }
								    if(toPropertie.getPropertyType()== Date.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum(value) });
									    return;
								    }
								    if(toPropertie.getPropertyType()== Short.class||toPropertie.getPropertyType()== short.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf(value) });
									    return;
								    }
								    if(toPropertie.getPropertyType()== Double.class||toPropertie.getPropertyType()== double.class)
								    {
								    	toPropertie.getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf(value) });
									    return;
								    }	
							  }
							
					}
				  }
			   
		}
		catch(Exception e)
		{
			 throw new AppException("复制数据出错(" + e.getMessage() + ")");
		}
	}
	/**
	 * @deprecated
	 * @$comment 把eventTable(事件表)中存的数据对应的保存到basicTable(基本表)
	 * @param eventTable 事件表
	 * @param basicTable 基本表
	 * @param property   字段名
	 * @throws AppException
	 */
	public static void eventCopyToBasic(Object eventTable,Object basicTable,String property) throws AppException 
	{
		
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {
			fromBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			for (int i = 0; i < fromProperties.length; i++)
			{
				if(i==fromProperties.length-2){//获得相应属性的值 避免多次赋值
					for (int j = 0; j < toProperties.length; j++)
					{
						String newProperty= toProperties[j].getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							{
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}) });
							    return;
							}
								
							if(toProperties[j].getPropertyType()== Long.class)
						    {
						    	
							    if((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}).toString()).indexOf("-")>0)
							    {
							    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString() ) });
                                    return;
							    }
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    }
						    if(toProperties[j].getPropertyType()== Integer.class)
					     	{
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
					     	}
						    if(toProperties[j].getPropertyType()== Float.class)
						    {
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
						    }
						    if(toProperties[j].getPropertyType()== Date.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Short.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Double.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }					    

					}
				  }
			   }
			}
		}
		catch(Exception ex)
		{
			 throw new AppException("复制数据出错",ex);
		}
	}

	/**
	 * @$comment 实现从基本表到事件表值的拷贝，
	 *           此方法只适用于单位模块，原因是事件表的字段被定死了。
	 *           由于是对AE53表的操作，所以对其他表的操作就要作相应的修改，有待完善中。
	 * @param basicTable 基本表
	 * @param eventId 事件ID
	 * @param keyId 事件表的主键
	 * @param orgId 组织ID
	 * @return 
	 * @throws AppException
	 */
/*
    public static List<Ae53> basicCopyToEvent(Object basicTable,Long eventId,String keyId,String orgId) throws AppException 
    {
    	Ae53 eventTable=new Ae53();
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {			
			fromBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			List<Ae53> eventLst=new java.util.ArrayList<Ae53>();
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			Object newProValue=null;
			for (int i = 0; i < fromProperties.length; i++)
			{
                    if(fromProperties[i].getName().equalsIgnoreCase("aaz001")){newProValue=orgId;}
                    else{newProValue=fromProperties[i].getReadMethod().invoke(basicTable,new Object[] {});}            
                    if(newProValue==null||newProValue.equals("")||newProValue.equals("null")){continue;}	
				    Ae53 ae53=new Ae53();				    
					for (int j = 0; j < toProperties.length; j++)
					{
                        if(fromProperties[i].getName().equalsIgnoreCase("ab02Data")||fromProperties[i].getName().equalsIgnoreCase("ab31Set")||fromProperties[i].getName().equalsIgnoreCase("ae52Data"))
                        {continue;}			
						String oldProperty=fromProperties[i].getName();
						String fromProperty=toProperties[j].getName();
						if(fromProperty.equalsIgnoreCase("eaz069")){//组织登记明细id
							String seqcode=HBUtil.getSequence(keyId);
							ae53.setEaz069(Long.parseLong(seqcode));
						}
						if(fromProperty.equalsIgnoreCase("aaz308")){//事件ID
							ae53.setAaz308(eventId);
						}
						if(fromProperty.equalsIgnoreCase("aaz312")){//属性名
							ae53.setAaz312(oldProperty.toUpperCase());
						}
						if(fromProperty.equalsIgnoreCase("aaz313")){//属性值
							ae53.setAaz313(newProValue.toString());
						}
						if(j==toProperties.length-1){
							eventLst.add(ae53);
						}
																
					}
			}
			return eventLst;
		}
		catch(Exception e)
		{
			 throw new AppException("复制数据出错(" + e.getMessage() + ")");
		}    	
    }
	
*/
    /**
	 * @$comment 实现从基本表到纵表值的拷贝
	 *           此方法现在只适用于单位模块，原因是事件表的字段被定死了。
	 *           由于是对AE19表的操作，所以对其他表的操作就要作相应的修改，有待完善中。
     * @param basicTable_Old 从数据库里查出的修改前的对象
     * @param basicTable_New 从界面或者人员处理后的对象
     * @param eventId 事件ID
     * @param tableName 基本表的表名
     * @return List<Ae19> 
     * @throws AppException
     */
/*
	public static List<Ae19> basicCopyToEventForUpdate(Object basicTable_Old,Object basicTable_New,Long eventId,String tableName) throws AppException
	{
		Ae19 eventTable=new Ae19();
		BeanInfo oldBeanInfo = null;
		BeanInfo newBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		
		try {
			oldBeanInfo = Introspector.getBeanInfo(basicTable_Old.getClass(),
					Object.class);
			newBeanInfo = Introspector.getBeanInfo(basicTable_New.getClass(),
					Object.class);
			toBeanInfo  = Introspector.getBeanInfo(eventTable.getClass(),
					Object.class);
			List<Ae19> eventLst=new java.util.ArrayList<Ae19>();
			PropertyDescriptor[] oleProperties = oldBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] newProperties = newBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] toProperties  = toBeanInfo
			        .getPropertyDescriptors();

			for (int i = 0; i < oleProperties.length; i++)
				for (int j = 0; j < newProperties.length; j++)
					if (oleProperties[i].getName().equals(newProperties[j].getName())) 
					{
						if(oleProperties[i].getName().equalsIgnoreCase("aaz308"))
						{
							break;
						}
						if (isBasicClass(oleProperties[i].getPropertyType())&& isBasicClass(newProperties[j].getPropertyType()))
						{
							Object oldValue=oleProperties[i].getReadMethod().invoke(basicTable_Old, new Object[]{});
							Object newValue=newProperties[j].getReadMethod().invoke(basicTable_New, new Object[]{});
							if(oldValue==null){
								oldValue="";
							}
							if(newValue!=null&&newValue.toString().equalsIgnoreCase("1000057863456")){
								newValue="";							
							}
							if(newValue!=null&&!newValue.toString().equalsIgnoreCase("null")&&!newValue.toString().equalsIgnoreCase(oldValue.toString())){
								CommonQueryBS query=new CommonQueryBS();
								query.setConnection(HBUtil.getHBSession().connection());
								String column_name=newProperties[j].getName().toUpperCase();
								query.setQuerySQL("select s.comments from user_col_comments s where s.table_name ='"+tableName+"' and s.column_name ='"+column_name+"'");
								Vector<?> vector=query.query();
								String comments=null;
								Iterator<?> iterator = vector.iterator();
								if (iterator.hasNext())
					            {
									HashMap tmp= (HashMap)iterator.next();
									comments=(String)tmp.get("comments");
								}							
							    Ae19 ae19=new Ae19();
							    String aaz019=HBUtil.getSequence("SQ_AAZ019");
								for (int k = 0; k < toProperties.length; k++)
								{				
									String fromProperty=toProperties[k].getName();
									if(fromProperty.equalsIgnoreCase("aaz019")){//组织信息变更明细ID
										ae19.setAaz019(Long.parseLong(aaz019));
									}
									if(fromProperty.equalsIgnoreCase("eae001")){//表名
										ae19.setEae001(tableName);
									}
									if(fromProperty.equalsIgnoreCase("aae122")){//变更项目
										ae19.setAae122(oleProperties[i].getName().toUpperCase());
									}
									if(fromProperty.equalsIgnoreCase("aae123")){//变更前信息
										ae19.setAae123(oldValue.toString());
									}
									if(fromProperty.equalsIgnoreCase("aae124")){//变更后信息
										ae19.setAae124(newValue.toString());
									}
									if(fromProperty.equalsIgnoreCase("aaz308")){//组织信息变更事件ID
										ae19.setAaz308(eventId);
									}
									if(fromProperty.equalsIgnoreCase("aae121")){//变更项中文含义
										ae19.setAae121(comments);
									}
									if(k==toProperties.length-1){
									    eventLst.add(ae19);	
									}
								}	
								
							}								
							break;
						}
					}	
						
			return eventLst;
		} catch (Exception e) {
			throw new AppException("属性复制失败(" + e.getMessage() + ")");
		}
		
	}
	
*/
	/**
	 * @deprecated
	 * @$comment 把eventTable(事件表)中存的数据对应的保存到basicTable(基本表)
	 * @param eventTable 事件表
	 * @param basicTable 基本表
	 * @param property 事件表中存横表属性的字段值
	 * @throws AppException
	 */
	public static void eventCopyToBasicForUpdate(Object eventTable,Object basicTable,String property) throws AppException 
	{
		
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;	
		try {
			fromBeanInfo = Introspector.getBeanInfo(eventTable.getClass(),Object.class);
			toBeanInfo = Introspector.getBeanInfo(basicTable.getClass(),Object.class);
			
			PropertyDescriptor[] fromProperties = fromBeanInfo.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo.getPropertyDescriptors();
			for (int i = 0; i < fromProperties.length; i++)
			{
				if(i==fromProperties.length-4){//获得相应属性的值 避免多次赋值
					for (int j = 0; j < toProperties.length; j++)
					{
						String newProperty= toProperties[j].getName();
						String oldProperty=property;
						
						if(oldProperty.equalsIgnoreCase(newProperty))
						{
							if(isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							{
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}) });
							    return;
							}
								
							if(toProperties[j].getPropertyType()== Long.class)
						    {
						    	
							    if((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {}).toString()).indexOf("-")>0)
							    {
							    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString() ) });
                                    return;
							    }
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Long.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    }
						    if(toProperties[j].getPropertyType()== Integer.class)
					     	{
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Integer.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
					     	}
						    if(toProperties[j].getPropertyType()== Float.class)
						    {
						    	toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Float.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
						    	return;
						    }
						    if(toProperties[j].getPropertyType()== Date.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { DateUtil.strDateToNum((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Short.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Short.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }
						    if(toProperties[j].getPropertyType()== Double.class)
						    {
							    toProperties[j].getWriteMethod().invoke(basicTable,new Object[] { Double.valueOf((fromProperties[i].getReadMethod().invoke(eventTable,new Object[] {})).toString()) });
							    return;
						    }					    

					}
				  }
			   }
			}
		}
		catch(Exception ex)
		{
			 throw new AppException("复制数据出错(" + ex.getMessage() + ")");
		}
	}
	/**
	 * @$comment 通过属性的匹配，把fromObj中不为null值的属性的值拷贝到toObj(从数据库中查出的修改前的对象)，为档案的修改提供简洁操作，
	 *           避免每个属性都进行是否做过修改与非空的判断操作。
	 * @param fromObj 存放界面过来值的DTO
	 * @param toObj 从数据库中查出的修改前的对象
	 * @throws AppException
	 */
	public static void copy(Object fromObj, Object toObj) throws AppException {
		BeanInfo fromBeanInfo = null;
		BeanInfo toBeanInfo = null;

		try {
			fromBeanInfo = Introspector.getBeanInfo(fromObj.getClass(),
					Object.class);
			toBeanInfo = Introspector.getBeanInfo(toObj.getClass(),
					Object.class);

			PropertyDescriptor[] fromProperties = fromBeanInfo
					.getPropertyDescriptors();
			PropertyDescriptor[] toProperties = toBeanInfo
					.getPropertyDescriptors();
			Object fromValue=null;
			Object toValue=null;
			for (int i = 0; i < fromProperties.length; i++)
				for (int j = 0; j < toProperties.length; j++)
					if (fromProperties[i].getName().equals(toProperties[j].getName())) {
						if (isBasicClass(fromProperties[i].getPropertyType())&& isBasicClass(toProperties[j].getPropertyType())&& fromProperties[i].getPropertyType() == toProperties[j].getPropertyType())
							fromValue=fromProperties[i].getReadMethod().invoke(fromObj, new Object[]{});
						    toValue=toProperties[j].getReadMethod().invoke(toObj, new Object[]{});
						    if(fromValue!=null&&fromValue.toString().equalsIgnoreCase("331023413400")){
						    	fromValue="";
						    }
						    if(toValue==null){
						    	toValue="";
						    }
						    if(fromValue!=null&&!fromValue.toString().equalsIgnoreCase(toValue.toString())&&!fromValue.toString().equalsIgnoreCase("null"))
							{
								toProperties[j].getWriteMethod().invoke(toObj,new Object[] { fromValue });
							}						
				        	break;
					}
		} catch (Exception e) {
			 throw new AppException( "属性复制失败",e );
		}
	}

}

