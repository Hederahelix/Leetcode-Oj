package leetcode;

public class reverseWords {
	
	public  static String fun(String s)
    {
        String[] parts = s.split(" ");  	
        
        String tmp = "";
        for(int i=parts.length-1;i>-1;i--)
        {
        	if(parts[i].equals(""))
        		continue;
        	
        	if(i>0)
        		tmp += parts[i]+" ";
        	else
        		tmp += parts[i];
        }
        return tmp.trim();
    }
	
	public  static void test(String s)
    {
        String[] parts = s.split(" ");  	
        

        for(int i=0;i<parts.length;i++)
        {
        	
        	System.out.println(i+":"+parts[i]);
        }

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
