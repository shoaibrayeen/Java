import java.util.*;

class Shape {
	private int x, y;
	Scanner in=new Scanner(System.in);
	public void getcoord() {
		System.out.println("\nEnter the coordinates");
		System.out.print("x :\t");
		x=in.nextInt();
		System.out.print("y :\t");
		y=in.nextInt();
	}
	public void showcoord() {
		System.out.println("Coordinates are :");
		System.out.println("x :\t"+x+"\ny :\t"+y);
	}
};

class Rect extends Shape {
	private int l, b;

	public void getcoord() {
		System.out.println("\nEnter length of rectangle:\t");
		l=in.nextInt();
		System.out.println("Enter breadth of rectangle:\t");
		b=in.nextInt();
	}

	public void showcoord() {
		System.out.println("Length :\t"+l);
		System.out.println("Breadth :\t"+b);
	}
};

public class rectOverride {
	public static void main(String args[]) {
		Shape object=new Rect();
		object.getcoord();
		object.showcoord();
	}
}
