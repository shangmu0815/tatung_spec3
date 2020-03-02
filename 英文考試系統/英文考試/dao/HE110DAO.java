/*
 * �b 2008/9/16 �إ�
 *
 * �Y�n�ܧ�o�Ӳ��ͪ��ɮת��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
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
 * �Y�n�ܧ�o�Ӳ��ͪ����O���Ѫ��d���A�в���
 * ���� > �ߦn�]�w > Java > �{���X���� > �{���X�M����
 */
public class HE110DAO extends CommonDAO{
	private boolean DEBUG = false;
//	----�d�ߦҸեN�X��--
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
		// TODO �۰ʲ��� catch �϶�
		e.printStackTrace();
		throw new HeException("�d�ߦҸեN�X�ɧ@�~����->"+e.toString());
	}
	finally{
		this.closeHDBAConnection();
	}
	return dataV;
}
//-----check�O�_�w�g���ҸեN�X��--
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
		throw new HeException("check�O�_�w�g���ҸեN�X�ɧ@�~����"+e.toString());
	}finally{
		this.closeHDBAConnection();		
	}
	return checkBln;
}

//-----�s�W�ҸեN�X��--
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
		throw new HeException("�s�W�ҸեN�X�ɥ���"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----�ק�ҸեN�X��--
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
		throw new HeException("�ק�ҸեN�X�ɥ���"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

//-----�R���ҸեN�X��--
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
		throw new HeException("�R���ҸեN�X�ɥ���"+e.toString());
	}finally{
		this.closeHDBAConnection();
	}
	return iResult;	
}

}
