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

	public int populate(GameTreeNode gtn, int player){
		if(gtn.board.winState() != 0){
			return gtn.board.winState();
		}
		gtn.addChildren(findLegalMoves(gtn, player));
		if(!gtn.children.isEmpty()){
			for(GameTreeNode n : gtn.children){
				System.out.println(n+" has "+gtn.children.size()+" children" );
				populate(n, switchTurn(player));
			}
		}
		return 0;
		
	}
	public List<GameTreeNode> findLegalMoves(GameTreeNode gtn, int player){
		List<GameTreeNode> children = new ArrayList<GameTreeNode>();
		List<List<String>> legalMoves = new ArrayList<List<String>>(gtn.board.legalMoves(player));
		for(List<String> m : legalMoves){
			GameTreeNode child = new GameTreeNode(m);
			children.add(child);
		}
			return children;

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
