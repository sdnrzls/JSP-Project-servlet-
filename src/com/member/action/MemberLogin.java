package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momber.model.SMemberDAOImpl;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/member/login.me")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
		   String userid = request.getParameter("userid");
		   String pwd = request.getParameter("pwd");
		   SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		   int flag = dao.loginCheck(userid, pwd);
		   if(flag==0 || flag==1){
			   HttpSession session=request.getSession();//HttpSession�� session�� �������������� ��� �Ұ���
			   session.setAttribute("userid", userid);
		   }
		 response.setContentType("type/html;charset=utf-8");
		 PrintWriter out = response.getWriter(); //PrintWriter�� out��ü �������������� ��� �Ұ���
		 out.println(flag);
	}

}
