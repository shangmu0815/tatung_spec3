/*
 * �b 2008/11/10 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;

import javax.servlet.ServletException;

import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE_150Qry implements Command {
	public void execute(RequestHelper helper)	throws ServletException, IOException {
		String strF1=helper.getRequest().getParameter("F1");
		String strF2=helper.getRequest().getParameter("F2");
		String strF3="ddd";
		System.out.println("1*strF1->"+strF1);
		System.out.println("2*strF2->"+strF2);
		System.out.println("3*strF3->"+strF3);
		//  ....
		helper.getRequest().setAttribute("stF1",strF1);
		helper.getRequest().setAttribute("stF2",strF2);	

	}

}
