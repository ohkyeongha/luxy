package customer;

import java.util.ArrayList;
import java.util.Scanner;

public class NewMainClass {

	public static void printAll(ArrayList<CustomerVO> clist) {
		for (CustomerVO cvo : clist) {
			System.out.println(cvo.toString());
		}
	}
	
	public static void print(ArrayList<CustomerVO> vlist) {
		for(CustomerVO cvo : vlist) {
			System.out.println(cvo.toString());
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomerFunction cf = new CustomerFunction();
		while (true) {
			System.out.println("------------------------");
			System.out.println("고객 정보 관리 시스템");
			System.out.println("1. 모든 고객 정보 조회");
			System.out.println("2. 한 명의 고객 정보 조회");
			System.out.println("3. 고객 정보 추가");
			System.out.println("4. 고객 정보 갱신");
			System.out.println("5. 고객 정보 삭제");
			System.out.println("etc. 종료");
			System.out.println("------------------------");
			System.out.print("> ");
			String num = sc.nextLine();

			if (num.equals("1")) {			//모든 고객 정보 조회
				printAll(cf.selectAllCustomer());
				
			} else if (num.equals("2")) {	//한 명의 고객 정보 조회(이름)
				System.out.println("1. 고객번호로 검색");
				System.out.println("2. 이름으로 검색");
				System.out.println("3. 주소로 검색");
				System.out.println("4. 전화번호로 검색");
				System.out.print("> ");
				
				int item = sc.nextInt();
				sc.nextLine();
				
				if (item==1) {
					System.out.println("조회할 고객번호를 입력하세요.");
					System.out.print("> ");
				} else if (item==2) {
					System.out.println("조회할 고객의 이름을 입력하세요.");
					System.out.print("> ");
				} else if (item==3) {
					System.out.println("조회할 고객의 주소를 입력하세요.");
					System.out.print("> ");
				} else if (item==4) {
					System.out.println("조회할 고객의 전화번호를 입력하세요.");
					System.out.print("> ");
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					continue;
				}
				String search = sc.nextLine();
				
				print(cf.selectCustomer(item, search));
				
			} else if (num.equals("3")) {	//고객 정보 추가
				System.out.println("추가할 고객의 고객번호를 입력하세요");
				System.out.print("> ");
				int custId = sc.nextInt();
				sc.nextLine();
				System.out.println("고객의 이름을 입력하세요.");
				System.out.print("> ");
				String name = sc.nextLine();
				System.out.println("고객의 주소를 입력하세요.");
				System.out.print("> ");
				String address = sc.nextLine();
				System.out.println("고객의 전화번호를 입력하세요.");
				System.out.print("> ");
				String phone = sc.nextLine();
				
				int result = cf.insertCustomer(custId, name, address, phone);
				if (result != 0) {
					System.out.println("고객의 정보가 정상적으로 등록되었습니다.");
				} else {
					System.out.println("고객의 정보가 잘못되었습니다. 다시 입력해주세요");
				}
				
			} else if (num.equals("4")) {	//고객 정보 갱신(고객번호)
				System.out.println("변경할 고객의 고객번호를 입력하세요.");
				System.out.print("> ");
				int custId = sc.nextInt();
				System.out.println("1. " + custId + "번 고객의 이름을 변경");
				System.out.println("2. " + custId + "번 고객의 주소를 변경");
				System.out.println("3.  " + custId + "번 고객의 전화번호 변경");
				System.out.println("4.  " + custId + "번 고객의 모든 정보 변경");
				System.out.print("> ");
				int updateitem = sc.nextInt();
				sc.nextLine();
				String name = null;
				String address = null;
				String phone = null;
				
				if(updateitem == 1) {
					System.out.println("변경된 이름을 입력하세요.");
					System.out.print("> ");
					name = sc.nextLine();
					
				} else if(updateitem == 2) {
					System.out.println("변경된 주소를 입력하세요.");
					System.out.print("> ");
					address = sc.nextLine();
					
				} else if(updateitem == 3) {
					System.out.println("변경된 전화번호를 입력하세요.");
					System.out.print("> ");
					phone = sc.nextLine();
					
				} else if(updateitem == 4) {
					System.out.println("변경된 이름을 입력하세요.");
					System.out.print("> ");
					name = sc.nextLine();
					System.out.println("변경된 주소를 입력하세요.");
					System.out.print("> ");
					address = sc.nextLine();
					System.out.println("변경된 전화번호를 입력하세요.");
					System.out.print("> ");
					phone = sc.nextLine();
					
				} else {
					System.out.println("잘못 입력했습니다. 다시 입력해주세요");
					continue;
				}
				
				int result = cf.updateCustomer(updateitem, custId, name, address, phone);
				if(result != 0) {
					System.out.println(custId +"번 고객의 정보가 정상적으로 수정되었습니다.");
				} else {
					System.out.println(custId +"번 고객의 정보가 비정상적으로 작성되었습니다. 다시 입력해주세요.");
					
				}
				
			} else if (num.equals("5")) {	//고객 정보 삭제(이름)
				System.out.println("삭제할 고객의 번호를 입력하세요.");
				System.out.print("> ");
				int custId = sc.nextInt();
				sc.nextLine();
				int result = cf.deleteCustomer(custId);
				if(result != 0) {
					System.out.println(custId +"번 고객의 정보가 정상적으로 삭제되었습니다.");
				} else {
					System.out.println("존재하지 않는 고객의 정보입니다. (" + custId + "번) 다시 입력해주세요.");
					
				}
				
			} else  {						//그 외 값 입력 : 종료
				System.out.println("고객 정보 관리 시스템을 종료합니다.");
				break;
			} 

			

		}

	}

}
