/*
 * 在 2008/9/8 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
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
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
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
				sMessage="修改失敗,無考試代碼檔資料可修改!";				
			}else{
				iResult=model.updHbtTestcodeVO(hMap);
				if(DEBUG) System.out.println("iResult->"+iResult);
				if(iResult>0){
					sMessage="修改成功";					
				}else{
					sMessage="修改失敗";	
				}				
			}
			if(DEBUG) System.out.println("sMessage->"+sMessage);						
		} catch (Exception e){
			// TODO 自動產生 catch 區塊
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
				// TODO 自動產生 catch 區塊
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
