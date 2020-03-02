/*
 * �b 2008/9/18 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import com.tatung.imis.H.HE.dao.HE120DAO;
import com.tatung.imis.H.HE.data.HE120VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE120Model {
	//---�d�ߦҸեN�X��
	 public HE120VO qryHbtTestcodeVO(String strTest,String strSubject)throws Exception{
		HE120DAO dao=new HE120DAO();
		HE120VO dataVO=dao.qryHbtTestcodeVO(strTest,strSubject);
		return dataVO;	 	
	 }
	//---�d�ߦҸլ�����
	 public Vector qryHbtTestrecordV(String strIdno)throws Exception{
		Vector dataV=new Vector();
		HE120DAO dao=new HE120DAO();
		dataV=dao.qryHbtTestrecordV(strIdno);
		for(int i=0;i<dataV.size();i++){
			String strTestName1="";
			String strTestName2="";
			String strSubjName1="";
			String strSubjName2="";
			HE120VO vo=(HE120VO)dataV.get(i);
			HE120VO dataVO1=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT1());
			if(dataVO1!=null){
				strTestName1=dataVO1.getTESTCODETESTNAME();
				strSubjName1=dataVO1.getTESTCODESUBJNAME();
			}
			HE120VO dataVO2=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT2());
			if(dataVO2!=null){
				strTestName2=dataVO2.getTESTCODETESTNAME();
				strSubjName2=dataVO2.getTESTCODESUBJNAME();
			}
			if(!strTestName1.equals("")&&strTestName2.equals("")){
				vo.setH_TESTNAME(strTestName1);
			}else if(strTestName1.equals("")&&!strTestName2.equals("")){
				vo.setH_TESTNAME(strTestName2);
			}else{
				vo.setH_TESTNAME(strTestName1);
			}
			vo.setH_SUBJNAME1(strSubjName1);
			vo.setH_SUBJNAME2(strSubjName2);
		}
		return dataV;	 	
	 }
	//---�d�ߤH�ƥD��
	 public HE120VO qryHbvPerson1VO(String strIdno)throws Exception{
		Vector dataV=new Vector();
		HE120DAO dao=new HE120DAO();
		HE120VO dataVO=dao.qryHbvPerson1VO(strIdno);
		return dataVO;	 	
	 }
	//---check�O�_�w���Ҹլ�����--
	 public boolean checkHbtTestrecord(HashMap hMap)throws Exception{
		Vector dataV=new Vector();
		HE120DAO dao=new HE120DAO();
		boolean checkBln=dao.checkHbtTestrecordVO(hMap);
		return checkBln;	 	
	 }
	//--�s�W�Ҹլ�����---
	public int addHbtTestrecordVO(HashMap hMap)throws Exception{
		int iResult=0;
		HE120DAO dao=new HE120DAO();
		iResult=dao.addHbtTestrecordVO(hMap);
		return iResult;		
	}
	//--�ק�Ҹլ�����--
	public int updHbtTestrecordVO(HashMap hMap)throws Exception{
		int iResult=0;
		HE120DAO dao=new HE120DAO();
		iResult=dao.updHbtTestrecordVO(hMap);
		return iResult;		
	}
	//--�R���Ҹլ�����--
	public int delHbtTestrecordVO(HashMap hMap)throws Exception{
		int iResult=0;
		HE120DAO dao=new HE120DAO();
		iResult=dao.delHbtTestrecordVO(hMap);
		return iResult;		
	}
	//---�d�ߤH�ƥD��2
	 public Vector qryHbvPerson1V(String strName)throws Exception{
		Vector dataV=new Vector();
		HE120DAO dao=new HE120DAO();
		dataV=dao.qryHbvPerson1V(strName);
		return dataV;	 	
	 }
	 //----check �O�_���Ʀr
	public static boolean isInteger(String aNumber){
		boolean bIsInteger = true;
		try{
			Integer.parseInt(aNumber);
		}catch(Exception e){
			bIsInteger = false;
		}
		return bIsInteger;
	}
}
