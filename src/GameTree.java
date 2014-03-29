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
	
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
	}
}
