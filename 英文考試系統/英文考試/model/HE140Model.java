/*
 * �b 2008/9/23 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.model;

import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Vector;

import com.tatung.imis.H.HE.dao.HE140DAO;
import com.tatung.imis.H.HE.data.HE140VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE140Model {
	//--�d���N��,���W��
	public TreeMap hatDeptfilQry()throws HeException, SQLException{
		HE140DAO dao=new HE140DAO();	
		Vector dataV=new Vector();
		TreeMap tmpHatDeptfil=new TreeMap();
			dataV=dao.qryHatDeptfilV();			//	�H¾��N�X�d��¾������N�X��,�^�Ǥ��ߥN�X,���ߦW��
			for(int i=0;i<dataV.size();i++){
				HE140VO vo=(HE140VO)dataV.get(i);
//				System.out.println("vo deptno->"+vo.getHATDEPTFILDEPTNO());
//				System.out.println("vo dept->"+vo.getHATDEPTFILDEPT());
				tmpHatDeptfil.put(vo.getH_CTRNO(),vo.getH_CTR());
			}
		  return tmpHatDeptfil;
}
//---�d�ߦҸեN�X��
public HE140VO qryHbtTestcodeVO(String strTest,String strSubject)throws HeException,SQLException{
	HE140DAO dao=new HE140DAO();
	HE140VO dataVO=dao.qryHbtTestcodeVO(strTest,strSubject);
   return dataVO;	 	
}
//---�d�ߦҸլ�����
public Vector qryHbtTestrecordByScoreV(String strScore1,String strScore2,String strCtrno,String strType)throws HeException,SQLException{
   Vector dataV=new Vector();
   HE140DAO dao=new HE140DAO();
   dataV=dao.qryHbtTestrecordByScoreV(strScore1,strScore2,strCtrno,strType);
   if(dataV.size()>0){
	   for(int i=0;i<dataV.size();i++){
		   String strTestName1="";
		   String strTestName2="";
		   String strSubjName1="";
		   String strSubjName2="";
			HE140VO vo=(HE140VO)dataV.get(i);
			HE140VO dataVO1=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT1());
		   if(dataVO1!=null){
			   strTestName1=dataVO1.getTESTCODETESTNAME();
			   strSubjName1=dataVO1.getTESTCODESUBJNAME();
		   }
			HE140VO dataVO2=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT2());
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
   }
   return dataV;	 	
}
 //----check �O�_���Ʀr
public boolean isInteger(String aNumber){
	boolean bIsInteger = true;
	try{
		Integer.parseInt(aNumber);
	}catch(Exception e){
		bIsInteger = false;
	}
	return bIsInteger;
}
}
