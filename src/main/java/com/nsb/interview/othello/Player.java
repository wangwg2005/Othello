package com.nsb.interview.othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nsb.interview.othello.Board.Rule;

public class Player {
	
	private static final BufferedReader SYS_READER;
	
	static {
		SYS_READER=new BufferedReader(new InputStreamReader(System.in));
	}

	private char name;
	private Board board;

	public Player(char name,Board board) {
		this.name = name;
		this.board=board;
	}

	public char getName() {
		return name;
	}
	
	public void play() throws IOException {
		System.out.print("Player '"+name+"' move: ");
		String pos=SYS_READER.readLine();
		
		int row=pos.charAt(0)-'1';
		int col=pos.charAt(1)-'a';
		
		if(row<=0 || row>8 || col<=0 || row>8 || !board.play(name, col, row)) {
			play("Invalid move. Please try again.");
		}
		
		
		
		
		
	}
	
	private void play(String msg) throws IOException {
		System.out.println(msg);
		play();
	}
	
	

}
