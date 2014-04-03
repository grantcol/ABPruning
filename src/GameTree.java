import java.util.List;

public class GameTree {

	private GameTreeNode root;

	public GameTree(){
		root = new GameTreeNode();
	}

	public GameTree(List<String> initConfig) {
		root = new GameTreeNode(initConfig, true);
	}

	public GameTreeNode getRoot(){
		return this.root;
	}

	public int maxValue(GameTreeNode gtn, int alpha, int beta, int depth){
		System.out.println("At a depth of "+depth);
		if(gtn.isEndGame()){
			return gtn.score;
		}
		findLegalMoves(gtn);
		gtn.printChildren();
		System.out.println("EXPANDING");
		gtn.board.print();
		System.out.println("______________ ");
		for(GameTreeNode n : gtn.children){
			n.board.print();
			//n.score = Math.max(alpha, minValue(n,alpha, beta, depth+1));
			alpha = Math.max(alpha, minValue(n,alpha, beta, depth+1));
			System.err.println("alpha: "+alpha);
			if(alpha >= beta){
				return alpha;
			}
		}
		return alpha; 
	}
	
	public int minValue(GameTreeNode gtn, int alpha, int beta, int depth){
		System.out.println("At a depth of "+depth);
		if(gtn.isEndGame()){
			return gtn.score;
		}
		findLegalMoves(gtn);
		gtn.printChildren();
		System.out.println("EXPANDING");
		gtn.board.print();
		System.out.println("______________ ");
		for(GameTreeNode n : gtn.children){
			n.board.print();
			n.score = Math.min(beta, maxValue(n, alpha, beta, depth+1));
			beta = Math.min(beta, maxValue(n, alpha, beta, depth+1));
			System.err.println("beta: "+beta);
			if(beta <= alpha){
				return beta;
			}
		}
		return beta;
	}
	public void findLegalMoves(GameTreeNode gtn){
		gtn.board.makeLegalMoves(gtn);
	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
		System.out.println(root.score);
	}
}
