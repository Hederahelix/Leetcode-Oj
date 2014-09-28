package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}


public class maxPoints {
	
	public  static int fun(Point[] points)
    {
		int size = points.length,maxnum=2;
		HashMap<String,HashMap> lines = new HashMap<String,HashMap>();//线-点
		HashMap<String,String> used = new HashMap<String,String>();
		
		if(size==0)
			return 0;
		
		if(size==1)
			return 1;
		
		//任选两个点，不分顺序
		Point p1, p2; double a, b;
		for(int i=0;i<size;i++)
		{
			for(int j=i+1;j<size;j++)
			{
				p1=points[i];
				p2=points[j];
				String pkey;
				if(p1.x+p1.y>p2.x+p2.y)
					pkey = p2.x+","+p2.y+";"+p1.x+","+p1.y;
				else
					pkey = p1.x+","+p1.y+";"+p2.x+","+p2.y;
				
				String key;
				if(used.containsKey(pkey))
					key=used.get(pkey);
				else{
					if(p1.x!=p2.x)
					{
						a=(p1.y-p2.y+0.0)/(p1.x-p2.x);
						b=p1.y-a*p1.x;
						key = a+":"+(b+"00000").substring(0, 5);
					}else{
						key = "Vert"+p1.x;
					}
					used.put(pkey, key);
				}
				
				
				
				if(lines.containsKey(key))
				{
					HashMap ps = lines.get(key);
					int num = ps.entrySet().size();
					if(!ps.containsKey(i))
					{
						ps.put(i, 1);
						num++;
					}
						
					if(!ps.containsKey(j))
					{
						ps.put(j, 1);
						num++;
					}
					
					if(num>maxnum)
						maxnum=num;
						
					lines.put(key,ps);
				}else{
					HashMap ps = new HashMap();
					ps.put(i, 1);
					ps.put(j, 1);
					lines.put(key, ps);
				}
			}
		}
		
//		Iterator iter = lines.entrySet().iterator(),iter2; 
//		while (iter.hasNext()) { 
//		    Map.Entry entry = (Map.Entry) iter.next(); 
//		    Map val = (Map) entry.getValue(); 
//		    System.out.print(val.entrySet().size()+" "+entry.getKey());
//		    
//		    iter2 = val.entrySet().iterator(); 
//	    	while (iter2.hasNext()) { 
//	    		entry = (Map.Entry) iter2.next(); 
//	    		int key = (Integer) entry.getKey(); 
//	    		System.out.print(key+" ");
//	    	}
//	    	System.out.print("\n");
//		} 
		
		return maxnum;
    }
	
	public  static Point[] make(String t)
    {
		String[] arr =  t.split(",");
		int size = arr.length;
		Point[] points = new Point[size/2];
		
		for(int i=0;i<size;i++)
		{
			if(i%2==0)
				arr[i] = arr[i].substring(1);
			else
				arr[i] = arr[i].substring(0, arr[i].length()-1);
		}
		
		for(int i=0;i<size;i+=2)
			points[i/2] = new Point(Integer.parseInt(arr[i]),Integer.parseInt(arr[i+1]));
		
		return points;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point[] points = make("(560,248),(0,16),(30,250),(950,187),(630,277),(950,187),(-212,-268),(-287,-222),(53,37),(-280,-100),(-1,-14),(-5,4),(-35,-387),(-95,11),(-70,-13),(-700,-274),(-95,11),(-2,-33),(3,62),(-4,-47),(106,98),(-7,-65),(-8,-71),(-8,-147),(5,5),(-5,-90),(-420,-158),(-420,-158),(-350,-129),(-475,-53),(-4,-47),(-380,-37),(0,-24),(35,299),(-8,-71),(-2,-6),(8,25),(6,13),(-106,-146),(53,37),(-7,-128),(-5,-1),(-318,-390),(-15,-191),(-665,-85),(318,342),(7,138),(-570,-69),(-9,-4),(0,-9),(1,-7),(-51,23),(4,1),(-7,5),(-280,-100),(700,306),(0,-23),(-7,-4),(-246,-184),(350,161),(-424,-512),(35,299),(0,-24),(-140,-42),(-760,-101),(-9,-9),(140,74),(-285,-21),(-350,-129),(-6,9),(-630,-245),(700,306),(1,-17),(0,16),(-70,-13),(1,24),(-328,-260),(-34,26),(7,-5),(-371,-451),(-570,-69),(0,27),(-7,-65),(-9,-166),(-475,-53),(-68,20),(210,103),(700,306),(7,-6),(-3,-52),(-106,-146),(560,248),(10,6),(6,119),(0,2),(-41,6),(7,19),(30,250)");
//		for(Point p:points)
//			System.out.println(p.x+" "+p.y);
		System.out.println(fun(points));
	}

}
