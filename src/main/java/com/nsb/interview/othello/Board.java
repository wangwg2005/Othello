package com.nsb.interview.othello;

import java.util.Arrays;

public class Board {

	public static final int SCALE = 8;

	private static final char[][] POS = new char[8][8];
	
	public static final char EMPTY='-';

	private Rule rule = new Rule();

	public void init() {
		for (char[] rows : POS) {
			Arrays.fill(rows, EMPTY);
		}
		POS[3][3] = 'O';
		POS[3][4] = 'X';
		POS[4][3] = 'X';
		POS[4][4] = 'O';

	}

	public Rule getRule() {
		return rule;
	}

	/**
	 * print the board to console
	 */
	public void print() {
		for (int i = 0; i < SCALE; i++) {

			System.out.print(i + 1);
			System.out.print(" ");
			for (int j = 0; j < SCALE; j++) {
				System.out.print(POS[i][j]);
			}
			System.out.println();
		}
		System.out.println("  abcdefgh");
	}

	public boolean play(char player, int col, int row) {
		if (POS[row][col] != EMPTY)
			return false;

		if( rule.valid(player, col, row,true)) {
//			POS[row][col]=player;
			return true;
		}
		return false;
	}
	
	private void drawLine(char player, int col, int row, int colInc, int rowInc) {
		char endPoint=POS[row][col]==player?EMPTY:player;
		int r=row, c = col;
		while(POS[r][c]!=endPoint) {
			POS[r][c]=player;
			r+=rowInc;
			c+=colInc;
		}
	}


	public class Rule {
		
		
		private boolean test(char player, int col, int row, int colInc, int rowInc) {
			int c=col;
			int r=row;
			
			boolean hasCounterParty=false;
			while((c+=colInc)<SCALE && c>=0 &&(r+=rowInc)>=0&& r<SCALE) {
				if(POS[r][c]==player) {
					if(hasCounterParty) {
						 return true;
					 }else {
						 break;
					 }
				}else if(POS[r][c]==EMPTY) {
					 break;
				}else {
					hasCounterParty=true;
				}
			}
			return false;
		}

		public boolean valid(char player, int col, int row,boolean draw) {
			boolean result=false;
			for(int i=-1;i<2;i++) {
				for(int j=-1;j<2;j++) {
					if(i==0 && j==0)
						continue;
					if(test(player,col,row,i,j)) {
						result=true;
						if(draw) {
							drawLine(player,col,row,i,j);
						}else {
							break;
						}
					}
				}
			}
			return result;

		}

		public boolean playable(char player) {
			for(int i=0;i<SCALE;i++) {
				for(int j=0;j<SCALE;j++) {
					if(POS[i][j]==EMPTY&& valid(player,i,j,false)) {
						return true;
					}
				}
			}
			return false;
		}

		public boolean gameEnd() {
			for (int i = 0; i < SCALE; i++) {
				for (int j = 0; j < SCALE; j++)
					if (POS[i][j] == EMPTY ) {
						if(playable('X') || playable('O')) {
							return false;
						}
					}
			}
			return true;
		}
	}
	
	public void printEndStat() {
		System.out.println("No further moves available");
		int x=0;
		int o=0;
		for (int i = 0; i < SCALE; i++) {
			for (int j = 0; j < SCALE; j++)
				if (POS[i][j] == 'X' ) {
					x++;
				}else if(POS[i][j]=='O') {
					o++;
				}
		}
		if(x==0) {
			System.out.println("Even "+x+" vs "+o+" )"); 
		}else {
			char winner=x>o?'X':'O';
			System.out.println("Player '"+winner+"' wins ("+Math.max(x, o)+" vs "+Math.min(x, o)+")");
			
		}
		
	}
		

}
