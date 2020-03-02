/*
 * 在 2008/9/8 建立
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
import com.tatung.imis.H.HE.model.HE110Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_110Qry implements Command {
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String Test=helper.getRequest().getParameter("test").trim();
		String Subject=helper.getRequest().getParameter("subject").trim();
		HE110Model model=new HE110Model();
		String sMessage="";
		Vector resultV =new Vector();
		String strFlag="disabled";
		try {
			resultV=model.qryHbtTestcodeV(Test,Subject);
			if(resultV.size()>0){
				sMessage="查詢成功,共"+resultV.size()+"筆";
			}else{
				sMessage="查無考試代碼檔資料!";
			}
		} catch (Exception e) {
			// TODO 自動產生 catch 區塊
			e.printStackTrace();
			sMessage=e.toString();
		}finally{
			helper.getRequest().setAttribute("flag",strFlag);
			helper.getRequest().setAttribute("sMessage",sMessage);
			helper.getRequest().setAttribute("test",Test);
			helper.getRequest().setAttribute("subject",Subject);
			helper.getRequest().setAttribute("resultV",resultV);
		}		

	}

}
