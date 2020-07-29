package com.momber.model;

import java.util.ArrayList;

public interface MemberDAO {
	//�߰�
	public void memberInsert(MemberDTO vo);
	//��ü����
	public ArrayList<MemberDTO> memberList();
	//����
	public int memberUpdate(MemberDTO vo);
	//�󼼺���
	public MemberDTO memberView(String userid);
	//����
	public void memberDel(String userid);
	//���̵�üũ(�ߺ�üũ)
	public String idCheck(String userid);


}






