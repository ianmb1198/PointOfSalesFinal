import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Item
{
	private NumberFormat f = new DecimalFormat("#0.00");
	private String item;
	private double price;

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

	@Override
	public String toString() {
		//String itemInfo = (item + "  \t\t$" + price);
		String itemInfo = (item + " $" + f.format(price));
		return itemInfo;
	}
}
