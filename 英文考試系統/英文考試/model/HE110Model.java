/*
 * �b 2008/9/15 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE110Model {
	//---�d�ߦҸեN�X��
	 public Vector qryHbtTestcodeV(String strTest,String strSubject)throws HeException,SQLException{
	 	Vector dataV=new Vector();
	 	HE110DAO dao=new HE110DAO();
		dataV=dao.qryHbtTestcodeV(strTest,strSubject);
		return dataV;	 	
	 }
	//---check�O�_�w���ҸեN�X��--
	 public boolean checkHbtTestcode(HashMap hMap)throws HeException,SQLException{
		Vector dataV=new Vector();
		HE110DAO dao=new HE110DAO();
		boolean checkBln=dao.checkHbtTestcodeVO(hMap);
		return checkBln;	 	
	 }
	//--�s�W�ҸեN�X��--
	public int addHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.addHbtTestcodeVO(hMap);
		return iResult;		
	}
	//--�ק�ҸեN�X��--
	public int updHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.updHbtTestcodeVO(hMap);
		return iResult;		
	}
	//--�R���ҸեN�X��--
	public int delHbtTestcodeVO(HashMap hMap)throws HeException,SQLException{
		int iResult=0;
		HE110DAO dao=new HE110DAO();
		iResult=dao.delHbtTestcodeVO(hMap);
		return iResult;		
	}
	

}
