package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.momber.model.MemberDTO;
import com.momber.model.SMemberDAOImpl;

/**
 * Servlet implementation class UserDelete
 */
@WebServlet("/member/userDelete.me")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				String userid =request.getParameter("userid");
				SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
				dao.memberDel(userid);
				ArrayList<MemberDTO>arr=dao.memberList();
				int count= dao.getCount();
				//JSON형태로 값을 가져가야함
				JSONObject mainobj = new JSONObject();
				JSONArray jarr = new JSONArray();
				for(MemberDTO dto : arr) {
					String mode = dto.getAdmin()==1?"관리자":"일반회원";
					JSONObject obj = new JSONObject();
					obj.put("name", dto.getName());
					obj.put("userid", dto.getUserid());
					obj.put("mail", dto.getMail());
					obj.put("phone", dto.getPhone());
					obj.put("mode",mode);
					jarr.add(obj);
				}
				JSONObject countobj = new JSONObject();
				countobj.put("count", count);
				
				mainobj.put("root",jarr);
				mainobj.put("rootCount",countobj);
				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(mainobj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
