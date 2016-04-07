package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.fileupload.FileItem;
import file.MyFile;
import sql.SqlHelper;
import common.CatalogHelper;
import common.Catalog;
import java.util.*;

public final class homePage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String currentPath = "./home";
if(request.getParameter("currentPath")!=null){

	currentPath = request.getParameter("currentPath"); 
}else if(request.getAttribute("currentPath")!=null){

	currentPath = (String)request.getAttribute("currentPath"); 
}
	//获取路径列表
	ArrayList<Catalog> catalog = CatalogHelper.getCatalog(currentPath);
	//获得用户当前所在文件夹下的所有文件
	ArrayList<MyFile> fileList = SqlHelper.getSqlHelper().getAllFileByPath(currentPath);
	//优化目标，将第一次从数据库得来的文件信息存入到本地数据库中;
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"utf-8\">\r\n");
      out.write("\t<title>在线文件管理器</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style_checkbox.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/title.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/file_con.css\">\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 上传文件 -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/zyUpload.css\" type=\"text/css\">\r\n");
      out.write("\t<script src=\"js/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery.cookie.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/fileClass.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"head_nav\">\r\n");
      out.write("\t\t<div id=\"icon_home\" path=");
      out.print(currentPath );
      out.write(" class=\"head_menu\">\r\n");
      out.write("\t\t\tlogo\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"head_bar\">memu1</div>\r\n");
      out.write("\t\t<div class=\"head_bar\">menu2</div>\r\n");
      out.write("\t\t<div class=\"head_bar\">menu3</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"icon_search\">\r\n");
      out.write("\t\t\t<label onclick=\"searchHelper();\">搜索</label>\r\n");
      out.write("\t\t\t<select id=\"selOption\" onchange=\"searchTypeChange();\">\r\n");
      out.write("\t\t\t\t<option value=\"fimeName\">文件名</option>\t\t\t\t\r\n");
      out.write("\t\t\t\t<option value=\"fileDate\">上传日期</option>\r\n");
      out.write("\t\t\t\t<option value=\"fileContent\">全文检索</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<input id=\"search_key\" />\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"user\">\r\n");
      out.write("\t\t\t<span id=\"user_name\">userName</span>\r\n");
      out.write("\t\t\t<div id=\"user_box\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"fileMsg\">\r\n");
      out.write("\t\t\t<ul id=\"file_catalog\">\t\t\t\t\t\r\n");
      out.write("\t\t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /homePage.jsp(69,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("catalogItem");
      // /homePage.jsp(69,4) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(catalog);
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t\t<li><a href=\"homePage.jsp?currentPath=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${catalogItem.link}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${catalogItem.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("> </a></li>\r\n");
            out.write("\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      }
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"fileBar\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<input type=\"checkbox\" id=\"checkbox_c1\" isSel=\"0\" onclick=\"selectAll();\" class=\"chk_3\" /><label for=\"checkbox_c1\"></label>\r\n");
      out.write("\t\t<span id=\"functionBar\">\r\n");
      out.write("\t\t\t<label>已选中<span id=\"fileSelected\"></span>文件/文件夹</label>\r\n");
      out.write("\t\t\t<label id=\"MulShare\" class=\"MulDo\">分享</label>\r\n");
      out.write("\t\t\t<label id=\"MulDown\" onclick=\"mulDown();\" class=\"MulDo\"><img src=\"./images/MulDown.png\">下载</label>\r\n");
      out.write("\t\t\t<label id=\"MulDel\" onclick=\"mulDel();\" class=\"MulDo\"><img src=\"./images/MulDel.png\">删除</label>\r\n");
      out.write("\t\t\t<label id=\"MulCopy\" class=\"MulDo\">复制到</label>\r\n");
      out.write("\t\t\t<label id=\"MulTrans\" onclick=\"showFileSendMenu();\" class=\"MulDo\">发送至</label>\r\n");
      out.write("\t\t</span>\t\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id='menu_second'>\r\n");
      out.write("\t\t<span onclick='showuploadBorder();'><img alt=\"上传文件\" src=\"images/fileUpload.png\">上传</span>\r\n");
      out.write("\t\t<span onclick='creatFileBorder(\"creatDir\");'><img alt=\"新建文件夹\" src =\"images/newFile.png\">新建文件夹</span>\r\n");
      out.write("\t\t<span><img alt=\"文件下载\" src=\"images/fileDownload.png\">离线下载</span>\r\n");
      out.write("\t\t<div id=\"sel_show_con\">\r\n");
      out.write("\t\t\t<a id=\"sel_grid\" href=\"javascript:changeShowFile(0);\"><img src=\"./images/grid.png\"></a>\r\n");
      out.write("\t\t\t<a id=\"sel_list\" href=\"javascript:changeShowFile(1);\"><img src=\"./images/list.png\"></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"file_content\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<ul id=\"file_ul_grid\">\r\n");
      out.write("\t\t\t");
      out.print(fileList.size()>0?"":"文件夹空空的，赶快上吧！" );
      out.write("\r\n");
      out.write("\t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /homePage.jsp(100,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("fileItem");
      // /homePage.jsp(100,3) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setItems(fileList );
      int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
        if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t<li class = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileType=='floder'?'dir':'file'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" path = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileLogicPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" fileName = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileRandomName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(" \">\r\n");
            out.write("\t\t\t\t\t<img src ='");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.filePic}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'/>\r\n");
            out.write("\t\t\t\t\t<p>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</p>\r\n");
            out.write("\t\t\t\t</li>\r\n");
            out.write("\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f1.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
      }
      out.write("\t\t\t \r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul id=\"file_ul_list\">\r\n");
      out.write("\t\t\t");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f2.setParent(null);
      // /homePage.jsp(108,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setVar("fileItem");
      // /homePage.jsp(108,3) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setItems(fileList );
      int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
        if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t\t<li class = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileType=='floder'?'dir':'file'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" path = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileLogicPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" fileName = \"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileRandomName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(" \">\r\n");
            out.write("\t\t\t\t\t<input class=\"checkFile\" type=\"checkbox\" />\r\n");
            out.write("\t\t\t\t\t<img src ='");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.filePic}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'/>\r\n");
            out.write("\t\t\t\t\t<p>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</p>\r\n");
            out.write("\t\t\t\t\t<span class=\"fileCtime\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fileItem.fileCtime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</span>\r\n");
            out.write("\t\t\t\t</li>\r\n");
            out.write("\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f2.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
      }
      out.write("\t\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"con_cover\">\r\n");
      out.write("\t\t<div id=\"new_file\">\r\n");
      out.write("\t\t\t<img onclick=\"cancelNewFile();\" id=\"file_cancel\" src=\"images/icon_cancel.png\"/>\r\n");
      out.write("\t\t\t<span id=\"newfile_title\">----新建文件</span>\r\n");
      out.write("\t\t\t<input type=\"text\" id=\"name\" placeholder=\"文件名\" />\t\t\t\r\n");
      out.write("\t\t\t<button id=\"file_confirm\">确认</button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"menu\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--右侧的展示栏  -->\r\n");
      out.write("\t<div id=\"funcboard\">\r\n");
      out.write("\t\t<ul id=\"searchRes_con\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 上传文件的border -->\r\n");
      out.write("\t<div id=\"demo\" class=\"demo\"></div>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/file.handle.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/menu.func.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/common.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/ajax.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/draw.js\"></script>\r\n");
      out.write("\t<!-- 引用核心层插件 -->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/zyFile.js\"></script>\r\n");
      out.write("\t<!-- 引用控制层插件 -->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/zyUpload.js\"></script>\r\n");
      out.write("\t<!-- 引用初始化JS -->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/demo.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<!-- 弹出的消息框 -->\r\n");
      out.write("<div id='msg_con'></div>\r\n");
      out.write("</body>\r\n");

	if(request.getAttribute("msg")!=null){
	String msg = (String)request.getAttribute("msg");
	out.print("<script type = 'text/javascript'>giveMsg('"+msg+"')</script>");
}else if(request.getParameter("msg")!=null){

	String msg = request.getParameter("msg");
	
	out.print("<script type = 'text/javascript'>giveMsg('"+msg+"')</script>");

}

	
 
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
