import java.io.IOException;


public class Main {
	
	static GameTree gt;
	static TreeBuilder tb = new TreeBuilder("input-8.txt");
	
	public static void main(String [] args) throws IOException{
		gt = tb.initTree();
		gt.print();
	}
}
