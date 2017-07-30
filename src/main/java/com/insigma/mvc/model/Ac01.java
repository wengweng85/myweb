package com.insigma.mvc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class Ac01  extends PageInfo implements java.io.Serializable  {
    
	/**
	 * 在JSR303中已经定义的Constraint如下：
		空检查
		
		@Null       验证对象是否为null
		
		@NotNull      验证对象是否不为null, 无法查检长度为0的字符串
		
		@NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
		
		@NotNull 检查约束元素是否为NULL或者是EMPTY.
		
		Booelan检查
		
		@AssertTrue     验证 Boolean 对象是否为 true  
		
		@AssertFalse    验证 Boolean 对象是否为 false  
		 
		
		长度检查
		
		@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
		
		@Length(min=, max=) 
		验证字符串的长度是否在给定的范围之内，包含两端
		
		日期检查
		
		@Past        验证 Date 和 Calendar 对象是否在当前时间之前  
		
		@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
		
		@Pattern    验证 String 对象是否符合正则表达式的规则
		 
		
		数值检查：建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为"",Integer为null
		
		@Min            验证 Number 和 String 对象是否大等于指定的值  
		
		@Max            验证 Number 和 String 对象是否小等于指定的值  
		
		@DecimalMax 被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
		
		@DecimalMin 被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
		
		@Digits     验证 Number 和 String 的构成是否合法  
		
		@Digits(integer=,fraction=) 验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。
		 
		
		@Range(min=, max=) Checks whether the annotated value lies between (inclusive) the specified minimum and maximum.
		
		@Range(min=10000,max=50000,message="range.bean.wage")
		private BigDecimal wage;
		
		@Valid递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
		
		@CreditCardNumber信用卡验证
		
		@Email 验证是否是邮件地址，如果为null,不进行验证，算通过验证。
		
		@ScriptAssert(lang= ,script=, alias=)
		
		@URL(protocol=,host=, port=,regexp=, flags=)
	 */
	
	private String aac001;
    
	@NotNull(message="姓名不能为空")
	@Length (min=2, max=5, message="姓名长度只能在3-5之间")
    private String aac003;

	@NotNull(message="身份证号码不能为空")
    private String aac002;

	@NotNull(message="性别不能为空")
    private String aac004;

	@NotNull(message="民族不能为空")
    private String aac005;

	@NotNull(message="出生日期不能为空")
    private Date aac006;
    
    private Date aac006_begin;
    
    private Date aac006_end;
    
    private String aae009;

    @NotNull(message="健康状况不能为空")
	private String aac033;

    @NotNull(message="婚姻状况不能为空")
    private String aac017;

    @NotNull(message="政治面貌不能为空")
    private String aac024;

    @NotNull(message="学历不能为空")
    private String aac011;

    @NotNull(message="联系电话不能为空")
    private String aae006;

    private String aac067;

    @NotNull(message="邮箱不能为空")
    @Email (message="邮件格式错误")
    private String aae015;

    private String aac007;

    private String aac027;

    private String adc100;

    private String aaf011;
    
    private String aae011;
    
    private String aae010;


	private Date aae036;

    private Long aaz002;

    private String aae100;

    private String eae052;

    private String aae200;

    private Date aae202;

    private String aae201;

    private String aae203;

    private String aaa148;

    private String adc300;

    private String aac127;

    private String aac032;

    private Integer aac034;

    private Integer aac035;
    
    private String aab301;

    public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}

	public String getSelectnodes() {
		return selectnodes;
	}

	public void setSelectnodes(String selectnodes) {
		this.selectnodes = selectnodes;
	}

	private String aac036;

    private String aae013;
    
    private String selectnodes;
    
    
    public String getAae010() {
		return aae010;
	}

	public void setAae010(String aae010) {
		this.aae010 = aae010;
	}
	
    public Date getAac006_begin() {
		return aac006_begin;
	}

	public void setAac006_begin(Date aac006_begin) {
		this.aac006_begin = aac006_begin;
	}

	public Date getAac006_end() {
		return aac006_end;
	}

	public void setAac006_end(Date aac006_end) {
		this.aac006_end = aac006_end;
	}

	public String getAae009() {
  		return aae009;
  	}

  	public void setAae009(String aae009) {
  		this.aae009 = aae009;
  	}

    public String getAac001() {
        return aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001 == null ? null : aac001.trim();
    }

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003 == null ? null : aac003.trim();
    }

    public String getAac002() {
        return aac002;
    }

    public void setAac002(String aac002) {
        this.aac002 = aac002 == null ? null : aac002.trim();
    }

    public String getAac004() {
        return aac004;
    }

    public void setAac004(String aac004) {
        this.aac004 = aac004 == null ? null : aac004.trim();
    }

    public String getAac005() {
        return aac005;
    }

    public void setAac005(String aac005) {
        this.aac005 = aac005 == null ? null : aac005.trim();
    }

    public Date getAac006() {
        return aac006;
    }

    public void setAac006(Date aac006) {
        this.aac006 = aac006;
    }

    public String getAac033() {
        return aac033;
    }

    public void setAac033(String aac033) {
        this.aac033 = aac033 == null ? null : aac033.trim();
    }

    public String getAac017() {
        return aac017;
    }

    public void setAac017(String aac017) {
        this.aac017 = aac017 == null ? null : aac017.trim();
    }

    public String getAac024() {
        return aac024;
    }

    public void setAac024(String aac024) {
        this.aac024 = aac024 == null ? null : aac024.trim();
    }

    public String getAac011() {
        return aac011;
    }

    public void setAac011(String aac011) {
        this.aac011 = aac011 == null ? null : aac011.trim();
    }

    public String getAae006() {
        return aae006;
    }

    public void setAae006(String aae006) {
        this.aae006 = aae006 == null ? null : aae006.trim();
    }

    public String getAac067() {
        return aac067;
    }

    public void setAac067(String aac067) {
        this.aac067 = aac067 == null ? null : aac067.trim();
    }

    public String getAae015() {
        return aae015;
    }

    public void setAae015(String aae015) {
        this.aae015 = aae015 == null ? null : aae015.trim();
    }

    public String getAac007() {
        return aac007;
    }

    public void setAac007(String aac007) {
        this.aac007 = aac007 == null ? null : aac007.trim();
    }

    public String getAac027() {
        return aac027;
    }

    public void setAac027(String aac027) {
        this.aac027 = aac027 == null ? null : aac027.trim();
    }

    public String getAdc100() {
        return adc100;
    }

    public void setAdc100(String adc100) {
        this.adc100 = adc100 == null ? null : adc100.trim();
    }

    public String getAaf011() {
        return aaf011;
    }

    public void setAaf011(String aaf011) {
        this.aaf011 = aaf011 == null ? null : aaf011.trim();
    }

    public String getAae011() {
        return aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011 == null ? null : aae011.trim();
    }

    public Date getAae036() {
        return aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public Long getAaz002() {
        return aaz002;
    }

    public void setAaz002(Long aaz002) {
        this.aaz002 = aaz002;
    }

    public String getAae100() {
        return aae100;
    }

    public void setAae100(String aae100) {
        this.aae100 = aae100 == null ? null : aae100.trim();
    }

    public String getEae052() {
        return eae052;
    }

    public void setEae052(String eae052) {
        this.eae052 = eae052 == null ? null : eae052.trim();
    }

    public String getAae200() {
        return aae200;
    }

    public void setAae200(String aae200) {
        this.aae200 = aae200 == null ? null : aae200.trim();
    }

    public Date getAae202() {
        return aae202;
    }

    public void setAae202(Date aae202) {
        this.aae202 = aae202;
    }

    public String getAae201() {
        return aae201;
    }

    public void setAae201(String aae201) {
        this.aae201 = aae201 == null ? null : aae201.trim();
    }

    public String getAae203() {
        return aae203;
    }

    public void setAae203(String aae203) {
        this.aae203 = aae203 == null ? null : aae203.trim();
    }

    public String getAaa148() {
        return aaa148;
    }

    public void setAaa148(String aaa148) {
        this.aaa148 = aaa148 == null ? null : aaa148.trim();
    }

    public String getAdc300() {
        return adc300;
    }

    public void setAdc300(String adc300) {
        this.adc300 = adc300 == null ? null : adc300.trim();
    }

    public String getAac127() {
        return aac127;
    }

    public void setAac127(String aac127) {
        this.aac127 = aac127 == null ? null : aac127.trim();
    }

    public String getAac032() {
        return aac032;
    }

    public void setAac032(String aac032) {
        this.aac032 = aac032 == null ? null : aac032.trim();
    }

    public Integer getAac034() {
        return aac034;
    }

    public void setAac034(Integer aac034) {
        this.aac034 = aac034;
    }

    public Integer getAac035() {
        return aac035;
    }

    public void setAac035(Integer aac035) {
        this.aac035 = aac035;
    }

    public String getAac036() {
        return aac036;
    }

    public void setAac036(String aac036) {
        this.aac036 = aac036 == null ? null : aac036.trim();
    }

    public String getAae013() {
        return aae013;
    }

    public void setAae013(String aae013) {
        this.aae013 = aae013 == null ? null : aae013.trim();
    }
    
}