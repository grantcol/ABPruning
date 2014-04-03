import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTreeNode {

	//public String moveDescription;
	public List<GameTreeNode> children;
	public Board board;
	public boolean maxToMove;
	public int score = 0;

	public GameTreeNode(List<String> initConfig, boolean maxToMove){
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(initConfig, true);
		this.maxToMove = maxToMove;
	}

	public GameTreeNode(){
		this.children = new ArrayList<GameTreeNode>();
	}
	public GameTreeNode(Board b, boolean m2m){
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(b);
		this.maxToMove = m2m;
	}
	public void addChild(GameTreeNode child){
		this.children.add(child);
	}
	public void addChildren(List<GameTreeNode> clist){
		for(GameTreeNode gtn : clist){
			this.children.add(gtn);
		}
	}
	public boolean isEndGame(){
		int whiteCount = 0;
		int blackCount = 0;
		for(int i = 0; i < 3; i++){
			if(this.board.boardState[0][i] == 'W'){
				this.score = 1;
				System.out.println("White wins by homerow victory "+this.score);
				return true;
			}
			else if(this.board.boardState[5][i] == 'B'){
				this.score = -1;
				System.out.println("Black wins by homerow victory "+this.score);
				return true;
			}
		}
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 3; j++){
				if(this.board.boardState[i][j] == 'W'){
					whiteCount++;
				}
				else if(this.board.boardState[i][j] == 'B'){
					blackCount++;
				}
			}
		}
		if(whiteCount == 0){
			this.score = -1;
			return true;
		}
		else if(blackCount == 0){
			this.score = 1;
			return true;
		}
		return false;
	}
	public void printChildren(){
		System.out.println("# of children is: "+children.size());
	}



	public class Board {
		//board spaces read from the top down ie (0,0) = 0 (0,1) = 1
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
		}
		public Board(List<String> rows, boolean dumb){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 3; j++){
					this.boardState[i][j] = rows.get(i).charAt(j);
				}
			}
		}
		public Board(Board b){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 3; j++){
					this.boardState[i][j] = b.boardState[i][j];
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
						if(i > 0){
							if( node.board.boardState[i][j] == self){
								//if we are looking at a white space and are not in the victory row

								if(j == 1){
									//if we are in the middle column we can move lrdiag and fwd

									//forward
									if(node.board.boardState[i-1][j] == empty){
										//System.out.println("space ("+(i-1)+","+j+") is empty");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i-1][j+1] == empty || node.board.boardState[i-1][j+1] == opp){
										//System.out.println("space ("+(i-1)+","+(j+1)+") has an enemy or is empty");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j+1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i-1][j-1] == empty || node.board.boardState[i-1][j-1] == opp){
										//System.out.println("space ("+(i-1)+","+(j-1)+") has an enemy or is empty");
										GameTreeNode move = new GameTreeNode(node.board,false);
										move.board.boardState[i-1][j-1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
								}

								else if(j == 0){
									//we are in the left column we can move rdiag and fwd
									if(node.board.boardState[i-1][j] == empty){
										//System.out.println("space ("+(i-1)+","+j+") is empty");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i-1][j+1] == empty || node.board.boardState[i-1][j+1] == opp){
										//System.out.println("space ("+(i-1)+","+(j+1)+") has an enemy");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j+1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
								}
								else if(j == 2){
									//we are in the right column we can move ldiag and fwd
									if(node.board.boardState[i-1][j] == empty){
										//System.out.println("space ("+(i-1)+","+j+") is empty");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i-1][j-1] == empty || node.board.boardState[i-1][j-1] == opp){
										//System.out.println("space ("+(i-1)+","+(j-1)+") has an enemy");
										GameTreeNode move = new GameTreeNode(node.board, false);
										move.board.boardState[i-1][j-1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
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
						if(i < 5) {
							if(node.board.boardState[i][j] == self){
								if(j == 1){
									//middle
									if(node.board.boardState[i+1][j] == empty){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i+1][j-1] == empty || node.board.boardState[i+1][j-1] == opp){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j-1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i+1][j+1] == empty || node.board.boardState[i+1][j+1] == opp){
										GameTreeNode move = new GameTreeNode(node.board,true);
										move.board.boardState[i+1][j+1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
								}
								else if(j == 0){
									//right
									if(node.board.boardState[i+1][j] == empty){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i+1][j+1] == empty || node.board.boardState[i+1][j+1] == opp){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j+1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
								}
								else if(j == 2){
									if(node.board.boardState[i+1][j] == empty){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
									if(node.board.boardState[i+1][j-1] == empty || node.board.boardState[i+1][j-1] == opp){
										GameTreeNode move = new GameTreeNode(node.board, true);
										move.board.boardState[i+1][j-1] = self;
										move.board.boardState[i][j] = empty;
										node.addChild(move);
									}
								}
							}
						}
					}
				}
			}
		}

		public void printMove(int from, int to, String player){
			System.out.print(player+" moved from "+placeValues.get(from)+" to "+placeValues.get(to));
			System.out.println(" ");
		}
		public void print(){
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 3; j++){
					if(j == 2)
						System.out.println(boardState[i][j]);
					else
						System.out.print(boardState[i][j]);
				}
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
