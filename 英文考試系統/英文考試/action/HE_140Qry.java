/*
 * �b 2008/9/23 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
					//-----check�������ƽd��϶���ƬO�_���Ʀr----
					boolean integerCondition1=model.isInteger(strScore1);
					if(integerCondition1==false){
						throw new HeException("�������ƽd��϶�1��J��Ʀ��~!");
					}
				}
			}
			if(strScore2!=null){
				if(!strScore2.trim().equals("")){
					//-----check�������ƽd��϶���J��ƬO�_���Ʀr----
					boolean integerCondition2=model.isInteger(strScore2);
					if(integerCondition2==false){
						throw new HeException("�������ƽd��϶�2��J��Ʀ��~!");
					}	
				}
			}
			resultV=model.qryHbtTestrecordByScoreV(strScore1,strScore2,strCtrno,strType);
			if(DEBUG) System.out.println("resultV SIZE->"+resultV.size());
			if(resultV.size()>0){
				sMessage="�d�ߦ��\,�@"+resultV.size()+"��";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE140VO vo=(HE140VO)resultV.get(i);
						if(vo.getH_PASS().equals("1")){
							strFlagArray[i]="checked";
						}							
				}				
			}else{
				sMessage="�d�L�Ҹլ����ɸ��!";
			}
		} catch (Exception e) {
			// TODO �۰ʲ��� catch �϶�
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
