package member.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.dto.MemberDTO;

public class MemberMapper {
	private static SqlSessionFactory sqlMapper;
	static {
		try {
			String resource = "configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("DB연결 오류 발생" + e.getMessage());
		}
	}
	
	public static List<MemberDTO> listMember() {
		SqlSession session = sqlMapper.openSession();
		try {
			List<MemberDTO> list = session.selectList("listMember");
			return list;
			
		}finally {
			session.close();
		}
	}


	public static List<MemberDTO> findMember(Map<String, String> map) {
		SqlSession session = sqlMapper.openSession();
		
		try {
			List<MemberDTO> list = session.selectList("findMember", map);
			return list;
			
		}finally {
			session.close();
		}
	}
	
	
	
	public static boolean checkMember(Map<String, String> map) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("checkMember", map);
			if (dto == null) return false;
			else return true;
		}finally{
			session.close();
		}
	}
	
	
	
	public static int insertMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.insert("insertMember", dto);
			session.commit();
			return res;
			
		}finally {
			session.close();
		}
	}


	public static MemberDTO getMember(int no) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("getMember", no);
			return dto;			
		}finally {
			session.close();
		}
	}

	
	/* Mapper에 같은 이름 select 두 개인데 이대로 가능? => 안돼 */
	public static MemberDTO getMember(String id) {
		SqlSession session = sqlMapper.openSession();
		try {
			MemberDTO dto = session.selectOne("getMemberId", id);
			return dto;			
		}finally {
			session.close();
		}
	}
	
	

	public static int updateMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.update("updateMember", dto);
			session.commit();
			return res;
			
		}finally {
			session.close();
		}
	}
	
	
	public static int deleteMember(int no) {
		SqlSession session = sqlMapper.openSession();
		try {
			int res = session.delete("deleteMember", no);
			session.commit();
			return res;
			
		}finally {
			session.close();
		}
	}


	public static String searchMember(String name, String ssn1, String ssn2, String id) {
		SqlSession session = sqlMapper.openSession();
		String sql = null;

		if (id == null) {
			sql = "select * from jsp_member where name=" +
					name + "and ssn1=" + ssn1 + "and ssn2=" + ssn2;
		}else {
			sql = "select * from jsp_member where name=" +
					name + "and ssn1=" + ssn1 + "and ssn2=" + ssn2 +
					"and id=" + id;
		}
		
		try {
			
			MemberDTO dto = session.selectOne("getMember", id);
			String msg = null;
			if (id == null) msg = "아이디 : " + dto.getId() + " 입니다.";
			else msg = "비밀번호 : " + dto.getPasswd() + " 입니다.";
			return msg;
		}finally {
			session.close();
		}
	}
	

}
