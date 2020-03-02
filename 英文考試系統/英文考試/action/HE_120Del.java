/*
 * 在 2008/9/18 建立
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
import com.tatung.imis.H.HE.data.HE120VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE110Model;
import com.tatung.imis.H.HE.model.HE120Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_120Del implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String strIdno="";
		String strDate="";
		String strName="";
		String strDept="";
		HashMap hMap=new HashMap();
		Vector resultV=new Vector();
		HE120Model model=new HE120Model();
		String sMessage="";
		int iResult=0;
		String[] strFlagArray = null;
		try {
			strIdno=helper.getRequest().getParameter("strIdno").trim();
			strDate=helper.getRequest().getParameter("strDate").trim();
			strName=helper.getRequest().getParameter("strName").trim();
			strDept=helper.getRequest().getParameter("strDept").trim();
			
			hMap.put("strIdno",strIdno);
			hMap.put("strDate",strDate);			
			boolean chkBln=model.checkHbtTestrecord(hMap);
			if(DEBUG) System.out.println("chkBln->"+chkBln);
			if(chkBln==false){
				sMessage="刪除失敗,無考試紀錄檔可刪除!";				
			}else{
				iResult=model.delHbtTestrecordVO(hMap);
				if(DEBUG) System.out.println("iResult->"+iResult);
				if(iResult>0){
					sMessage="刪除成功";
				}else{
					sMessage="刪除失敗";	
				}				
			}
			if(DEBUG) System.out.println("sMessage->"+sMessage);						
		
		} catch (Exception e){
			// TODO 自動產生 catch 區塊
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
			} catch (Exception e1) {
				// TODO 自動產生 catch 區塊
				e1.printStackTrace();
				sMessage=e1.toString();
			}
			finally{
				helper.getRequest().setAttribute("resultV",resultV);
				helper.getRequest().setAttribute("strIdno",strIdno);
				helper.getRequest().setAttribute("strName",strName);
				helper.getRequest().setAttribute("strDept",strDept);
				helper.getRequest().setAttribute("sMessage",sMessage);
				helper.getRequest().setAttribute("strFlagArray",strFlagArray);
//				helper.saveRequestParameterToAttribute();				
			}		
			
		}
	}

}
