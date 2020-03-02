<!--**********************

*************************-->
<HTML>
<%@include file="/inc/H_scrollbarColor"%>
<HEAD>
<%@ page language="java" contentType="text/html; charset=Big5" 
         isErrorPage="true"  errorPage="/public/Error.html"  
         import="java.util.Vector,com.tatung.imis.H.Common.util.*" %>
<%--<jsp:useBean id="dataBean"          scope="request"  class="com.tatung.imis.H.Common.util.SetDataBean"/>--%>                                                                                                                                 
<%@ page import="com.tatung.imis.H.HE.data.HE140VO"%>
<%@ page  import="com.tatung.imis.H.HE.model.HE140Model"%>
<%@ page  import="java.util.*"%> 
<%@ page import ="java.util.StringTokenizer"%>
<%@ page import="com.tatung.imis.A.Common.data.*" %> 
<%@ page import="com.tatung.imis.A.Common.helper.*" %>  
<%@ page import="java.text.SimpleDateFormat"%>

<% 
   int HRMID= 0; 
   String strID    = "HE_140";                 
   String strTitle = java.net.URLEncoder.encode(""); 
   String strTitle1 = "考試成績查詢(查詢條件-平均分數)"; 
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
	String strMessage="";
	String strScore1="";
	String strScore2="";
	String strCtrno="";
	String strType="";

	strScore1=request.getAttribute("strScore1")==null?"":request.getAttribute("strScore1").toString();
	strScore2=request.getAttribute("strScore2")==null?"":request.getAttribute("strScore2").toString();	
	strCtrno=request.getAttribute("strCtrno")==null?"":request.getAttribute("strCtrno").toString();	
	strType=request.getAttribute("strType")==null?"":request.getAttribute("strType").toString();	

	strMessage=request.getAttribute("sMessage")==null?"":request.getAttribute("sMessage").toString();
	String keyValue1="";
	TreeMap tM=new TreeMap();
	Vector dataV=new Vector();
	HE140Model model=new HE140Model();
	tM=model.hatDeptfilQry();
	
	//------------中心代號,中心名稱		
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
<%--	
//------當查詢後相對應的單位show出來
	
		if(request.getAttribute("tMap")!=null){
		System.out.println("tMap != null ");
		TreeMap tMap=(TreeMap)request.getAttribute("tMap");
		Iterator it2=tMap.keySet().iterator();
		System.out.println("**strDeptno*>"+strDeptno);	
		keyValue1="<option value=''>&nbsp;</option>";
			while(it2.hasNext())
			{				
				String ctrno=it2.next().toString();
				String ctr=(String)tMap.get(deptno);
//				System.out.println("**deptno*>"+deptno);
//				System.out.println("**dept*>"+dept);				
				String strSelected="";
				if(ctrno.equals(strCtrno)) strSelected=" selected";	     
					keyValue1+="<option value='"+ctrno+"'"+strSelected+">"+ctrno+ctr+"</option>";
//				if(strDeptno.equals(""))
//					keyValue1="<option value=''>ALL</option>";	
			}
		}

--%>
<%
		String stDeptno="";
	  	UserInfo userinfoSession = UserInfoImpl.getInstance(); //取得操作者(還未查詢及新增.修改.刪除時取目前之操作者)
  		UserInfoVO userinfo = userinfoSession.getUserInfo(request);
  		if(userinfo != null) {
      		stDeptno= userinfo.getHb_deptno();      	
  		}
%>	
<META http-equiv="Content-Type" content="text/html; charset=BIG5">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK href="/HRMWeb/css/NormalRule.css" rel="stylesheet" type="text/css">

<TITLE>HE_140.jsp</TITLE>
<SCRIPT  src="/HRMWeb/js/H_commonJS.js"></SCRIPT>
<SCRIPT  src="/HRMWeb/js/H_checkInputJSHT.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

function buttonStatus(status){
	//enable全部的submit button
	if(status==false) InnerMsg.innerText = "";
}
function Maintain(){
	switch(event.srcElement.name){	
		case "queryBN":
//			if(H_ShowSpaceMsg("F1","平均分數範圍1")==false){return;}
//			if(H_ShowSpaceMsg("F2","平均分數範圍2")==false){return;}
			document.myform.command.value = "HE.140Qry";
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
             <INPUT  TYPE="button"  ID="B4"    name="queryBN"  value="查詢" class="ButtonStyle1" onClick="Maintain();">     
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
		<TD width="34%">平均分數範圍區間
		    <INPUT TYPE="text"  ID="F1"  size=3 maxlength="3" tabindex="5" class="InputStyle1" name="strScore1" value="<%=strScore1%>" >~
		    <INPUT TYPE="text"  ID="F2"  size=3 maxlength="3" tabindex="6" class="InputStyle1" name="strScore2" value="<%=strScore2%>" >
		</TD>
		<TD width="66%">
				中心名稱
		       <SELECT class="InputStyle1"  id="F3" name="strCtrno">
					 <%=keyValue1%>                                                                                    
                </SELECT>
                及格與否:
                <SELECT class="InputStyle1"  id= "F4" name="strType">
                	 <OPTION VALUE = "2" <%if(strType.equals("2")) out.println("selected");%>>&nbsp;</OPTION>
                     <OPTION VALUE = "1" <%if(strType.equals("1")) out.println("selected");%>>已及格</OPTION>
                     <OPTION VALUE = "0" <%if(strType.equals("0")) out.println("selected");%>>未及格</OPTION>                        
                </SELECT>                
		</TD>
		
	</TR>

</TABLE>
<DIV  style="overflow-y:scroll;height:0" align="center">
<TABLE width="100%" border="0" cellpadding="1" cellspacing="2"	bordercolor="#999999">
	<TR align="center" valign="middle" class="TRStyle1">
		<TD height="20" width="9%">身分證號</TD>
		<TD height="20" width="7%">姓名</TD>
		<TD height="20" width="10%">單位名稱</TD>
		<TD height="20" width="8%">考試名稱</TD>
		<TD height="20" width="7%">考試日期</TD>
		<TD height="20" width="10%">科目1名稱</TD>
		<TD height="20" width="5%">科目1成績</TD>
		<TD height="20" width="10%">科目2名稱</TD>
		<TD height="20" width="5%">科目2成績</TD>
		<TD height="20" width="5%">平均成績</TD>
		<TD height="20" width="22%">備註</TD>
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
						HE140VO vo=(HE140VO)v.get(i);
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
