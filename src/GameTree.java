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

	public void populate(GameTreeNode gtn){
		gtn.addChildren(findLegalMoves(gtn, 1));
		if(!gtn.children.isEmpty()){
			System.out.println("NODES FOUND: "+gtn.children.size());
			for(int i = 0; i < gtn.children.size(); i++){
			//for(GameTreeNode n : gtn.children){
				GameTreeNode n = gtn.children.get(i);
				System.out.println("WORKING ON: "+i);
				populate(n);
			}
		}
	}
	public List<GameTreeNode> findLegalMoves(GameTreeNode gtn, int player){
		List<GameTreeNode> children = new ArrayList<GameTreeNode>();
		List<List<String>> legalMoves = gtn.board.legalMoves(player);
		for(List<String> m : legalMoves){
			//System.out.println(m.toString());
			GameTreeNode child = new GameTreeNode(m);
			children.add(child);
		}
		return children;
	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
	}
}
