package customer;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		CustomerFunction cf = new CustomerFunction();
		ArrayList<CustomerVO> clist = new ArrayList<CustomerVO>();
		clist = cf.selectAllCustomer();
		
//		for(int i=0; i < clist.size() ; i++{
		for(CustomerVO cvo : clist) {	
			System.out.println(cvo.toString());
		}
		
//		int result = cf.insertCustomer(6, "정안식", "대한민국 경기도", "010-0000-0000");
//		if (result==1) {
//			System.out.println("insert가 잘 동작하였습니다.");
//		} else {
//			System.out.println("insert에 에러가 발생했습니다.");
//		}
////		int resultUpdate = cf.updateCustomer(6, "정안식", "대한민국 서울", "000-0000-0000");
//		if (resultUpdate==1) {
//			System.out.println("update가 잘 동작하였습니다.");
//		} else {
//			System.out.println("update에 에러가 발생했습니다.");
//		}
//		cf.deleteCustomer(6);
//		cf.selectAllCustomer();
//		
		
		
	}

}
