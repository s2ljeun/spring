package com.ezen.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.member.dto.MemberDTO;

@Service
public class MemberMapper {
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberDTO> listMember(){
		return sqlSession.selectList("listMember");
	}
	
	public List<MemberDTO> findMember(Map<String, String> map) {
		List<MemberDTO> list = sqlSession.selectList("findMember", map);
		return list;
	}
	
	public boolean checkMember(Map<String, String> map) {
		MemberDTO dto = sqlSession.selectOne("checkMember", map);
		if (dto == null) return false;
		else return true;
	}
	
	public int insertMember(MemberDTO dto) {
		return sqlSession.insert("insertMember", dto);
	}


	public MemberDTO getMember(int no) {
		return sqlSession.selectOne("getMember", no);
			
	}
	
	public MemberDTO getMember(String id) {
		return sqlSession.selectOne("getMemberId", id);
	}
	
	public int updateMember(MemberDTO dto) {
		return sqlSession.update("updateMember", dto);
	}
	
	public int deleteMember(int no) {
		return sqlSession.delete("deleteMember", no);
	}
	
	public String searchMember(String name, String ssn1, 
			String ssn2, String id) {
		String sql = null;
		String msg = null;

		if (id == null) {
			sql = "select * from jsp_member where name=" +
					name + "and ssn1=" + ssn1 + "and ssn2=" + ssn2;
		}else {
			sql = "select * from jsp_member where name=" +
					name + "and ssn1=" + ssn1 + "and ssn2=" + ssn2 +
					"and id=" + id;
		}
		
		MemberDTO dto = sqlSession.selectOne("getMember", id);
		
		if (id == null) msg = "아이디 : " + dto.getId() + " 입니다.";
		else msg = "비밀번호 : " + dto.getPasswd() + " 입니다.";
		return msg;
	}
	
}
