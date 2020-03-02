/*
 * 在 2008/11/10 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.action;

import java.io.IOException;

import javax.servlet.ServletException;

import com.tatung.imis.Common.controller.Command;
import com.tatung.imis.Common.controller.RequestHelper;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
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
