/*
 * �b 2008/9/22 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
package com.tatung.imis.H.HE.model;

import java.sql.SQLException;
import java.util.TreeMap;
import java.util.Vector;

import com.tatung.imis.H.HE.dao.HE130DAO;
import com.tatung.imis.H.HE.data.HE130VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE130Model {
//	(HE_130)-�d�ߤ��ߥN�X,���ߦW��
	public TreeMap hatDeptfilQry()throws Exception{
		HE130DAO dao=new HE130DAO();	
		Vector dataV=new Vector();
		TreeMap tmpHatDeptfil=new TreeMap();
			dataV=dao.qryHatDeptfilV();			//	�H¾��N�X�d��¾������N�X��,�^�Ǥ��ߥN�X,���ߦW��			
			for(int i=0;i<dataV.size();i++){
				HE130VO vo=(HE130VO)dataV.get(i);
//				System.out.println("vo deptno->"+vo.getHATDEPTFILDEPTNO());
//				System.out.println("vo dept->"+vo.getHATDEPTFILDEPT());
				tmpHatDeptfil.put(vo.getH_CTRNO(),vo.getH_CTR());
			}
		  return tmpHatDeptfil;
}
//---�d�ߦҸեN�X��
 public HE130VO qryHbtTestcodeVO(String strTest,String strSubject)throws Exception{
	HE130DAO dao=new HE130DAO();
	HE130VO dataVO=dao.qryHbtTestcodeVO(strTest,strSubject);
	return dataVO;	 	
 }
//---�d�ߦҸլ�����
 public Vector qryHbtTestrecordByTimeV(String strDate1,String strDate2,String strCtrno,String strType)throws Exception{
	Vector dataV=new Vector();
	HE130DAO dao=new HE130DAO();
	dataV=dao.qryHbtTestrecordByTimeV(strDate1,strDate2,strCtrno,strType);
//	System.out.println("*****dataV size->"+dataV);
	if(dataV.size()>0){
		for(int i=0;i<dataV.size();i++){
			String strTestName1="";
			String strTestName2="";
			String strSubjName1="";
			String strSubjName2="";
			HE130VO vo=(HE130VO)dataV.get(i);
			HE130VO dataVO1=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT1());
			if(dataVO1!=null){
				strTestName1=dataVO1.getTESTCODETESTNAME();
				strSubjName1=dataVO1.getTESTCODESUBJNAME();
			}
			HE130VO dataVO2=qryHbtTestcodeVO(vo.getH_TEST(),vo.getH_SUBJECT2());
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
}
