/*
 * 在 2008/9/23 建立
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
import com.tatung.imis.H.HE.data.HE140VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE140Model;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE_140Qry implements Command {
	private boolean DEBUG = false;
	public void execute(RequestHelper helper) throws ServletException, IOException {
		String strScore1="";
		String strScore2="";
		String strCtrno="";
		String strType="";
		strScore1=helper.getRequest().getParameter("strScore1").trim();
		strScore2=helper.getRequest().getParameter("strScore2").trim();
		strCtrno=helper.getRequest().getParameter("strCtrno");
		strType=helper.getRequest().getParameter("strType").trim();		
		TreeMap tMap=new TreeMap();
		HE140Model model=new HE140Model();
		String sMessage="";
		Vector resultV =new Vector();
		String strFlag="disabled";
		String[] strFlagArray = null;
		try {
			if(strScore1!=null){
				if(!strScore1.trim().equals("")){
					//-----check平均分數範圍區間資料是否為數字----
					boolean integerCondition1=model.isInteger(strScore1);
					if(integerCondition1==false){
						throw new HeException("平均分數範圍區間1輸入資料有誤!");
					}
				}
			}
			if(strScore2!=null){
				if(!strScore2.trim().equals("")){
					//-----check平均分數範圍區間輸入資料是否為數字----
					boolean integerCondition2=model.isInteger(strScore2);
					if(integerCondition2==false){
						throw new HeException("平均分數範圍區間2輸入資料有誤!");
					}	
				}
			}
			resultV=model.qryHbtTestrecordByScoreV(strScore1,strScore2,strCtrno,strType);
			if(DEBUG) System.out.println("resultV SIZE->"+resultV.size());
			if(resultV.size()>0){
				sMessage="查詢成功,共"+resultV.size()+"筆";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE140VO vo=(HE140VO)resultV.get(i);
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
			helper.getRequest().setAttribute("strScore1",strScore1);
			helper.getRequest().setAttribute("strScore2",strScore2);
			helper.getRequest().setAttribute("strCtrno",strCtrno);
			helper.getRequest().setAttribute("strType",strType);
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strFlagArray",strFlagArray);		
		}		

	}

}
