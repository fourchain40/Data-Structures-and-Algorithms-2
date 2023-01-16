package vector;
import java.util.*;


public class daycare {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		int[] answer = new int[2];
		while(count!=2) {
			int num = in.nextInt();
			ArrayList<Room> sameRooms = new ArrayList<Room>();
			ArrayList<Room> lowRooms = new ArrayList<Room>();
			ArrayList<Room> highRooms = new ArrayList<Room>();
			for(int i = 0;i<num;i++) {
				int num1 = in.nextInt();
				int num2 = in.nextInt();
				if(num1<num2) {
					lowRooms.add(new Room(num1,num2));
				}
				else if(num1>num2) {
					highRooms.add(new Room(num1,num2));
				}
				else {
					sameRooms.add(new Room(num1,num2));
				}
			}
			Room[] sameRooms1 = new Room[sameRooms.size()];
			for(int i =0;i<sameRooms1.length;i++) {
				sameRooms1[i]=sameRooms.get(i);
			}
			Room[] lowRooms1 = new Room[lowRooms.size()];
			for(int i =0;i<lowRooms1.length;i++) {
				lowRooms1[i]=lowRooms.get(i);
			}
			Room[] highRooms1 = new Room[highRooms.size()];
			for(int i =0;i<highRooms1.length;i++) {
				highRooms1[i]=highRooms.get(i);
			}
			Arrays.sort(lowRooms1,new iSort());
			Arrays.sort(highRooms1,new nSort());
			Arrays.sort(sameRooms1,new nSort());
			/*for(int i =0;i<highRooms1.length;i++) {
				System.out.println(highRooms1[i].remodel);
			}*/
			answer[count] = getCapacity(sameRooms1,lowRooms1,highRooms1);
			count++;
		}
		for(int i : answer) {
			System.out.println(i);
		}
	}
	public static int getCapacity(Room[] same, Room[] low, Room[] high) {
		int open = 0;
		int cap = 0;
		int samecount = 0;
		int lowcount = 0;
		int highcount = 0;
		if(low.length>0) {
			cap+=low[0].old;
			open+=low[0].remodel;
			lowcount++;
		}
		else if(same.length>0) {
			cap+=same[0].old;
			open+=same[0].remodel;
			samecount++;
		}
		else {
			cap+=high[0].old;
			open+=high[0].remodel;
			highcount++;
		}
		for(int i =lowcount;i<low.length;i++) {
			if(open-low[i].old>=0) {
				open = open-low[i].old;
				open += low[i].remodel;
				
			}
			else {
				cap+=low[i].old-open;
				open = low[i].remodel;
			}
		}
		for(int i = samecount;i<same.length;i++) {
			if(open-same[i].old>=0) {
				open = open-same[i].old;
				open += same[i].remodel;
			}
			else {
				cap+=same[i].old-open;
				open = same[i].remodel;
			}
		}
		for(int i = highcount;i<high.length;i++) {
			if(open-high[i].old>=0) {
				open = open-high[i].old;
				open += high[i].remodel;
			}
			else {
				cap+=high[i].old-open;
				open = high[i].remodel;
			}
		}
		return cap;
	}
	public static class Room{
		int old;
		int remodel;
		public Room(int old, int remodel) {
			this.old = old;
			this.remodel = remodel;
		}
	}
	public static class iSort implements Comparator<Room>{
		 public int compare(Room s1,Room s2) {
	            if (s1.old > s2.old) {
	                return 1;
	            }
	            else if (s1.old < s2.old) {
	                return -1;
	            }
	            return 0;
	            }
	}
	public static class nSort implements Comparator<Room>{
		 public int compare(Room s1,Room s2) {
	            if (s1.remodel > s2.remodel) {
	                return -1;
	            }
	            else if (s1.remodel < s2.remodel) {
	                return 1;
	            }
	            return 0;
	            }
	}
	
}
