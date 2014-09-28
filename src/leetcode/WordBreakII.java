package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//class record{
//	ArrayList<Integer> index;//包含的单词
//    int current;//现在处于字符串的位置
//    
//	public record(ArrayList<Integer> index, int current) {
//		super();
//		this.index = index;
//		this.current = current;
//	}
//    
//	public record() {
//		super();
//		this.index = new ArrayList<Integer>();
//		this.current = 0;
//	}
//	
//	public void add(ArrayList<Integer> index){
//		this.index.addAll(index);
//	}
//}
//
//class word{
//	String word;//包含的单词
//    int index;//现在处于字符串的位置
//    
//	public word(String word, int index) {
//		super();
//		this.word = word;
//		this.index = index;
//	}
//}
//
//class plan{
//	String scerno;
//	ArrayList<ArrayList<Integer>> index;
//	
//	public plan(String scerno, ArrayList<ArrayList<Integer>> index) {
//		super();
//		this.scerno = scerno;
//		this.index = index;
//	}
//}

public class WordBreakII {
	
	public static List<word>  findWord(char s,String[] dictA)
	{
		List<word> backup = new ArrayList<word>();
		int low = 0,high = dictA.length - 1,index = -1;
		
		while (low <= high) 
		{
	        int mid = (low + high) /2;
	        char midVal = dictA[mid].charAt(0);
	        
	        if (midVal < s)
	        {
	        	low = mid + 1;
	        }else if (midVal > s){
	        	high = mid - 1;
	        }else{
	        	index = mid;
	        	break;
	        }
	    }
		
		if(index!=-1)
		{
			//向前找
			for(int i=0;index-i>-1&&dictA[index-i].charAt(0)==s;i++)
			{
				backup.add(new word(dictA[index-i],index-i));
			}
			//向后找
			for(int i=1;index+i<dictA.length&&dictA[index+i].charAt(0)==s;i++)
			{
				backup.add(new word(dictA[index+i],index+i));
			}
		}
		
		
	    return backup;
	}
	
//	public static List<String> wordBreak(String s, Set<String> dict)
//	{
//		List<String> result = new ArrayList<String>();
//		//1.字典排序
//	    String[] dictA = 	dict.toArray(new String[dict.size()]);
//	    Arrays.sort(dictA, String.CASE_INSENSITIVE_ORDER);
//	    
//	    //2.查找单词
//	    Queue<record> records = new LinkedList<record>();
//	    records.offer(new record());
//	    
//	    record rec;
//	    int size = s.length();
//	    while((rec=records.poll())!=null)
//	  	{
//	    	int current = rec.current;
//	    	
//	    	if(current==size)
//	    	{
//	    		1
//	    		StringBuffer sb = new StringBuffer();
//	    		while(bit.hasNext())
//		    	{
//	    			sb.append(dictA[(Integer) bit.next()]+" ");
//		    	}
//	
//	    		result.add(sb.deleteCharAt(sb.length()-1).toString());
//	    	}else{
//		    	String ss = s.substring(current);
//		    	List<word> backup = findWord(s.charAt(current), dictA);
//		    	Iterator bit = backup.iterator();
//		    	while(bit.hasNext())
//		    	{
//		    		word w = (word)bit.next();
//		    		if(ss.startsWith(w.word))
//		    		{
//		    			int newcurrent = current+w.word.length();
//		    			ArrayList<Integer> newindex = new ArrayList<Integer>(rec.index);
//		    			newindex.add(w.index);
//		    			record newrec = new record(newindex,newcurrent);
//		    			records.offer(newrec);
//		    		}
//		    	}
//	    	}
//	  	}
//		return result;
//	}
	
	public static plan findPlan(String s,ArrayList<plan> map)
	{
		
		for(int i=map.size();i>0;i--)//最长匹配
		{
			plan p =	map.get(i);
			String scerno = p.scerno;
			if(s.startsWith(scerno))
			{
				return p;
			}
		}

		return null;
	}
	
	public static int updatePlan(Queue<record> process,ArrayList<plan> map,int mapcurrent,String s)
	{
		int mincurrent=Integer.MAX_VALUE;
		ArrayList<record> res = new ArrayList<record>();
		Iterator bit = process.iterator();
		while(bit.hasNext())
		{
			record trec = (record)bit.next();
			if(trec.current<mincurrent)
			{
				res.clear();
				mincurrent = trec.current;
				res.add(trec);
			}else if(trec.current==mincurrent){
				res.add(trec);
			}
		}
		
		if(mapcurrent<mincurrent)//(String scerno, ArrayList<ArrayList<Integer>> index)
		{
			String scerno =  s.substring(0, mincurrent);
			ArrayList<ArrayList<Integer>> index = new ArrayList<ArrayList<Integer>>();
			bit = res.iterator();
			while(bit.hasNext())
			{
				index.add(((record)bit.next()).index);
			}
			map.add(new plan(scerno,index));
		}
		return mincurrent;
	}
	
	public static List<String> wordBreak(String s, Set<String> dict)
	{
		List<String> result = new ArrayList<String>();
		//1.字典排序
	    String[] dictA = 	dict.toArray(new String[dict.size()]);
	    Arrays.sort(dictA, String.CASE_INSENSITIVE_ORDER);
	    
	    //2.查找单词
	    ArrayList<plan> map = new ArrayList<plan>();   	    
	    int mapcurrent = 0;
	    
	    Queue<record> process = new LinkedList<record>();
	    ArrayList<Integer> currents = new ArrayList<Integer>();   	 
	    process.offer(new record());
	    
	    record rec;
	    int size = s.length(); 
	    while((rec=process.poll())!=null)
	  	{
	    	int current = rec.current;
	    	
	    	if(current==size)
	    	{
	    		Iterator bit = rec.index.iterator();	
	    		StringBuffer sb = new StringBuffer();
	    		while(bit.hasNext())
		    	{
	    			sb.append(dictA[(Integer) bit.next()]+" ");
		    	}
	
	    		result.add(sb.deleteCharAt(sb.length()-1).toString());
	    	}else{
		    	String ss = s.substring(current);
		    	plan p = findPlan(ss,map);
		    	
		    	if(p==null){
		    		List<word> backup = findWord(s.charAt(current), dictA);
			    	Iterator bit = backup.iterator();
			    	while(bit.hasNext())
			    	{
			    		word w = (word)bit.next();
			    		if(ss.startsWith(w.word))
			    		{
			    			int newcurrent = current+w.word.length();
			    			
			    			ArrayList<Integer> newindex = new ArrayList<Integer>(rec.index);
			    			newindex.add(w.index);
			    			record newrec = new record(newindex,newcurrent);
			    			process.offer(newrec);
			    		}
			    	}
			    	//更新plan
			    	mapcurrent=updatePlan(process,map,mapcurrent,s);
		    	}else{
		    		Iterator bit = p.index.iterator();
		    		while(bit.hasNext())
			    	{
		    			int newcurrent = current+p.scerno.length();
		    			ArrayList<Integer> index = (ArrayList<Integer>) bit.next();
		    			ArrayList<Integer> newindex = new ArrayList<Integer>(rec.index);
		    			newindex.addAll(index);
		    			record newrec = new record(newindex,newcurrent);
		    			process.offer(newrec);
			    	}
		    	}
		    	
	    	}
	    	
	  	}
		return result;
	}
	
	public static Set<String> initDictA(String... args)
	{
		Set<String> dict = new HashSet();  
		for(String tmp:args)
		{
			dict.add(tmp);
		}
		return dict;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Set<String> dict = initDictA("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");  
//		
//		List<String> res = wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",dict);
//	
//		Iterator it = res.iterator();
//		while(it.hasNext())
//	  	{
//			System.out.println(it.next());
//	  	}
		
	}

}
