import java.util.Scanner;
public class toggleNthBit
{
	public static void main(String[] args)
	{
		Scanner obj=new Scanner(System.in);
		int number,bit;
		System.out.print("\nEnter the Number\t:\t");
		number=obj.nextInt();
		System.out.print("\nEnter the bit you want to toggle\t:\t");
		bit=obj.nextInt();
		number= number^(1<<(bit-1));
		System.out.print("\nNumber after toggling the " + bit + "th bit\t:\t"+number);
		System.out.println();
	}	
}
	
