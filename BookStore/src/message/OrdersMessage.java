package message;

import java.io.Serializable;
import java.util.ArrayList;

import orders.OrdersVO;

public class OrdersMessage implements Serializable, Message{
	// state - insert, update, delete, select
	private int state;

	// 결과값 - insert,update,delete : int
	// select : int
	private int result;

	// 주문 객체
	private OrdersVO ovo;

	// 주문 목록
	private ArrayList<OrdersVO> olist;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public OrdersVO getOvo() {
		return ovo;
	}

	public void setOvo(OrdersVO ovo) {
		this.ovo = ovo;
	}

	public ArrayList<OrdersVO> getOlist() {
		return olist;
	}

	public void setOlist(ArrayList<OrdersVO> olist) {
		this.olist = olist;
	}
	
	
}
