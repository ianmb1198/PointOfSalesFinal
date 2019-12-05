
public class EOD 
{
	private int tabNumber;
	private double tabTotal;
	
	
	public EOD(int tabNumber, double tabTotal) {
		this.tabNumber = tabNumber;
		this.tabTotal = tabTotal;
	}
	public int getTabNumber() {
		return tabNumber;
	}
	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}
	public double getTabTotal() {
		return tabTotal;
	}
	public void setTabTotal(double tabTotal) {
		this.tabTotal = tabTotal;
	}
	
	public String toString()
	{
		String eodInformation = "Tab Number: " + tabNumber + "\tTotal: $" + tabTotal;
		return eodInformation;
	}
}
