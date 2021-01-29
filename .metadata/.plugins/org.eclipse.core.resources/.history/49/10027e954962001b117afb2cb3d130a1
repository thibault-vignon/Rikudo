import java.util.ArrayList;

public class TestNul {

	public static void main(String[] args) {
		
		int sum = 0;
		final int attempts = 50;
		final int limit = 50;
		ArrayList<ArrayList<Boolean>> graph = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Boolean> ligne = new ArrayList<Boolean>();
		
		ArrayList<ArrayList<Boolean>> diamonds = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Integer> lambda = new ArrayList<Integer>();
		for (int k = 0; k < limit; k++) {
			ligne = new ArrayList<Boolean>();
			for (int i = 0; i< limit; i++) {ligne.add(false); }
			diamonds.add(ligne);
		}
		
		lambda.add(0);
		for (int k = 0; k < limit - 1; k++) {lambda.add(null);}
		
		for (int n = limit; n < limit + 1; n++) {

		    for (int a = 0; a < attempts; a++) {
		    	
		    	graph.clear();
		    	for (int i = 0; i < n; i++) {
		    		ligne = new ArrayList<Boolean>();
		    		for (int k = 0; k < n; k++) {
		    			if (Math.random() < 0.6) {
		    				ligne.add(true);
		    			}
		    			else {
		    				ligne.add(false);
		    			}
		    		}
		    		graph.add(ligne);
		    	}

		    	if (SAT.rikudoSAT(graph, diamonds, lambda).length != 0) {
		    		if (Backtracking.solveBacktracking(graph,0) 
		    	}
		    	
		    }

		    }

	}

}
