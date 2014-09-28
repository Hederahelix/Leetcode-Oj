package leetcode;

import java.util.Stack;

public class evalRPN {
	
	public  static int fun(String[] tokens)
    {
		Stack stack = new Stack();
		for(String param:tokens)
		{
			if(param.equals("+")||param.equals("-")||param.equals("*")||param.equals("/"))
			{
				int v2 = (Integer)stack.pop();
				int v1 = (Integer)stack.pop();
				
				if(param.equals("+")){
					stack.push(v1+v2);
					
				}else if(param.equals("-")){
					stack.push(v1-v2);
					
				}else if(param.equals("*")){
					stack.push(v1*v2);
					
				}else if(param.equals("/")){
					stack.push(v1/v2);
					
				}
				
			}else{
				stack.push(Integer.parseInt(param));
			}
		}
		
		return (Integer)stack.pop();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr ={"4", "13", "5", "/", "+"};
		System.out.println(fun(arr));
	}

}
