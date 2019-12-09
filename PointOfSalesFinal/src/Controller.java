import java.util.Scanner;

public class Controller
{
	private Tab tab;
	private Tab[] tabs;
	
	int count;
	int currentTab;
	int currentItemIndex;
	int eodCount;
	
	Scanner cin = new Scanner(System.in);
	
	public Controller()
	{
		tabs = new Tab[5];
		count = 0;
		eodCount = 0;
		//welcomeScreen();
	}

//--------------------------------------------------------------------------------------------------------------------------//
	
	/**
	 * Welcome Screen Portion and Main Tab Controllers
	 */
	public void welcomeScreen()
	{
		Scanner cin = new Scanner(System.in);
		int choice;
		do
		{
			System.out.println("****************************************\n" + 
								"1. Begin Tab\n2. Open Existing Tab\n3. Print EoD Report\n" +
								"****************************************");
			System.out.print("--> ");
			choice = cin.nextInt();
			cin.nextLine();
			if (choice == 1)
			{
				beginTab();
			}
			else if (choice == 2)
			{
				openExistingTab();
			}
			else 
			{
				System.out.println("Error... Incorrect Input.");
			}
			
		} while (choice != 1 || choice != 2);
	}
	
	public void beginTab()
	{
		Scanner cin = new Scanner(System.in);
		int input;
		boolean test;
		do
		{
			System.out.print("\nTab Number: ");
			input = cin.nextInt();
			cin.nextLine();
			test = doesTabExist(input);
			if (test == true)
			{
				System.out.println("\nERROR: Tab number already exists...\n");
				continue;
			}
			else
				break;
			
		} while (test == true);

		tabs[count] = new Tab(input);
		count++;
		System.out.println();
		menuDisplay(count - 1);
	}

	public void beginTab(int input)
	{
		boolean test;
		do
		{
			test = doesTabExist(input);
			if (test == true)
			{
				System.out.println("\nERROR: Tab number already exists...\n");
				continue;
			}
			else
				break;
			
		} while (test == true);

		tabs[count] = new Tab(input);
		count++;
	}
	
	public void openExistingTab()
	{
		if (count == 0)
		{
			System.out.println("\nERROR: There are no tabs that are open...\n");
			welcomeScreen();
		}
		else
		{
			int input;
			do {
				System.out.println("\n************************Open Tabs************************");
				for (int i = 0; i < count; i++)
				{
					System.out.println((i + 1) + ". " + tabs[i].getTabNumber());
				}
				System.out.println("*********************************************************");
				System.out.print("-->");
				input = cin.nextInt();
				cin.nextLine();
				
				if ((input) > count)
				{
					System.out.println("\nERROR: Tab does not exist.\n");
					continue;
				}
				else
					break;
			} while ((input) > count);

			tabDisplay(input - 1);
			menuDisplay(input - 1);
		}
	}
	
	public boolean doesTabExist(int testTabNum)
	{
		
		for (int i = 0; i < count; i++)
		{
			
			if (tabs[i].getTabNumber() == testTabNum)
				return true;
		}
		return false;
	}
	
	public void payTab(int currentTabIndex)
	{
		/**TODO:
		 * 
		 * 
		 * 
		 * Mark that the tab has been payed for.
		 * 
		 * 
		 */
		//Display tab and total.
		System.out.println("\nPayment Screen");
		tabDisplay(currentTabIndex);
		
		//Enter how much cash the customer had given.
		System.out.print("Enter amount in cash customer gave: $");
		double cashAmount = cin.nextDouble();
		cin.nextLine();
		tab = tabs[currentTabIndex];
		
		//Calculate the change due to the customer and display it.
		double cashChange = cashAmount - tab.getFoodTotal();
		System.out.println("Change due: $" + cashChange);
		
	
		
		//Call the closeTab method.
		closeTab(currentTabIndex);
		
	}
	
	public void closeTab(int currentTabIndex)
	{
		/**TODO:
		 * 
		 * 
		 * Save sales and tip info in a EOD report.
		 */
		
		//Close Tab
		for (int i = 0; i < count; i++)
		{
			if (tabs[i] != null)
			{
				//Swap all existing tabs forward in array.
				if (tabs[i].equals(tabs[currentTabIndex]))
				{
					
					tabs[i] = tabs[count - 1];
					tabs[count - 1] = null;
					count --;
					break;
				}
			}
		}
	}

	public void tabWriter(String item, double price, int currentTabIndex, String[] arr)
	{
		tabs[currentTabIndex].addItem(item, price);
		tabDisplay(currentTabIndex);
	}
	
	public void itemRemover(int currentTabIndex)
	{
		
		boolean itemRemoved;
		tabDisplay(currentTabIndex);
		System.out.print("Which item do you need voided?\n--> ");
		int choice = cin.nextInt();
		cin.nextLine();
		tab = tabs[currentTabIndex];
		itemRemoved = tab.removeItem(choice);
		
		if (itemRemoved == true)
		{
			System.out.println("Item successfully removed.\n");
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
		}
		else
		{
			System.out.println("Item doesn't exist.\n");
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
		}
	}
	
	public void tabDisplay(int currentTabIndex)
	{
		tab = tabs[currentTabIndex];
		System.out.println("\n----------------------------------Tab " + tab.getTabNumber() + 
						   "----------------------------------\n");
		System.out.println(tab);
		System.out.println("--------------------------------------------------------------------------\n");
	}
	
	public void cinClose()
	{
		cin.close();
	}
//--------------------------------------------------------------------------------------------------------------------------//	
	
	/**
	 * Main Menu and Menu Categories Portion
	 */
	public void menuDisplay(int currentTabIndex)
	{
		int choice;

		System.out.println("***************** Menu *****************");
		System.out.println("1. Appetizers\n2. Garden\n3. Sandwhiches\n4. Burgers\n" +
				"5. Flatbreads\n6. MacNCheeses\n7. Skillets\n8. Tacos\n9. Sides\n10.KidsMeals\n11. NA Beverages\n\n-1. Send\n-2. Pay\n-3. Void");
		System.out.println("****************************************");
		System.out.print("--> ");
		choice = cin.nextInt();
		cin.nextLine();
		System.out.println();
		openCategory(choice, currentTabIndex);
	}
	
	private String[] Appetizers = { "", "Campfire Smplr,15.39",
			"Rodeo Nachos,12.09",
			"Chx Tenders,9.67",
			"Chips+Queso,7.69",
			"Fried Pickles,9.11",
			"Chick-a-Dilla,10.99",
			"Onion Rings,9.11",
			"Beer Fries,9.11",
			"Waffle Fried,7.69",
			"Bavarian Prtzl,8.79" };
	private String[] Garden = { "", "Cowboy Salad,13.19",
			"Buffalo Chx Salad,12.09",
			"Sthwst Taco Salad,12.64",
			"House Combo Salad,10.99" };
	private String[] Sandwiches = { "", "Billy Philly Sw,12.63",
			   "Blazing Brisk Sw,11.49",
			   "Roadhouse Sw,13.19",
			   "8HourPullPork Sw,10.99",
			   "Pot Roast Dip Sw,12.64", 
			   "Stkhouse Wrap,12.64",
			   "MsqChx Q Wrap,10.49", };
	private String[] Burgers = { "", "The Classic Burger,12.09",
			"Works Burger,13.19",
			"On The Run Burger,12.64",
			"Shrooms Burger,12.64",
			"BBQ Chipotle Burger,12.64",
			"Cajun Ranch Burger,12.64",
			"The Beast Burger,13.19",
			"Feel n'Gouda Burger,13.19" };
	private String[] Flatbreads = { "", "Smack 'n Mac FB,11.45",
				"Sthwst Taco FB,11.45",
				"Buffalo Chx FB,11.45",
				"Bacon Chx Ran FB,11.45" };
	private String[] MacNCheeses = { "", "Loaded Mac,11.64",
				"Boneyard BBQ Mac,13.19",
				"Smokey Gouda Mac,12.64" };
	private String[] Skillets = { "", "Mesq Steak Fajita,15.94",
			  "Chicken Fajita,14.84",
			  "Shrimp Fajita,15.94" };
	private String[] Tacos = { "", "Chipotle Lime Tacos,13.19",
		   "Blcknd Shrimp Tacos,13.74",
		   "Carnitas Tacos,12.64",
		   "Carne Asada Tacos,13.19" };
	private String[] Sides = { "", "Chips+Salsa Side,4.39",
		   "Crinkle Cut Fries Side,2.99",
		   "Swt Pot Fries,3.29",
		   "House Salad,3.89",
		   "Coleslaw Side,2.19",
		   "Cup of Soup,3.89",
		   "Cornbread Side,3.99" };
	private String[] KidsMeals = { "", "Kids Wings,5.99",
			   "Kids Cheese Qsd,6.89",
			   "Kids Mac,6.89",
			   "Kids Chx Tenders,6.89",
			   "Kids Mini Chz Brgr,6.89" };
	private String[] Beverages = { "", "Water,0.00",
				"Pepsi,1.50",
				"Diet Pepsi,1.50",
				"Sierra Mist,1.50",
				"Root Beer,1.50",
				"Dr. Pepper,1.50",
				"Iced Tea,1.50",
				"Brisk,1.50"};
	
	public String[] getApp()
	{
		return Appetizers;
	}
	
	public String[] getGarden()
	{
		return Garden;
	}
	
	public String[] getSandwiches()
	{
		return Sandwiches;
	}
	
	public String[] getFlatreads()
	{
		return Flatbreads;
	}
	
	public String[] getTacoSkillet()
	{
		return Tacos;
	}
	
	public String[] getBeverage()
	{
		return Beverages;
	}
	
	public int getCount()
	{
		return count;
	}
	
	/*public Tab getTab(int tabIndex)
	{
		return tabs[tabIndex];
	}*/
	
	public String toString(int tabIndex)
	{
		return tabs[tabIndex].toString();
	}
	
	public void openCategory(int choice, int currentTabIndex)
	{
		int itemNum;
		switch (choice)
		{
		case 1:
			do 
			{        
				System.out.println("************** APPETIZERS **************");
				itemNum = menuWriter(Appetizers, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 2:
			do 
			{
				System.out.println("**************** GARDEN ****************");
				itemNum = menuWriter(Garden, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 3:
			do 
			{
				System.out.println("************** SANDWHICHES *************");
				itemNum = menuWriter(Sandwiches, currentTabIndex);	
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 4:
			do 
			{
				System.out.println("*************** BURGERS ****************");
				itemNum = menuWriter(Burgers, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 5:
			do 
			{
				System.out.println("************** FLATBREADS **************");
				itemNum = menuWriter(Flatbreads, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 6:
			do 
			{
				System.out.println("************ MAC N' CHEESES ************");
				itemNum = menuWriter(MacNCheeses, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 7:
			do 
			{
				System.out.println("*************** SKILLETS ***************");
				itemNum = menuWriter(Skillets, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 8:
			do 
			{
				System.out.println("**************** TACOS *****************");
				itemNum = menuWriter(Tacos, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 9:
			do 
			{
				System.out.println("**************** SIDES *****************");
				itemNum = menuWriter(Sides, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 10:
			do 
			{
				System.out.println("************** KIDS MEALS **************");
				itemNum = menuWriter(KidsMeals, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case 11:
			do 
			{
				System.out.println("************* NA BEVERAGES *************");
				itemNum = menuWriter(Beverages, currentTabIndex);
			} while(itemNum != 0);
			tabDisplay(currentTabIndex);
			menuDisplay(currentTabIndex);
			break;
		case -1:
			welcomeScreen();
			break;
		case -2:
			payTab(currentTabIndex);
			break;
		case -3:
			itemRemover(currentTabIndex);
			break;
		default:
			System.out.println("Invalid choice...");
			break;
		}
	}	
	
	public int menuWriter(String[] arr, int currentTabIndex)
	{
		int itemNum;
		int length = arr.length;
		String[] items = new String[length];
		double[] prices = new double[length];
		
		for (int i = 0; i < length; i++)
		{
			String info = arr[i];
			String[] strSplit = info.split(",");
			items[i] = strSplit[0];
			prices[i] = Double.parseDouble(strSplit[1]);
		}
		for (int i = 0; i < length; i++)
		{
			System.out.println((i+1) + ". " + items[i]);
		}
		
		System.out.println("\n0. Return to Menu\n****************************************");
		System.out.print("--> ");
		itemNum = cin.nextInt();
		cin.nextLine();
		System.out.println();
		if (itemNum!=0 && itemNum <= length)
		{
			tabWriter(items[itemNum - 1], prices[itemNum - 1], currentTabIndex, arr);
			
			return itemNum;
		}
		else if (itemNum > length)
		{
			System.out.println("\nERROR: Item does not exist...\n");
			return itemNum;
		}
		else
			return itemNum;
	}

}