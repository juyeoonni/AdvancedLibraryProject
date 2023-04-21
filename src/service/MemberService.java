package service;

import static jdbc.ConnectionTest.close;
import static jdbc.ConnectionTest.commit;
import static jdbc.ConnectionTest.rollback;

import java.sql.Connection;
import java.util.List;

import dto.MemberDTO;
import dao.MemberDAO;

public class MemberService {
	
	private MemberDAO mdao = new MemberDAO();
	
	// selectMember()_전체 회원 조회
	public List<MemberDTO> selectMember() {
		List<MemberDTO> list = mdao.selectMember();
		return list;
	}

	// insertMember()_새로운 회원 등록
	public int insertMember(MemberDTO member) {
		int result = mdao.insertMember(member);
		return result;
	}

	// updateMember()_회원 정보 수정
	public int updateMember(MemberDTO member) {
		int result = mdao.updateMember(member);
		return result;
	}

	// deleteMember()_회원 삭제
	public int deleteMember(String memberID) {
		int result = mdao.deleteMember(memberID);
		return result;
	}
	
	// redoDeleteMember()_회원 삭제 취소 
	public int redoDeleteMember() {
		int result = mdao.redoDeleteMember();
		return result;
	}
	
	
}
