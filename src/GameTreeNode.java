import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameTreeNode {

	public String moveDescription;
	public ArrayList<GameTreeNode> children;
	public Board board;

	public GameTreeNode(List<String> initConfig){
		this.moveDescription = "root";
		this.children = new ArrayList<GameTreeNode>();
		this.board = new Board(initConfig);
	}

	public GameTreeNode(){
		this.children = new ArrayList<GameTreeNode>();
	}

	public void addChildren(ArrayList<GameTreeNode> clist){
		this.children = clist;
	}

	public class Board {
		//board spaces read from the top down ie (0,0) = 0 (0,1) = 1
		public Map<Integer, String> board = new HashMap<Integer, String>();
		int count = 0;
		public Board(List<String> rows) {

			for(int i = 0; i < rows.size(); i++){

				String[] slots = rows.get(i).split("");

				for(String s : slots) {
					if(!s.isEmpty()){ //this a pretty shitty way to guard against /n
						this.board.put(count, s);
						count++;
					}
				}
			}
		}

		public void print(){
			System.out.println(board.toString());
		}
	}
}
