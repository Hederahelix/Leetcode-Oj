package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

class process{
	ArrayList<Integer> index;//包含的单词
    int pos;//现在处于字符串的位置
    
	public process(ArrayList<Integer> index, int pos) {
		super();
		this.index = index;
		this.pos = pos;
	}
}

class plan{
	String scerno;//字符串
	ArrayList<ArrayList<Integer>> index;//匹配的模式
	
	public plan(String scerno, ArrayList<ArrayList<Integer>> index) {
		super();
		this.scerno = scerno;
		this.index = index;
	}
}

public class WordBreakII2 {
	static ArrayList<plan> Plans = new ArrayList<plan>();   	 //已有的专家库
	static Queue<process> Processes = new LinkedList<process>();	 //现有的进程
	static List<String> result = new ArrayList<String>();
	static String[] dictA;
	static HashMap<Character,String>  dictIndex = new HashMap<Character,String>();
	 
	public static plan findPlan(String s)
	{
		return  null;
	}
	
	public static boolean addPlan(String s)
	{
		return  true;
	}
	
	public static boolean addProcess(int pos,ArrayList<Integer> index)
	{
		return  true;
	}
	
	public static boolean isComplete(String s,process p)
	{
		return  true;
	}
	
	public static boolean addResult(process p)
	{
		Iterator it = p.index.iterator();	
		StringBuffer sb = new StringBuffer();
		while(it.hasNext())
    	{
			sb.append(dictA[(Integer) it.next()]+" ");
    	}

		result.add(sb.deleteCharAt(sb.length()-1).toString());
		return  true;
	}
	
	public static boolean initDict()
	{
		int size = dictA.length;
		char cchar = 0; 
		int begin = 0;
		boolean hold = false;
		for(int i=0;i<size;i++)
		{
			if(!dictIndex.containsKey(dictA[i].charAt(0)) && !hold)
			{
				begin = i;
				cchar = dictA[i].charAt(0);
				hold = true;
			}
			
			if(dictA[i].charAt(0)!=cchar)
			{
				dictIndex.put(cchar, begin+","+(i-1));
				hold = false;
			}
			
			if(!dictIndex.containsKey(dictA[i].charAt(0)) && !hold)
			{
				begin = i;
				cchar = dictA[i].charAt(0);
				hold = true;
			}
		}
		dictIndex.put(cchar, begin+","+(size-1));
		
		return true;
	}
	
	public static boolean checkDict(String s,process p)
	{
		String ss = s.substring(p.pos);
		char firstchar = ss.charAt(0);
		if(dictIndex.containsKey(firstchar))
		{
			
			int begin = Integer.parseInt(dictIndex.get(firstchar).split(",")[0]);
			int end    = Integer.parseInt(dictIndex.get(firstchar).split(",")[1]);
			for(int i=begin;i<=end;i++)
			{
				if(ss.startsWith(dictA[i]))
				{
					ArrayList<Integer> newindex = new ArrayList<Integer>(p.index);
					newindex.add(i);
					process newp = new process(newindex,p.pos+dictA[i].length());
					Processes.offer(newp);
				}
			}
			
		}else{
			int low = 0,high = dictA.length - 1,index = -1;
			
			while (low <= high) 
			{
		        int mid = (low + high) /2;
		        char midVal = dictA[mid].charAt(0);
		        
		        if (midVal < firstchar)
		        {
		        	low = mid + 1;
		        }else if (midVal > firstchar){
		        	high = mid - 1;
		        }else{
		        	index = mid;
		        	break;
		        }
		    }
			
			if(index!=-1)
			{
				ArrayList<Integer> indexs = new ArrayList<Integer>();
				
				//向前找
				for(int i=0;index-i>-1&&dictA[index-i].charAt(0)==firstchar;i++)
				{
					if(ss.startsWith(dictA[index-i]))
					{
						ArrayList<Integer> newindex = new ArrayList<Integer>(p.index);
						newindex.add(index-i);
						process newp = new process(newindex,p.pos+dictA[index-i].length());
						Processes.offer(newp);
					}
				}
				//向后找
				for(int i=1;index+i<dictA.length&&dictA[index+i].charAt(0)==firstchar;i++)
				{
					if(ss.startsWith(dictA[index+i]))
					{
						ArrayList<Integer> newindex = new ArrayList<Integer>(p.index);
						newindex.add(index+i);
						process newp = new process(newindex,p.pos+dictA[index+i].length());
						Processes.offer(newp);
					}
				}
		}
		
			
			//plan newplan = new plan();//public plan(String scerno, ArrayList<ArrayList<Integer>> index)
		}
		
		return  true;
	}
	
	public static List<String> wordBreak(String s, Set<String> dict)
	{
		//1.字典排序
	    dictA = 	dict.toArray(new String[dict.size()]);
	    Arrays.sort(dictA, String.CASE_INSENSITIVE_ORDER);
	    
		addProcess(0,new ArrayList<Integer>());
		process p;
		while((p=Processes.poll())!=null)
		{
			if(isComplete(s,p)){
				//该进程已经结束
				addResult(p);
			}else{
				//该进程没有结束
				
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
