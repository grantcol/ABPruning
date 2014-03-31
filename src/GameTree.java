import java.util.ArrayList;
import java.util.List;

public class GameTree {

	private GameTreeNode root;

	public GameTree(){
		root = new GameTreeNode();
	}

	public GameTree(List<String> initConfig) {
		root = new GameTreeNode(initConfig);
	}

	public GameTreeNode getRoot(){
		return this.root;
	}

	public void populate(GameTreeNode gtn, int player){
		gtn.addChildren(findLegalMoves(gtn, player));
		if(!gtn.children.isEmpty()){
			for(GameTreeNode n : gtn.children){
				System.out.println("children: "+gtn.children.size());
				//gtn.board.print();
				populate(n, switchTurn(player));
			}
		}
		else{
			//gtn.board.print();
			//gtn.score = gtn.board.winState();
			//System.out.println(gtn.score);
		}
	}
	public List<GameTreeNode> findLegalMoves(GameTreeNode gtn, int player){
		List<GameTreeNode> children = new ArrayList<GameTreeNode>();
		List<List<String>> legalMoves = gtn.board.legalMoves(player);
		for(List<String> m : legalMoves){
			GameTreeNode child = new GameTreeNode(m);
			children.add(child);
		}
		if(children.size() > 0)
			return children;
		else return null;
	}
	public int switchTurn(int currentPlayer){
		switch (currentPlayer){
		case 1:
			return 2;
		case 2:
			return 1;
		}
		return -1;
	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
	}
}
