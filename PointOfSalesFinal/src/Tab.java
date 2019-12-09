import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Tab 
{
	private Item[] items;
	private Item itemInfo;
	
	
	private int tabNumber;
	int count;
	int currentItem;

	private NumberFormat f = new DecimalFormat("#0.00");
	double foodTotal;
	
	public Tab()
	{
		tabNumber = 000;
		items = new Item[100];
		
		foodTotal = 0.0;
		
		count = 0;
		currentItem = 0;
	}
	
	public Tab(int tabNumber)
	{
		this.tabNumber = tabNumber;
		items = new Item[100];
		
		foodTotal = 0.0;

		count = 0;
		currentItem = 0;
	}

	public int getCount()
	{
		return count;
	}
	
	public void addItem(String item, double price)
	{
		itemInfo = new Item(item, price);
		items[count] = itemInfo;
		foodTotal = addFoodTotal(price);
		count++;
	}
	
	public boolean removeItem(int itemIndex)
	{
		for (int i = 0; i < count; i++)
		{
			if (items[i] != null)
			{
				if (items[i].equals(items[itemIndex - 1]))
				{
					foodTotal = foodTotal - items[i].getPrice();
					items[i] = items[count - 1];
					items[count - 1] = null;
					count--;
					return true;
				}
			}
		}
		return false;
	}
	
	public int getTabNumber() {
		return tabNumber;
	}

	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}
	
	public double addFoodTotal(double price)
	{
		return (foodTotal = foodTotal + price);
	}
	
	public double getFoodTotal()
	{
		return foodTotal;
	}
	public void setFoodTotal(double foodTotal)
	{
		this.foodTotal = foodTotal;
	}
	
	public Item getItem(int itemIndex)
	{
		return items[itemIndex];
	}
	
	public Item[] getItems()
	{
		return items;
	}
	
	public void setEqualTo(Tab tab)
	{
		tabNumber = tab.getTabNumber();
		for (int i = 0; i < 100; i++)
		{
			items[i] = tab.getItems()[i];
		}
		foodTotal = tab.getFoodTotal(); 
		count = tab.getCount();
	}
	
	public void setEmpty()
	{
		tabNumber = 000;
		for (int i = 0; i < 100; i++)
		{
			items[i] = null;
		}
		
		foodTotal = 0.0;
		
		count = 0;
		currentItem = 0;
	}
	
	
	@Override
	public String toString()
	{
		String tabNum = String.valueOf(tabNumber);
		String information = "Tab number: " + tabNum + '\n';
		for (int i = 0; i < count; i++)
		{
			information += ((i+1) + ". " + items[i].toString() + "\n");
		}
		information += ("\nTotal: $" + f.format(getFoodTotal()));

		return information;
	}
	
	
}