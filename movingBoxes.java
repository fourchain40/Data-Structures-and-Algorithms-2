package vector;
import java.util.*;


public class movingBoxes {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int count = 0;
		while(count<num) {
			int b = in.nextInt();
			int m = in.nextInt();
			int c = in.nextInt();
			company[] companies = new company[c];
			for(int i = 0;i<c;i++) {
				companies[i]=new company(in.next(),in.nextInt(),in.nextInt());
			}
			printMin(b,m,companies,count+1);
			count++;
		}
		
	}
	public static void printMin(int b, int m,company[] companies, int num){
		for(company c: companies) {
			findMin(b,m,c,0);
		}
		Arrays.sort(companies,new cSort());
		System.out.println("Case "+num);
		for(company c:companies) {
			System.out.println(c.label+" "+(int)c.solution);
		}
	}
	public static void findMin(double b, int m, company c,double num) {
		if(b==m) {
			c.solution = num;
			return;
		}
		if(b-Math.ceil(b/2)<m) {
			num = num + (b-m)*c.first;
			findMin(m,m,c,num);
		}
		else {
			if(Math.ceil(b/2)*c.first<c.second) {
				num = num + (Math.ceil(b/2)*c.first);
				findMin(b-Math.ceil(b/2),m,c,num);
			}
			else {
				num = num + c.second;
				findMin(b-Math.ceil(b/2),m,c,num);
			}
		}
			
	}
	public static class company{
		String label;
		int first;
		int second;
		double solution;
		public company(String label, int first, int second) {
			this.label = label;
			this.first = first;
			this.second = second;
		}
	}
	public static class cSort implements Comparator<company>{
		 public int compare(company s1,company s2) {
	            if (s1.solution > s2.solution) {
	                return 1;
	            }
	            else if (s1.solution < s2.solution) {
	                return -1;
	            }
	            return 0;
	            }
	}

}
