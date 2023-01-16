package vector;
import java.util.*;


public class Trading {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int count = 0;
			String num = in.next();
			if(num!=null) {
				int num1 = Integer.valueOf(num);
				if(num1<2||num1>100000) {
					System.exit(0);
				}
				Point[] ptList = new Point[num1];
				while(count<num1) {
					double num2 = in.nextDouble();
					double num3 = in.nextDouble();
					if(Math.abs(num2)>50000||Math.abs(num3)>50000) {
						System.exit(0);
					}
					ptList[count]= new Point(num2,num3);
					count++;
				}
				Arrays.sort(ptList,new xSort());
				double median = (ptList[0].x+ptList[ptList.length-1].x)/2;
				double l = getClosestLeft(ptList,median);
				double r = getClosestRight(ptList,median);
				if(l>r) {
					double m = getClosestMiddle(ptList,r,median);
					
					if(l>m) {
						if(r>m) {
							if(m<10000) {
								System.out.printf("%.4f %n",m);
							}
							else {
								System.out.println("infinity");
							}
						}
						else {
							if(r<10000) {
								System.out.printf("%.4f %n",r);
							}
							else {
								System.out.println("infinity");
							}
						}
					}
					else {
						if(r>l) {
							if(l<10000) {
								System.out.printf("%.4f %n",r);
							}
							else {
								System.out.println("infinity");
							}
						}
						else {
							if(r<10000) {
								System.out.printf("%.4f %n",r);
							}
							else {
								System.out.println("infinity");
							}
						}
					}
				}
				else {
					double m = getClosestMiddle(ptList,l,median);
					if(l>m) {
						if(r>m) {
							if(m<10000) {
								System.out.printf("%.4f %n",m);
							}
							else {
								System.out.println("infinity");
							}
						}
						else {
							if(r<10000) {
								System.out.printf("%.4f %n",r);
							}
							else {
								System.out.println("infinity");
							}
						}
					}
					else {
						if(r>l) {
							if(l<10000) {
								System.out.printf("%.4f %n",l);
							}
							else {
								System.out.println("infinity");
							}
						}
						else {
							if(r<10000) {
								System.out.printf("%.4f %n",r);
							}
							else {
								System.out.println("infinity");
							}
						}
					}
				}
				
		}
			else {
				break;
			}
	}
	
	}
	public static double getClosestLeft(Point[] points, double median) {
		double ans = 100000;
    	for(int i = 0; i<points.length;i++) {
    		if(points[i].x <= median) {
    			for(int j = i+1;j<points.length;j++) {
    				if(points[j].x <= median) {
	    				double dist = Math.sqrt( Math.pow(points[j].x - points[i].x,2) + Math.pow(points[j].y - points[i].y,2));
	    				if(dist<ans) {
	    					ans = dist;
	    				}
    				}
    			}
    		}
    	}
    	return ans;
    }
	public static double getClosestRight(Point[] points, double median) {
		double ans = 100000;
    	for(int i = 0; i<points.length;i++) {
    		if(points[i].x > median) {
    			for(int j = i+1;j<points.length;j++) {
    				if(points[j].x > median) {
	    				double dist = Math.sqrt( Math.pow(points[j].x - points[i].x,2) + Math.pow(points[j].y - points[i].y,2));
	    				if(dist<ans) {
	    					ans = dist;
	    				}
    				}
    			}
    		}
    	}
    	return ans;
    }
	public static double getClosestMiddle(Point[] points, double edge, double median) {
		double ans = 100000;
		Arrays.sort(points,new ySort());
    	for(int i = 0; i<points.length;i++) {
    		if(points[i].x>=median - edge&&points[i].x<=median+edge) {
    			for(int j = i+1;j<i+8;j++) {
    				if(j==points.length) {
    					break;
    				}
    				if(points[j].x>=median - edge&&points[j].x<=median+edge) {
    					double dist = Math.sqrt( Math.pow(points[j].x - points[i].x,2) + Math.pow(points[j].y - points[i].y,2));
	    				if(dist<ans) {
	    					ans = dist;
	    				}
    	    		}

    			}
    		}
    	}
    	return ans;
	}
	static class Point{

		double x;
	    double y;

	    public Point(double x, double y) {
	        this.x = x;
	        this.y = y;
	    }
	    
	   
	   
	}
	static class xSort implements Comparator<Point>{
		 public int compare(Point s1,Point s2) {
	            if (s1.x > s2.x) {
	                return 1;
	            }
	            else if (s1.x < s2.x) {
	                return -1;
	            }
	            return 0;
	            }
	}
	static class ySort implements Comparator<Point>{
		 public int compare(Point s1,Point s2) {
	            if (s1.y > s2.y) {
	                return 1;
	            }
	            else if (s1.y < s2.y) {
	                return -1;
	            }
	            return 0;
	            }
	}
}
