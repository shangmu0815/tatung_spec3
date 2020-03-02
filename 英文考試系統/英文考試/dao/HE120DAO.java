/*
 * 在 2008/9/18 建立
 *
 * 若要變更這個產生的檔案的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
package com.tatung.imis.H.HE.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import com.tatung.imis.Common.dao.CommonDAO;
import com.tatung.imis.H.HE.data.HE120VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE120DAO extends CommonDAO{
	private boolean DEBUG = false;
//	----查詢考試代碼檔--
public HE120VO qryHbtTestcodeVO(String strTest,String strSubject){
	this.initalHDBAConnection();
	HE120VO vo=null;
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
				vo=new HE120VO();
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
//	----查詢考試紀錄檔--
public Vector qryHbtTestrecordV(String strIdno){
	Vector dataV=new Vector();
	this.initalHDBAConnection();
	HE120VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT  " +
		"a.H_IDNO AS IDNO " +
		",ISNULL(a.H_TEST,'') AS TEST " +
		",CONVERT(CHAR(8),a.H_DATE,112) AS DATE " +		
		",ISNULL(a.H_SUBJECT1,'') AS SUBJECT1 " +
		",a.H_SCORE1 AS SCORE1 " +
		",ISNULL(a.H_SUBJECT2,'') AS SUBJECT2 " +
		",a.H_SCORE2 AS SCORE2 " +
		",a.H_AVERAGE AS AVERAGE " +
		",ISNULL(a.H_PASS,'') AS PASS " +
		",ISNULL(a.H_TEXT,'') AS TEXT " +	
		"FROM badm.dbo.HBT_TESTRECORD a,badm.dbo.HBV_PERSON_1 b ");
		sql1.append("WHERE a.H_IDNO=b.H_IDNO ");		
		sql1.append("AND a.H_IDNO='"+strIdno+"' ");
		sql1.append("ORDER BY a.H_DATE DESC,b.H_DEPTNO ASC,b.H_IDNO ASC  ");		
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		while(rs.next()){
				vo=new HE120VO();
				vo.setH_IDNO(rs.getString("IDNO"));
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

//	----查詢人事主檔--
public HE120VO qryHbvPerson1VO(String strIdno){
	this.initalHDBAConnection();
	HE120VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT  " +
		"ISNULL(a.H_IDNO,'') AS IDNO " +
		",ISNULL(a.H_NAME,'') AS NAME " +
		",ISNULL(a.H_DEPTNO,'') AS DEPTNO " +
		",DEPT=ISNULL((SELECT H_DEPT FROM badm.dbo.HAT_DEPTFIL WHERE H_DEPTNO=a.H_DEPTNO),'') " +
		"FROM badm.dbo.HBV_PERSON_1 a ");
		sql1.append("WHERE a.H_IDNO='"+strIdno+"' ");			
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		if(rs.next()){
			vo=new HE120VO();
			vo.setH_IDNO(rs.getString("IDNO")==null?"":this.replaceTheBlank(rs.getString("IDNO"),"　",""));
			vo.setHBVPERSON1NAME(rs.getString("NAME")==null?"":this.replaceTheBlank(rs.getString("NAME"),"　",""));
			vo.setHBVPERSON1DEPTNO(rs.getString("DEPTNO")==null?"":this.replaceTheBlank(rs.getString("DEPTNO"),"　",""));
			vo.setHATDEPTFILDEPT(rs.getString("DEPT")==null?"":this.replaceTheBlank(rs.getString("DEPT"),"　",""));
		}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢人事主檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return vo;
}

//-----check是否已經有考試紀錄檔--
public boolean checkHbtTestrecordVO(HashMap hMapHbtTestcodeData){
	String strIdno=(String)hMapHbtTestcodeData.get("strIdno");
	String strDate=(String)hMapHbtTestcodeData.get("strDate");
	boolean checkBln=false;
	try {
	this.initalHDBAConnection();
	String sql="SELECT * " +
					 "FROM badm.dbo.HBT_TESTRECORD "+
					 "WHERE H_IDNO=? "+
					 "AND H_DATE=? ";	
		pstmt=conn.prepareStatement(sql.toString());
		pstmt.setString(1,strIdno);
		pstmt.setString(2,strDate);
		rs=pstmt.executeQuery();
		checkBln=rs.next();
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("check是否已經有考試紀錄檔作業失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();		
	}
	return checkBln;
}



//-----新增考試紀錄檔--
public int addHbtTestrecordVO(HashMap hMapHbtTestrecordData){
	String strIdno=(String)hMapHbtTestrecordData.get("strIdno");
	String strTest=(String)hMapHbtTestrecordData.get("strTest");
	String strDate=(String)hMapHbtTestrecordData.get("strDate");		
	String strSubject1=(String)hMapHbtTestrecordData.get("strSubject1");
	String strScore1=(String)hMapHbtTestrecordData.get("strScore1");
	String strSubject2=(String)hMapHbtTestrecordData.get("strSubject2");
	String strScore2=(String)hMapHbtTestrecordData.get("strScore2");
	String strAverage=(String)hMapHbtTestrecordData.get("strAverage");
	String strText=(String)hMapHbtTestrecordData.get("strText");
	String strPass=(String)hMapHbtTestrecordData.get("strPass");
	String strTcop=(String)hMapHbtTestrecordData.get("strTcop");
	String strTdop=(String)hMapHbtTestrecordData.get("strTdop");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("INSERT badm.dbo.HBT_TESTRECORD ");
	sql1.append("(H_IDNO,H_DATE,H_TEST,H_SUBJECT1,H_SCORE1,H_SUBJECT2,H_SCORE2,H_AVERAGE,H_PASS,H_TEXT,H_COP,H_DOP) ");
	sql1.append("VALUES ('"+strIdno+"','"+strDate+"','"+strTest+"','"+strSubject1+"','"+strScore1+"','"+strSubject2+"','"+strScore2+"','"+strAverage+"','"+strPass+"','"+strText+"','"+strTcop+"','"+strTdop+"') ");
	stmt=conn.createStatement();
	if(DEBUG) System.out.println("sql1.toString()->"+sql1.toString());
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("新增考試紀錄檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----修改考試紀錄檔--
public int updHbtTestrecordVO(HashMap hMapHbtTestrecordData){
	String strIdno=(String)hMapHbtTestrecordData.get("strIdno");
	String strTest=(String)hMapHbtTestrecordData.get("strTest");
	String strDate=(String)hMapHbtTestrecordData.get("strDate");
	String strSubject1=(String)hMapHbtTestrecordData.get("strSubject1");
	String strScore1=(String)hMapHbtTestrecordData.get("strScore1");
	String strSubject2=(String)hMapHbtTestrecordData.get("strSubject2");
	String strScore2=(String)hMapHbtTestrecordData.get("strScore2");
	String strAverage=(String)hMapHbtTestrecordData.get("strAverage");
	String strText=(String)hMapHbtTestrecordData.get("strText");
	String strPass=(String)hMapHbtTestrecordData.get("strPass");
	String strTcop=(String)hMapHbtTestrecordData.get("strTcop");
	String strTdop=(String)hMapHbtTestrecordData.get("strTdop");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("UPDATE badm.dbo.HBT_TESTRECORD ");
	sql1.append("SET H_TEST='"+strTest+"',H_SUBJECT1='"+strSubject1+"',H_SCORE1='"+strScore1+"',H_SUBJECT2='"+strSubject2+"',H_SCORE2='"+strScore2+"',H_AVERAGE='"+strAverage+"',H_PASS='"+strPass+"',H_TEXT='"+strText+"',H_COP='"+strTcop+"',H_DOP='"+strTdop+"' ");
	sql1.append("WHERE H_IDNO='"+strIdno+"' AND H_DATE='"+strDate+"' ");
	stmt=conn.createStatement();
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("修改考試紀錄檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----刪除考試紀錄檔--
public int delHbtTestrecordVO(HashMap hMapHbtTestcodeData){	
	String strIdno=(String)hMapHbtTestcodeData.get("strIdno");
	String strDate=(String)hMapHbtTestcodeData.get("strDate");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("DELETE badm.dbo.HBT_TESTRECORD ");
	sql1.append("WHERE H_IDNO='"+strIdno+"' AND H_DATE='"+strDate+"' ");
	stmt=conn.createStatement();
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("刪除考試紀錄檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
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

//	----查詢人事主檔2--
public Vector qryHbvPerson1V(String strName){
	this.initalHDBAConnection();
	HE120VO vo=null;
	Vector qryHbvPerson1V=new Vector();
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT  " +
		"ISNULL(a.H_IDNO,'') AS IDNO " +
		",ISNULL(a.H_NAME,'') AS NAME " +
		",ISNULL(a.H_DEPTNO,'') AS DEPTNO " +
		",DEPT=ISNULL((SELECT H_DEPT FROM badm.dbo.HAT_DEPTFIL WHERE H_DEPTNO=a.H_DEPTNO),'') " +
		"FROM badm.dbo.HBV_PERSON_1 a ");
		sql1.append("WHERE a.H_NAME='"+strName+"' ");			
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		while(rs.next()){
			vo=new HE120VO();
			vo.setH_IDNO(rs.getString("IDNO")==null?"":this.replaceTheBlank(rs.getString("IDNO"),"　",""));
			vo.setHBVPERSON1NAME(rs.getString("NAME")==null?"":this.replaceTheBlank(rs.getString("NAME"),"　",""));
			vo.setHBVPERSON1DEPTNO(rs.getString("DEPTNO")==null?"":this.replaceTheBlank(rs.getString("DEPTNO"),"　",""));
			vo.setHATDEPTFILDEPT(rs.getString("DEPT")==null?"":this.replaceTheBlank(rs.getString("DEPT"),"　",""));
			qryHbvPerson1V.add(vo);
		}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢人事主檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return qryHbvPerson1V;
}  	
}
