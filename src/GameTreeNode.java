import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTreeNode {

	//public String moveDescription;
	public List<GameTreeNode> children;
	public Board board;
	public int score;

	public GameTreeNode(List<String> initConfig){
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(initConfig);
	}

	public GameTreeNode(){
		this.children = new ArrayList<GameTreeNode>();
	}

	public void addChildren(List<GameTreeNode> clist){
		//this.children = clist;
		for(GameTreeNode gtn : clist){
			this.children.add(gtn);
		}
	}
	public boolean	 isTerminal(){
		return children.isEmpty();
	}
	public class Board {
		//board spaces read from the top down ie (0,0) = 0 (0,1) = 1
		public List<String> board = new ArrayList<String>();
		Map<Integer, String> placeValues = new HashMap<Integer, String>();
	
		public Board(List<String> rows) {
			
			placeValues.put(0, "(0,0)");
			placeValues.put(1, "(0,1)");
			placeValues.put(2, "(0,2)");
			placeValues.put(3, "(1,0)");
			placeValues.put(4, "(1,1)");
			placeValues.put(5, "(1,2)");
			placeValues.put(6, "(2,0)");
			placeValues.put(7, "(2,1)");
			placeValues.put(8, "(2,2)");
			placeValues.put(9, "(3,0)");
			placeValues.put(10, "(3,1)");
			placeValues.put(11, "(3,2)");
			placeValues.put(12, "(4,0)");
			placeValues.put(13, "(4,1)");
			placeValues.put(14, "(4,2)");
			placeValues.put(15, "(5,0)");
			placeValues.put(16, "(5,1)");
			placeValues.put(17, "(5,2)");
			
			for(int i = 0; i < rows.size(); i++){

				String[] slots = rows.get(i).split("");

				for(String s : slots) {
					if(!s.isEmpty()){ //this a pretty shitty way to guard against newline
						this.board.add(s);
					}
				}
			}
		}

		public List<GameTreeNode> legalMoves(int player){

			String p = null;
			String empty = "X";
			switch(player){
			case 1: 
				p = "W";
				break;
			case 2:
				p = "B";
				break;
			}

			//a list of posible board configs we discover as new moves
			List<GameTreeNode> newBoards = new ArrayList<GameTreeNode>();
				if(player == 1){
					for(int i = board.size()-1; i >= 0; i--){

						int goal = 0;
						int ahead = i-3;
						int ldiag = i-4;
						int rdiag = i-2;

						//System.out.println(p+" "+board.get(i)+": "+board.get(i).equals(p)+" space "+i+" goal "+goal+", ahead "+ahead+", rdiag "+rdiag+", ldiag "+ldiag);
						//if the current slot is on the far left
						if(i % 3 == 0){
							if( ahead > goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								newBoards.add(gtn);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(rdiag > goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
								List<String> moveRightDiag = new ArrayList<String>(board);
								moveRightDiag.set(rdiag, moveRightDiag.get(i));
								moveRightDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveRightDiag);
								//newBoards.add(moveRightDiag);
								newBoards.add(gtn);
								printAsBoard(moveRightDiag);
								printMove(i, rdiag, p);
							}
						}
						//far right
						else if((i+1) % 3 == 0){
							if( ahead > goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								newBoards.add(gtn);
								//newBoards.add(moveAhead);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(ldiag > goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
								List<String> moveLeftDiag = new ArrayList<String>(board);
								moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
								moveLeftDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveLeftDiag);
								newBoards.add(gtn);
								//newBoards.add(moveLeftDiag);
								printAsBoard(moveLeftDiag);
								printMove(i, ldiag, p);
							}
						}
						//middle
						else{
							if( ahead > goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								newBoards.add(gtn);
								//newBoards.add(moveAhead);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(rdiag > goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
								List<String> moveRightDiag = new ArrayList<String>(board);
								moveRightDiag.set(rdiag, moveRightDiag.get(i));
								moveRightDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveRightDiag);
								newBoards.add(gtn);
								//newBoards.add(moveRightDiag);
								printAsBoard(moveRightDiag);
								printMove(i, rdiag, p);

							}
							if(ldiag > goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
								List<String> moveLeftDiag = new ArrayList<String>(board);
								moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
								moveLeftDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveLeftDiag);
								newBoards.add(gtn);
								//newBoards.add(moveLeftDiag);
								printAsBoard(moveLeftDiag);
								printMove(i, ldiag, p);
							}
						}
					}
				}
				else {
					for(int i = 0; i < board.size(); i++){

						int goal = board.size();
						int ahead = i+3;
						int ldiag = i+2;
						int rdiag = i+4;

						//System.out.println(p+" "+board.get(i)+": "+board.get(i).equals(p)+" space "+i+" goal "+goal+", ahead "+ahead+", rdiag "+rdiag+", ldiag "+ldiag);
						//if the current slot is on the far left
						if(i % 3 == 0){

							if( ahead < goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								//newBoards.add(moveAhead);
								newBoards.add(gtn);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(rdiag < goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
								List<String> moveRightDiag = new ArrayList<String>(board);
								moveRightDiag.set(rdiag, moveRightDiag.get(i));
								moveRightDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveRightDiag);
								newBoards.add(gtn);
								printAsBoard(moveRightDiag);
								printMove(i, rdiag, p);
							}
						}
						//far right
						else if((i+1) % 3 == 0){
							if( ahead < goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								newBoards.add(gtn);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(ldiag < goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
								List<String> moveLeftDiag = new ArrayList<String>(board);
								moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
								moveLeftDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveLeftDiag);
								newBoards.add(gtn);
								printAsBoard(moveLeftDiag);
								printMove(i, ldiag, p);
							}
						}
						//middle
						else{
							if( ahead < goal && board.get(i).equals(p) && board.get(ahead).equals(empty)){
								List<String> moveAhead = new ArrayList<String>(board);
								moveAhead.set(ahead, moveAhead.get(i));
								moveAhead.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveAhead);
								newBoards.add(gtn);
								printAsBoard(moveAhead);
								printMove(i, ahead, p);
							}
							if(rdiag < goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
								List<String> moveRightDiag = new ArrayList<String>(board);
								moveRightDiag.set(rdiag, moveRightDiag.get(i));
								moveRightDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveRightDiag);
								newBoards.add(gtn);
								printAsBoard(moveRightDiag);
								printMove(i, rdiag, p);

							}
							if(ldiag < goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
								List<String> moveLeftDiag = new ArrayList<String>(board);
								moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
								moveLeftDiag.set(i, empty);
								GameTreeNode gtn = new GameTreeNode(moveLeftDiag);
								newBoards.add(gtn);
								printMove(i, ldiag, p);
							}
						}
					}
				}
			return newBoards;
		}
		public int winState(){
			//a player has reached the home of opponent
			for(int i = 0; i < 3; i++){
				if(board.get(i).equals("W")){
					System.out.println("Winner is W by home row victory");
					return 1;
				}
			}
			for(int i = board.size()-1; i > 14; i--){
				if(board.get(i).equals("B")){
					System.out.println("Winner is B by home row victory");
					return -1;
				}
			}
			//if a player has captured all opponents
			if(!board.contains("B"))
				return 1;
			if(!board.contains("W"))
				return -1;

			return 0;
		}
		public void printMove(int from, int to, String player){
			System.out.print(player+" moved from "+placeValues.get(from)+" to "+placeValues.get(to));
			System.out.println(" ");
		}
		public void print(){
			for(int i = 0; i < board.size(); i++){
				if((i+1) % 3 == 0){
					System.out.println(board.get(i));
				}
				else 
					System.out.print(board.get(i));

			}
		}
		public void printAsBoard(List<String> b){
			for(int i = 0; i < b.size(); i++){
				if((i+1) % 3 == 0){
					System.out.println(b.get(i));
				}
				else 
					System.out.print(b.get(i));

			}
			System.out.println("_______________");
		}
	}
}
