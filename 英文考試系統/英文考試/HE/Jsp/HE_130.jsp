<!--**********************

*************************-->
<HTML>
<%@include file="/inc/H_scrollbarColor"%>
<HEAD>
<%@ page language="java" contentType="text/html; charset=Big5" 
         isErrorPage="true"  errorPage="/public/Error.html"  
         import="java.util.Vector,com.tatung.imis.H.Common.util.*" %>
<%--<jsp:useBean id="dataBean"          scope="request"  class="com.tatung.imis.H.Common.util.SetDataBean"/>--%>                                                                                                                                 
<%@ page import="com.tatung.imis.H.HE.data.HE130VO"%>
<%@ page  import="com.tatung.imis.H.HE.model.HE130Model"%>
<%@ page  import="java.util.*"%> 
<%@ page import ="java.util.StringTokenizer"%>
<%@ page import="com.tatung.imis.A.Common.data.*" %> 
<%@ page import="com.tatung.imis.A.Common.helper.*" %>  
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.TreeMap"%>

<% 
   int HRMID= 0; 
   String strID    = "HE_130";                 
   String strTitle = java.net.URLEncoder.encode(""); 
   String strTitle1 = "�Ҹզ��Z�d��(�d�߱���-�Ҹդ��)"; 
   String readonlycolor = "background-color: #EEEEFF;  border: 1 outset #E7F0F1";
%>
<%--@ taglib uri = "HRMtaglib" prefix="Htag"--%>   
<%  
//*****UserInfo and HRMWeb �����h************//
  boolean bnkey = true;
  H_VarSourceSet obj = new H_VarSourceSet(session,request);
  //userinfo
  boolean userInfoBN = obj.H_getUserInfoIS();

//*****UserInfo and HRMWeb �����h************//      
%>
<%     
   response.setHeader("Pragma","No-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires",0); 
   //deatil
%>
<%
		String stDeptno="";
	  	UserInfo userinfoSession = UserInfoImpl.getInstance(); //���o�ާ@��(�٥��d�ߤηs�W.�ק�.�R���ɨ��ثe���ާ@��)
  		UserInfoVO userinfo = userinfoSession.getUserInfo(request);
  		if(userinfo != null) {
      		stDeptno= userinfo.getHb_deptno();      	
  		}
%>	
<%
	String[] strFlagArray=null;
	String strMessage="";
	String strDate1="";
	String strDate2="";
	String strCtrno="";
	String strType="";
	strDate1=request.getAttribute("strDate1")==null?"":request.getAttribute("strDate1").toString();
	strDate2=request.getAttribute("strDate2")==null?"":request.getAttribute("strDate2").toString();	
	strCtrno=request.getAttribute("strCtrno")==null?"":request.getAttribute("strCtrno").toString();
	strType=request.getAttribute("strType")==null?"":request.getAttribute("strType").toString();		
	//System.out.println("jsp strCtrno-->"+strCtrno);
	strMessage=request.getAttribute("sMessage")==null?"":request.getAttribute("sMessage").toString();
	String keyValue1="";
	TreeMap tM=new TreeMap();
	Vector dataV=new Vector();
	HE130Model model=new HE130Model();
	tM=model.hatDeptfilQry();
	
	//------------���N��,���W��		
			Iterator it=tM.keySet().iterator();
			keyValue1="<option value=''>&nbsp;</option>";
				while(it.hasNext())
				{
					String Ctrno=it.next().toString();
					String Ctr=(String)tM.get(Ctrno);
					String strSelected="";
					if(Ctrno.equals(strCtrno)) strSelected=" selected";    
					keyValue1+="<option value='"+Ctrno+"'"+strSelected+">"+Ctrno+Ctr+"</option>";
				}
%>

<META http-equiv="Content-Type" content="text/html; charset=BIG5">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="/HRMWeb/css/NormalRule.css" rel="stylesheet" type="text/css">

<TITLE>HE_130.jsp</TITLE>
<SCRIPT  src="/HRMWeb/js/H_commonJS.js"></SCRIPT>
<SCRIPT  src="/HRMWeb/js/H_checkInputJSHT.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

function buttonStatus(status){
	//enable������submit button
//	eval("form1.qryBtn.disabled = "+status+";"); 
//	eval("form1.prtBtn.disabled ="+status+";"); 
	if(status==false) InnerMsg.innerText = "";
}
function Maintain(){
	switch(event.srcElement.name){	
		case "queryBN":
			if(H_CheckDateMM(document.getElementById("F1").value,1,"�Ҹդ���϶�1")==false){return;}
			if(H_CheckDateMM(document.getElementById("F2").value,1,"�Ҹդ���϶�2")==false){return;}
			document.myform.command.value = "HE.130Qry";
			H_setInnerMsg();
			msg.innerText="";
			setTimeout('buttonStatus(false)',8000);				
			document.myform.submit();	
		break;  
		
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
             <INPUT  TYPE="button"  ID="B4"    name="queryBN"  value="�d��" class="ButtonStyle1" onClick="Maintain();">     
        </TD>
        <TD><label id="InnerMsg"></label></TD>                         
        <TD><INPUT TYPE="hidden" name="command" ></TD>
   </TR>   
</TABLE>             
<HR  class='HRStyle1'>
<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">      
   <TR class='MessageWord' >
    	<TD width="10%"class="MessageWord"  height=40 colspan=4>���浲�G�G</TD>
    	<TD id="msg" width="90%">
 		<%=strMessage%>
   		</TD>           
   </TR>       	        
</TABLE>
<TABLE width="100%" border="1" cellpadding="0" cellspacing="0">
	<TR class="ButtonStyle1" align="left">
		<TD width="34%">�Ҹդ���϶�
		    <INPUT TYPE="text"  ID="F1"  size=10 maxlength="8" tabindex="2" class="InputStyle1" name="strDate1" value="<%=strDate1%>" onkeyup="H_GotoNext(this,this.value,0);">~
		    <INPUT TYPE="text"  ID="F2"  size=10 maxlength="8"  class="InputStyle1" name="strDate2" value="<%=strDate2%>" >
		</TD>
		<TD width="66%">���ߦW��
		       <SELECT class="InputStyle1"  id="F3" name="strCtrno" >
					 <%=keyValue1%>                                                                                    
                </SELECT>
                 �ή�P�_:
                <SELECT class="InputStyle1"  id= "F4" name="strType">
                	 <OPTION VALUE = "2" <%if(strType.equals("2")) out.println("selected");%>>&nbsp;</OPTION>
                     <OPTION VALUE = "1" <%if(strType.equals("1")) out.println("selected");%>>�w�ή�</OPTION>
                     <OPTION VALUE = "0" <%if(strType.equals("0")) out.println("selected");%>>���ή�</OPTION>                        
                </SELECT> 
		</TD>
	</TR>
</TABLE>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE width="100%" border="0" cellpadding="1" cellspacing="2"	bordercolor="#999999">
	<TR align="center" valign="middle" class="TRStyle1">
		<TD height="20" width="9%">�����Ҹ�</TD>
		<TD height="20" width="7%">�m�W</TD>
		<TD height="20" width="10%">���W��</TD>
		<TD height="20" width="8%">�ҸզW��</TD>
		<TD height="20" width="7%">�Ҹդ��</TD>
		<TD height="20" width="10%">���1�W��</TD>
		<TD height="20" width="5%">���1���Z</TD>
		<TD height="20" width="10%">���2�W��</TD>
		<TD height="20" width="5%">���2���Z</TD>
		<TD height="20" width="5%">�������Z</TD>
		<TD height="20" width="22%">�Ƶ�</TD>
		<TD height="20" width="2%">�X��</TD>
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
						HE130VO vo=(HE130VO)v.get(i);
						if((i%2)==0)
							bgcolor="TRStyle3";
						else
							bgcolor="TRStyle2";
							
							String Idno=vo.getH_IDNO();
							String Hbvperson1name=vo.getHBVPERSON1NAME();
							String Hatdeptfildept=vo.getHATDEPTFILDEPT();
							String Testname=vo.getH_TESTNAME();
							String Date=vo.getH_DATE();
							String Subjname1=vo.getH_SUBJNAME1();
							String Score1=vo.getH_SCORE1();
							String Subjname2=vo.getH_SUBJNAME2();
							String Score2=vo.getH_SCORE2();
							String Average=vo.getH_AVERAGE();
							String Text=vo.getH_TEXT();
							if(Subjname1.trim().equals("")&&Score1.equals("0"))
							{
								Subjname1="";
								Score1="";
							}
							if(Subjname2.trim().equals("")&&Score2.equals("0"))
							{
								Subjname2="";
								Score2="";
							}		
							
							out.print("<tr align='center'  valign='middle'  class='"+bgcolor+"' onMouseOver='changeTRFontColor()' onMouseOut='changeTRFontColor()' </td>");							
						    out.print("<td width='9%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Idno==null || Idno.trim().equals(""))?"&nbsp;":Idno.trim())+"</td>");
						    out.print("<td width='7%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Hbvperson1name==null || Hbvperson1name.trim().equals(""))?"&nbsp;":Hbvperson1name.trim())+"</td>");
						    out.print("<td width='10%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Hatdeptfildept==null || Hatdeptfildept.trim().equals(""))?"&nbsp;":Hatdeptfildept.trim())+"</td>");
						    out.print("<td width='8%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Testname==null || Testname.trim().equals(""))?"&nbsp;":Testname.trim())+"</td>");
			  				out.print("<td width='7%'  vAlign='middle' align='center' >"+((Date==null || Date.trim().equals(""))?"&nbsp;":Date.trim())+"</td>");  				
							out.print("<td width='10%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Subjname1==null || Subjname1.trim().equals(""))?"&nbsp;":Subjname1.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Score1==null || Score1.trim().equals(""))?"&nbsp;":Score1.trim())+"</td>");
							out.print("<td width='10%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Subjname2==null || Subjname2.trim().equals(""))?"&nbsp;":Subjname2.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Score2==null || Score2.trim().equals(""))?"&nbsp;":Score2.trim())+"</td>");
							out.print("<td width='5%'  vAlign='middle' align='center' >"+((Average==null || Average.trim().equals(""))?"&nbsp;":Average.trim())+"</td>");
							out.print("<td width='22%'  vAlign='middle' style='word-break:break-all' align='left' >"+((Text==null || Text.trim().equals(""))?"&nbsp;":Text.trim())+"</td>");
							out.print("<TD width='2%'><INPUT type='checkbox'  class='InputStyle1'  name='rowId' value='"+i+"' "+strFlagArray[i]+" ></TD>");
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
