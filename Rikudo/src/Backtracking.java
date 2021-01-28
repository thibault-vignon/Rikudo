import java.util.ArrayList;

public class Backtracking {
	
	public static void solveBacktracking(ArrayList<ArrayList<Boolean>> graph, int source) {
		
		int s = source;
		
		int n = graph.size();
		int[] path = new int[n];
		int k =1;  //où on en est dans le chemin
		path[0] = source;
		
		
		// on supprime les arcs qui vont vers source
		
				for (int i =0; i<n; i+=1) {
					ArrayList<Boolean> ligne = graph.get(i);
					ligne.set(source, false);
					graph.set(i, ligne);
				}
		
		
		// on crée une copie du graphe
		
		ArrayList<ArrayList<Boolean>> graph2 = new ArrayList<ArrayList<Boolean>>();
		
		for (int i = 0; i<n; i+=1) {
			
			ArrayList<Boolean> ligne = graph.get(i);
			ArrayList<Boolean> ligne2 = new ArrayList<Boolean>();
			ligne2.addAll(ligne);
			graph2.add(i, ligne2);
		}
		
		
		
		
		// pour savoir si il n'y a pas de chemin hamiltonien possible : on est revenus à s et il n'a pas de voisin donc k=0
		// pour savoir si on a trouvé le chemin :  k=n-1
		
		while (k>0 && k<n) {
			
			
		
		// on cherche le premier voisin
		int j = 0;
		while (j<n && ! graph2.get(s).get(j)) {
			j+=1;
		}
		
		if (j==n) {
			// il n'y a plus de voisin à j, on fait demi tour
			k -=1;
			if (k==0) {
				break;    // cas où source n'a plus de voisin
			}
			s = path[k];
			// on remet les arcs qui vont vers s
			for (int i =0; i<n; i+=1) {
				ArrayList<Boolean> ligne = graph2.get(i);
				ligne.set(s, graph.get(i).get(s));
				graph2.set(i, ligne);
			}
			// on supprime l'arc qu'on vient de remonter
			ArrayList<Boolean> ligne = graph.get(path[k]);
			ligne.set(path[k+1], false);
			graph.set(path[k], ligne);
			ArrayList<Boolean> ligne2 = graph2.get(path[k]);
			ligne2.set(path[k+1], false);
			graph2.set(path[k], ligne2); 
			
		}
		
		else {
			// on ajoute j au chemin et on supprime tous les arcs qui vont vers j
			path[k] = j;
			k+=1;

			for (int i =0; i<n; i+=1) {
				ArrayList<Boolean> ligne = graph2.get(i);
				ligne.set(j, false);
				graph2.set(i, ligne);
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
		int k =1;  //où on en est dans le chemin
		path[0] = source;
		
		// on supprime les arcs qui vont vers source et qui partent de t
		
				for (int i =0; i<n; i+=1) {
					ArrayList<Boolean> ligne = graph.get(i);
					ligne.set(source, false);
					graph.set(i, ligne);
					ArrayList<Boolean> ligne2 = graph.get(t);
					ligne2.set(i, false);
					graph.set(t, ligne);
				}
		
		
		// on crée deux copies du graphe : une pour éviter de passer deux fois par le même sommet, l'autre pour s'assurer que si on a réussi un chemin on ne va pas repasser par le même
		
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
		
		
		
		

		
		
		// pour savoir si il n'y a plus de chemin hamiltonien possible : on est revenus à s et il n'a pas de voisin donc k=0
		// quand on trouve un chemin : on incrémente
		
		while (k>0 ) {
			
		if (s==t && k==n) {
			compteur +=1;
		}
	
			
		
		// on cherche le premier voisin
		int j = 0;
		while (j<n && ! graph3.get(s).get(j)) {
			j+=1;
		}
		
		if (j==n) {
			// il n'y a plus de voisin à j, on fait demi tour
			k -=1;
			if (k==0) {
				break;    // cas où source n'a plus de voisin
			}
			s = path[k];
			// on remet les arcs qui vont vers s
			for (int i =0; i<n; i+=1) {
				ArrayList<Boolean> ligne = graph2.get(i);
				ligne.set(s, graph.get(i).get(s));
				graph2.set(i, ligne);
				ArrayList<Boolean> ligne3 = graph3.get(i);
				ligne3.set(s, graph.get(i).get(s));
				graph3.set(i, ligne3);
			}
			// on interdit provisoirement l'arc qu'on vient de remonter
			ArrayList<Boolean> ligne = graph3.get(path[k]);
			ligne.set(path[k+1], false);
			graph3.set(path[k], ligne);
			
			// on réautorise les arcs qui en partent
			
			ArrayList<Boolean> ligne3 = graph3.get(path[k+1]);
			for (int i=0; i<n; i+=1) {
				ligne3.set(i, graph2.get(path[k+1]).get(i));
			}
			graph3.set(path[k+1], ligne3); 
			
			
		}
		
		else {
			// on ajoute j au chemin et on supprime tous les arcs qui vont vers j
			path[k] = j;
			k+=1;

			for (int i =0; i<n; i+=1) {
				ArrayList<Boolean> ligne = graph2.get(i);
				ligne.set(j, false);
				graph2.set(i, ligne);
				ArrayList<Boolean> ligne3 = graph3.get(i);
				ligne3.set(s, graph.get(i).get(s));
				graph3.set(i, ligne);
			}
			s = j;	
		}
		 
		
		
		
		
	}
		
		return compteur;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ArrayList<ArrayList<Boolean>> graph = new ArrayList<ArrayList<Boolean>>();
		
		ArrayList<Boolean> ligne1 = new ArrayList<Boolean>();
		ligne1.add(false);
		ligne1.add(true);
		ligne1.add(false);
		ligne1.add(true);
		graph.add(ligne1);
		
		ArrayList<Boolean> ligne2 = new ArrayList<Boolean>();
		ligne2.add(true);
		ligne2.add(false);
		ligne2.add(true);
		ligne2.add(false);
		graph.add(ligne2);
		
		ArrayList<Boolean> ligne3 = new ArrayList<Boolean>();
		ligne3.add(false);
		ligne3.add(true);
		ligne3.add(false);
		ligne3.add(true);
		graph.add(ligne3);
		
		ArrayList<Boolean> ligne4 = new ArrayList<Boolean>();
		ligne4.add(false);
		ligne4.add(true);
		ligne4.add(false);
		ligne4.add(false);
		graph.add(ligne4);
		
		
		System.out.println(countBacktracking(graph,0,2));
		
	}

}
