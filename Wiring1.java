package vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.*;




public class Wiring1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num1 = in.nextInt();
		int num2 = in.nextInt();
		int count = 0;
		graph graph = new graph();
		graph.adjNodes = new HashMap<Node,ArrayList<NW>>();
		String start = "";
		String type = "";
		Node a = new Node("");
		while(count<num1) {
			String junc = in.next();
			if(count == 0) {
				start = junc;
			}
			type = in.next();
			if(type.charAt(0)!='l'&&type.charAt(0)!='b'&&type.charAt(0)!='s'&&type.charAt(0)!='o') {
				System.exit(0);
			}
			if(type.charAt(0)=='s') {
				a = new Node(junc);
			}
			if(type.charAt(0)=='l') {
				graph.addNode(junc);
				for(Node n: graph.adjNodes.keySet()) {
					if(n.label==junc) {
						n.type = type;
						n.light = a;
					}
				}
			}
			else {
				graph.addNode(junc);
				for(Node n: graph.adjNodes.keySet()) {
					if(n.label==junc) {
						n.type = type;
					}
				}

			}
			
			count++;
		}
		count = 0;
		Node a1 = new Node("");
		Node a2 = new Node("");

		while(count<num2) {
			count++;
			String n1 = in.next();
			String n2 = in.next();
			int n3 = in.nextInt();
			if(graph.getEdge(n1).equals(new String("light"))&&graph.getEdge(n2).equals(new String("light"))) {
				for(Node n : graph.adjNodes.keySet()) {
					if(n.equals(new Node(n2))) {
						a2 = n;
					}
					if(n.equals(new Node(n1))) {
						a1 = n;
					}
				}
				if(a2.light.equals(a1.light)) {
					graph.addEdge(n1, n2,n3);
				}

			}
			else if(graph.getEdge(n1).equals(new String("light"))) {
				for(Node n : graph.adjNodes.keySet()) {
					if(n.equals(new Node(n1))) {
						if(n.light.equals(new Node(n2))) {
							graph.addEdge(n1, n2,n3);
						}
					}
				}
				}
			else if(graph.getEdge(n2).equals(new String("light"))) {
				for(Node n : graph.adjNodes.keySet()) {
					if(n.equals(new Node(n2))) {
						if(n.light.equals(new Node(n1))) {
							graph.addEdge(n1, n2,n3);
						}
					}
				}
			}
			else if(graph.getEdge(n1).equals(new String("switch"))&&graph.getEdge(n2).equals(new String("switch"))){
					continue;
		}
			else {
				graph.addEdge(n1, n2,n3);
			}
			
		}
		
		graph.Prims(new Node(start));
		System.out.println(graph.total);
		
		
	}
	static class graph{
		HashMap<Node, ArrayList<NW>> adjNodes;
		PriorityQueue<NW> PQ_box = new PriorityQueue<NW>();
		PriorityQueue<NW> PQ_switch = new PriorityQueue<NW>();

		ArrayList<Node> visited = new ArrayList<Node>();
		int total = 0;
		public void addNode(String label) {
			adjNodes.putIfAbsent(new Node(label), new ArrayList<>());
		}
		public void addEdge(String label1, String label2,int w) {
		   Node n1 = new Node(label1);
		   Node n2 = new Node(label2);
		   adjNodes.get(n1).add(new NW(n2,w));
		   adjNodes.get(n2).add(new NW(n1,w));
		}
		public String getEdge(String label3) {
			for(Node n:adjNodes.keySet()) {
				if(n.label.equals(label3)) {
					return n.type;
				}
			}
			return null;
		}
		/*private void percolateUpBox(NW n) {
			
			double a = index/2;
			int pIndex = (int)Math.floor(a);
			if(heap.get(index).compareTo(heap.get(pIndex))<0) {
				swap(pIndex,index);
				percolateUp(pIndex);
			}
			
		}
		*/
	
		public void Prims(Node s) {
			NW start = new NW(s,0);
			PQ_box.add(start);
			while(PQ_box.size()!=0) {
				PriorityQueue<NW> PQ_box1 = new PriorityQueue<NW>();
				for(NW n: PQ_box) {
					PQ_box1.add(n);
				}
				PQ_box = PQ_box1;
				/*
				for(NW n: PQ_switch) {
					System.out.print(n.node.label+" "+n.weight+" ");
				}
				*/
				NW v = PQ_box.poll();

				if(visited.contains(v.node)) {
					continue;
				}
				visited.add(v.node);
				total = total + v.weight;
				for(NW n: adjNodes.get(v.node)) {  
					if(!(visited.contains(n.node))) {
							boolean found = false;
							for(NW i:PQ_box) {
								if(n.node.equals(i.node)){
									found = true;
									if(n.weight<i.weight) {
										i.weight=n.weight;
										i.node.pi=v.node;
										found = true;
	
								}
								}
							}
							for(NW k:PQ_switch) {
								if(n.node.equals(k.node)){
									found = true;
									if(n.weight<k.weight) {
										k.weight=n.weight;
										k.node.pi=v.node;
										found = true;
			
										}
							}
							}
							if(!found) {
								if(getEdge(n.node.label).equals(new String("switch"))) {
									PQ_switch.add(new NW(n.node,n.weight));
									n.node.pi = v.node;
								}
								else{
									PQ_box.add(new NW(n.node,n.weight));
									n.node.pi = v.node;
								}
								
							}
						}
					}
				}
			
			while(PQ_switch.size()!=0) {
				PriorityQueue<NW> PQ_switch1 = new PriorityQueue<NW>();
				for(NW n: PQ_switch) {
					PQ_switch1.add(n);
				}
				PQ_switch = PQ_switch1;
				
				NW v = PQ_switch.poll();
				if(visited.contains(v.node)) {
					continue;
				}
				visited.add(v.node);
				total = total + v.weight;
				for(NW n: adjNodes.get(v.node)) {  
					if(!(visited.contains(n.node))) {
							boolean found = false;
							for(NW i:PQ_switch) {
								if(n.node.equals(i.node)){
									found = true;
									if(n.weight<i.weight) {
										i.weight=n.weight;
										i.node.pi=v.node;
										found = true;
	
								}
							}
							}
							if(!found) {
									PQ_switch.add(new NW(n.node,n.weight));
									n.node.pi = v.node;
								}
								
							}
						}
					}
			}
				
			
			
		}
	
	
		
		
	

	static class NW implements Comparable<NW>{
		Node node;
		int weight;
		public NW(Node node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(node, weight);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NW other = (NW) obj;
			return Objects.equals(node, other.node) && weight == other.weight;
		}
	
		@Override
		public int compareTo(NW s1) {
            if (this.weight > s1.weight) {
                return 1;
            }
            else if (this.weight < s1.weight) {
                return -1;
            }
            return 0;
            }
    
		
		
	}
	static class Node{
		String label;
		String color;
		int d;
		int f;
		Node pi;
		Node light;
		String type;
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
		
	}


}
