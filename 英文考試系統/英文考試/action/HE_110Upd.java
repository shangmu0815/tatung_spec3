/*
 * �b 2008/9/8 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletException;

import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE110Model;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE_110Upd implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String Test=helper.getRequest().getParameter("test").trim();
		String Subject=helper.getRequest().getParameter("subject").trim();
		String strTest=helper.getRequest().getParameter("strTest").trim();
		String strTestname=helper.getRequest().getParameter("strTestname").trim();
		String strSubject=helper.getRequest().getParameter("strSubject").trim();
		String strSubjname=helper.getRequest().getParameter("strSubjname").trim();
		HashMap hMap=new HashMap();
		Vector resultV=new Vector();
		hMap.put("strTest",strTest);
		hMap.put("strTestname",strTestname);
		hMap.put("strSubject",strSubject);
		hMap.put("strSubjname",strSubjname);
		HE110Model model=new HE110Model();
		String sMessage="";
		int iResult=0;
		try {
			boolean chkBln=model.checkHbtTestcode(hMap);
			if(DEBUG) System.out.println("chkBln->"+chkBln);
			if(chkBln==false){
				sMessage="�ק異��,�L�ҸեN�X�ɸ�ƥi�ק�!";				
			}else{
				iResult=model.updHbtTestcodeVO(hMap);
				if(DEBUG) System.out.println("iResult->"+iResult);
				if(iResult>0){
					sMessage="�ק令�\";					
				}else{
					sMessage="�ק異��";	
				}				
			}
			if(DEBUG) System.out.println("sMessage->"+sMessage);						
		} catch (Exception e){
			// TODO �۰ʲ��� catch �϶�
			e.printStackTrace();
			sMessage=e.toString();
		}finally{			
			try {
				resultV = model.qryHbtTestcodeV(Test, Subject);
				if(DEBUG) System.out.println("resultV size->"+resultV.size());
				strTest="";
				strTestname="";
				strSubject="";
				strSubjname="";
			} catch (Exception e1) {
				// TODO �۰ʲ��� catch �϶�
				e1.printStackTrace();
				sMessage=e1.toString();
			}
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strTest",strTest);
			helper.getRequest().setAttribute("strTestname",strTestname);
			helper.getRequest().setAttribute("strSubject",strSubject);
			helper.getRequest().setAttribute("strSubjname",strSubjname);
			helper.getRequest().setAttribute("sMessage",sMessage);			
		}
	}

}
