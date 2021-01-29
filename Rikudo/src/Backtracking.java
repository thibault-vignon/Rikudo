import java.util.ArrayList;

public class Backtracking {
	
	public static void solveBacktracking(ArrayList<ArrayList<Boolean>> graph, int source) {
		
		int s = source;
		
		int n = graph.size();
		int[] path = new int[n];
		int k =1;  //o� on en est dans le chemin
		path[0] = source;
		
		
		
		// on cr�e une copie du graphe
		
		ArrayList<ArrayList<Boolean>> graph2 = new ArrayList<ArrayList<Boolean>>();
		
		for (int i = 0; i<n; i+=1) {
			
			ArrayList<Boolean> ligne = graph.get(i);
			ArrayList<Boolean> ligne2 = new ArrayList<Boolean>();
			ligne2.addAll(ligne);
			graph2.add(i, ligne2);
		}
		
		// on supprime les arcs qui vont vers source
		
		for (int i =0; i<n; i+=1) {
			graph2.get(i).set(source, false);
		}
		
		
		
		
		// pour savoir si il n'y a pas de chemin hamiltonien possible : on est revenus � s et il n'a pas de voisin donc k=0
		// pour savoir si on a trouv� le chemin :  k=n-1
		
		while (k>0 && k<n) {
			
		
		// on cherche le premier voisin
		int j = 0;
		while (j<n && ! graph2.get(s).get(j)) {
			j+=1;
		}
		
		if (j==n) {
			
			// il n'y a plus de voisin � j, on fait demi tour
			k -=1;
			if (k==0) {
				break;    // cas o� source n'a plus de voisin
			}
			s = path[k-1];
			// on remet les arcs qui vont vers path[k]
			for (int i =0; i<n; i+=1) {
				graph2.get(i).set(path[k], graph.get(i).get(path[k]));
			}
			// on supprime provisoirement l'arc qu'on vient de remonter
			graph2.get(path[k-1]).set(path[k], false); 
			
			
			// on remet les arcs qui partent de path[k]
			for (int i=0; i<n; i+=1) {
				graph2.get(path[k]).set(i, graph.get(path[k]).get(i));
			}
			
		}
		
		else {
			// on ajoute j au chemin et on supprime tous les arcs qui vont vers j
			path[k] = j;
			k+=1;

			for (int i =0; i<n; i+=1) {
				graph2.get(i).set(j, false);
			}
			s = j;	
		}
		 
		
		
		
		
	}
		
		if (k==n) {
			System.out.println("Satisfiable problem!");
			for (int i =0; i<n; i+=1) {
			System.out.print(path[i] + " ");
			}
		}
		else {
			System.out.println("Unsatisfiable problem!");
		}
		
	}
	
	public static int countBacktracking(ArrayList<ArrayList<Boolean>> graph, int source, int t) {
		
		
		int s = source;
		
		int compteur = 0;
		
		int n = graph.size();
		int[] path = new int[n];
		int k =1;  //o� on en est dans le chemin
		path[0] = source;
		
		// on supprime les arcs qui vont vers source et qui partent de t
		
				for (int i =0; i<n; i+=1) {
					ArrayList<Boolean> ligne = graph.get(i);
					ligne.set(source, false);
					graph.set(i, ligne);
					ArrayList<Boolean> ligne2 = graph.get(t);
					ligne2.set(i, false);
					graph.set(t, ligne2);
				}
		
		
		// on cr�e une copie du graphe
		
		ArrayList<ArrayList<Boolean>> graph2 = new ArrayList<ArrayList<Boolean>>();
		ArrayList<ArrayList<Boolean>> graph3 = new ArrayList<ArrayList<Boolean>>();
		
		
		for (int i = 0; i<n; i+=1) {
			
			ArrayList<Boolean> ligne = graph.get(i);
			ArrayList<Boolean> ligne2 = new ArrayList<Boolean>();
			ArrayList<Boolean> ligne3 = new ArrayList<Boolean>();
			ligne2.addAll(ligne);
			ligne3.addAll(ligne);
			graph2.add(i, ligne2);
			graph3.add(i, ligne3);
		}
		
		
		// pour savoir si il n'y a plus de chemin hamiltonien possible : on est revenus � s et il n'a pas de voisin donc k=0
		// quand on trouve un chemin : on incr�mente
	
		
		while (k>0 ) {

		if (s==t && k==n) {
			compteur +=1;

		}
		
		// on cherche le premier voisin
		int j = 0;
		while (j<n && ! graph2.get(s).get(j)) {
			j+=1;
		}
		

		if (j==n) {

			
			// il n'y a plus de voisin � j, on fait demi tour
			k -=1;
			if (k==0) {
				break;    // cas o� source n'a plus de voisin
			}
			s = path[k-1];
			
			// on remet les arcs qui vont vers path[k]
			for (int i =0; i<n; i+=1) {
				graph2.get(i).set(path[k], graph.get(i).get(path[k]));
				graph3.get(i).set(path[k], graph.get(i).get(path[k]));
			}
			// on interdit provisoirement l'arc qu'on vient de remonter
			graph2.get(s).set(path[k], false);
			
			// on r�autorise les arcs qui en partent
			
			for (int i=0; i<n; i+=1) {
					graph2.get(path[k]).set(i, graph3.get(path[k]).get(i));
			} 
			
		}
		
		else {
			// on ajoute j au chemin et on supprime tous les arcs qui vont vers j$
			path[k] = j;
			k+=1;

			for (int i =0; i<n; i+=1) {
				graph2.get(i).set(j, false);
				graph3.get(i).set(j, false);
			}
			s = j;	
		}
		
	}
		System.out.println("compteur=" + compteur);
		return compteur;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int limit = 11;
		int[] result = new int[limit - 2];
		int attempts = 1;
		ArrayList<ArrayList<Boolean>> graph = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Boolean> ligne = new ArrayList<Boolean>();

		 ArrayList<Boolean> ligne1 = new ArrayList<Boolean>();
	        ligne1.add(false);
	        ligne1.add(true);
	        ligne1.add(false);
	        ligne1.add(true);
	        ligne1.add(true);
	        graph.add(ligne1);
	       
	        ArrayList<Boolean> ligne2 = new ArrayList<Boolean>();
	        ligne2.add(true);
	        ligne2.add(false);
	        ligne2.add(true);
	        ligne2.add(false);
	        ligne2.add(false);
	        graph.add(ligne2);
	       
	        ArrayList<Boolean> ligne3 = new ArrayList<Boolean>();
	        ligne3.add(false);
	        ligne3.add(true);
	        ligne3.add(false);
	        ligne3.add(false);
	        ligne3.add(false);
	        graph.add(ligne3);
	       
	        ArrayList<Boolean> ligne4 = new ArrayList<Boolean>();
	        ligne4.add(false);
	        ligne4.add(true);
	        ligne4.add(false);
	        ligne4.add(false);
	        ligne4.add(true);
	        graph.add(ligne4);
	        
	        ArrayList<Boolean> ligne5 = new ArrayList<Boolean>();
	        ligne5.add(false);
	        ligne5.add(true);
	        ligne5.add(false);
	        ligne5.add(true);
	        ligne5.add(false);
	        graph.add(ligne5);
	        
	        countBacktracking(graph, 0, 2);
		
		/* for (int n = 2; n < limit; n++) {
			    System.out.println(n);
			    for (int a = 0; a < attempts; a++) {
			    	
		    	graph.clear();
		    	for (int i = 0; i < n*n ; i++) {
		    		ligne = new ArrayList<Boolean>();
		    			if (i % n == 0) {
		    				for (int k = 0; k < n * n; k++) {
		    					if (k == i + 1 || k == i + n || k == i - n) { ligne.add(true); }
		    					else { ligne.add(false); }
		    				}
		    			}
		    			else {if (i % n == n - 1) {
		    				for (int k = 0; k < n * n; k++) {
		    					if (k == i - 1 || k == i + n || k == i - n) { ligne.add(true); }
		    					else { ligne.add(false); }
		    				}
		    			}
		    			else {
		    				for (int k = 0; k < n * n; k++) {
		    					if (k == i - 1 || k == i+1 || k == i + n || k == i - n) { ligne.add(true); }
		    					else { ligne.add(false); }
		    				}
		    			}
		    		}
		    		graph.add(ligne);
		    	}

		    	result[n-2] = Backtracking.countBacktracking(graph, 0, n*n - 1);
			 }
		
		 }
		 
		 System.out.println(result);*/
	}

}
