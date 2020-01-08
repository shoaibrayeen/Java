import java.util.Scanner;
public class bankAccount {
	String name;
  	long accountNumber;
  	double balance;
  	final double TAXRATE=7;
  	enum gender{male, female};
  	gender gen;

  	public bankAccount() {
    		name="";
    		accountNumber=0;
    		balance=0;
   		gen=gender.male;
  	}

  	public void printAll() {
    		System.out.println("\n\nname : " + name + "\ngender : " + gen + "\naccount number : " + accountNumber + "\navailable balance : " + balance);
  	}
  	public void deposit(double amt) {
    		balance += amt;
    		System.out.println(amt + "depositted.\navailable balance : " + balance);
  	}
  
	public void withdraw(double amt) {
    		try {
        		if(amt > balance) {
            			throw new Exception();
			}
        		balance-=amt;
        		System.out.println(amt + " has been withdrawn.\navailable balance : " + balance);
      		}
    		catch(Exception e) {
      			System.out.println("you have insufficient balance...");
    		}
  	}
	
  	public double getBalance() {
    		return balance;
  	}

  	public void taxDeduction() {
    		double tax = (TAXRATE * balance)/100;
    		balance -= tax;
    		System.out.println("an amount of " + tax + " has been deducted.\navailable balance : " + balance);
  	}
	
  	public void getDetails() {
    		Scanner scan = new Scanner(System.in);
    		System.out.println("enter the following details :");
    		System.out.print("name : ");
    		name = scan.nextLine();
    		System.out.print("gender(male/female) : ");
    		gen = gender.valueOf(scan.nextLine());
 		System.out.print("account number ");
    		accountNumber = scan.nextLong();
    		System.out.println("account created.");
  	}

  	public static void main(String[] args) {
      		Scanner scan = new Scanner(System.in);
      		bankAccount acc = new bankAccount();
      		acc.getDetails();
     		int ch=0;
      		double amt;
      		do {
        		System.out.println("------------------------------------");
        		System.out.println("1.Deposit\n2.Withdraw.\n3.print account details.\n4.tax deduction\n5.Exit\t\t");
        		ch = scan.nextInt();
        		switch(ch) {
          			case 1	:	System.out.print("enter amount to be depositted : ");
                    				amt = scan.nextDouble();
                    				acc.deposit(amt);
                    				break;
          			case 2	:	System.out.print("enter amount to be withdrawn : ");
                    				amt = scan.nextDouble();
                    				acc.withdraw(amt);
                    				break;
          			case 3 	:	acc.printAll();
                    				break;
          			case 4 	:  	acc.taxDeduction();
          			case 5 	:  	break;
          			default	: 	System.out.println("invalid option...");
        		}
      		} while (ch!=5);

  	}

}
