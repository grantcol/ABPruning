import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TreeBuilder {

	private BufferedReader reader;
	private String path; 
	
	public TreeBuilder(String path){
		this.path = path;
	}
	
	public GameTree initTree() throws IOException { 
		
		List<String> lines = new ArrayList<String>();
		//char[][] slots;
		reader = new BufferedReader(new FileReader(path));
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
			
		}
		GameTree gt = new GameTree(lines);
		int result = gt.maxValue(gt.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		System.out.println("THE RESULT IS "+result);
		return gt;
	}
}
