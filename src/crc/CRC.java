package crc;

public class CRC
{
	static String getSpecialXOR(String b1, String b2)
	{
		String r = "";
		
		for(int i = 0; i < b1.length(); i++)
		{
			r+=Integer.toString(Character.getNumericValue(b1.charAt(i))^Character.getNumericValue(b2.charAt(i)));
		}
		
		r = r.startsWith("0")?r.substring(1):r;
		
		return r;
	}
	
	static String getEmptySequence(String s)
	{
		String r = "";
		
		for(int i = 0; i < s.length()-1; i++)
			r+="0";
		
		return r;
	}
	
	static String getSpecialCase(String s)
	{
		String r = "";
		
		for(int i = 0; i < s.length(); i++)
			r+="0";
		
		return r;
	}
	
	static String getCRC(String data, String standard)
	{
		String dividendo = data;
		String divisor = standard;
		String specialCase = getSpecialCase(divisor);
		
		int p = divisor.length();
		String actual = getSpecialXOR(dividendo.substring(0, divisor.length()), divisor);
		actual+=dividendo.charAt(p);
		p++;
		
//		System.out.println("1: " + actual);
		
		while(p < dividendo.length())
		{
			if(actual.startsWith("0"))
				actual = getSpecialXOR(actual, specialCase);
			else
				actual = getSpecialXOR(actual, divisor);
			
			actual+=dividendo.charAt(p);
//			System.out.println((p-divisor.length()+1) + ": "+ actual);
			
			p++;
		}
		
		String residuo = actual.startsWith("0")?getSpecialXOR(actual, specialCase):getSpecialXOR(actual, divisor);
		
		return residuo;
	}
}
