/*
 * 在 2008/9/22 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletException;

import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;
import com.tatung.imis.H.HE.data.HE130VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE130Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_130Qry implements Command {
	public void execute(RequestHelper helper) throws ServletException, IOException {
		String strDate1="";
		String strDate2="";
		String strCtrno="";
		String strType="";
		strDate1=helper.getRequest().getParameter("strDate1").trim();
		strDate2=helper.getRequest().getParameter("strDate2").trim();
		strCtrno=helper.getRequest().getParameter("strCtrno");
		strType=helper.getRequest().getParameter("strType").trim();
		TreeMap tMap=new TreeMap();

		HE130Model model=new HE130Model();
		String sMessage="";
		Vector resultV =new Vector();
		String strFlag="disabled";
		String[] strFlagArray = null;
		try {
			resultV=model.qryHbtTestrecordByTimeV(strDate1,strDate2,strCtrno,strType);
			if(resultV.size()>0){
				sMessage="查詢成功,共"+resultV.size()+"筆";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE130VO vo=(HE130VO)resultV.get(i);
						if(vo.getH_PASS().equals("1")){
							strFlagArray[i]="checked";
						}							
				}				
			}else{
				sMessage="查無考試紀錄檔資料!";
			}
		} catch (Exception e) {
			// TODO 自動產生 catch 區塊
			e.printStackTrace();
			sMessage=e.toString();
		}finally{
			helper.getRequest().setAttribute("sMessage",sMessage);
			helper.getRequest().setAttribute("strDate1",strDate1);
			helper.getRequest().setAttribute("strDate2",strDate2);
			helper.getRequest().setAttribute("strCtrno",strCtrno);
			helper.getRequest().setAttribute("strType",strType);
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strFlagArray",strFlagArray);	
			
		}		

	}

}
