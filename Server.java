   import java.net.*;
   import java.io.*;
import java.util.*;
 	
           


    class Clientcrc
{

 	
                public static  int msg[];
		public static int gen[];
		public static int app_msg[];
		public static int rem[];
		public static int trans_msg[];
		public static int msg_bits,gen_bits,total_bits; 


	public static void main(String [] args) throws Exception{
		Socket socket = new Socket("localhost",2001);
		System.out.println("Got connected...");


		//BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                PrintWriter pw = new PrintWriter(socket.getOutputStream());	 
              
               
                

 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        System.out.println("\n enter no. of bits in message:");
		msg_bits=Integer.parseInt(br.readLine());
		msg=new int[msg_bits];
		System.out.println("\n Enter message bits:");
		for(int i=0;i<msg_bits;i++)
		{
			msg[i]=Integer.valueOf(br.readLine());
		}

		System.out.println("\n enter no. of bits in gen:");
		gen_bits=Integer.parseInt(br.readLine());
		gen=new int[gen_bits];
		System.out.println("\n Enter gen bits:");
		for(int i=0;i<gen_bits;i++)
		{
			gen[i]=Integer.valueOf(br.readLine());
   		}

		total_bits=msg_bits+gen_bits-1;
		
		app_msg=new int[total_bits];
		rem=new int[total_bits];
		trans_msg=new int[total_bits];

		for(int i=0;i<msg.length;i++)
		{
			app_msg[i]=msg[i];
		}

		System.out.println("\n Message bits are:");
		for(int i=0;i<msg_bits;i++)
		{
			System.out.print(msg[i]);
		}

		System.out.println("\n Gen bits are:");
		for(int i=0;i<gen_bits;i++)
		{
			System.out.print(gen[i]);
		}

		System.out.println("\n Appended Message is:");
		for(int i=0;i<app_msg.length;i++)
		{
			System.out.print(app_msg[i]);
		}

		for(int j=0;j<app_msg.length;j++)
		{
			rem[j]=app_msg[j];
		}

		rem=computecrc(app_msg,gen,rem);

		for(int i=0;i<app_msg.length;i++)
		{
			trans_msg[i]=(app_msg[i]^rem[i]);
		}
char c[]=new char[trans_msg.length];
		System.out.println("\n Transmitted Message from the transmitter is:");
		for(int i=0;i<trans_msg.length;i++)
		{
			System.out.print(trans_msg[i]);
		}
		System.out.println(" ");
for(int i=0;i<trans_msg.length;i++)
		{
			c[i]=Character.forDigit(trans_msg[i],10);
System.out.println(c[i]);
		}
              
		
String str=new String(c);
                System.out.println(str);
                pw.println(str);

                pw.flush();

   
		
	}


	static int[] computecrc(int app_msg[],int gen[],int rem[])
	{
		int current=0;
		while(true)
		{
			for(int i=0;i<gen.length;i++)
			{
				rem[current+i]=(rem[current+i]^gen[i]);
			}

			while(rem[current]==0 && current!=rem.length-1)	
			{
				current++;
			}
			
			if((rem.length-current)<gen.length)
			{
				break;
			}
		}
		return rem;
	}
}
