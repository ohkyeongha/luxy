package message;

import java.io.Serializable;
import java.util.ArrayList;

import book.BookVO;

public class BookMessage implements Serializable, Message{
	// state - insert, update, delete, select
	private int state;

	// 결과값 - insert,update,delete : int
	// select : int
	private int result;

	// 책 객체
	private BookVO bvo;

	// 책목록
	private ArrayList<BookVO> blist;

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

	public BookVO getBvo() {
		return bvo;
	}

	public void setBvo(BookVO bvo) {
		this.bvo = bvo;
	}

	public ArrayList<BookVO> getBlist() {
		return blist;
	}

	public void setBlist(ArrayList<BookVO> blist) {
		this.blist = blist;
	}
	
	
}
