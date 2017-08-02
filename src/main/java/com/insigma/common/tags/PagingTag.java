package com.insigma.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 分页标签
 * @author wengsh
 * @date 2012-6-5
 *
 */
public class PagingTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * 请求地址
	 */
	private String url;
	/**
	 * 总共的记录条数
	 */
	private int totalCount;
	/**
	 * 当前页
	 */
	private int curPage;
	/**
	 * 页大小
	 */
	@SuppressWarnings("unused")
	private int pageNumber;

	/**
	 * 每页数量
	 */
	private int pageSize;
	/**
	 * 查询表单名字
	 */
	private String queryFormId;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getQueryFormId() {
		return queryFormId;
	}

	public void setQueryFormId(String queryFormId) {
		this.queryFormId = queryFormId;
	}



	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();

		// 得到分页后的页数,(总页数/页大小)+1
		if (pageContext.getRequest().getAttribute("pagesize") == null || pageContext.getRequest().getAttribute("pagesize").equals("")) {
			pageSize = 15;
		}else{
			pageSize=Integer.parseInt((String)pageContext.getRequest().getAttribute("pagesize").toString());
		}
		if (pageContext.getRequest().getAttribute("curPage") == null || pageContext.getRequest().getAttribute("curPage").equals("")) {
			curPage = 0;
		}else{
			curPage=Integer.parseInt(pageContext.getRequest().getAttribute("curPage").toString());
		}
		if (pageContext.getRequest().getAttribute("totalCount") == null || pageContext.getRequest().getAttribute("totalCount").equals("")) {
			totalCount = 0;
		}else{
			totalCount=Integer.parseInt((String) pageContext.getRequest().getAttribute("totalCount").toString());
		}
		int pageNumber = 0;// 共有页数
		if (totalCount % pageSize == 0) {
			pageNumber = totalCount/ pageSize;
		} else {
			pageNumber = totalCount/ pageSize + 1;
		}
		if (curPage < 0) {
			curPage = 0;
		}
		if(curPage>pageNumber-1){
			curPage=pageNumber-1;
		}
		try {
		//if (pageNumber > 1) {
			out.println("<form action='"+url+"' method='post' id='pageForm' style='display: inline;'>");
			if(totalCount>0){
				out.println("总记录数:<font style='color:red;font-weight:bold;'>"+totalCount+"</font>&nbsp;&nbsp;");
			}
			else{
				out.println("<font style='color:red;font-weight:bold;'>没有找到相关的记录!</font>");
			}
			if (pageNumber > 1) {
				out.println("|");
				out.println("<a href='#location'></a>");
				if (curPage - 1 < 0) {
					out.println("<font style='color:#ccc;cursor:pointer;' >首&nbsp;页</font>&nbsp;");
					out.println("<font style='color:#ccc;cursor:pointer;' >上一页</font>&nbsp;");
				} else {
					out.println("<a href='javascript:;' title='首页' onclick='page(" + 0 + ")'>首&nbsp;页</a>&nbsp;");
					out.println("<a href='javascript:;' title='上一页' onclick='page(" + (curPage - 1)+ ")'>上一页</a>&nbsp;");
				}
				int begin =curPage-5;
				int end=curPage+5;
				if(curPage<5){
					begin=0;
					end=pageNumber>10?10:pageNumber;
				}
				if(curPage>pageNumber-5){
					begin=pageNumber-10<0?0:pageNumber-10;
					end=pageNumber;
				}
				for (int i=begin;i<end;i++){
					if(curPage==i){
					    out.println("<a href='javascript:;' class='page' style='color:red;font-weight:bold;' onclick='page(" + i+ ")'>"+(i+1)+"</a>");
					}else{
					    out.println("<a href='javascript:;' class='page' onclick='page(" + i+ ")'>"+(i+1)+"</a>");
					}
				}
				// 下一页
				if (curPage >= pageNumber - 1) {
					out.println("<font style='color:#ccc;cursor:pointer;' >下一页</font>&nbsp;");
					//out.println("<font style='color:#ccc;cursor:pointer;' >尾&nbsp;页</font>&nbsp;");
				} else {
					out.println("<a href='javascript:;' title='下一页' onclick='page(" + (curPage + 1)+ ")'>下一页</a>&nbsp;");
					//out.println("<a href='javascript:;' title='尾页' onclick='page(" + (pageNumber- 1)+ ")'>尾&nbsp;页</a>&nbsp;");
				}
				//out.println("|");
				//out.println("当前页次<font style='color:red;font-weight:bold;'>"+(curPage+1)+"</font>/"+pageNumber+"页");
				//out.println("|");
				/**
				out.println("转到<input type='text' id='gtop' style='width:28px;height:14px;line-height:14px;text-align:center;' maxlength='3'>页</input>&nbsp;");
				out.println("<a href='javascript:void(0);' onclick='gotoP()'>确定</a>");
				 */
				out.println("<input type=hidden id='p' name='p'>");
				out.println("<script type='text/javascript'>");
				/**
				out.println("function gotoP(){ var n=$('#gtop').val();if(n){if(isNaN(n)){alert('请输入正确的页码！');}");
				out.println("else{if(n>"+pageNumber+"||n<=0){alert('此页码不存在！')}else{page(n-1);}}}else{alert('请输入正确的页码！');}}");
				*/
				out.println("function page(n){ ");
				out.print("$('#p').val(n);");
				if(getQueryFormId()!=null&&!("").equals(getQueryFormId())){
					out.print("var action=$('#"+queryFormId+"').attr('action'); var param='&p='+$('#p').val(); ");
					out.println("$('#"+queryFormId+"').attr('action',action+param); $('#"+queryFormId+"').submit();");
				}else{
					out.print("$('#pageForm').submit();");
				}
				out.print("}");
				out.println("</script>");
			}
			out.println("</form>");
		//}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}







}
