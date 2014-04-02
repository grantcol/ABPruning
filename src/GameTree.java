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
			gtn.score = gtn.board.winState();
			return gtn.score;
		}
		gtn.addChildren(findLegalMoves(gtn, switchPlayer(player)));
		System.err.println("max children "+gtn.children.size());
		for(GameTreeNode n : gtn.children){
			//n.board.print();System.out.println("The board as it stands");
			alpha = Math.max(alpha, minValue(n, switchPlayer(player), alpha, beta));
			System.err.println("alpha: "+alpha);
			if(alpha >= beta){
				return alpha;
			}
		}
		return alpha; 
	}

	
	
	public int minValue(GameTreeNode gtn, int player, int alpha, int beta){
		if(gtn.board.winState() != 0){
			gtn.score = gtn.board.winState();
			return gtn.board.winState();
		}
		gtn.addChildren(findLegalMoves(gtn, switchPlayer(player)));
		System.out.println("min children "+gtn.children.size());
		for(GameTreeNode n : gtn.children){
			beta = Math.min(beta, maxValue(n,switchPlayer(player), alpha, beta));
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
