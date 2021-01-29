import java.util.ArrayList;

	import javafx.application.Application;
	import javafx.stage.Stage;   
	import javafx.scene.chart.NumberAxis;
	import javafx.scene.Scene;
	import javafx.scene.chart.LineChart;
	
public class TestBacktracking extends Application {
	
	@Override
	public void start(Stage stage) {
		
			long startTime = 0;
			long endTime = 0;
			long totalTime = 0;
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> ligne = new ArrayList<Integer>();
			
			long sum = 0;
			final int attempts = 20;
			final int limit = 50;
		
	    	NumberAxis xAxis= new NumberAxis(0, limit + 5, 5);
	    	NumberAxis yAxis= new NumberAxis(0, 3000, 10);

		    xAxis.setLabel("Number of vertices");
		    yAxis.setLabel("Runtime (milliseconds)");

		    LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		    chart.setTitle("Runtime of Backtracking solver for different types of graphs");
		    
		    LineChart.Series complete = new LineChart.Series();
		    complete.setName("Complete graphs");
		    
		    LineChart.Series grid = new LineChart.Series();
		    grid.setName("Grid graphs");
		    
		    LineChart.Series cycle = new LineChart.Series();
		    cycle.setName("Cycle graphs");
		    
		    LineChart.Series random = new LineChart.Series();
		    random.setName("Random graphs");
		    
		    
		    //First we test complete graphs : we build them for different 
		    //Numbers of vertices and measure the execution time of our solver
		    
		    for (int n = 2; n < limit + 1; n++) {
		    	
		    	sum = 0;
			    
			    for (int a = 0; a < attempts; a++) {
			    	
		    	graph.clear();
		    	for (int i = 0; i < n; i++) {
		    		ligne = new ArrayList<Integer>();
		    		for (int k = 0; k < n; k++) {
		    				ligne.add(k);
		    		}
		    		graph.add(ligne);
		    	}
		    	
		    	startTime = System.nanoTime();
		    	NewBacktracking.solveBacktracking(graph, 0, n-1);
		    	endTime = System.nanoTime();
		    	totalTime = (endTime - startTime) / 1000 ;
		    	
		    	sum += totalTime;
			    }
		    	
		    	complete.getData().add(new LineChart.Data(n, sum / attempts));
		    }
		    
		    chart.getData().add(complete);

		    //Now we test cycle graphs
		    
		    for (int n = 2; n < limit + 1; n++) {
		    
		    sum = 0;
		    
		    for (int a = 0; a < attempts; a++) {
		    	
		    	graph.clear();
		    	for (int i = 0; i < n; i++) {
		    		ligne = new ArrayList<Integer>();
		    		ligne.add((i+1) % n);
		    		graph.add(ligne);
		    	}
		    	
		    	startTime = System.nanoTime();
		    	NewBacktracking.solveBacktracking(graph, 0, n-1);
		    	endTime = System.nanoTime();
		    	totalTime = (endTime - startTime) / 1000 ;
		    	
		    	sum += totalTime;
		    }
		    	
		    	cycle.getData().add(new LineChart.Data(n, sum / attempts));
		    }
		    
		    chart.getData().add(cycle);
		    
		    //Now we test grid graphs
		    
		    for (int n = 2; n < (int) (Math.sqrt(limit)) + 1; n++) {
		    	
		    	sum = 0;
			    
			    for (int a = 0; a < attempts; a++) {
			    	
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
		    	
			    
		    	
		    	startTime = System.nanoTime();
		    	NewBacktracking.solveBacktracking(graph, 0, n*n - 1);
		    	endTime = System.nanoTime();
		    	totalTime = (endTime - startTime) / 1000 ;
		    	
		    	sum += totalTime;
		    	
			    }
		    	
		    	grid.getData().add(new LineChart.Data(n * n, sum / attempts));
		    }
		    
		    chart.getData().add(grid);

		    //Now we generate graphs with n vertices and a 60% chance for each edge to exist
		    
		    /*for (int n = 2; n < limit + 1; n++) {
		    
		    sum = 0;
		    
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
		    	
		    	startTime = System.nanoTime();
		    	Backtracking.solveBacktracking(graph, 0);
		    	endTime = System.nanoTime();
		    	totalTime = (endTime - startTime) / 1000 ;
		    	
		    	sum += totalTime;
		    }
		    	
		    	random.getData().add(new LineChart.Data(n, sum / attempts));
		    }
		    
		    chart.getData().add(random);*/
		    Scene view = new Scene(chart, 1000, 600);
		    stage.setScene(view);
		    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
