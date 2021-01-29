import java.util.ArrayList;

public class NewBacktracking {
	
	public static ArrayList<Boolean> autorisation = new ArrayList<Boolean>();
	public static ArrayList<Integer> path = new ArrayList<Integer>();
	
	public static int auxBacktracking(ArrayList<ArrayList<Integer>> graph, int s, int t, int n, boolean countPaths, int k) {
		
		if (s==t && n==(graph.size() - 1)) {
			return 1;
		}
		
		int number_paths = 0;
		autorisation.set(s, false);
		
		for (int i = 0; i < graph.get(s).size(); i++) {
			if (autorisation.get(graph.get(s).get(i))) {
				int a = auxBacktracking(graph, graph.get(s).get(i), t, n+1, countPaths, k);
				if (a>0) {
					path.set(n + 1, graph.get(s).get(i));
					number_paths += a;
					if (!countPaths && number_paths >= k) {break;}
				}
			}
		}
		
		autorisation.set(s, true);
		return(number_paths);
	}
	
	public static ArrayList<Integer> solveBacktracking(ArrayList<ArrayList<Integer>> graph, int s, int t) {
		autorisation = new ArrayList<Boolean>();
		autorisation.add(true);
		path = new ArrayList<Integer>();
		path.add(s);
		for (int i = 0; i < graph.size() - 1; i++) {
			path.add(-1);
			autorisation.add(true);
		}
		if (auxBacktracking(graph, s, t, 0, false, 1) > 0) {
			System.out.println("Satsifiable problem");
			return(path);
		}
		else {
			System.out.println("Unsatisfiable problem");
			return(null);
		}
	}
	
	public static int countBacktracking(ArrayList<ArrayList<Integer>> graph, int s, int t) {
		autorisation = new ArrayList<Boolean>();
		autorisation.add(true);
		path = new ArrayList<Integer>();
		path.add(s);
		for (int i = 0; i < graph.size() - 1; i++) {
			path.add(-1);
			autorisation.add(true);
		}
		return auxBacktracking(graph, s, t, 0, true, 0);
	}
	
	public static ArrayList<Integer> k_limit_Backtracking(ArrayList<ArrayList<Integer>> graph, int s, int t, int k) {
		autorisation = new ArrayList<Boolean>();
		autorisation.add(true);
		path = new ArrayList<Integer>();
		path.add(s);
		for (int i = 0; i < graph.size() - 1; i++) {
			path.add(-1);
			autorisation.add(true);
		}
		if (auxBacktracking(graph, s, t, 0, false, k) >= k) {
			System.out.println("At least"+k+"paths");
			return(path);
		}
		else {
			System.out.println("Less than"+k+"paths");
			return(null);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> ligne;
		
		ArrayList<Integer> ligne0 = new ArrayList<Integer>();
		ligne0.add(1);
		ligne0.add(3);
		ligne0.add(4);
		graph.add(ligne0);
		
		ArrayList<Integer> ligne1 = new ArrayList<Integer>();
		ligne1.add(2);
		graph.add(ligne1);
		
		ArrayList<Integer> ligne2 = new ArrayList<Integer>();
		graph.add(ligne2);
		
		ArrayList<Integer> ligne3 = new ArrayList<Integer>();
		ligne3.add(1);
		ligne3.add(4);
		graph.add(ligne3);
		
		ArrayList<Integer> ligne4 = new ArrayList<Integer>();
		ligne4.add(3);
		ligne4.add(1);
		graph.add(ligne4);
	        
	    System.out.println(countBacktracking(graph, 0, 2));
	       
	    ArrayList<Integer> results = new ArrayList<Integer>();
	       for (int n = 2; n < 8; n++) {
			    	
		    	graph.clear();
		    	for (int i = 0; i < n*n ; i++) {
		    		ligne = new ArrayList<Integer>();
		    			if (i % n == 0) {
		    				ligne.add(i+1);
		    				if (i >= n) { ligne.add(i-n);}
		    				if (i < n*(n-1)) {ligne.add(i+n);}
		    			}
		    			else {if (i % n == n - 1) {
			    				ligne.add(i-1);
			    				if (i >= n) { ligne.add(i-n);}
			    				if (i < n*(n-1)) {ligne.add(i+n);}
		    			}
		    			else {
		    				ligne.add(i+1);
		    				ligne.add(i-1);
		    				if (i >= n) { ligne.add(i-n);}
		    				if (i < n*(n-1)) {ligne.add(i+n);}
		    			}
		    		}
		    		graph.add(ligne);
		    	}

		    	//results.add(countBacktracking(graph, 0, n*n - 1));
		    	System.out.println(solveBacktracking(graph, 0, n*n - 1));
			    }
	       
	       System.out.println(results);
	}

}