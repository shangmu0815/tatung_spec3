/*
 * �b 2008/9/18 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletException;

import com.tatung.imis.A.Common.data.UserInfoVO;
import com.tatung.imis.A.Common.helper.UserInfo;
import com.tatung.imis.A.Common.helper.UserInfoImpl;
import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;
import com.tatung.imis.H.HE.data.HE120VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE120Model;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE_120Upd implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper)throws ServletException, IOException {
//		�ާ@��	
			  String strTcop="";
//			  ��t�Τ��
			  Calendar calendar = Calendar.getInstance();
			  Date date = calendar.getTime();
			  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			  String strTdop = sf.format(date);
//			  ���o�ާ@��
			  UserInfo userinfoSession = UserInfoImpl.getInstance(); 
			  UserInfoVO userinfoVO = userinfoSession.getUserInfo(helper.getRequest());
			  if(userinfoVO != null) {
				  strTcop = userinfoVO.getHb_name();     	
			  }
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
		String strPass="";
		String sMessage="";
		float fltAverage=0;
		float fltScore1=0;
		float fltScore2=0;
		HashMap hMap=new HashMap();
		Vector resultV=new Vector();	
		HE120Model model=new HE120Model();		
		int iResult=0;
		String[] strFlagArray = null;
		try {
			strIdno=helper.getRequest().getParameter("strIdno").trim();
			strTest=helper.getRequest().getParameter("strTest").trim();
			strDate=helper.getRequest().getParameter("strDate").trim();
			strSubject1=helper.getRequest().getParameter("strSubject1").trim();
			strScore1=helper.getRequest().getParameter("strScore1").trim();
			strSubject2=helper.getRequest().getParameter("strSubject2").trim();
			strScore2=helper.getRequest().getParameter("strScore2").trim();			
			strText=helper.getRequest().getParameter("strText").trim();
			strPass=helper.getRequest().getParameter("strPass");	//---pass��1 �_�h��null
			if(DEBUG) 
			{
				System.out.println("strTest->"+strTest); 
				System.out.println("strSubject1->"+strSubject1);
				System.out.println("strSubject2->"+strSubject2);
		    }	
			if(strScore1!=null){
				if(!strScore1.trim().equals("")){
					//-----check��ئ��Z��J��ƬO�_���Ʀr----
					boolean integerCondition1=HE120Model.isInteger(strScore1);
					if(integerCondition1==false){
						throw new HeException("��ئ��Z1��J��Ʀ��~!");
					}
				}
			}
			if(strScore2!=null){
				if(!strScore2.trim().equals("")){
					//-----check��ئ��Z��J��ƬO�_���Ʀr----
					boolean integerCondition2=HE120Model.isInteger(strScore2);
					if(integerCondition2==false){
						throw new HeException("��ئ��Z2��J��Ʀ��~!");
					}	
				}
			}
			//---------�d�H�ƥD�ɩm�W,���
			HE120VO dataVO=model.qryHbvPerson1VO(strIdno);
			if(dataVO!=null){					
				strName=dataVO.getHBVPERSON1NAME().trim();
				strDept=dataVO.getHATDEPTFILDEPT().trim();
			}else{
				throw new HeException("�d�L���H�ƥD�ɸ��");
			}		
			
			if(!strScore1.equals("")){
				fltScore1=Integer.parseInt(strScore1);
			}else{
				fltScore1=0;
				strScore1=String.valueOf(0);
			}
			if(!strScore2.equals("")){
				fltScore2=Integer.parseInt(strScore2);
			}else{
				fltScore2=0;
				strScore2=String.valueOf(0);
			}
			if(!strSubject1.trim().equals("")&&!strSubject2.trim().equals("")){
				fltAverage=(fltScore1+fltScore2)/2;
				strAverage=String.valueOf(fltAverage);
			}
			if(!strSubject1.trim().equals("")&&strSubject2.trim().equals("")){
				fltAverage=fltScore1;
				strAverage=String.valueOf(fltAverage);
			}
			if(strSubject1.trim().equals("")&&!strSubject2.trim().equals("")){
				fltAverage=fltScore2;
				strAverage=String.valueOf(fltAverage);
			}
			
			if(strPass==null){
				strPass="0";  //---��pass ->  Pass=0 �_�h Pass=1
			}else{
				strPass="1";
			}			

			hMap.put("strIdno",strIdno);
			hMap.put("strTest",strTest);
			hMap.put("strDate",strDate);
			hMap.put("strSubject1",strSubject1);
			hMap.put("strScore1",strScore1);
			hMap.put("strSubject2",strSubject2);
			hMap.put("strScore2",strScore2);
			hMap.put("strAverage",strAverage);
			hMap.put("strText",strText);
			hMap.put("strPass",strPass);
			hMap.put("strTcop",strTcop);
			hMap.put("strTdop",strTdop);
						
			boolean chkBln=model.checkHbtTestrecord(hMap);
			if(DEBUG) System.out.println("chkBln->"+chkBln);
			if(chkBln==false){
				sMessage="�ק異��,�d�L�Ҹլ�����!";				
			}else{
				if(!strSubject1.equals("")){
					HE120VO vo1=model.qryHbtTestcodeVO(strTest,strSubject1);
					if(vo1==null){
						throw new HeException("�d�L�ҸեN�X�ɦҸեN�X��"+strTest+",��إN�X��"+strSubject1+"���Ҹլ�ئW��");
					}
				}
				if(!strSubject2.equals("")){	
					HE120VO vo2=model.qryHbtTestcodeVO(strTest,strSubject2);
					if(vo2==null){
						throw new HeException("�d�L�ҸեN�X�ɦҸեN�X��"+strTest+",��إN�X��"+strSubject2+"���Ҹլ�ئW��");
					}
				}	
				iResult=model.updHbtTestrecordVO(hMap);
				if(DEBUG) System.out.println("iResult->"+iResult);
				if(iResult>0){
					sMessage="�ק令�\";
					
				}else{
					sMessage="�����Ҹ���"+strIdno+"���ק�@�~����!!";	
				}				
			}
			if(DEBUG) System.out.println("sMessage->"+sMessage);						
		} catch (Exception e){
			// TODO �۰ʲ��� catch �϶�
			e.printStackTrace();
			sMessage=e.toString();			

		}finally{			
			try {				
				resultV = model.qryHbtTestrecordV(strIdno);
				if(DEBUG) System.out.println("resultV size->"+resultV.size());
				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE120VO vo=(HE120VO)resultV.get(i);
						if(vo.getH_PASS().equals("1")){
							strFlagArray[i]="checked";
						}							
				}
				strTest="";
				strTestname="";
				strDate="";
				strSubject1="";
				strSubjname1="";
				strScore1="";
				strSubject2="";
				strSubjname2="";
				strScore2="";
				strAverage="";
				strText="";
			} catch (Exception e1) {
				// TODO �۰ʲ��� catch �϶�
				e1.printStackTrace();
				sMessage=e1.toString();
			}
			helper.getRequest().setAttribute("sMessage",sMessage);
			helper.getRequest().setAttribute("strIdno",strIdno);
			helper.getRequest().setAttribute("strName",strName);
			helper.getRequest().setAttribute("strDept",strDept);
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strFlagArray",strFlagArray);
			
			helper.getRequest().setAttribute("strTest",strTest);
			helper.getRequest().setAttribute("strTestname",strTestname);
			helper.getRequest().setAttribute("strDate",strDate);
			helper.getRequest().setAttribute("strSubject1",strSubject1);
			helper.getRequest().setAttribute("strSubjname1",strSubjname1);
			helper.getRequest().setAttribute("strScore1",strScore1);
			helper.getRequest().setAttribute("strSubject2",strSubject2);
			helper.getRequest().setAttribute("strSubjname2",strSubjname2);
			helper.getRequest().setAttribute("strScore2",strScore2);
			helper.getRequest().setAttribute("strAverage",strAverage);
			helper.getRequest().setAttribute("strText",strText);
			
		
			
		}
	}

}
