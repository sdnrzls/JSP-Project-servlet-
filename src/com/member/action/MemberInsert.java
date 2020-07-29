package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momber.model.MemberDTO;
import com.momber.model.SMemberDAOImpl;

/**
 * Servlet implementation class MemberInsert
 */
@WebServlet("/member/insert.me")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		MemberDTO member =new MemberDTO();
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));
		member.setMail(request.getParameter("mail"));
		member.setName(request.getParameter("name"));
		member.setPwd(request.getParameter("pwd"));
		member.setPhone(request.getParameter("phone"));
		member.setUserid(request.getParameter("userid"));
		dao.memberInsert(member);
		response.sendRedirect("login.me");
	}

}
