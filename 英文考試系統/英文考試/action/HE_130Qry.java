/*
 * �b 2008/9/22 �إ�
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
import com.tatung.imis.H.HE.data.HE130VO;
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE130Model;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
				sMessage="�d�ߦ��\,�@"+resultV.size()+"��";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE130VO vo=(HE130VO)resultV.get(i);
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
			helper.getRequest().setAttribute("strDate1",strDate1);
			helper.getRequest().setAttribute("strDate2",strDate2);
			helper.getRequest().setAttribute("strCtrno",strCtrno);
			helper.getRequest().setAttribute("strType",strType);
			helper.getRequest().setAttribute("resultV",resultV);
			helper.getRequest().setAttribute("strFlagArray",strFlagArray);	
			
		}		

	}

}
