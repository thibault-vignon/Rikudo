import java.util.ArrayList;

public class BacktrackingLin {
	
	public static void solveBacktrackingLin(ArrayList<ArrayList<Integer>> adja, int source) {
		
		int s = source;
		
		int n = adja.size();
		int[] path = new int[n];
		int k =1;  //o� on en est dans le chemin
		path[0] = source;
		
		// on cr�e une copie des listes d'adjacence
		
		ArrayList<ArrayList<Integer>> adja2 = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i<n; i+=1) {
			
			ArrayList<Integer> ligne = adja.get(i);
			ArrayList<Integer> ligne2 = new ArrayList<Integer>();
			ligne2.addAll(ligne);
			adja2.add(i, ligne2);
		}
		
		
		// on supprime les arcs qui vont vers source
		
		for (int i =0; i<n; i+=1) {
			ArrayList<Integer> ligne = adja2.get(i);
			ligne.remove((Integer)source);
			adja2.set(i, ligne);
		}
		
		// pour savoir si il n'y a pas de chemin hamiltonien possible : on est revenus � s et il n'a pas de voisin donc k=0
		// pour savoir si on a trouv� le chemin :  k=n-1
		
		while (k>0 && k<n) {
			
		
		//on regarde si s a un voisin
					
		if (adja2.get(s).isEmpty()) {
			// il n'y a plus de voisin � j, on fait demi tour
			k -=1;
			if (k==0) {
				break;    // cas o� source n'a plus de voisin
			}
			s = path[k];
			// on remet les arcs qui vont vers s
			for (int i =0; i<n; i+=1) {
				ArrayList<Integer> ligne = adja2.get(i);
				if (adja.get(i).contains(s)) {
					ligne.add(s);
				}
				adja2.set(i, ligne);
			}
			// on supprime l'arc qu'on vient de remonter
			ArrayList<Integer> ligne = adja.get(path[k]);
			ligne.remove((Integer)path[k+1]);
			adja.set(path[k], ligne);
			ArrayList<Integer> ligne2 = adja2.get(path[k]);
			ligne2.remove((Integer)path[k+1]);
			adja2.set(path[k], ligne2);
			
		}
		
		else {
			
			// on prend le premier voisin
			int j = adja2.get(s).get(0);
									
			// on ajoute j au chemin et on supprime tous les arcs qui vont vers j
			path[k] = j;
			k+=1;

			for (int i =0; i<n; i+=1) {
				ArrayList<Integer> ligne = adja2.get(i);
				ligne.remove((Integer)j);
				adja2.set(i, ligne);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		ArrayList<ArrayList<Integer>> adja = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> ligne1 = new ArrayList<Integer>();
		ligne1.add(1);
		ligne1.add(3);
		adja.add(ligne1);
		
		ArrayList<Integer> ligne2 = new ArrayList<Integer>();
		ligne2.add(0);
		ligne2.add(2);
		adja.add(ligne2);
		
		ArrayList<Integer> ligne3 = new ArrayList<Integer>();
		ligne3.add(1);
		ligne3.add(3);
		adja.add(ligne3);
		
		ArrayList<Integer> ligne4 = new ArrayList<Integer>();
		ligne4.add(1);
		adja.add(ligne4);
		
		
		solveBacktrackingLin(adja,2);
		
	}

}

