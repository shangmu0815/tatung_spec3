/*
 * 在 2008/9/23 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.tatung.imis.Common.dao.CommonDAO;
import com.tatung.imis.H.HE.data.HE140VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE140DAO extends CommonDAO{
	private boolean DEBUG = false;
//	----查詢考試代碼檔--
public HE140VO qryHbtTestcodeVO(String strTest,String strSubject){
	this.initalHDBAConnection();
	HE140VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT  " +
		"ISNULL(H_TEST,'') AS TEST " +
		",ISNULL(H_TESTNAME,'') AS TESTNAME " +
		",ISNULL(H_SUBJECT,'') AS SUBJECT " +
		",ISNULL(H_SUBJNAME,'') AS SUBJNAME " +
		"FROM badm.dbo.HBT_TESTCODE ");
		sql1.append("WHERE H_TEST='"+strTest+"' AND H_SUBJECT='"+strSubject+"' ");			
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		if(rs.next()){
				vo=new HE140VO();
				vo.setTESTCODETEST(rs.getString("TEST"));
				vo.setTESTCODETESTNAME(rs.getString("TESTNAME"));
				vo.setTESTCODESUBJECT(rs.getString("SUBJECT"));
				vo.setTESTCODESUBJNAME(rs.getString("SUBJNAME"));
			}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢考試代碼檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return vo;
}	
	//	----查詢單位資料檔--
public Vector qryHatDeptfilV(){
	Vector dataV=new Vector();
	this.initalHDBAConnection();
	HE140VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT " +
		"DISTINCT " +
		"ISNULL(H_CTRNO,'') AS CTRNO " +
		",ISNULL(H_CTR,'') AS CTR " +
		"FROM badm.dbo.HAT_DEPTFIL  ");
		sql1.append("ORDER BY CTRNO ASC ");	
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		while(rs.next()){
			vo=new HE140VO();
			if(rs.getString("CTRNO").trim().equals(""))
			continue;
			vo.setH_CTRNO(rs.getString("CTRNO")==null?"":this.replaceTheBlank(rs.getString("CTRNO"),"　",""));
			vo.setH_CTR(rs.getString("CTR")==null?"":this.replaceTheBlank(rs.getString("CTR"),"　",""));
			dataV.add(vo);
			}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢單位資料檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return dataV;
}

//	----查詢考試紀錄檔--
public Vector qryHbtTestrecordByScoreV(String strScore1,String strScore2,String strCtrno,String strType){

	Vector dataV=new Vector();
	this.initalHDBAConnection();
	HE140VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT " +
		"a.H_IDNO AS IDNO "+
		",b.H_NAME AS NAME "+
		",DEPT=ISNULL((SELECT H_DEPT FROM badm.dbo.HAT_DEPTFIL WHERE H_DEPTNO=b.H_DEPTNO),'') " +
		",ISNULL(a.H_TEST,'') AS TEST " +
		",ISNULL(a.H_SUBJECT1,'') AS SUBJECT1 " +		
		",CONVERT(CHAR(8),a.H_DATE,112) AS DATE " +		
		",a.H_SCORE1 AS SCORE1 " +
		",ISNULL(a.H_SUBJECT2,'') AS SUBJECT2 " +
		",a.H_SCORE2 AS SCORE2 " +
		",a.H_AVERAGE AS AVERAGE " +
		",ISNULL(a.H_PASS,'') AS PASS " +
		",ISNULL(a.H_TEXT,'') AS TEXT " +	
		"FROM badm.dbo.HBT_TESTRECORD a,badm.dbo.HBV_PERSON_1 b ");
		sql1.append("WHERE a.H_IDNO=b.H_IDNO ");
		//---若只查及格的員工,而不輸入分數,則不考量平均分數
		if(strScore1.equals("")||strScore2.equals("")){
			sql1.append("");
		}else{
			sql1.append("AND (a.H_AVERAGE BETWEEN "+strScore1+" AND "+strScore2+" ) ");
		}
		//---若只查某單位員工,則加入中心代號限制	
		if(strCtrno.trim().equals("")){
			sql1.append("");					
		}else{
			sql1.append("AND b.H_CTRNO='"+strCtrno+"' ");
		}		
		//---若及格別選未及格,則只撈及格別=0者
		//---若及格別選已及格,則只撈及格別=1者		
		//---若未選,則及格別不考慮
		if(strType.equals("0")){
			sql1.append("AND a.H_PASS='0' ");
		}else if(strType.equals("1")){
			sql1.append("AND a.H_PASS='1' ");
		}else if(strType.equals("2")){
			sql1.append("");
		}
		sql1.append("ORDER BY a.H_DATE DESC,b.H_DEPTNO ASC,b.H_IDNO ASC  ");	
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		while(rs.next()){
				vo=new HE140VO();
				vo.setH_IDNO(rs.getString("IDNO"));
				vo.setHBVPERSON1NAME(rs.getString("NAME"));
				vo.setHATDEPTFILDEPT(rs.getString("DEPT"));	
				vo.setH_TEST(rs.getString("TEST"));
				vo.setH_DATE(rs.getString("DATE"));
				vo.setH_SUBJECT1(rs.getString("SUBJECT1"));
				vo.setH_SCORE1(rs.getString("SCORE1"));
				vo.setH_SUBJECT2(rs.getString("SUBJECT2"));
				vo.setH_SCORE2(rs.getString("SCORE2"));
				vo.setH_AVERAGE(rs.getString("AVERAGE"));
				vo.setH_PASS(rs.getString("PASS"));
				vo.setH_TEXT(rs.getString("TEXT"));
				dataV.add(vo);
			}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢考試紀錄檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return dataV;
}
//-------將欄位中之部分data以部分資料取代之function--replaceTheBlank(原始資料欄位,欲取代之資料 ex.全形空白,取代成之資料 ex.空字串)--------------------------
public String replaceTheBlank(String strFieldValue,String strBeforeReplace,String strReplacementName){
	int replaceStrting=-1;
	do{
		replaceStrting=strFieldValue.indexOf(strBeforeReplace);
		if(replaceStrting!=-1)
		strFieldValue=strFieldValue.substring(0,replaceStrting)+strReplacementName+strFieldValue.substring(replaceStrting+1);
	}while(replaceStrting!=-1);
  return strFieldValue;
}
}
