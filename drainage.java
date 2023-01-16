package vector;
import java.util.*;
public class drainage {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int count = 0;
		while(count<num) {
			String location = in.next();
			int num1 = in.nextInt();
			int num2 = in.nextInt();
			path[][] grid = new path[num1][num2];
			for(int i = 0;i<num1;i++) {
				for(int j = 0;j<num2;j++) {
					grid[i][j] = new path(in.nextInt(),0,i,j);
				}
			}
			System.out.println(location+": "+ findPathNumber(grid));
			count++;
		}
		in.close();
	}
	public static int findPathNumber(path[][] grid) {
		int max = 0;
		for(int i = 0;i<grid.length;i++) {
			for(int j = 0;j<grid[0].length;j++) {
				if(grid[i][j].path==0) {
					findPath(i,j,grid);
				}
				if(grid[i][j].path>max) {
					max = grid[i][j].path;
				}
			}
		}
		return max;
		
	}
	public static int findPath(int i, int j, path[][]grid) {
		int num0 = 1;
		int num1 = 1;
		int num2 = 1;
		int num3 = 1;
		
		ArrayList<path> next = new ArrayList<path>();
		if(grid.length==1) {
			if(j==0) {
				next.add(grid[i][j+1]);
			}
				
			else if(j==grid[0].length-1) {
				next.add(grid[i][j-1]);
			}
			else {
				next.add(grid[i][j+1]);
				next.add(grid[i][j-1]);
			}
			}
		else if(grid[0].length==1) {
			if(i==0) {
				next.add(grid[i+1][j]);
			}
			else if(i==grid.length-1) {
				next.add(grid[i-1][j]);
			}
			else {
				next.add(grid[i-1][j]);
				next.add(grid[i+1][j]);
			}
		}
		else if(j==0) {
			if(i==0) {
				next.add(grid[i+1][j]);
				next.add(grid[i][j+1]);
			}
			else if(i==grid.length-1) {
				next.add(grid[i-1][j]);
				next.add(grid[i][j+1]);
			}
			else {
				next.add(grid[i+1][j]);
				next.add(grid[i][j+1]);
				next.add(grid[i-1][j]);
			}
			
		}
		else if(j==grid[0].length-1) {
			if(i==0) {
				next.add(grid[i+1][j]);
				next.add(grid[i][j-1]);
			}
			else if(i==grid.length-1) {
				next.add(grid[i-1][j]);
				next.add(grid[i][j-1]);
			}
			else {
				next.add(grid[i+1][j]);
				next.add(grid[i][j-1]);
				next.add(grid[i-1][j]);
			}
		}
		else if(i==0) {
			next.add(grid[i][j+1]);
			next.add(grid[i][j-1]);
			next.add(grid[i+1][j]);
		}
		else if(i==grid.length-1) {
			next.add(grid[i][j+1]);
			next.add(grid[i][j-1]);
			next.add(grid[i-1][j]);
		}
		else {
			next.add(grid[i][j+1]);
			next.add(grid[i][j-1]);
			next.add(grid[i-1][j]);
			next.add(grid[i+1][j]);
		}
		for(int s = 0; s<next.size();s++) {
			if(next.get(s).num<grid[i][j].num) {
				if(s == 0) {
					if(next.get(s).path!=0) {
						num0 = num0 + next.get(s).path;
					}
					else {
						num0 = num0 + findPath(next.get(s).r,next.get(s).c,grid);
					}
				}
				if(s == 1) {
					if(next.get(s).path!=0) {
						num1 = num1 + next.get(s).path;
					}
					else {
						num1 = num1 + findPath(next.get(s).r,next.get(s).c,grid);
					}
				}
				if(s == 2) {
					if(next.get(s).path!=0) {
						num2 = num2 + next.get(s).path;
					}
					else {
						num2 = num2 + findPath(next.get(s).r,next.get(s).c,grid);
					}
				}
				if(s == 3) {
					if(next.get(s).path!=0) {
						num3 = num3 + next.get(s).path;
					}
					else {
						num3 = num3 + findPath(next.get(s).r,next.get(s).c,grid);
					}
				}
			}
		}
		int[] nums = new int[4];
		nums[0]=num0;
		nums[1]=num1;
		nums[2]=num2;
		nums[3]=num3;
		Arrays.sort(nums);
		grid[i][j].path=nums[3];
		return nums[3];
	}
	public static int[] findMax(int[][]grid) {
		int max = 0;
		int[] coordinates = new int[2];
		for(int i = 0;i<grid.length;i++) {
			for(int j = 0;j<grid[0].length;j++) {
				if(grid[i][j]>max) {
					max = grid[i][j];
					coordinates[0]=i;
					coordinates[1]=j;
				}
			}
		}
		return coordinates;
	}
public static class path{
	int num;
	int path;
	int r;
	int c;
	public path(int num, int path, int r, int c) {
		this.num = num;
		this.path = path;
		this.r = r;
		this.c = c;
	}
}
}
