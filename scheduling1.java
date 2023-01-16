package vector;

import java.util.*;
import java.util.PriorityQueue;




public class scheduling1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean i = true;
		while(i) {
			int num = in.nextInt();
			int num1 = in.nextInt();
			int num2 = in.nextInt();
			if(num==0&&num1==0&&num2==0) {
				break;
			}
			int count = 0;
			graph graph = new graph();
			graph residual = new graph();
			ArrayList<String> strlist = new ArrayList<>();
			ArrayList<String> classlist = new ArrayList<>();
			HashMap<String,ArrayList<String>> pairs = new HashMap<String,ArrayList<String>>();
			while(count<num) {
				String num3 = in.next();
				String num4 = in.next();
				pairs.putIfAbsent(num3,new ArrayList<>());
				if(!(strlist.contains(num3))) {
					strlist.add(num3);
					
				}
				if(!(pairs.get(num3).contains(num4))) {
					pairs.get(num3).add(num4);
				}
				if(!(classlist.contains(num4))) {
					classlist.add(num4);
					
				}
				
				count++;
			}
			int size = num1+ strlist.size()+2;
			graph.adjMatrix = new int[size][size];
			graph.adjMatrix = new int[size][size];
			residual.adjMatrix = new int[size][size];
			residual.adjMatrix = new int[size][size];
			graph.adjCap = new int[size][size];
			for(String s:pairs.keySet()) {
				graph.addEdge(0,strlist.indexOf(s)+1,0);
				residual.addEdge(0,strlist.indexOf(s)+1,num2);
				residual.addEdge(strlist.indexOf(s)+1, 0,0);
				for(String k:pairs.get(s)) {
					graph.addEdge(strlist.indexOf(s)+1,graph.adjMatrix.length-classlist.indexOf(k)-2,0);
					residual.addEdge(strlist.indexOf(s)+1,graph.adjMatrix.length-classlist.indexOf(k)-2,1);
					residual.addEdge(graph.adjMatrix.length-classlist.indexOf(k)-2,strlist.indexOf(k)+1,0);
				}
			}
			for(String s:classlist) {
				graph.addEdge(graph.adjMatrix.length-classlist.indexOf(s)-2, graph.adjMatrix.length-1, 0);
				residual.addEdge(graph.adjMatrix.length-classlist.indexOf(s)-2, graph.adjMatrix.length-1,0);
				residual.addEdge(graph.adjMatrix.length-1, graph.adjMatrix.length-classlist.indexOf(s)-2,0);
			}
			count = 0;
			while(count<num1) {
				String label = in.next();
				int cap = in.nextInt();
				for(String n: classlist) {
					if(n.equals(label)) {
						graph.adjCap[graph.adjMatrix.length-classlist.indexOf(n)-2][graph.adjMatrix.length-1]=cap;
					}
				}
				for(String n: classlist) {
					if(n.equals(label)) {
						residual.adjMatrix[graph.adjMatrix.length-classlist.indexOf(n)-2][graph.adjMatrix.length-1]=cap;
	
					}
				}
				
				count++;
			}
			while(residual.DFS(residual)==1) {
				FF(graph,residual);
				residual.visited = new ArrayList<Integer>();
				residual.visited1 = new ArrayList<Integer>();
	
			}
			int max = 0;
			for(int n:graph.adjMatrix[0]) {
				max += n;
			}
			if(max==num2*strlist.size()) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
		
		
		
	}
	public static void FF(graph G, graph R) {
		int min = R.visited1.get(1);
		for(int n = 1;n<R.visited1.size();n++) {
				if(R.visited1.get(n)<min) {
					min = R.visited1.get(n);
				}
			}
		for(int i = 0;i<R.visited.size()-1;i++) {
			G.adjMatrix[R.visited.get(i)][R.visited.get(i+1)]+=min;
		}
		for(int i = 0;i<R.visited.size()-1;i++) {
			R.adjMatrix[R.visited.get(i)][R.visited.get(i+1)]-=min;
			R.adjMatrix[R.visited.get(i+1)][R.visited.get(i)]+=min;

		}
		
		
	}
	static class graph{
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Integer> visited1 = new ArrayList<>();
		int [][] adjMatrix;
		int [][] adjCap;

		
		
		public void addEdge(int label1, int label2,int w) {
		   adjMatrix[label1][label2] = w;
			}
		public int DFS(graph G) {
			visited1.add(0);
			DFS_Visit(G,0,visited);
			if(visited.contains(adjMatrix.length-1)) {
				return 1;
			}
			else {
				return 0;
			}
			
			}
		public  void DFS_Visit(graph G, int u, ArrayList<Integer> visited){
			visited.add(u);
			if(visited.contains(adjMatrix.length-1)) {
				return;
			}
			
			for(int i =0;i<adjMatrix.length;i++)  {
				if(visited.contains(adjMatrix.length-1)) {
					return;
				}
				if(adjMatrix[u][i]!=0) {
					if(!(visited.contains(i))) {
						visited1.add(adjMatrix[u][i]);
						DFS_Visit(G,i,visited);
					}
				}
			}
			if(visited.contains(adjMatrix.length-1)) {
				return;
			}
			visited.remove(visited.size()-1);
			visited1.remove(visited1.size()-1);
			
		}
	
	}

    
		
		
	}


