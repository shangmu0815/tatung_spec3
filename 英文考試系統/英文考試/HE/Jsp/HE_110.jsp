<!--**********************

*************************-->
<HTML>
<%@include file="/inc/H_scrollbarColor"%>
<HEAD>
<%@ page language="java" contentType="text/html; charset=Big5" 
         isErrorPage="true"  errorPage="/public/Error.html"  
         import="java.util.Vector,com.tatung.imis.H.Common.util.*" %>
<%--<jsp:useBean id="dataBean"          scope="request"  class="com.tatung.imis.H.Common.util.SetDataBean"/>--%>                                                                                                                                 
<%@ page import="com.tatung.imis.H.HE.data.HE110VO"%>       
<%@ page  import="java.util.*"%> 
<%@ page import ="java.util.StringTokenizer"%>
<%@ page import="com.tatung.imis.A.Common.data.*" %> 
<%@ page import="com.tatung.imis.A.Common.helper.*" %>  
<%@ page import="java.text.SimpleDateFormat"%>
<% 
   int HRMID= 0; 
   String strID    = "HE_110";                 
   String strTitle = java.net.URLEncoder.encode("考試代碼維護"); 
   String strTitle1 = "考試代碼維護"; 
   String readonlycolor = "background-color: #EEEEFF;  border: 1 outset #E7F0F1";
%>
<%--@ taglib uri = "HRMtaglib" prefix="Htag"--%>   
<%  
//*****UserInfo and HRMWeb 介面層************//
  boolean bnkey = true;
  H_VarSourceSet obj = new H_VarSourceSet(session,request);
  //userinfo
  boolean userInfoBN = obj.H_getUserInfoIS();
//	String strName=obj.H_getUserInfoHName();
	
//	System.out.println("strName->"+strName);
		String strTcop="";

 	 	UserInfo userinfoSession = UserInfoImpl.getInstance(); //取得操作者
  		UserInfoVO userinfo = userinfoSession.getUserInfo(request);
  		if(userinfo != null) {
      	strTcop= userinfo.getHb_name();      	
  		}
  		System.out.println("strTcop->"+strTcop);
//*****UserInfo and HRMWeb 介面層************//      
%>
<%     
   response.setHeader("Pragma","No-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires",0); 
   //deatil
%>
<%
	String Test="";
	String Subject="";
	String strTest="";
	String strTestname="";
	String strSubject="";
	String strSubjname="";
	String strMessage="";
	String strFlag="";

	Test=request.getAttribute("test")==null?"":request.getAttribute("test").toString();
	Subject=request.getAttribute("subject")==null?"":request.getAttribute("subject").toString();
	
	strTest=request.getAttribute("strTest")==null?"":request.getAttribute("strTest").toString();
	strTestname=request.getAttribute("strTestname")==null?"":request.getAttribute("strTestname").toString();
	strSubject=request.getAttribute("strSubject")==null?"":request.getAttribute("strSubject").toString();
	strSubjname=request.getAttribute("strSubjname")==null?"":request.getAttribute("strSubjname").toString();
//	System.out.println("strTest->"+strTest);
//	System.out.println("strTestname->"+strTestname);
//	System.out.println("strSubject->"+strSubject);
//	System.out.println("strSubjname->"+strSubjname);
	
	strMessage=request.getAttribute("sMessage")==null?"":request.getAttribute("sMessage").toString();
	strFlag=request.getAttribute("flag")==null?"disabled":request.getAttribute("flag").toString();

%>	
<META http-equiv="Content-Type" content="text/html; charset=BIG5">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="/HRMWeb/css/NormalRule.css" rel="stylesheet" type="text/css">

<TITLE>HE_110.jsp</TITLE>
<SCRIPT  src="/HRMWeb/js/H_commonJS.js"></SCRIPT>
<SCRIPT  src="/HRMWeb/js/H_checkInputJSHT.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

function buttonStatus(status){

	if(status==false) InnerMsg.innerText = "";
}
function Maintain(){
	switch(event.srcElement.name){	
		case "queryBN":
			document.myform.command.value = "HE.110Qry";
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);				
			document.myform.submit();	
		break;  

		case "insertBN":  
			if(H_ShowSpaceMsg("F3","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F4","考試名稱")==false){return;}
			if(H_ShowSpaceMsg("F5","科目代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","科目名稱")==false){return;}
   			document.myform.elements["command"].value = "HE.110Add";  
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);		
			document.myform.submit();
			break;	 
		
			case "updateBN":
			if(H_ShowSpaceMsg("F3","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F4","考試名稱")==false){return;}
			if(H_ShowSpaceMsg("F5","科目代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","科目名稱")==false){return;}
			if(document.myform.oldStrTest.value!=document.myform.strTest.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strTest.value=document.myform.oldStrTest.value;document.myform.strTest.focus();return;}
			if(document.myform.oldStrSubject.value!=document.myform.strSubject.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strSubject.value=document.myform.oldStrSubject.value;document.myform.strSubject.focus();return;}
   			document.myform.elements["command"].value = "HE.110Upd";   
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);		
			document.myform.submit();
		break;
			
		case "deleteBN":
			if(H_ShowSpaceMsg("F3","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F4","考試名稱")==false){return;}
			if(H_ShowSpaceMsg("F5","科目代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","科目名稱")==false){return;}

			if(document.myform.oldStrTest.value!=document.myform.strTest.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strTest.value=document.myform.oldStrTest.value;document.myform.strTest.focus();return;}
			if(document.myform.oldStrSubject.value!=document.myform.strSubject.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strSubject.value=document.myform.oldStrSubject.value;document.myform.strSubject.focus();return;}
		    conf = confirm("警告！確定刪除？");
			 if (conf == true){
		 		 document.myform.elements["command"].value = "HE.110Del";
			}else{
				return false;
	     	}
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);
			document.myform.submit();	
		break;   
	}
		
}


function qryDetailValue(i){
	var objEventSource = event.srcElement;
	if(objEventSource.nodeName == "TD"){
//		alert("i->"+i);
		document.myform.strTest.value= dataT.rows[i].cells[0].innerText.replace(/\ /g, "");
		document.myform.strTestname.value= dataT.rows[i].cells[1].innerText.replace(/ /g, "");
		document.myform.strSubject.value= dataT.rows[i].cells[2].innerText.replace(/\ /g, "");
		document.myform.strSubjname.value= dataT.rows[i].cells[3].innerText.replace(/\ /g, "");
		
		document.myform.oldStrTest.value=document.myform.strTest.value;
		document.myform.oldStrTestname.value=document.myform.strTestname.value;
		document.myform.oldStrSubject.value=document.myform.strSubject.value;
		document.myform.oldStrSubjname.value=document.myform.strSubjname.value;
		document.myform.updateBN.disabled=false;
		document.myform.deleteBN.disabled=false;
	}
}

</SCRIPT>
</HEAD>
<BODY marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" >
<%@include file="/inc/H_head2"%>
<FORM name="myform" method="post" action="/HRMWeb/Controller">                                      
<TABLE border="0" width="100%" >      
   <TR>
       <TD >
             <INPUT  TYPE="button"  ID="B4"    name="queryBN"  value="查詢" class="ButtonStyle1" onClick="Maintain();">     
             <INPUT  TYPE="button"  ID="B1"    name="insertBN"   value="新增" class="ButtonStyle1" onClick="Maintain();">
             <INPUT  TYPE="button"  ID="B2"    name="updateBN" value="修改" class="ButtonStyle1" <%=strFlag%> onClick="Maintain();">
             <INPUT  TYPE="button"  ID="B3"	name="deleteBN"  value="刪除" class="ButtonStyle1" <%=strFlag%> onClick="Maintain();">
        </TD>
        <TD><label id="InnerMsg"></label></TD>                         
        <TD><INPUT TYPE="hidden" name="command" ></TD>
   </TR>   
</TABLE>             
<HR  class='HRStyle1'>
<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">      
   <TR class='MessageWord' >
    	<TD width="10%"class="MessageWord"  height=40 colspan=4>執行結果：</TD>
    	<TD id="msg" width="90%">
 		<%=strMessage%>
   		</TD>           
   </TR>       	        
</TABLE>
<TABLE width="100%" border="1" cellpadding="0" cellspacing="0">
	<TR class="ButtonStyle1" align="left">
		<TD width="9%">考試代碼
		    <INPUT TYPE="text"  ID="F1"  size=4 maxlength="2" tabindex="5" class="InputStyle1" name="test" value="<%=Test%>" onkeyup="H_GotoNext(this,this.value,0)">
		</TD>
		<TD width="42%">科目代碼
		    <INPUT TYPE="text" ID="F2"  size=4 maxlength="2" tabindex="6" class="InputStyle1" name="subject" value="<%=Subject%>" >
		</TD>
	</TR>
</TABLE>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE  width="100%" border="1" cellpadding="0" cellspacing="0" >
      <TR  align= "center">
         <TD  class="TitleText1" width="10%">
     	  		 <INPUT type="text"  id="F3" size="4"  tabindex="7" class="InputStyle1"    maxlength="2"  name="strTest" value="<%=strTest%>" >
         </TD>
         <TD  class="TitleText1" width="40%" >
     	  		 <INPUT type="text" id="F4"   size="30" tabindex="8"  class="InputStyle1"    maxlength="50"  name="strTestname" value="<%=strTestname%>">
         </TD>
         <TD  class="TitleText1" width="10%" >
     	  		 <INPUT type="text" id="F5"   size="4"  tabindex="9"  class="InputStyle1"    maxlength="2"  name="strSubject" value="<%=strSubject%>" >
         </TD>
         <TD  class="TitleText1" width="40%" >
     	  		 <INPUT type="text" id="F6"   size="30" tabindex="10"  class="InputStyle1"  maxlength="50"  name="strSubjname" value="<%=strSubjname%>" >
         </TD>
       </TR>       
</TABLE>
</DIV>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE width="100%" border="0" cellpadding="1" cellspacing="2"	bordercolor="#999999">
	<TR align="center" valign="middle" class="TRStyle1">
		<TD height="20" width="10%">考試代碼</TD>
		<TD height="20" width="40%">考試名稱</TD>
		<TD height="20" width="10%">科目代碼</TD>
		<TD height="20" width="40%">科目名稱</TD>
	</TR>
</TABLE>
</DIV>
<!-- detail -->
<DIV  style="overflow-y:scroll;height:400" align="center">
   <TABLE  width="100%" border="1" cellpadding="0" cellspacing="0" id="dataT" frame="box">
   			<%

				if(request.getAttribute("resultV")!=null){
				String bgcolor="";
					Vector v=(Vector)request.getAttribute("resultV");
					for(int i=0;i<v.size();i++){
						HE110VO vo=(HE110VO)v.get(i);
						if((i%2)==0)
							bgcolor="TRStyle3";
						else
							bgcolor="TRStyle2";
							out.print("<tr align='center'  valign='middle'  class='"+bgcolor+"' onMouseOver='changeTRFontColor()' onMouseOut='changeTRFontColor()' onClick=\"qryDetailValue('"+i+"');\"</td>");							
			   				out.print("<td width='10%' vAlign='middle' align='left' >"+((vo.getH_TEST()==null || vo.getH_TEST().trim().equals(""))?"&nbsp;":vo.getH_TEST().trim())+"</td>");   				
						    out.print("<td width='40%'  vAlign='middle' align='left' >"+((vo.getH_TESTNAME()==null || vo.getH_TESTNAME().trim().equals(""))?"":vo.getH_TESTNAME().trim())+"</td>");
			  				out.print("<td width='10%'  vAlign='middle' align='left' >"+((vo.getH_SUBJECT()==null || vo.getH_SUBJECT().trim().equals(""))?"&nbsp;":vo.getH_SUBJECT().trim())+"</td>");  				
							out.print("<td width='40%'  vAlign='middle' align='left' >"+((vo.getH_SUBJNAME()==null || vo.getH_SUBJNAME().trim().equals(""))?"&nbsp;":vo.getH_SUBJNAME().trim())+"</td>");
			   				out.print("</tr>");								
					}				
				}  						
		%>                                       
  </TABLE>
</DIV>           
<!-- detail -->
<INPUT type="hidden" name="oldStrTest" >
<INPUT type="hidden" name="oldStrTestname" >
<INPUT type="hidden" name="oldStrSubject" >
<INPUT type="hidden" name="oldStrSubjname" >
</FORM>       
</BODY>
</HTML>
