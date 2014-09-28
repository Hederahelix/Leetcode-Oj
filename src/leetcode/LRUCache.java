package leetcode;

import java.util.HashMap;

public class LRUCache {
	HashMap<Integer , Integer> map = new HashMap<Integer , Integer>(); 
	int head;
	int[] keyset;
	int[] next;
	int size;
	int capacity;
	
	public void update(int key){
		int font=head;
		int newhead=head;
		while(true)
		{
			if(keyset[newhead]==key)
			{
				break;
			}
			font=newhead;
			newhead=next[newhead];
		}
		next[font]=next[newhead];
		next[newhead]=head;
		head=newhead;
	}
	
	public LRUCache(int capacity) {
		this.capacity=capacity;
		head=-1;
		keyset = new int[capacity];
		next = new int[capacity];
    }
    
    public int get(int key) {
    	if(head==-1)
    		return -1;
    	
    	if(map.containsKey(key)){
    		update(key);
    		return map.get(key);
    	}else{
    		return -1;
    	}
    }
    
    public void set(int key, int value) {
        
    	if(map.containsKey(key))
        {
    		update(key);
        	map.put(key, value);
        }else{
        	if(size<capacity)
        	{
        		
        	}else{
        		
        	}
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
