package leetcode;

public class maxProduct {
	
//	public static int fun(int[] A) {
//        int max=Integer.MIN_VALUE,num=0;
//        int head=1,tailor=1;//子序列的头,尾
//		
//		int size = A.length;
//        for(int i=0;i<size;i++)
//        {
//        	if(A[i]!=0)
//        	{
//        		if(num!=0)//不是全新的序列
//        		{
//        			int sign;
//        			if(A[i]!=head)
//        				sign = (A[i]-head)/(Math.abs(A[i]-head));//sign=1 递增  sign=-1 递减
//        			else
//        				sign = Integer.MAX_VALUE;
//        			
//        			if(A[i]-tailor==sign)//序列继续延续
//        			{
//        				num=num*A[i];
//        				tailor=A[i];
//        			}else{
//        				//一个序列结束,开始判断乘
//                    	
//                    	if(num<0)//序列全部是负数
//                		{
//                			if(head<tailor)
//                				num=num/tailor;//序列递增
//                			if(head>tailor)
//                				num=num/head;//序列递减
//                		}
//                		
//                		if(num>max)
//            				max=num;
//                		
//                		head = A[i];
//            			tailor = A[i];
//            			num = A[i];
//        			}
//        		}else{//全新的序列开始
//        			head = A[i];
//        			tailor = A[i];
//        			num = A[i];
//        		}
//        		
//        	}else{
//        		//一个序列结束,开始判断乘
//            	
//            	if(num<0)//序列全部是负数
//        		{
//        			if(head<tailor)
//        				num=num/tailor;//序列递增
//        			if(head>tailor)
//        				num=num/head;//序列递减
//        		}
//        		
//        		if(num>max)
//    				max=num;
//        		
//        		num=0;//查找新的序列
//        	}
//        }
//        
//    	if(num<0)//序列全部是负数
//		{
//			if(head<tailor)
//				num=num/tailor;//序列递增
//			if(head>tailor)
//				num=num/head;//序列递减
//		}
//		
//		if(num>max)
//			max=num;
//        
//		return max;
//    }

	public static int fun(int[] A) {
        int max=Integer.MIN_VALUE,num=0;
        int head=1,tailor=1,negative=0;
	
		int size = A.length;
		int i;
		for( i=0;i<size;i++)
        {
        	if(A[i]>0)
        	{
        		if(num!=0)
        			num*=A[i];
        		else
        			num=A[i];
        		
        		if(negative==0)//还没遇到第一个负数
        		{
            			head*=A[i];
        		}else{	
            			tailor*=A[i];
        		}
        		
        	}else if(A[i]<0){
        		if(num!=0)
        			num*=A[i];
        		else
        			num=A[i];
        		
        		if(negative==0)//遇到第一个负数
        		{
            			head*=A[i];
        		}
        		tailor=A[i];
        		negative++;
        	}else{
        		if(negative%2!=0)
        		{
        			if(!(i-2<0||A[i-2]==0))
        			{
        				if(head<tailor)
            				num/=tailor;
            			else
            				num/=head;
        			}
        		}
        		
        		if(num<0)
        			num=0;
        		
        		if(max<num)
    				max=num;
        		
        		num=0;
        		negative=0;
        		head=1;
        		tailor=1;
        	}
        }
        
		if(negative%2!=0)
		{
			if(!(i-2<0||A[i-2]==0))
			{
				if(head<tailor)
    				num/=tailor;
    			else
    				num/=head;
			}
		}
		
		if(max<num)
			max=num;
        
		return max;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={-1,2,0};
		System.out.println( fun(a));
	}

}
