import java.util.Scanner;

public class Controller
{
	public Controller()
	{
		
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
	

}