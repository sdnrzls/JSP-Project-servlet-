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

		//DB����
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
	
	//ȸ������
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
	
	//ȸ����
	public int getCount() {
		Connection con=null;
		Statement st =null;
		ResultSet rs =null;
		int count =0;
		
		try {
			con = getConnection();
			String sql = "select count(*) from member";
			st =con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return count;
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
	
	//��ü����
	@Override
	public ArrayList<MemberDTO> memberList() {
		Connection con = null;
		Statement st =null;
		ResultSet rs= null;
		ArrayList<MemberDTO> arr = new ArrayList<>();		
		try {
			con = getConnection();
			String sql ="select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setAdmin(rs.getInt("admin"));
				dto.setMail(rs.getString("mail"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}return arr;
	}

	@Override
	public int memberUpdate(MemberDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;
		try {
			con = getConnection();
			String sql = "update member set"
					+ "  name =? ,mail=?, phone=?, admin=? where userid =?";
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getName());
			ps.setString(2,vo.getMail());
			ps.setString(3,vo.getPhone());
			ps.setInt(4,vo.getAdmin());
			ps.setString(5,vo.getUserid());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return flag;
	}

	@Override
	public MemberDTO memberView(String userid) {
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO member = null;
		
		try {
			con = getConnection();
			String sql  ="select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				member = new MemberDTO();
				member.setAdmin(rs.getInt("admin"));
				member.setMail(rs.getString("mail"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setPwd(rs.getString("pwd"));
				member.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		} return member;
	}
	
	//����
	@Override
	public void memberDel(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			String sql = "delete from member where userid ='"+userid+"'";
			st =con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}closeConnection(con, st, null);
		
	}

	@Override
	public String idCheck(String userid) {
		Connection con = null;
		Statement st= null;
		ResultSet rs = null;
		String flag = "yes"; // ��밡��
		try {
			con = getConnection();
			String sql = "select * from member where userid ='"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				flag = "no"; //���Ұ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return flag;
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
