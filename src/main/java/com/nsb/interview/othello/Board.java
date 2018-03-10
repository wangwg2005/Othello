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

		if( rule.valid(player, col, row)) {
//			POS[row][col]=player;
			return true;
		}
		return false;
	}
	
	private void transferV(int col, int startR, int endR, char player) {
		for(int i=startR;i<endR;i++) {
			POS[i][col]=player;
		}
	}
	
	private void transferH(int row, int startC, int endC, char player) {
		for(int i=startC;i<endC;i++) {
			POS[row][i]=player;
		}
	}
	

	public class Rule {

		public boolean valid(char player, int col, int row) {
			int r=row;
			int c=col;
			boolean test=false;
			while (++c < 8) {
				if(POS[r][c]==player) {
					if(test) {
						transferH(row,col,c,player);
						 return true;
					 }else {
						 break;
					 }
				}else if(POS[r][c]==EMPTY) {
					 break;
				}else {
					test=true;
				}
			}
			if(c==8)
				return false;
			r=row;
			c=col;
			test=false;
			while (--c >=0) {
				if(POS[r][c]==player) {
					if(test) {
						transferH(row,c+1,col+1,player);
						 return true;
					 }else {
						 break;
					 }
				}else if(POS[r][c]==EMPTY) {
					 break;
				}else {
					test=true;
				}
			}
			if(c<0)
				return false;
			r=row;
			c=col;
			test=false;
			while (++r < 8) {
				if(POS[r][c]==player) {
					if(test) {
						transferV(c,row,r,player);
						 return true;
					 }else {
						 break;
					 }
				}else if(POS[r][c]==EMPTY) {
					 break;
				}else {
					test=true;
				}

			}
			if(r==8)
				return false;
			r=row;
			c=col;
			test=false;
			while (--r >=0) {
				if(POS[r][c]==player) {
					if(test) {
						transferV(c,r+1,row+1,player);
						 return true;
					 }else {
						 break;
					 }
				}else if(POS[r][c]==EMPTY) {
					 break;
				}else {
					test=true;
				}
			}
			return r<0;
		}

		public boolean playable(char player) {
			for(int i=0;i<SCALE;i++) {
				for(int j=0;j<SCALE;j++) {
					if(POS[i][j]==EMPTY&& valid(player,i,j)) {
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
