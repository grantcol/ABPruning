import java.io.IOException;


public class Main {
	
	static GameTree gt;
	static TreeBuilder tb = new TreeBuilder("input.txt");
	
	public static void main(String [] args) throws IOException{
		gt = tb.buildTree();
		gt.print();
	}
}
