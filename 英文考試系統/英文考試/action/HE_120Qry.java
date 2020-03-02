/*
 * 在 2008/9/18 建立
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
import com.tatung.imis.H.HE.data.HE120VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE120Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_120Qry implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String strIdno="";
		String strName="";
		String strDept="";
		String strPass="";
		strIdno=helper.getRequest().getParameter("strIdno").trim();
		if(DEBUG) System.out.println("*strIdno-->"+strIdno);
		HE120Model model=new HE120Model();
		String sMessage="";
		Vector resultV =new Vector();
		String strFlag="disabled";
		String[] strFlagArray = null;
		try {
			HE120VO dataVO = model.qryHbvPerson1VO(strIdno);
			if(dataVO!=null){
				//---------若人事主檔有資料,以人事主檔為主
				strName=dataVO.getHBVPERSON1NAME().trim();
				strDept=dataVO.getHATDEPTFILDEPT().trim();						
			}else{
				throw new HeException("查無此人事主檔資料");
			}
			
			resultV=model.qryHbtTestrecordV(strIdno);
			if(resultV.size()>0){
				sMessage="查詢成功,共"+resultV.size()+"筆";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE120VO vo=(HE120VO)resultV.get(i);
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

			helper.getRequest().setAttribute("flag",strFlag);
			helper.getRequest().setAttribute("sMessage",sMessage);
			helper.getRequest().setAttribute("strIdno",strIdno);
			helper.getRequest().setAttribute("strName",strName);
			helper.getRequest().setAttribute("strDept",strDept);
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strFlagArray",strFlagArray);			
		}		

	}

}
