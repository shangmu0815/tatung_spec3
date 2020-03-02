<!--**********************

*************************-->
<HTML>
<%@include file="/inc/H_scrollbarColor"%>
<HEAD>
<%@ page language="java" contentType="text/html; charset=Big5" 
         isErrorPage="true"  errorPage="/public/Error.html"  
         import="java.util.Vector,com.tatung.imis.H.Common.util.*" %>
<%--<jsp:useBean id="dataBean"          scope="request"  class="com.tatung.imis.H.Common.util.SetDataBean"/>--%>                                                                                                                                 
<%@ page import="com.tatung.imis.H.HE.data.HE120VO"%>       
<%@ page  import="java.util.*"%> 
<%@ page import ="java.util.StringTokenizer"%>
<%@ page import="com.tatung.imis.A.Common.data.*" %> 
<%@ page import="com.tatung.imis.A.Common.helper.*" %>  
<%@ page import="java.text.SimpleDateFormat"%>
<% 
   int HRMID= 0; 
   String strID    = "HE_120";                 
   String strTitle = java.net.URLEncoder.encode("考試紀錄維護"); 
   String strTitle1 = "考試紀錄維護"; 
   String readonlycolor = "background-color: #EEEEFF;  border: 1 outset #E7F0F1";
%>
<%--@ taglib uri = "HRMtaglib" prefix="Htag"--%>   
<%  
//*****UserInfo and HRMWeb 介面層************//
  boolean bnkey = true;
  H_VarSourceSet obj = new H_VarSourceSet(session,request);
  //userinfo
  boolean userInfoBN = obj.H_getUserInfoIS();

//*****UserInfo and HRMWeb 介面層************//      
%>
<%     
   response.setHeader("Pragma","No-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires",0); 
   //deatil
%>
<%
	String[] strFlagArray=null;
	String strIdno="";
	String strName="";
	String strDept="";
	
	String strTest="";
	String strTestname="";
	String strDate="";
	String strSubject1="";
	String strSubjname1="";
	String strScore1="";
	String strSubject2="";
	String strSubjname2="";
	String strScore2="";
	String strAverage="";
	String strText="";
	String strMessage="";
	String strFlag="";
	
	String strPass="0";
	

	strIdno=request.getAttribute("strIdno")==null?"":request.getAttribute("strIdno").toString();
	strName=request.getAttribute("strName")==null?"":request.getAttribute("strName").toString();
	strDept=request.getAttribute("strDept")==null?"":request.getAttribute("strDept").toString();
//		System.out.println("strIdno->"+strIdno);
//		System.out.println("strName->"+strName);
//		System.out.println("strDept->"+strDept);
		
	strTest=request.getAttribute("strTest")==null?"":request.getAttribute("strTest").toString();
	strTestname=request.getAttribute("strTestname")==null?"":request.getAttribute("strTestname").toString();
	strDate=request.getAttribute("strDate")==null?"":request.getAttribute("strDate").toString();
	strSubject1=request.getAttribute("strSubject1")==null?"":request.getAttribute("strSubject1").toString();
	strSubjname1=request.getAttribute("strSubjname1")==null?"":request.getAttribute("strSubjname1").toString();
	strScore1=request.getAttribute("strScore1")==null?"":request.getAttribute("strScore1").toString();
	strSubject2=request.getAttribute("strSubject2")==null?"":request.getAttribute("strSubject2").toString();
	strSubjname2=request.getAttribute("strSubjname2")==null?"":request.getAttribute("strSubjname2").toString();
	strScore2=request.getAttribute("strScore2")==null?"":request.getAttribute("strScore2").toString();
	strAverage=request.getAttribute("strAverage")==null?"":request.getAttribute("strAverage").toString();
	strText=request.getAttribute("strText")==null?"":request.getAttribute("strText").toString();	
	strMessage=request.getAttribute("sMessage")==null?"":request.getAttribute("sMessage").toString();
	strFlag=request.getAttribute("flag")==null?"disabled":request.getAttribute("flag").toString();	
	strPass=request.getAttribute("strPass")==null?"":request.getAttribute("strPass").toString();



%>	
<META http-equiv="Content-Type" content="text/html; charset=BIG5">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="/HRMWeb/css/NormalRule.css" rel="stylesheet" type="text/css">

<TITLE>HE_120.jsp</TITLE>
<SCRIPT  src="/HRMWeb/js/H_commonJS.js"></SCRIPT>
<SCRIPT  src="/HRMWeb/js/H_checkInputJSHT.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

function buttonStatus(status){
	//enable全部的submit button
//	eval("form1.qryBtn.disabled = "+status+";"); 
//	eval("form1.prtBtn.disabled ="+status+";"); 
	if(status==false) InnerMsg.innerText = "";
}
function Maintain(){
	switch(event.srcElement.name){	
		case "queryBN":
		if(document.myform.strIdno.value ==""&&document.myform.strName.value ==""){
			alert("[HRM 訊息]身分證號和姓名:不可同時空白!!");
			document.myform.strIdno.focus();
			return;
		}
		//--當填入身分證號
		if(document.myform.strIdno.value !=""){
			document.myform.strIdno.value=document.myform.strIdno.value.toUpperCase();
			document.myform.command.value = "HE.120Qry";
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);				
			document.myform.submit();
		}
		//--當填入姓名
		if(document.myform.strIdno.value ==""&&document.myform.strName.value !=""){ 
			window.open('/HRMWeb/Controller?command=HE.1201Qry&strIdno='+document.myform.strIdno.value+'&strName='+document.myform.strName.value,'單位代號與身分證號的選擇','left=100,top=100,width=350,height=350,resizable=yes,scrollbars=yes');
		}
		break;  

		case "insertBN":  
			if(H_ShowSpaceMsg("F1","身分證號")==false){return;}
			if(H_ShowSpaceMsg("F4","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","考試日期")==false){return;}
			if(document.getElementById("F7").value==""&&document.getElementById("F10").value=="")
			{
				alert("[HRM 訊息]科目代碼1與科目代碼2不可同時空白!!");
				return;
			}
			if(document.getElementById("F7").value!=""&&document.getElementById("F9").value=="")
			{
				alert("[HRM 訊息]科目代碼1未填科目成績!!");
				return;
			}
			if(document.getElementById("F7").value==""&&document.getElementById("F9").value!="")
			{
				alert("[HRM 訊息]未填科目代碼1!!");
				return;
			}
			if(document.getElementById("F10").value!=""&&document.getElementById("F12").value=="")
			{
				alert("[HRM 訊息]科目代碼2未填科目成績!!");
				return;
			}
			if(document.getElementById("F10").value==""&&document.getElementById("F12").value!="")
			{
				alert("[HRM 訊息]未填科目代碼2!!");
				return;
			}
			if(H_CheckDateMM(document.getElementById("F6").value,1,"考試日期")==false){return;}
   			document.myform.elements["command"].value = "HE.120Add";  
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);		
			document.myform.submit();
			break;	 
		
			case "updateBN":
			if(H_ShowSpaceMsg("F1","身分證號")==false){return;}
			if(H_ShowSpaceMsg("F4","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","考試日期")==false){return;}
			if(document.getElementById("F7").value==""&&document.getElementById("F10").value=="")
			{
				alert("[HRM 訊息]科目代碼1與科目代碼2不可同時空白!!");
				return;
			}
			if(document.getElementById("F7").value!=""&&document.getElementById("F9").value=="")
			{
				alert("[HRM 訊息]科目代碼1未填科目成績!!");
				return;
			}
			if(document.getElementById("F7").value==""&&document.getElementById("F9").value!="")
			{
				alert("[HRM 訊息]未填科目代碼1!!");
				return;
			}
			if(document.getElementById("F10").value!=""&&document.getElementById("F12").value=="")
			{
				alert("[HRM 訊息]科目代碼2未填科目成績!!");
				return;
			}
			if(document.getElementById("F10").value==""&&document.getElementById("F12").value!="")
			{
				alert("[HRM 訊息]未填科目代碼2!!");
				return;
			}
			if(H_CheckDateMM(document.getElementById("F6").value,1,"考試日期")==false){return;}
			if(document.myform.oldStrDate.value!=document.myform.strDate.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strDate.value=document.myform.oldStrDate.value;document.myform.strDate.focus();return;}
   			document.myform.elements["command"].value = "HE.120Upd";   
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);		
			document.myform.submit();
		break;
			
		case "deleteBN":
			if(H_ShowSpaceMsg("F1","身分證號")==false){return;}
			if(H_ShowSpaceMsg("F4","考試代碼")==false){return;}
			if(H_ShowSpaceMsg("F6","考試日期")==false){return;}
			if(H_CheckDateMM(document.getElementById("F6").value,1,"考試日期")==false){return;}
			if(document.myform.oldStrDate.value!=document.myform.strDate.value){alert("[HRM 訊息] 代號:KEY值不允許更改!!");document.myform.strDate.value=document.myform.oldStrDate.value;document.myform.strDate.focus();return;}
		    conf = confirm("警告！確定刪除？");
			 if (conf == true){
		 		 document.myform.elements["command"].value = "HE.120Del";
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
		document.myform.strTestname.value= dataT.rows[i].cells[1].innerText.replace(/\ /g, "");
		document.myform.strDate.value= dataT.rows[i].cells[2].innerText.replace(/\ /g, "");
		document.myform.strSubject1.value= dataT.rows[i].cells[3].innerText.replace(/\ /g, "");
		document.myform.strSubjname1.value= dataT.rows[i].cells[4].innerText.replace(/\ /g, "");
		document.myform.strScore1.value= dataT.rows[i].cells[5].innerText.replace(/\ /g, "");
		document.myform.strSubject2.value= dataT.rows[i].cells[6].innerText.replace(/\ /g, "");
		document.myform.strSubjname2.value= dataT.rows[i].cells[7].innerText.replace(/\ /g, "");
		document.myform.strScore2.value= dataT.rows[i].cells[8].innerText.replace(/\ /g, "");
		document.myform.strAverage.value= dataT.rows[i].cells[9].innerText.replace(/\ /g, "");
		document.myform.strText.value= dataT.rows[i].cells[10].innerText.replace(/\ /g, "");
		document.myform.strPass.checked=dataT.rows[i].cells[11].childNodes[0].checked;
		document.myform.oldStrDate.value=document.myform.strDate.value;
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
		<TD width="20%">身分證號
		    <INPUT TYPE="text"  ID="F1"  size=10 maxlength="10" tabindex="5" class="InputStyle1" name="strIdno" value="<%=strIdno%>" >
		</TD>
		<TD width="20%">姓名
		    <INPUT TYPE="text" ID="F2"  size=10 maxlength="10" tabindex="6" class="InputStyle1" name="strName" value="<%=strName%>" >
		</TD>
		<TD width="60%">單位名稱
		    <INPUT TYPE="text" ID="F3"  size=40 maxlength="20" tabindex="7" class="" name="strDept" value="<%=strDept%>" >
		</TD>
	</TR>
</TABLE>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE  width="100%" border="1" cellpadding="0" cellspacing="0" >
      <TR  align= "center">
         <TD  class="TitleText1" width="4%">
     	  		 <INPUT type="text"  id="F4" size="2"  tabindex="8" class="InputStyle1"    maxlength="2"  name="strTest" value="<%=strTest%>" >
         </TD>
         <TD  class="TitleText1" align="left" width="14%">
         	<INPUT type="text" id="F5" size="16" tabindex="9" class="" maxlength="15" name="strTestname" value="<%=strTestname%>" readonly>
         </TD>
         <TD  class="TitleText1" align="left" width="8%">
     	  		 <INPUT type="text" id="F6" size="8" tabindex="10" class="InputStyle1" maxlength="8" name="strDate" value="<%=strDate%>">
         </TD>
         <TD  class="TitleText1" width="5%" >
     	  		 <INPUT type="text" id="F7"   size="2" tabindex="11"  class="InputStyle1"  maxlength="2"  name="strSubject1" value="<%=strSubject1%>" >
         </TD>
         <TD  class="TitleText1" width="9%" align="left">
     	  		 <INPUT type="text" id="F8"   size="8" tabindex="12"  class=""  maxlength="10"  name="strSubjname1" value="<%=strSubjname1%>" readonly>
         </TD>
         <TD  class="TitleText1" width="5%" >
     	  		 <INPUT type="text" id="F9"   size="3" tabindex="13"  class="InputStyle1"  maxlength="3"  name="strScore1" value="<%=strScore1%>" >
         </TD>
         <TD  class="TitleText1" width="5%">
     	  		 <INPUT type="text"  id="F10" size="2"  tabindex="14" class="InputStyle1"    maxlength="2"  name="strSubject2" value="<%=strSubject2%>" >
         </TD>
         <TD  class="TitleText1" width="10%" align="left">
     	  		 <INPUT type="text"  id="F11" size="8"  tabindex="15" class=""    maxlength="10"  name="strSubjname2" value="<%=strSubjname2%>" readonly>
         </TD>
         <TD  class="TitleText1" width="5%">
     	  		 <INPUT type="text"  id="F12" size="3"  tabindex="16" class="InputStyle1"    maxlength="3"  name="strScore2" value="<%=strScore2%>" >
         </TD>
         <TD  class="TitleText1" width="5%">
     	  		 <INPUT type="text"  id="F13" size="3"  tabindex="17" class=""    maxlength="3"  name="strAverage" value="<%=strAverage%>" readonly>
         </TD>
         <TD  class="TitleText1" width="28%" align="left">
     	  		 <INPUT type="text"  id="F14" size="28"  tabindex="18" class="InputStyle1"    maxlength="58"  name="strText" value="<%=strText%>" >
         </TD>
         <TD  class="TitleText1" width="2%" align="left">
     	  		 <INPUT type='checkbox'  id="F15" size="1"  tabindex="19" class="InputStyle1"    maxlength="1"  name="strPass" value="1">
         </TD>
       </TR>       
</TABLE>
</DIV>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE width="100%" border="0" cellpadding="1" cellspacing="2"	bordercolor="#999999">
	<TR align="center" valign="middle" class="TRStyle1">
		<TD height="20" width="4%">考試代碼</TD>
		<TD height="20" width="14%">考試名稱</TD>
		<TD height="20" width="8%">考試日期</TD>
		<TD height="20" width="5%">科目1代碼</TD>
		<TD height="20" width="9%">科目1名稱</TD>
		<TD height="20" width="5%">科目1成績</TD>
		<TD height="20" width="5%">科目2代碼</TD>
		<TD height="20" width="10%">科目2名稱</TD>
		<TD height="20" width="5%">科目2成績</TD>
		<TD height="20" width="5%">平均成績</TD>
		<TD height="20" width="28%">備註</TD>
		<TD height="20" width="2%">合格</TD>
	</TR>
</TABLE>
</DIV>
<!-- detail -->
<DIV  style="overflow-y:scroll;height:400" align="center">
   <TABLE  width="100%" border="1" cellpadding="0" cellspacing="0" id="dataT">
   			<%
			int iCount=0;
				if(request.getAttribute("resultV")!=null){
					if(request.getAttribute("strFlagArray")!=null){
						strFlagArray=(String[])request.getAttribute("strFlagArray");
					}
				String bgcolor="";
					Vector v=(Vector)request.getAttribute("resultV");
					for(int i=0;i<v.size();i++){
						HE120VO vo=(HE120VO)v.get(i);
						if((i%2)==0)
							bgcolor="TRStyle3";
						else
							bgcolor="TRStyle2";
							String Test=vo.getH_TEST();
							String Testname=vo.getH_TESTNAME();
							String Date=vo.getH_DATE();
							String Subject1=vo.getH_SUBJECT1();
							String Subjname1=vo.getH_SUBJNAME1();
							String Score1=vo.getH_SCORE1();
							String Subject2=vo.getH_SUBJECT2();
							String Subjname2=vo.getH_SUBJNAME2();
							String Score2=vo.getH_SCORE2();
							String Average=vo.getH_AVERAGE();
							String Text=vo.getH_TEXT();
							if(Subject1.trim().equals("")&&Score1.equals("0"))
							{
								Subject1="";
								Score1="";
							}
							if(Subject2.trim().equals("")&&Score2.equals("0"))
							{
								Subject2="";
								Score2="";
							}	
										
							out.print("<tr align='center'  valign='middle'  class='"+bgcolor+"' onMouseOver='changeTRFontColor()' onMouseOut='changeTRFontColor()' onClick=\"qryDetailValue('"+i+"');\"</td>");							
			   				out.print("<td width='4%' vAlign='middle' align='center' >"+((Test==null || Test.trim().equals(""))?"&nbsp;":Test.trim())+"</td>");   				
						    out.print("<td width='14%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Testname==null || Testname.trim().equals(""))?"&nbsp;":Testname.trim())+"</td>");
			  				out.print("<td width='8%'  vAlign='middle' align='center' >"+((Date==null || Date.trim().equals(""))?"&nbsp;":Date.trim())+"</td>");  				
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Subject1==null || Subject1.trim().equals(""))?"&nbsp;":Subject1.trim())+"</td>");
							out.print("<td width='9%'  vAlign='middle' style='word-break:break-all' align='left' align='left' >"+((Subjname1==null || Subjname1.trim().equals(""))?"&nbsp;":Subjname1.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Score1==null || Score1.trim().equals(""))?"&nbsp;":Score1.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Subject2==null || Subject2.trim().equals(""))?"&nbsp;":Subject2.trim())+"</td>");
							out.print("<td width='10%'  vAlign='middle' style='word-break:break-all' align='left' align='left' >"+((Subjname2==null || Subjname2.trim().equals(""))?"&nbsp;":Subjname2.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Score2==null || Score2.trim().equals(""))?"&nbsp;":Score2.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Average==null || Average.trim().equals(""))?"&nbsp;":Average.trim())+"</td>");
							out.print("<td width='28%'  vAlign='middle' style='word-break:break-all' align='left' align='left' >"+((Text==null || Text.trim().equals(""))?"&nbsp;":Text.trim())+"</td>");
							out.print("<TD width='2%'><INPUT type='checkbox'  class='InputStyle1'  name='rowId' value='"+i+"' "+strFlagArray[i]+"></TD>");
			   				out.print("</tr>");								
					}				
				}  						
		%>                                       
  </TABLE>
</DIV>           
<!-- detail -->

<INPUT type="hidden" name="oldStrDate" >
<INPUT TYPE="hidden" name="count" value=<%=iCount%>>
</FORM>       
</BODY>
</HTML>
