package com.nsb.interview.othello;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	private static Board board; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board=new Board();
	}

	@Test
	public void printInitBoard() {
		board.init();
		board.print();
	}

}
