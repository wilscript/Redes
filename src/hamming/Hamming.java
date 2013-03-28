package hamming;

import java.util.Vector;

class BitParidad
{
	public String bit;
	
	private void calcularParidad(Vector<Integer> posiciones, String dato)
	{
		int a = 0;
		
		for(int i = 0; i < posiciones.size(); i++)
		{
			if(dato.charAt(posiciones.elementAt(i))=='1')
			{
				a++;
			}
		}
		
		bit = a%2==0?"0":"1";
	}
	
	BitParidad(Vector<Integer> posiciones, String dato)
	{
		this.calcularParidad(posiciones, dato);
	}
};

public class Hamming
{
	public static String reverse(String s) 
	{
	    return new StringBuffer(s).reverse().toString();
	}
	
	public static int valueOfA = 65;
	
	private static int getParityAmount(String s)
	{
		int a = 0;
		
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i)!='0' && s.charAt(i)!='1')
				a++;
		}
		
		return a;
	}
	
	static String getStringWithParity(String s)
	{
		String r = "";
		int amount = getParityAmount(s);
		String paridades[] = new String[amount]; for(int i = 0; i < paridades.length; i++){paridades[i]="";}
		
		int b = amount;
		int bound = s.length();	
		int index = 1;
		
		for(int i = 0; i < b; i++)
		{
			index = i+1;
			Vector<Integer> pos = new Vector<Integer>();
			
			for(int j = 1; j <= bound; j++)
			{
				String tmp = reverse(Integer.toBinaryString(j));
				
				if(tmp.length() >= index)
				{
					if(tmp.charAt(index-1)=='1')
					{
						pos.addElement(j-1);
						//System.out.println(j);
					}
				}
			}
			
//			pos.addElement(3);
//			pos.addElement(4);
//			pos.addElement(5);
//			pos.addElement(6);
			BitParidad bit = new BitParidad(pos, s);
			paridades[i] = bit.bit;
			
			if(i==0)
				r = s.replace((char)(valueOfA+i), paridades[i].charAt(0));
			else
				r = r.replace((char)(valueOfA+i), paridades[i].charAt(0));
		}
		
//		Vector<Integer> pos = new Vector<Integer>();
//		pos.addElement(3);
//		pos.addElement(4);
//		pos.addElement(5);
//		pos.addElement(6);
//		
//		BitParidad bit = new BitParidad(pos, s);
//		System.out.println(bit.bit);
		
//		r = s.replace('A', paridades[0].charAt(0));
//		r = r.replace('B', paridades[0].charAt(0));
//		...
//		r = r.replace('D', paridades[0].charAt(0));
		
		return r;
	}
	
	static String getStringWithPositions(String original)
	{
		String transformed = "";
		Vector <Integer>posiciones = new Vector<Integer>();
		
		int n = 1;
		int amount = 0;
		posiciones.addElement(n);
		
		for(int i = 0; i < original.length(); i+=2)
		{	
			if(n < original.length())
			{
				amount++;
				n*=2;
				posiciones.addElement(n);
			}
		}

		int finalLength = original.length()+posiciones.size();
		String f = "";
		int o = 0, p = 0;
		
		boolean keep = true;
		
		for(int i = 0; i < finalLength; i++)
		{
			Integer value = posiciones.elementAt(p)-1;
			if(value.equals(i) && keep)
			{
				f+=(char)(valueOfA+p);
				p++;
				if(p==posiciones.size())
				{
					p = 0;
					keep = false;
				}
			}
			else
			{
				f+=original.charAt(o);
				o++;
			}
		}
//		
//		p = 0;
//		
//		for(int i = 0, j = 0; i < finalLength; i++)
//		{
//	
//				if(j< posiciones.size() && posiciones.elementAt(j).equals(i+1))
//				{
//					transformed+="X";
//					j++;
//				}
//		
//			else
//			{	
//				transformed+=original.charAt(p);
//				p++;
//			}
//		}
//		
//		return transformed;
		
		return f;
	}
	
	public static String getCodedSequence(String original)
	{
		String withPos = getStringWithPositions(original);
//		System.out.println(withPos);
		
		return getStringWithParity(withPos);
	}
	
	
//	public static void main(String[] args)
//	{
//		String original = "110110";//"1001000";
//		
//		String coded = getCodedSequence(original);
//	}
}
