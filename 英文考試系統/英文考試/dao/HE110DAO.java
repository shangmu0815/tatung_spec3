/*
 * 在 2008/9/16 建立
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
import com.tatung.imis.H.HE.data.HE110VO;
import com.tatung.imis.H.HE.exception.HeException;

/**
 * @author Administrator
 *
 * 若要變更這個產生的類別註解的範本，請移至
 * 視窗 > 喜好設定 > Java > 程式碼產生 > 程式碼和註解
 */
public class HE110DAO extends CommonDAO{
	private boolean DEBUG = false;
//	----查詢考試代碼檔--
public Vector qryHbtTestcodeV(String strTest,String strSubject){
	Vector dataV=new Vector();
	this.initalHDBAConnection();
	HE110VO vo=null;
	try{
		Statement stmt=null;
		StringBuffer sql1=new StringBuffer(
		"SELECT  " +
		"H_TEST AS TEST " +
		",ISNULL(H_TESTNAME,'') AS TESTNAME " +
		",H_SUBJECT AS SUBJECT " +
		",ISNULL(H_SUBJNAME,'') AS SUBJNAME "+
		"FROM badm.dbo.HBT_TESTCODE ");
		 if(strTest.trim().equals("")){
			sql1.append("");
		 }else if(!strTest.trim().equals("")&&strSubject.trim().equals("")){
			sql1.append("WHERE H_TEST>='"+strTest+"' ");			
		 }else if(!strTest.trim().equals("")&&!strSubject.trim().equals("")){
			sql1.append("WHERE H_TEST='"+strTest+"' AND H_SUBJECT='"+strSubject+"' ");
		 } 	
		sql1.append("ORDER BY H_TEST ");	
		stmt=conn.createStatement();
		if(DEBUG) System.out.println("sql1->"+sql1);
		rs=stmt.executeQuery(sql1.toString());		
		while(rs.next()){
				vo=new HE110VO();
				vo.setH_TEST(rs.getString("TEST"));
				vo.setH_TESTNAME(rs.getString("TESTNAME"));
				vo.setH_SUBJECT(rs.getString("SUBJECT"));
				vo.setH_SUBJNAME(rs.getString("SUBJNAME"));
				dataV.add(vo);
			}
	}catch (Exception e){
		// TODO 自動產生 catch 區塊
		e.printStackTrace();
		throw new HeException("查詢考試代碼檔作業失敗->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return dataV;
}
//-----check是否已經有考試代碼檔--
public boolean checkHbtTestcodeVO(HashMap hMapHbtTestcodeData){
	String strTest=(String)hMapHbtTestcodeData.get("strTest");
	String strSubject=(String)hMapHbtTestcodeData.get("strSubject");
	boolean checkBln=false;
	try {
	this.initalHDBAConnection();
	String sql="SELECT * " +					 "FROM badm.dbo.HBT_TESTCODE "+
					 "WHERE H_TEST=? "+
					 "AND H_SUBJECT=? ";	
		pstmt=conn.prepareStatement(sql.toString());
		pstmt.setString(1,strTest);
		pstmt.setString(2,strSubject);
		rs=pstmt.executeQuery();
		checkBln=rs.next();
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("check是否已經有考試代碼檔作業失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();		
	}
	return checkBln;
}

//-----新增考試代碼檔--
public int addHbtTestcodeVO(HashMap hMapHbtTestcodeData){	
	String strTest=(String)hMapHbtTestcodeData.get("strTest");
	String strTestname=(String)hMapHbtTestcodeData.get("strTestname");
	String strSubject=(String)hMapHbtTestcodeData.get("strSubject");
	String strSubjname=(String)hMapHbtTestcodeData.get("strSubjname");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("INSERT badm.dbo.HBT_TESTCODE ");
	sql1.append("(H_TEST,H_TESTNAME,H_SUBJECT,H_SUBJNAME)");
	sql1.append("VALUES ('"+strTest+"','"+strTestname+"','"+strSubject+"','"+strSubjname+"') ");
	stmt=conn.createStatement();
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("新增考試代碼檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----修改考試代碼檔--
public int updHbtTestcodeVO(HashMap hMapHbtTestcodeData){	
	String strTest=(String)hMapHbtTestcodeData.get("strTest");
	String strTestname=(String)hMapHbtTestcodeData.get("strTestname");
	String strSubject=(String)hMapHbtTestcodeData.get("strSubject");
	String strSubjname=(String)hMapHbtTestcodeData.get("strSubjname");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("UPDATE badm.dbo.HBT_TESTCODE ");
	sql1.append("SET H_TESTNAME='"+strTestname+"',H_SUBJNAME='"+strSubjname+"' ");
	sql1.append("WHERE H_TEST='"+strTest+"' AND H_SUBJECT='"+strSubject+"' ");
	stmt=conn.createStatement();
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("修改考試代碼檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----刪除考試代碼檔--
public int delHbtTestcodeVO(HashMap hMapHbtTestcodeData){	
	String strTest=(String)hMapHbtTestcodeData.get("strTest");
	String strTestname=(String)hMapHbtTestcodeData.get("strTestname");
	String strSubject=(String)hMapHbtTestcodeData.get("strSubject");
	String strSubjname=(String)hMapHbtTestcodeData.get("strSubjname");
	int iResult=0;
	try {
	this.initalHDBAConnection();
	Statement stmt=null;
	StringBuffer sql1=new StringBuffer("DELETE badm.dbo.HBT_TESTCODE ");
	sql1.append("WHERE H_TEST='"+strTest+"' AND H_SUBJECT='"+strSubject+"' ");
	stmt=conn.createStatement();
	iResult=stmt.executeUpdate(sql1.toString());
	}catch(Exception e){
		e.printStackTrace();
		throw new HeException("刪除考試代碼檔失敗"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

}
