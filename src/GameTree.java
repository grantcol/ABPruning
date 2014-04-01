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

	public int maxValue(GameTreeNode gtn, int player, int alpha, int beta){
		if(gtn.board.winState() != 0){
			return gtn.board.winState();
		}
		gtn.addChildren(findLegalMoves(gtn, player));
		for(GameTreeNode n : gtn.children){
			alpha = Math.max(alpha, minValue(n, 1, alpha, beta));
			if(alpha >= beta){
				return alpha;
			}
		}
		return alpha; 
	}

	
	
	public int minValue(GameTreeNode gtn, int player, int alpha, int beta){
		if(gtn.board.winState() != 0){
			//gtn.score = gtn.board.winState();
			return gtn.board.winState();
		}
		gtn.addChildren(findLegalMoves(gtn, player));
		System.out.println("chirren "+gtn.children.size());
		for(GameTreeNode n : gtn.children){
			beta = Math.min(beta, maxValue(n,2, alpha, beta));
			if(beta >= alpha){
				return beta;
			}
		}
		return beta;
	}
	
	public List<GameTreeNode> findLegalMoves(GameTreeNode gtn, int player){
		List<GameTreeNode> children = new ArrayList<GameTreeNode>(gtn.board.legalMoves(player));
		return children;

	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
		System.out.println(root.score);
	}
}
