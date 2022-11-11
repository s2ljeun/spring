package com.ezen.student.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.student.dto.StudentDTO;

@Service // Autowired로 사용 가능
public class StudentMapper {
	//내가 관리하는 것이 아니라 close, commit도 안 해줘도 된다...
	@Autowired
	private SqlSession sqlSession;
	
	public List<StudentDTO> listStudent(){
		return sqlSession.selectList("listStudent");
	}
	
	public int insertStudent(StudentDTO dto) {
		return sqlSession.insert("insertStudent", dto);
	}
	
	public int deleteStudent(String id) {
		return sqlSession.insert("deleteStudent", id);
	}
	
	public List<StudentDTO> findStudent(String name){
		return sqlSession.selectList("findStudent", name);
	}
	
	
}
