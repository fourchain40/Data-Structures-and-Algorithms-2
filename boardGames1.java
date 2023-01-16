package main;
import java.util.*;
public class boardGames1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num1 = in.nextInt();
		if(num1>1000) {
			System.exit(0);
		}
		Graph graph = new Graph();
		graph.adjMatrix = new int[num1][num1];
		int num2 = in.nextInt();
		if(num2>Math.pow(10, 6)) {
			System.exit(0);
		}
		int count = 0;
		while(count<num2){
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			if((n1<0||n1>num1-1)||(n2<0||n2>num1-1)) {
				System.exit(0);
			}
			graph.addEdge(n1, n2);
			count++;
		}
		int num3 = in.nextInt();
		if(num3>1000) {
			System.exit(0);
		}
		count=0;
		while(count<num3) {
			int n = in.nextInt();
			for(int i = 0;i<graph.adjMatrix.length;i++) {
				graph.adjMatrix[i][n]=0;
				graph.adjMatrix[n][i]=0;
			}
			count++;
		}
		graph.DFS(graph);
	/*	
		*/
	}
	
	static class Graph{
		int [][] adjMatrix;
		 ArrayList<Integer> visited = new ArrayList<>();
		public void addEdge(int label1, int label2) {
		   adjMatrix[label1][label2] = 1;
		   adjMatrix[label2][label1] = 1;
		}
		public void DFS(Graph G) {
			DFS_Visit(G,0,visited);
			}
		public  void DFS_Visit(Graph G, int u, ArrayList<Integer> visited){
			visited.add(u);
			if(visited.contains(adjMatrix.length-1)) {
				String ans = "";
				for(int a : visited) {
					if(visited.indexOf(a) == visited.size()-1) {
						ans = ans+a;
						}
					else 
						ans = ans+a+"-";
					}
				System.out.println(ans);
				visited.remove(visited.size()-1);
				return;
			}
			for(int i =0;i<adjMatrix.length;i++) {
				if(adjMatrix[u][i]==1) {
					if(!(visited.contains(i))) {
						DFS_Visit(G,i,visited);
					}
				}
			}
			visited.remove(visited.size()-1);
			//topological_list.add(0,u);
		}
		
		
	}
	static class Node{
		String label;
		String color;
		int d;
		int f;
		Node pi;
		Node(String label){
			this.label = label;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return Objects.equals(label, other.label);
		}
		@Override
		public int hashCode() {
			return Objects.hash(label);
		}
		public int compareTo(Node another) {
			int num = Integer.valueOf(label);
			int num1 = Integer.valueOf(another.label);
			if(num<num1) {
				return -1;
			}
			else if(num>num1) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
}
