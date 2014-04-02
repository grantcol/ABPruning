import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTreeNode {

	//public String moveDescription;
	public List<GameTreeNode> children;
	public Board board;
	public boolean maxToMove;
	public int score;

	public GameTreeNode(List<String> initConfig, boolean maxToMove){
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(initConfig, true);
		this.maxToMove = maxToMove;
	}

	public GameTreeNode(){
		this.children = new ArrayList<GameTreeNode>();
	}

	public void addChildren(List<GameTreeNode> clist){
		for(GameTreeNode gtn : clist){
			this.children.add(gtn);
		}
	}

	public boolean isTerminal(){
		if(board.winState(this)){
			return true;
		}
		return false;
	}





	public class Board {
		//board spaces read from the top down ie (0,0) = 0 (0,1) = 1
		public List<String> board = new ArrayList<String>();
		public char[][] boardState = new char[6][3]; 
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
		public Board(List<String> rows, boolean dumb){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 3; j++){
					this.boardState[i][j] = rows.get(i).charAt(j);
					System.out.println(this.boardState[i][j]);
				}
			}
		}

		public void makeLegalMoves(GameTreeNode node){
			char empty = 'X';
			char opp = 'N';
			char self = 'N';
			//first check the bias of the node passed in 
			if(node.maxToMove){
				//it is player 1's turn (White)
				opp = 'B';
				self = 'W';
				for(int i = 0; i < 6; i++){
					for(int j = 0; j < 3; j++){
						if(i > 0 && node.board.boardState[i][j] == self){
							//if we are looking at a white space and are not in the victory row

							if(j == 1){
								//if we are in the middle column we can move lrdiag and fwd

								//forward
								if(node.board.boardState[i-1][j] == empty){
									//GameTreeNode move = new GameTreeNode(node.board)
								}
								if(node.board.boardState[i-1][j+1] == empty || node.board.boardState[i-1][j+1] == opp){

								}
								if(node.board.boardState[i-1][j-1] == empty || node.board.boardState[i-1][j-1] == opp){
									
								}
							}

							else if(j == 0){
								//we are in the left column we can move rdiag and fwd
								if(node.board.boardState[i-1][j] == empty){

								}
								if(node.board.boardState[i-1][j+1] == empty || node.board.boardState[i-1][j+1] == opp){

								}
							}
							else if(j == 2){
								//we are in the right column we can move ldiag and fwd
								if(node.board.boardState[i-1][j] == empty){

								}
								if(node.board.boardState[i-1][j-1] == empty || node.board.boardState[i-1][j-1] == opp){

								}
							}
						}
					}
				}
			}
			else {
				opp = 'W';
				self = 'B';
				//it is player 2's turn (Black)
				for(int i = 0; i < 6; i++){
					for(int j = 0; j < 3; j++){
						if(i < 5 && node.board.boardState[i][j] == self){
							if(j == 1){
								//middle
								if(node.board.boardState[i+1][j] == empty){
									
								}
								if(node.board.boardState[i+1][j-1] == empty || node.board.boardState[i+1][j-1] == opp){
									
								}
								if(node.board.boardState[i+1][j+1] == empty || node.board.boardState[i+1][j+1] == opp){
									
								}
							}
							else if(j == 0){
								//right
								if(node.board.boardState[i+1][j] == empty){
									
								}
								if(node.board.boardState[i+1][j+1] == empty || node.board.boardState[i+1][j+1] == opp){
									
								}
							}
							else if(j == 2){
								if(node.board.boardState[i+1][j] == empty){
									
								}
								if(node.board.boardState[i+1][j-1] == empty || node.board.boardState[i+1][j-1] == opp){
									
								}
							}
						}
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
			System.out.println("P: "+p);
			List<GameTreeNode> newBoards = new ArrayList<GameTreeNode>();
			if(player == 1){
				for(int i = board.size()-1; i >= 0; i--){

					//int goal = 0;
					int ahead = i-3;
					int ldiag = i-4;
					int rdiag = i-2;

					//far left
					if(i % 3 == 0){
						if( ahead >= 0 && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, true);
							newBoards.add(gtn);
						}
						if(rdiag >= 1 && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveRightDiag, true);
							newBoards.add(gtn);
						}
					}
					//far right
					else if((i+1) % 3 == 0){
						if( ahead >= 2 && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, true);
							newBoards.add(gtn);
						}
						if(ldiag >= 1 && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveLeftDiag, true);
							newBoards.add(gtn);
						}
					}
					//middle
					else{
						if( ahead >= 1 && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, true);
							newBoards.add(gtn);
						}
						if(rdiag >= 2 && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveRightDiag, true);
							newBoards.add(gtn);

						}
						if(ldiag >= 0 && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveLeftDiag, true);
							newBoards.add(gtn);
						}
					}
				}
			}
			else {
				for(int i = 0; i < board.size(); i++){

					//int goal = board.size();
					int ahead = i+3;
					int ldiag = i+4;
					int rdiag = i+2;

					//if the current slot is on the far left
					if((i+1) % 3 == 0){

						if( (ahead <= 17) && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, false);
							newBoards.add(gtn);
						}
						if((rdiag <= 16) && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveRightDiag, false);
							newBoards.add(gtn);						}
					}
					//far right
					else if(i % 3 == 0){
						if( (ahead <= 15) && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, false);
							newBoards.add(gtn);
						}
						if((ldiag <= 16) && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveLeftDiag, false);
							newBoards.add(gtn);
						}
					}
					//middle
					else{
						if( (ahead <= 16) && board.get(i).equals(p) && board.get(ahead).equals(empty)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveAhead, false);
							newBoards.add(gtn);
						}
						if((rdiag <= 15) && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveRightDiag, false);
							newBoards.add(gtn);
						}
						if((ldiag < 17) && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							GameTreeNode gtn = new GameTreeNode(moveLeftDiag, false);
							newBoards.add(gtn);
						}
					}
				}
			}
			return newBoards;
		}
		public boolean winState(GameTreeNode gtn){
			//all of one type of piece have been captured
			if(!board.contains("B")){
				gtn.score = 1;
				return true;
			}

			if(!board.contains("W")){
				gtn.score = -1;
				return true;
			}

			//a player has reached the home of opponent
			for(int i = 0; i < 3; i++){
				if(board.get(i).equals("W")){
					System.out.println("Winner is W by home row victory");
					gtn.score = 1;
					return true;
				}
			}
			for(int j = board.size()-1; j > 14; j--){
				if(board.get(j).equals("B")){
					System.out.println("Winner is B by home row victory");
					gtn.score = -1;
					return true;
				}
			}

			return false;
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
