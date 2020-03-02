/*
 * �b 2008/9/18 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
				//---------�Y�H�ƥD�ɦ����,�H�H�ƥD�ɬ��D
				strName=dataVO.getHBVPERSON1NAME().trim();
				strDept=dataVO.getHATDEPTFILDEPT().trim();						
			}else{
				throw new HeException("�d�L���H�ƥD�ɸ��");
			}
			
			resultV=model.qryHbtTestrecordV(strIdno);
			if(resultV.size()>0){
				sMessage="�d�ߦ��\,�@"+resultV.size()+"��";

				strFlagArray=new String[resultV.size()];
				for(int i=0;i<resultV.size();i++){
						HE120VO vo=(HE120VO)resultV.get(i);
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
