/*
 * 在 2008/9/15 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import com.tatung.imis.H.HE.dao.HE110DAO;
import com.tatung.imis.H.HE.exception.HeException;


/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE110Model {
	//---查詢考試代碼檔
	 public Vector qryHbtTestcodeV(String strTest,String strSubject)throws HeException,SQLException{
	 	Vector dataV=new Vector();
	 	HE110DAO dao=new HE110DAO();
		dataV=dao.qryHbtTestcodeV(strTest,strSubject);
		return dataV;	 	
	 }
	//---check是否已有考試代碼檔--
	 public boolean checkHbtTestcode(HashMap hMap)throws HeException,SQLException{
		Vector dataV=new Vector();
		HE110DAO dao=new HE110DAO();
		boolean checkBln=dao.checkHbtTestcodeVO(hMap);
		return checkBln;	 	
	 }
	//--新增考試代碼檔--
	public int addHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.addHbtTestcodeVO(hMap);
		return iResult;		
	}
	//--修改考試代碼檔--
	public int updHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.updHbtTestcodeVO(hMap);
		return iResult;		
	}
	//--刪除考試代碼檔--
	public int delHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.delHbtTestcodeVO(hMap);
		return iResult;		
	}
	

}
