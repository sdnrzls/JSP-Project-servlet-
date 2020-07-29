package com.momber.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class SMemberDAOImpl implements MemberDAO{

		private static SMemberDAOImpl instance 
		                   = new SMemberDAOImpl();
		public static SMemberDAOImpl  getInstance() {
			return instance;
		}
	    private Connection getConnection() throws Exception{
	    	Context initCtx = new InitialContext();
			Context envCtx =(Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
			return ds.getConnection();
	    }
	
	
	@Override
	public void memberInsert(MemberDTO vo) {
		Connection con=null;
		PreparedStatement ps =null;
		
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getMail());
			ps.setString(5, vo.getPhone());
			ps.setInt(6, vo.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
	}
	
	//�α���üũ
	public int loginCheck(String userid, String pwd) {
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
    	int flag = -1;  // -1 ȸ���ƴ�, 0 �Ϲ�ȸ��, 1 ������, 2 �������
    	try {
			con = getConnection();
			String sql = "select  pwd, admin from member where userid='"+userid+"'";        
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) { //id�� ����(ȸ������)
				if(rs.getString("pwd").equals(pwd)) { //�������
				     flag = rs.getInt("admin"); //admin�� 1:������ 0:�Ϲ�ȸ��
				}else { // ���Ʋ��
					flag = 2; 
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
    	return flag;
    }

	@Override
	public ArrayList<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int memberUpdate(MemberDTO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO memberView(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberDel(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String idCheck(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//�ݱ�
			private void closeConnection(Connection con, Statement st , ResultSet rs) {
				try {
					if(rs!=null) rs.close();
					if(st!=null)  	st.close();
					if(con!=null ) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			private void closeConnection(Connection con, PreparedStatement ps) {
				try {
					if(ps!=null) ps.close();
					if(con!=null ) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

}
