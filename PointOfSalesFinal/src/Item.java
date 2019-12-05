public class Item
{
	private String item;
	private double price;
	private double sidePrice;
	
	double sidePriceTotal;
	private SideItem[] sides;
	private SideItem sideInfo;
	int count;
	
	public Item(String item, double price) {

		this.item = item;
		this.price = price;
		sides = new SideItem[10];
		count = 0;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setSidePrice(double sidePrice)
	{
		this.sidePrice = sidePrice;
	}
	public double getSidePrice()
	{
		return sidePrice;
	}
	public double getSidePriceTotal()
	{
		return sidePriceTotal;
	}
	
	public void addSideItem(String sideItem, double sidePrice)
	{
		sideInfo = new SideItem(sideItem, sidePrice);
		sides[count] = sideInfo;
		sidePriceTotal += sidePrice;
		count++;
	}
	
	public boolean removeSideItem(String sideItem)
	{
		for (int i = 0; i < count; i++)
		{
			if (sides[i] != null)
			{
				if (sides[i].getItem().equals(sideItem))
				{
					setSidePrice(sides[i].getPrice());
					sidePriceTotal = sidePriceTotal - sides[i].getPrice();
					sides[i] = sides[count - 1];
					sides[count - 1] = null;
					count--;
					return true;
				}
			}
		}
		return false;
	}	
	
	@Override
	public String toString() {
		String itemInfo = (item + "  \t\t$" + price);
		if (sides != null)
		{
			for (int i = 0; i < count; i++)
			{
				itemInfo += (sides[i].toString());
			}
		}
		return itemInfo;
	}
	
	
}
