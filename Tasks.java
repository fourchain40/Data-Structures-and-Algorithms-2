package main;


import java.util.*;


public class Tasks {
	public static void main(String[] args){
		graph graph = new graph();
		graph.adjNodes = new HashMap<node,ArrayList<node>>();
		Scanner in = new Scanner(System.in);
		int num1 = in.nextInt();
		int num2 = in.nextInt();
		if(num1 > Math.pow(10, 4)||num2 > Math.pow(10, 6)) {
			System.exit(0);
		}
		int count = 0;
		while(count<num1) {
			String x = in.next();
			graph.addnode(x);
			count++;
		}
		count = 0;
		while(count<num2) {
			String x = in.next();
			String y = in.next();
			graph.addEdge(x,y);
			count++;
		}		
		graph.DFS(graph);
		for(node n : graph.topological_list) {
			for(node n1 : graph.adjNodes.get(n)) {
				if(graph.topological_list.indexOf(n1)<graph.topological_list.indexOf(n)) {
					System.out.println("IMPOSSIBLE");
					System.exit(0);
				}
			}
		}
		for(node n : graph.topological_list) {
			System.out.print(n.label + " ");
		}
	}

		static class graph{
			int time = 0;
			 HashMap<node, ArrayList<node>> adjNodes;
			 ArrayList<node> topological_list = new ArrayList<>();
			 String path = "";
			
			public void addnode(String label) {
				adjNodes.putIfAbsent(new node(label), new ArrayList<>());
			}
			public void addEdge(String label1, String label2) {
			   node n1 = new node(label1);
			   node n2 = new node(label2);
			   adjNodes.get(n1).add(n2);
			}
			public void DFS(graph G) {
				ArrayList<node> visited = new ArrayList<>();
				for(node n1 : adjNodes.keySet()) {
					if(!(visited.contains(n1))) {
						DFS_Visit(G,n1,visited);
					}
				}
				
				}
			public  void DFS_Visit(graph G, node u, ArrayList<node> visited){
				visited.add(u);
				time++;
				u.d = time;
				for(node v : adjNodes.get(u)) {
					if(!(visited.contains(v))) {
						v.pi = u;
						DFS_Visit(G,v,visited);
					}
				}
				time++;
				u.f = time;
				topological_list.add(0,u);
			}
		}
		static class node{
			String label;
			String color;
			int d;
			int f;
			node pi;
			node(String label){
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
				node other = (node) obj;
				return Objects.equals(label, other.label);
			}
			@Override
			public int hashCode() {
				return Objects.hash(label);
			}
		}
}
