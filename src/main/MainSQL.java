package main;

import java.util.Scanner;

import controller.BookController;
import controller.LoanController;
import controller.MemberController;

public class MainSQL {

    public static void main(String[] args) {
    	
    	MemberController mc = new MemberController();
    	BookController bc = new BookController();
    	LoanController lc = new LoanController();
    	
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("=========== 도서 관리 프로그램 =============");
            System.out.println("1.회원관리    2.도서관리    3.대출관리    4.종료");
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    System.out.println("0.뒤로   1.회원조회   2.회원등록   3.회원수정   4.회원삭제   5.삭제취소");
                    int number1 = Integer.parseInt(scanner.nextLine());
                    switch (number1) {
                        case 0:
                            System.out.println("뒤로 가기");
                            break;
                        case 1:
                            mc.selectMember();
    						break;
                        case 2:
                        	mc.insertMember();
                        	break;
                        	
                        case 3:
                        	mc.updateMember();
                            break;
                        case 4:
                        	mc.deleteMember();
                            break;
         
                        case 5:
                        	mc.redoDeleteMember();
                            break;
       

                    }
                    System.out.println("======================================");
                    System.out.println();
                    break;
                   
                case 2:
                    System.out.println("0.뒤로   1.책 조회   2.책 등록   3.책 정보 수정   4.삭제   5.삭제취소");
                    int number2 = Integer.parseInt(scanner.nextLine());
                    switch (number2) {
                        case 0:
                            System.out.println("뒤로 가기");
                            break;
                        case 1:
                            bc.selectBook();
                            break;

                        case 2:
                        	bc.insertBook();
                        	break;

                        case 3:
                        	bc.updateBook();
                            break;

                        case 4:
                        	bc.deleteBook();
                        	break;
                        case 5: 
                        	bc.redoDeleteBook();
                            
                    }
                    System.out.println("======================================");
                    System.out.println();
                    break;

                case 3:
                    System.out.println("0.뒤로   1.대출가능도서   2.대출이력조회   3.대출신청   4.연장신청   5.반납");
                    int number3 = Integer.parseInt(scanner.nextLine());
                    switch (number3) {
                        case 0:
                            System.out.println("뒤로 가겠습니다.");
                            break;
                        case 1:
                            System.out.println("대출가능한 도서를 조회합니다.");
                            break;
                        case 2:
                            lc.selectAllLoanList();
                            break;
                        case 3:
                        	lc.loanBook();
                            break;
                        case 4:
                        	String id = lc.renewableList();
                        	lc.extendLoan(id);
                            break;
                        case 5:
                        	lc.returnBook();
                            break;
                    }
                    System.out.println("======================================");
                    System.out.println();
                    break;
                default:
                    System.out.println("종료하겠습니다.");

                    return;

            }
            
        }

    }

}
