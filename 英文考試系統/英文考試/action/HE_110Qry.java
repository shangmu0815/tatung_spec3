/*
 * �b 2008/9/8 �إ�
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
import com.tatung.imis.H.HE.exception.HeException;
import com.tatung.imis.H.HE.model.HE110Model;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
				sMessage="�d�ߦ��\,�@"+resultV.size()+"��";
			}else{
				sMessage="�d�L�ҸեN�X�ɸ��!";
			}
		} catch (Exception e) {
			// TODO �۰ʲ��� catch �϶�
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
