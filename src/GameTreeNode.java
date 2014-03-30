import java.util.ArrayList;
import java.util.List;


public class GameTreeNode {

	//public String moveDescription;
	public List<GameTreeNode> children;
	public Board board;

	public GameTreeNode(List<String> initConfig){
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(initConfig);
	}

	public GameTreeNode(){
		this.children = new ArrayList<GameTreeNode>();
	}

	public void addChildren(List<GameTreeNode> clist){
		this.children = clist;
	}

	public class Board {
		//board spaces read from the top down ie (0,0) = 0 (0,1) = 1
		public List<String> board = new ArrayList<String>();
		public Board(List<String> rows) {

			for(int i = 0; i < rows.size(); i++){

				String[] slots = rows.get(i).split("");

				for(String s : slots) {
					if(!s.isEmpty()){ //this a pretty shitty way to guard against /n
						this.board.add(s);
					}
				}
			}
		}

		public List<List<String>> legalMoves(int player){

			String opponent = null;
			String p = null;
			String empty = "X";
			switch(player){
			case 1: 
				opponent = "B";
				p = "W";
				break;
			case 2:
				opponent = "W";
				p = "B";
				break;
			}

			//a list of posible board configs we discover as new moves
			List<List<String>> newBoards = new ArrayList<List<String>>();
			if(player == 1){
				for(int i = board.size()-1; i >= 0; i--){

					int goal = 0;
					int ahead = i-3;
					int ldiag = i-4;
					int rdiag = i-2;

					//System.out.println(p+" "+board.get(i)+": "+board.get(i).equals(p)+" space "+i+" goal "+goal+", ahead "+ahead+", rdiag "+rdiag+", ldiag "+ldiag);
					//if the current slot is on the far left
					if(i % 3 == 0){
						if( ahead > goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							System.out.println(" LEFT AHEAD");
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							printAsBoard(moveAhead);
							//System.out.println(moveAhead.toString());
						}
						if(rdiag > goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							System.out.println("LEFT RDIAG");
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							newBoards.add(moveRightDiag);
							printAsBoard(moveRightDiag);
						}
					}
					//far right
					else if((i+1) % 3 == 0){
						if( ahead > goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							printAsBoard(moveAhead);
						}
						if(ldiag > goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							newBoards.add(moveLeftDiag);
							printAsBoard(moveLeftDiag);
						}
					}
					//middle
					else{
						if( ahead > goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							List<String> moveAhead = new ArrayList<String>(board);
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							printAsBoard(moveAhead);
						}
						if(rdiag > goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = new ArrayList<String>(board);
							moveRightDiag.set(rdiag, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							newBoards.add(moveRightDiag);
							printAsBoard(moveRightDiag);

						}
						if(ldiag > goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = new ArrayList<String>(board);
							moveLeftDiag.set(ldiag, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							newBoards.add(moveLeftDiag);
							printAsBoard(moveLeftDiag);
						}
					}
				}
			}
			else {
				for(int i = 0; i < board.size(); i++){

					int goal = 18;
					int ahead = i+3;
					int ldiag = i+4;
					int rdiag = i+2;

					//System.out.println(p+" "+board.get(i)+": "+board.get(i).equals(p)+" space "+i+" goal "+goal+", ahead "+ahead+", rdiag "+rdiag+", ldiag "+ldiag);
					//if the current slot is on the far left
					if(i % 3 == 0){
						if( ahead < goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							List<String> moveAhead = board;
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							printAsBoard(moveAhead);
							//System.out.println(moveAhead.toString());
						}
						if(rdiag < goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = board;
							moveRightDiag.set(ahead, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							newBoards.add(moveRightDiag);
							printAsBoard(moveRightDiag);
						}
					}
					//far right
					else if((i+1) % 3 == 0){
						if( ahead < goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							List<String> moveAhead = board;
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							
						}
						if(ldiag < goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = board;
							moveLeftDiag.set(ahead, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							newBoards.add(moveLeftDiag);
						}
					}
					//middle
					else{
						if( ahead < goal && board.get(i).equals(p) && !board.get(ahead).equals(p)){
							List<String> moveAhead = board;
							moveAhead.set(ahead, moveAhead.get(i));
							moveAhead.set(i, empty);
							newBoards.add(moveAhead);
							printAsBoard(moveAhead);
						}
						if(rdiag < goal && board.get(i).equals(p) && !board.get(rdiag).equals(p)){
							List<String> moveRightDiag = board;
							moveRightDiag.set(ahead, moveRightDiag.get(i));
							moveRightDiag.set(i, empty);
							newBoards.add(moveRightDiag);
							printAsBoard(moveRightDiag);

						}
						if(ldiag < goal && board.get(i).equals(p) && !board.get(ldiag).equals(p)){
							List<String> moveLeftDiag = board;
							moveLeftDiag.set(ahead, moveLeftDiag.get(i));
							moveLeftDiag.set(i, empty);
							newBoards.add(moveLeftDiag);
							printAsBoard(moveLeftDiag);
						}
					}
				}
			}
			if(newBoards.size() == 0)
				System.out.println("NO MOVES");
			return newBoards;
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
