public class Tab 
{
	private Item[] items;
	private Item itemInfo;
	
	
	private int tabNumber;
	int count;
	int currentItem;
	
	double foodTotal;
	
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
					foodTotal = foodTotal - items[i].getSidePriceTotal();
					items[i] = items[count - 1];
					items[count - 1] = null;
					count--;
					return true;
				}
			}
		}
		return false;
	}
	
	public void addSideToItem(String item, double price, int currentItemIndex)
	{
		items[currentItemIndex].addSideItem(item, price);
		foodTotal = addFoodTotal(price);
	}
	
	public boolean removeSideItem(String sideItem, int currentItemIndex)
	{
		boolean test = items[currentItemIndex].removeSideItem(sideItem);
		if (test == true)
		{
			foodTotal = foodTotal - itemInfo.getSidePrice();
			return true;
		}
		else
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
	
	
	@Override
	public String toString()
	{
		String information = "";
		for (int i = 0; i < count; i++)
		{
			information += ((i+1) + ". " + items[i].toString() + "\n");
			
		}
		information += ("\nTotal: $" + getFoodTotal());

		return information;
	}
	
	
}
