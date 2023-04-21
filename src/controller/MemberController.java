package controller;

import java.util.List;
import java.util.Scanner;

import dto.MemberDTO;
import service.MemberService;

public class MemberController {
	private Scanner scanner = new Scanner(System.in);

	private MemberService ms = new MemberService();

	// selectMember()_회원조회
	public void selectMember() { // 컨트롤러
		System.out.println("* 회원 조회 화면입니다 *");
		List<MemberDTO> list = ms.selectMember(); // 서비스
		for (MemberDTO m : list) { // 뷰
			System.out.println(m);
		}
	}

	// insertMember()_회원등록
	public void insertMember() {
		System.out.println("* 회원 등록을 시작합니다. 아래 정보를 기입하세요. *");
		System.out.print("이름: ");
		String name = scanner.nextLine();

		System.out.print("나이: ");
		int age = Integer.parseInt(scanner.nextLine());

		System.out.print("주소: ");
		String address = scanner.nextLine();

		System.out.print("연락처: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("생일: (년/월/일 형식으로 기입해주세요.)");
		String dateOfBirth = scanner.nextLine();

		System.out.print("등록이 완료되었습니다.");
		MemberDTO dto = new MemberDTO();
		dto.setName(name); // 뷰
		dto.setAge(age); // 뷰
		dto.setAddress(address); // 뷰
		dto.setPhoneNumber(phoneNumber); // 뷰
		dto.setDateOfBirth(dateOfBirth); // 뷰

		int cnt = ms.insertMember(dto);
	}

	// updateMember()_회원 정보 수정
	public void updateMember() {
		System.out.println("* 회원 정보를 수정합니다. 아래 정보를 기입하세요. *");
		System.out.println("본인의 ID를 입력하세요: ");
		String memberId = scanner.nextLine();
		
		System.out.println("이름: ");
		String name = scanner.nextLine();

		System.out.println("나이: ");
		int age = Integer.parseInt(scanner.nextLine());

		System.out.println("주소: ");
		String address = scanner.nextLine();

		System.out.println("연락처: ");
		String phoneNumber = scanner.nextLine();

		System.out.println("생일(년/월/일 형식): ");
		String dateOfBirth = scanner.nextLine();

		System.out.println("등록이 완료되었습니다.");
		System.out.println("오류찾기");
		MemberDTO dto = new MemberDTO();
		dto.setMemberID(memberId);
		dto.setName(name); // 뷰
		dto.setAge(age); // 뷰
		dto.setAddress(address); // 뷰
		dto.setPhoneNumber(phoneNumber); // 뷰
		dto.setDateOfBirth(dateOfBirth); // 뷰

		int result = ms.updateMember(dto);
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 업데이트되었습니다.");
		} else {
			System.out.println("회원 정보 업데이트에 실패하였습니다.");
		}

	}

	// deleteMember()_회원 삭제
	public void deleteMember() {
		System.out.println("* 회원 정보를 삭제합니다. 아래 정보를 기입하세요. *");
		System.out.println("삭제하고 싶은 회원ID: ");
		String memberID = scanner.next();
		int cnt = ms.deleteMember(memberID);
        System.out.println("삭제가 완료되었습니다.");
	}

	// redoDeleteMember()_회원 삭제 취소 
	public void redoDeleteMember() {
        System.out.println("삭제를 취소하겠습니다.");
		int result = ms.redoDeleteMember();
	}
}
