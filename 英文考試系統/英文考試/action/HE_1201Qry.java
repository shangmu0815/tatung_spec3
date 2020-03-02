/*
 * 在 2008/9/24 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;

import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE120Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_1201Qry implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String strIdno= helper.getRequest().getParameter("strIdno").trim();
		String strName= helper.getRequest().getParameter("strName").trim();
		Vector qryHbvPersonVResultV=new Vector();
		HE120Model model=new HE120Model();
		String sMessage="";
		int voSize  = 0;
		try {
			qryHbvPersonVResultV=model.qryHbvPerson1V(strName);
			if(qryHbvPersonVResultV.size()==0){
				sMessage="查無此人事檔資料!";
			}
			else{
					sMessage="查詢筆數:"+qryHbvPersonVResultV.size();
					voSize=qryHbvPersonVResultV.size();
			}
		}catch (Exception e) {
			sMessage=e.toString();
			e.printStackTrace();
			voSize=0;
		}
		finally{
			if(DEBUG) System.out.println("qryHbvPersonVResultV.size->"+qryHbvPersonVResultV.size());
			helper.getRequest().setAttribute("strIdno",strIdno);
			helper.getRequest().setAttribute("strName",strName);
			helper.getRequest().setAttribute("hbvPersonVResultV",(qryHbvPersonVResultV.size()==0?null:qryHbvPersonVResultV));
			helper.getRequest().setAttribute("VoSize",String.valueOf(voSize));
			helper.getRequest().setAttribute("sMessage",sMessage);	

		}			
	}
}
