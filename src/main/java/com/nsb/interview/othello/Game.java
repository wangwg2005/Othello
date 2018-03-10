package com.nsb.interview.othello;

import java.io.IOException;

public class Game {
	private Board board;
	
	private Player x;
	private Player o;
	private Player currentPlayer;
	
	public Game() {
		board=new Board();
		board.init();
		x=new Player('X',board);
		o=new Player('O',board);
		currentPlayer=x;
	}
	
	public void run() throws IOException {
		
		Board.Rule rule=board.getRule();
		
		Player other=null;
		board.print();
		
		do {
			currentPlayer.play();
			board.print();
			other= currentPlayer==x?o:x;
			if(rule.playable(other.getName())) {
				currentPlayer=other;
			}
		}while(!rule.gameEnd())	;	
		board.printEndStat();
		
	}
	

	public static void main(String[] args) throws IOException {		
		Game game=new Game();
		game.run();
	}

}
