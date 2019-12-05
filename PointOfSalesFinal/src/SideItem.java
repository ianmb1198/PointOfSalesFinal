public class SideItem 
{
	private String item;
	private double price;
	
	public SideItem(String item, double price) 
	{
		this.item = item;
		this.price = price;
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
	@Override
	public String toString() {
		return "\n\t" + item + "     \t\t$" + price;
	}
}