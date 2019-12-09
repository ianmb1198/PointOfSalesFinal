public class Item
{
	private String item;
	private double price;
	private double sidePrice;
	
	double sidePriceTotal;
	int count;
	
	public Item(String item, double price) {

		this.item = item;
		this.price = price;
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
	

	
	@Override
	public String toString() {
		String itemInfo = (item + " $" + price);
		return itemInfo;
	}
	
	
}
