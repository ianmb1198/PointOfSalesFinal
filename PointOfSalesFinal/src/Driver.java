import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args)
	{

		Scanner cin = new Scanner(System.in);
		Controller cont = new Controller();
		System.out.println("Welcome...");
		cont.welcomeScreen();
		cont.cinClose();
		cin.close();
		
	}
}
