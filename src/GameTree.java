import java.util.ArrayList;
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

	public int maxValue(GameTreeNode gtn, int player, int alpha, int beta, int depth){
		System.out.println("At a depth of "+depth);
		if(gtn.isTerminal()){
			return gtn.score;
		}
		gtn.addChildren(findLegalMoves(gtn, 1));
		for(GameTreeNode n : gtn.children){
			n.score = Math.max(n.score, minValue(n,2, alpha, beta, depth+1));
			alpha = Math.max(alpha, n.score);
			System.err.println("alpha: "+alpha);
			if(alpha >= beta){
				return alpha;
			}
		}
		return alpha; 
	}

	
	
	public int minValue(GameTreeNode gtn, int player, int alpha, int beta, int depth){
		System.out.println("At a depth of "+depth);
		if(gtn.isTerminal()){
			return gtn.score;
		}
		gtn.addChildren(findLegalMoves(gtn, 2));
		for(GameTreeNode n : gtn.children){
			n.score = Math.min(n.score, maxValue(n,1, alpha, beta, depth+1));
			beta = Math.min(beta, n.score);
			System.err.println("beta: "+beta);
			if(beta <= alpha){
				return beta;
			}
		}
		return beta;
	}
	
	public List<GameTreeNode> findLegalMoves(GameTreeNode gtn, int player){
		List<GameTreeNode> children = new ArrayList<GameTreeNode>(gtn.board.legalMoves(player));
		return children;

	}
	public int switchPlayer(int player){
		switch(player){
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 0;
		default:
			return 0;
		}
	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
		System.out.println(root.score);
	}
}
