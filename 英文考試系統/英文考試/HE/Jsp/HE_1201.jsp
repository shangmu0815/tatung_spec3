<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ page import="com.tatung.imis.H.HE.data.HE120VO"%>       
<%@ page  import="java.util.*"%> 
<%@ page import ="java.util.StringTokenizer"%>
<%@ page import="com.tatung.imis.A.Common.data.*" %> 
<%@ page import="com.tatung.imis.A.Common.helper.*" %>  
<%@ page import="java.text.SimpleDateFormat"%>


<% 
int iCount=0;
int i=0;
//int count=0;
//System.out.println("jsp 1 iCount->"+iCount);
		String sResult = "�L�B�z���A";

String strMessage="";
strMessage=request.getAttribute("sMessage")==null?"":request.getAttribute("sMessage").toString();
//System.out.println("jsp strMessage->"+strMessage);

%>

<SCRIPT language="JavaScript">
function qryData(){
//	window.opener.document.myform.strIdno.value= window.opener.document.myform.strIdno.value; 
	window.opener.document.myform.command.value ="HE.120Qry";  
	window.opener.document.myform.submit(); 
	window.close();
}
//---��h���ɪ����p,�I��1���W�hQry-
function getValue(strIdno,strName){
	window.opener.document.myform.strIdno.value=strIdno;
	window.opener.document.myform.strName.value=strName;
	qryData();
}

function iCountFinal(){

//-----0���ɪ����p----
if(document.myform.count.value==0){
//alert("document.myform.count.value=>"+document.myform.count.value);
window.opener.msg.innerText='<%=strMessage%>';
window.opener.document.myform.strDept.value="";

window.close();

}

//-----1���ɪ����p
	if(document.myform.count.value==1){
		window.opener.document.myform.strIdno.value=dataT.rows[1].cells[1].innerText;
		window.opener.document.myform.strName.value=dataT.rows[1].cells[2].innerText;
		qryData();
	}
		
}

</SCRIPT>
<META http-equiv="Content-Type" content="text/html; charset=BIG5">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/HRMWeb/css/NormalRule.css" type="text/css">
<script language="JavaScript1.2" src="/HRMWeb/js/formFun.js"></script>
<TITLE>1201_.jsp</TITLE>
</HEAD>
<body onload = "iCountFinal();">
<FORM name="myform" method="post" action="/HRMWeb/Controller">
<p><font color="#0000FF" size="3">&nbsp;���N���P�����Ҹ������</font></p>
<!--<div align="left" style="overflow-y:scroll;height:300">-->
<table class="fontSize" cellSpacing="1" cellPadding="2" width="300" border="0" id="dataT">
	<TR class="TRStyle1" align="left">
		<td class="TRStyle1" height="26" width="120">���W��</td>
		<td class="TRStyle1" height="26" width="79">�����Ҹ�</td>
		<td class="TRStyle1" height="26" width="65">�m�W</td>
		<td class="TRStyle1" height="26" width="57"><p align="left">���</td>
	</TR>
<%
//��ܸ��
//	int i=0;

	if(request.getAttribute("hbvPersonVResultV")!=null){
		String bgcolor="";		 	
		Vector v=(Vector)request.getAttribute("hbvPersonVResultV");
		iCount=v.size();
		System.out.println("jsp iCount->"+iCount);
		for(i=0;i<v.size();i++){
			HE120VO vo=(HE120VO)v.elementAt(i);
			String strDeptno=vo.getHBVPERSON1DEPTNO();
			String strDept=vo.getHATDEPTFILDEPT();
			String strIdno=vo.getH_IDNO();
			String strName=vo.getHBVPERSON1NAME();
			if(i%2==0) 
				bgcolor="TRStyle3";
			else
				bgcolor="TRStyle2"; 
			
			out.println("<tr align=center valign=middle class='"+bgcolor+"' onMouseOver='changeTRFontColor()' onMouseOut='changeTRFontColor()'>");
			out.println("<td>"+strDept+"</td>");
			out.println("<td>"+strIdno+"</td>");	
			out.println("<td>"+strName+"</td>");		
			out.println("<td><input type=button name='Change' onClick=\"getValue('"+strIdno+"','"+strName+"');\" value='���' ></td>");
			out.println("</tr>");
	 	}  	    	 	
	}
%>
</table>
<!--</div>-->
<!--INPUT type="hidden" name="I"  value="<%--=i--%>"-->
<!--�d�X������hidden�_��-->
<INPUT type="hidden" name="count"  value="<%=iCount%>">
<!--Qry��Type�q�e�ݱ��L�� hidden�_��-->
<!--INPUT type="hidden" name="qryType"  value="<%--=strQryType--%>"-->
<!--INPUT TYPE="hidden" name="strIdno" -->
<INPUT TYPE="hidden" name="command" >
</FORM>
</body>
</HTML>
