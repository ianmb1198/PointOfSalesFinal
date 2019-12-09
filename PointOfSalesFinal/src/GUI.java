import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GUI extends JFrame implements ActionListener
{	
	private Controller cont = new Controller();
	private Tab[] tabs = new Tab[16];
	private Tab tempTab = new Tab();
	private String eodReport = "";
	private double eodTotal = 0.00;
	private String currentWindow; /* Possible window titles: opening, number, 
	   							   *menu, existing, tabEdit, eod. */
	private int counter;
	private int tabNumTracker;
	private int[] deletedTabs = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}; 
	private boolean tabControlsOpen = false;
	
	private NumberFormat f = new DecimalFormat("#0.00");
	
	public GUI(String title)
	{
		super(title);
		counter = 0;
		currentWindow = "opening";
		createTabButtons();
		setActionListener();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setOpeningWindow();
		setVisible(true);
	}
	
//*****ELEMENTS AND VARIABLES THAT ARE USED ALL OVER THE PROGRAM*****\\
	private boolean mustBeCleared = false;
	
	private JButton deleteButton = new JButton("Delete");
	private JPanel emptyPanel1 = new JPanel();
	private JPanel emptyPanel2 = new JPanel();
	private JPanel modularPanel = new JPanel();
	
//******ELEMENTS THAT ARE USED IN THE OPENING WINDOW*****\\
	private JButton beginButton = new JButton("Begin new tab");
	private JButton viewButton = new JButton("View existing tabs");
	private JButton eodButton = new JButton("EOD Report");
	
	private JPanel openingPanel = new JPanel(new GridLayout(3, 1));
	
//*****ELEMENTS THAT ARE USED IN THE WINDOW FOR ENTERING THE TAB NUMBER*****\\
	private JButton enterNumButton = new JButton("Enter");
	private JButton clearButton = new JButton("Clear");
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton button6 = new JButton("6");
	private JButton button7 = new JButton("7");
	private JButton button8 = new JButton("8");
	private JButton button9 = new JButton("9");
	private JButton button0 = new JButton("0");
	
	private JPanel numPanel = new JPanel(new GridLayout(4, 3));
	private JPanel subPanel = new JPanel(new BorderLayout());
	
	private JTextArea numberField = new JTextArea("", 5, 7 );
	
//*****ELEMNETS THAT ARE USED IN THE MENU AND EDIT WINDOW*****\\
	private JLabel appLabel = new JLabel("Appetizers");
	private JLabel saladLabel = new JLabel("Salads");
	private JLabel sandwichLabel = new JLabel("Sandwiches");
	private JLabel flatbreadLabel = new JLabel("Flatbreads");
	private JLabel tacoSkilletLabel = new JLabel("Tacos and Skillets");
	private JLabel beverageLabel = new JLabel("Beverages");
	
	private JLabel removeLabel = new JLabel("Remove an item");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox appMenu = new JComboBox(cont.getApp());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox saladMenu = new JComboBox(cont.getGarden());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox sandwichMenu = new JComboBox(cont.getSandwiches());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox flatbreadMenu = new JComboBox(cont.getFlatreads());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox tacoSkilletMenu = new JComboBox(cont.getTacoSkillet());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox beverageMenu = new JComboBox(cont.getBeverage());
	
	private JButton appButton = new JButton("Enter Appetizer");
	private JButton saladButton = new JButton("Enter Salad");
	private JButton sandwichButton = new JButton("Enter Sandwich");
	private JButton flatbreadButton = new JButton("Enter Flatbread");
	private JButton tacoSkilletButton = new JButton("Enter Taco or Skillet");
	private JButton beverageButton = new JButton("Enter Beverage");
	
	private JButton removeButton = new JButton("Remove");
	private JButton removeAllButton = new JButton("Remove all items");
	private JButton confirmButton = new JButton("Confirm");
	private JButton backButton = new JButton("Back");
	
	private JPanel appPanel = new JPanel(new GridLayout(2, 1));
	private JPanel saladPanel = new JPanel(new GridLayout(2, 1));
	private JPanel sandwichPanel = new JPanel(new GridLayout(2, 1));
	private JPanel flatbreadPanel = new JPanel(new GridLayout(2, 1));
	private JPanel tacoSkilletPanel = new JPanel(new GridLayout(2, 1));
	private JPanel beveragePanel = new JPanel(new GridLayout(2, 1));
	
	private JPanel menuPanel = new JPanel(new GridLayout(6, 2));
	private JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
	private JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	private JTextField removeTxtField = new JTextField(10);
	
	private JTextArea tabInfo = new JTextArea(30, 17);
	private JScrollPane scrollPane = new JScrollPane(tabInfo);
	
	
	
//*****ELEMENTS THAT ARE USED IN THE EXISTING TABS WINDOW***\\\
	private JButton[] tabButtons = new JButton[16];
	private JPanel tabPanel = new JPanel(new GridLayout(4, 4));
	
  //***THESE ELEMENTS ARE USED FOR A SUB WINDOW OF THE EXISTING TABS WINDOW***\\
	private JLabel tabNumberLabel = new JLabel();
	private JLabel tabTotalLabel = new JLabel();
	
	private JButton payTabButton = new JButton("Pay tab");
	private JButton editTabButton = new JButton("Edit tab");
	private JButton	deleteTabButton = new JButton("Delete tab");
	
	private JPanel tabControls = new JPanel(new GridLayout(4, 1));
	private JPanel tabNumberAndTotalLabelPanel = new JPanel(new GridLayout(2, 1));

	

//*****ELEMENTS USED IN THE EOD WINDOW*****\\	
	/*all elements in the EOD window 
	  are also used somewhere else*/
	
	
//*****FUNCTIONS*****\\
	private boolean doesTabExist(int tabNum)
	{
		for (int i = 0; i < 16; i++)
		{
			if (tabs[i] != null)
			{
				if (tabs[i].getTabNumber() == tabNum)
				{
					return true;
				}
			}
		}
		return false;
	}
	private void createTabButtons()
	{
		for (int i = 0; i < 16; i ++)
		{
			tabButtons[i] = new JButton("Empty");
			tabPanel.add(tabButtons[i]);
		}
			
	}
	private void enterAnItem(@SuppressWarnings("rawtypes") JComboBox combobox)
	{
		if(!isEmpty(deletedTabs))
		{
			enterMenuItem(tabs[getSmallestElement(deletedTabs)], (String) combobox.getSelectedItem(), combobox);
			combobox.setSelectedIndex(0);
		}
		else if (combobox.getSelectedItem().equals(""))
		{
			JOptionPane.showMessageDialog(combobox, "Choose an item from the list.");
		}
		else
		{	
			enterMenuItem(tabs[counter], (String) combobox.getSelectedItem(), combobox);
			combobox.setSelectedIndex(0);
		}	
	}
	private void enterMenuItem(Tab tab, String item, JComboBox combobox)
	{
		if (item.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Choose an item from the list.");
		}
		else
		{
			String[] itemParts = item.split(",");
			String itemName = itemParts[0];
			double itemPrice = Double.parseDouble(itemParts[1]);
			tab.addItem(itemName, itemPrice);
			tabInfo.setText(tab.toString());
			combobox.setSelectedIndex(0);
		}
	}
	private int getSmallestElement(int[] array)
	{
		int smallest = 17; //17 because with how and when this function is used, no value will ever be over 17
		for (int i = 0; i < array.length; i++ )
		{	
			if (-1 < array[i] && array[i] < smallest)
			{
				smallest = array[i];
			}
		}
		return smallest;
	}
	private void removeSmallestElement(int[] array)
	{
		int smallest = 17; //17 because with how and when this function is used, no value will ever be over 17
		for (int i = 0; i < array.length; i++ )
		{	
			if (-1 < array[i] && array[i] < smallest)
			{
				smallest = array[i];
			}
		}
		
		for (int i = 0; i < array.length; i++ )
		{
			
			if (smallest == array[i])
			{
				array[i] = -1;
			}
		}
		
		
	}
	private void addToFirstNullSpot(int[] array, int number)
	{
		//it wont actually ever be null, it'll just be -1 any time this is used\\
		for (int i = 0; i < array.length; i++ )
		{	
			if (array[i] == -1)
			{
				array[i] = number;
				break;
			}
		}
	}
	private boolean isEmpty(int[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] != -1)
			{
				return false;
			}
		}
		
		return true;
	}

	
	
//*****SETTER FOR PRIMARY WINDOW*****\\
	private void setOpeningWindow()
	{
		setSize(500, 400);
		openingPanel.add(beginButton);
		openingPanel.add(viewButton);
		openingPanel.add(eodButton);
		currentWindow = "opening";
		add(openingPanel, BorderLayout.CENTER);
		currentWindow = "opening";
	}
	private void unSetOpeningWindow()
	{
		remove(openingPanel);
	}
	
//*****SETTERS FOR SECONDARY WINDOWS*****\\
	private void setExistingWindow()
	{
		setSize(500, 460);
		for (int i = 0; i < 16; i ++)
		{
			tabButtons[i].setOpaque(true);
			tabButtons[i].setBackground(Color.red);
			tabPanel.add(tabButtons[i]);
		}
			
		for (int i = 0; i < 16; i++)
		{
			if (tabs[i] == null)
			{
				tabButtons[i].setOpaque(true);
				tabButtons[i].setBackground(Color.red);
				tabButtons[i].setText("Empty");
			}
			else if (tabs[i] != null)
			{
				tabButtons[i].setOpaque(true);
				tabButtons[i].setBackground(Color.green);
				tabButtons[i].setText("Tab: " + String.valueOf(tabs[i].getTabNumber()));
			}
		}	
		
		//addTabControls();
		add(tabPanel, BorderLayout.CENTER);
		add(backButton, BorderLayout.WEST);
		currentWindow = "existing";
	}
	private void unSetExistingWindow()
	{
		unAddTabControls();
		remove(tabPanel);
		remove(backButton);
	}
	
	private void setNumberWindow()
	{
		setSize(500, 400);
		numPanel.add(button1);
		numPanel.add(button2);
		numPanel.add(button3);
		numPanel.add(button4);
		numPanel.add(button5);
		numPanel.add(button6);
		numPanel.add(button7);
		numPanel.add(button8);
		numPanel.add(button9);
		numPanel.add(deleteButton);
		numPanel.add(button0);
		numPanel.add(clearButton);
		subPanel.add(numPanel, BorderLayout.CENTER);
		subPanel.add(enterNumButton, BorderLayout.EAST);
		subPanel.add(backButton, BorderLayout.WEST);
		subPanel.add(numberField, BorderLayout.NORTH);
		add(subPanel, BorderLayout.CENTER);
		numberField.setText("Enter a tab number");
		mustBeCleared = true;
		currentWindow = "number";
	}
	private void unSetNumberWindow()
	{
		remove(subPanel);
		numberField.setText("");
	}
	
	private void setEODWindow()
	{
		setSize(350, 500);
		tabInfo.setText(eodReport + "\nTotal Sales: $" + f.format(eodTotal));
		add(scrollPane, BorderLayout.CENTER);
		add(backButton, BorderLayout.WEST);
		currentWindow = "eod";
		
	}
	private void unSetEODWindow()
	{
		tabInfo.setText("");
		remove(scrollPane);
		remove(backButton);
	}
	
//*****SETTERS FOR TERTIARY WINDOWS*****\\
	private void setMenuWindow(int index)
	{
		setSize(700, 500);
		appPanel.add(appLabel);
		appPanel.add(appMenu);
		menuPanel.add(appPanel);
		menuPanel.add(appButton);
		saladPanel.add(saladLabel);
		saladPanel.add(saladMenu);
		menuPanel.add(saladPanel);
		menuPanel.add(saladButton);
		sandwichPanel.add(sandwichLabel);
		sandwichPanel.add(sandwichMenu);
		menuPanel.add(sandwichPanel);
		menuPanel.add(sandwichButton);
		flatbreadPanel.add(flatbreadLabel);
		flatbreadPanel.add(flatbreadMenu);
		menuPanel.add(flatbreadPanel);
		menuPanel.add(flatbreadButton);
		tacoSkilletPanel.add(tacoSkilletLabel);
		tacoSkilletPanel.add(tacoSkilletMenu);
		menuPanel.add(tacoSkilletPanel);
		menuPanel.add(tacoSkilletButton);
		beveragePanel.add(beverageLabel);
		beveragePanel.add(beverageMenu);
		menuPanel.add(beveragePanel);
		menuPanel.add(beverageButton);
		add(menuPanel, BorderLayout.CENTER);
		
		removePanel.add(removeLabel);
		modularPanel.setLayout(new GridLayout(4, 1));
		modularPanel.add(removePanel);
		modularPanel.add(removeTxtField);
		modularPanel.add(removeButton);
		modularPanel.add(removeAllButton);
		buttonPanel.add(confirmButton);
		buttonPanel.add(emptyPanel1);
		buttonPanel.add(modularPanel);
		buttonPanel.add(emptyPanel2);
		buttonPanel.add(backButton);
		add(buttonPanel, BorderLayout.EAST);
		add(scrollPane, BorderLayout.WEST);
		tabInfo.setText(tabs[index].toString());
		currentWindow = "menu";
	}
	private void unSetMenuWindow()
	{
		remove(menuPanel);
		remove(buttonPanel);
		remove(scrollPane);
		tabInfo.setText("");
		removeTxtField.setText("");
	}

	
	private void setTabEditWindow(int tabIndex)
	{
		setSize(700, 500);
		appPanel.add(appLabel);
		appPanel.add(appMenu);
		menuPanel.add(appPanel);
		menuPanel.add(appButton);
		saladPanel.add(saladLabel);
		saladPanel.add(saladMenu);
		menuPanel.add(saladPanel);
		menuPanel.add(saladButton);
		sandwichPanel.add(sandwichLabel);
		sandwichPanel.add(sandwichMenu);
		menuPanel.add(sandwichPanel);
		menuPanel.add(sandwichButton);
		flatbreadPanel.add(flatbreadLabel);
		flatbreadPanel.add(flatbreadMenu);
		menuPanel.add(flatbreadPanel);
		menuPanel.add(flatbreadButton);
		tacoSkilletPanel.add(tacoSkilletLabel);
		tacoSkilletPanel.add(tacoSkilletMenu);
		menuPanel.add(tacoSkilletPanel);
		menuPanel.add(tacoSkilletButton);
		beveragePanel.add(beverageLabel);
		beveragePanel.add(beverageMenu);
		menuPanel.add(beveragePanel);
		menuPanel.add(beverageButton);
		add(menuPanel, BorderLayout.CENTER);
		
		removePanel.add(removeLabel);
		modularPanel.setLayout(new GridLayout(4, 1));
		modularPanel.add(removePanel);
		modularPanel.add(removeTxtField);
		modularPanel.add(removeButton);
		modularPanel.add(removeAllButton);
		buttonPanel.add(confirmButton);
		buttonPanel.add(emptyPanel1);
		buttonPanel.add(modularPanel);
		buttonPanel.add(emptyPanel2);
		buttonPanel.add(backButton);
		add(buttonPanel, BorderLayout.EAST);
		add(scrollPane, BorderLayout.WEST);
		tabNumTracker = tabIndex;
		tempTab.setEqualTo(tabs[tabIndex]);
		tabInfo.setText(tempTab.toString());
		currentWindow = "tabEdit";
	}
	private void unSetTabEditWindow()
	{
		remove(menuPanel);
		remove(buttonPanel);
		remove(scrollPane);
		tabInfo.setText("");
		removeTxtField.setText("");
	}

//*****METHODS FOR SUB WINDOWS	
	private void addTabControls(Tab tab)
		{
			setSize(650, 460);
			tabNumberLabel.setText("Tab: " + tab.getTabNumber());
			tabTotalLabel.setText("Total: $" + f.format(tab.getFoodTotal()));
			tabNumberAndTotalLabelPanel.add(tabNumberLabel);
			tabNumberAndTotalLabelPanel.add(tabTotalLabel);
			tabControls.add(tabNumberAndTotalLabelPanel);
			tabControls.add(payTabButton);
			tabControls.add(editTabButton);
			tabControls.add(deleteTabButton);
			add(tabControls, BorderLayout.EAST);
			tabControlsOpen = true;
		}
	private void unAddTabControls()
		{
			setSize(500, 460);
			remove(tabControls);
			tabControlsOpen = true;
		}
	
//*****ACTION LISTENERS*****\\
	private void setActionListener() 
	{
		beginButton.addActionListener(this);
		viewButton.addActionListener(this);
		eodButton.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		button0.addActionListener(this);
		deleteButton.addActionListener(this);
		//cancelButton.addActionListener(this);
		enterNumButton.addActionListener(this);
		clearButton.addActionListener(this);
		backButton.addActionListener(this);
		confirmButton.addActionListener(this);
		removeButton.addActionListener(this);
		removeAllButton.addActionListener(this);
		appButton.addActionListener(this);
		saladButton.addActionListener(this);
		sandwichButton.addActionListener(this);
		flatbreadButton.addActionListener(this);
		tacoSkilletButton.addActionListener(this);
		beverageButton.addActionListener(this);
		editTabButton.addActionListener(this);
		deleteTabButton.addActionListener(this);
		payTabButton.addActionListener(this);
		eodButton.addActionListener(this);
		for (int i = 0; i < 16; i ++)
		{
			tabButtons[i].addActionListener(this);
		}
	}
//------------------GUI ACTIONS--------------------------------------------------------
	
	
	@Override
	public void actionPerformed(ActionEvent event) 
   	{
		String callingBtn = event.getActionCommand();
		
		/* ignore this (used for clearING text after warning from 
		 * click enter without a tab number)*/
		if (mustBeCleared)
		{
			numberField.setText("");
			mustBeCleared = false;
		}
		
		
	//*****BUTTONS USED MULTIPLE TIMES*****\\\
		if (callingBtn.equalsIgnoreCase("back"))
		{
			switch(currentWindow)
			{
				
				case "existing":
				{
					unSetExistingWindow();
					setOpeningWindow();
					break;
				}
				
				case "number":
				{
					unSetNumberWindow();
					setOpeningWindow();
					numberField.setText("");
					break;
				}
				
				case "menu":
				{
					unSetMenuWindow();
					setNumberWindow();
					tabs[counter] = null;
					break;
				}
				
				
				case "tabEdit":
				{
					unSetTabEditWindow();
					setExistingWindow();
					addTabControls(tempTab);
					tempTab.setEmpty();
					tabNumTracker = 0;
					break;
				}
				case "eod":
				{
					unSetEODWindow();
					setOpeningWindow();
					break;
				}
				
			}
		}
		

		
	//*****OPENING WINDOW ACTIONS*****\\
		
		if (callingBtn.equalsIgnoreCase("begin new tab"))
		{
			unSetOpeningWindow();
			setNumberWindow();
		}
		
		if (callingBtn.equalsIgnoreCase("view existing tabs"))
		{
			unSetOpeningWindow();
			setExistingWindow();
		}
		
		if (callingBtn.equalsIgnoreCase("eod report"))
		{
			unSetOpeningWindow();
			setEODWindow();	
	
		}
		
	//*****ENTER TAB NUMBER WINDOW ACTIONS*****\\
		
		if (callingBtn.equalsIgnoreCase("1") || callingBtn.equalsIgnoreCase("2")
				 || callingBtn.equalsIgnoreCase("3") || callingBtn.equalsIgnoreCase("4")
				 || callingBtn.equalsIgnoreCase("5") || callingBtn.equalsIgnoreCase("6")
				 || callingBtn.equalsIgnoreCase("7") || callingBtn.equalsIgnoreCase("8")
				 || callingBtn.equalsIgnoreCase("9") || callingBtn.equalsIgnoreCase("0"))
		{
			numberField.append(callingBtn);
			callingBtn = "";
		}
			
		if (callingBtn.equalsIgnoreCase("delete"))
		{
			if (numberField.getText().equalsIgnoreCase(""))
			{
			}
			else
			{	
				numberField.setText(numberField.getText().substring(0, numberField.getText().length() - 1));
			}
		}
			
		if (callingBtn.equalsIgnoreCase("clear"))
		{
			numberField.setText("");
		}
			
		if (callingBtn.equalsIgnoreCase("enter"))
		{	
			int tabNum;
			
			if (numberField.getText().equalsIgnoreCase(""))
			{
				JOptionPane.showMessageDialog(null, "Must input a tab number.");
				mustBeCleared = true;
			}
				
			else
			{	
				tabNum = Integer.parseInt(numberField.getText());
				
				if (doesTabExist(tabNum))
				{
					JOptionPane.showMessageDialog(null, "Tab already exists.");
					mustBeCleared = true;
				}
				
				else if (counter == 16)
				{
					numberField.setText("Restaurant is full");
					mustBeCleared = true;
				}
				
				else
				{
					if (!isEmpty(deletedTabs))
					{	
						tabs[getSmallestElement(deletedTabs)] = new Tab(tabNum);
						unSetNumberWindow();
						setMenuWindow(getSmallestElement(deletedTabs));
					}
					
					else
					{	
						tabs[counter] = new Tab(tabNum);
						unSetNumberWindow();
						setMenuWindow(counter);
					}	
				}
			}	
		}
		
	//*****MENU AND EDIT TAB WINDOW ACTIONS*****\\
		if (callingBtn.equalsIgnoreCase("confirm"))
		{
			switch (currentWindow)
			{
				case "menu":
				{
					if(!isEmpty(deletedTabs))
					{
						removeSmallestElement(deletedTabs);
					}
					
					else
					{	
						counter++;
					}	
					unSetMenuWindow();
					setOpeningWindow();
					break;
				}
				
				case "tabEdit":
				{
					tabs[tabNumTracker].setEqualTo(tempTab);
					tempTab.setEmpty();
					tabNumTracker = 0;
					unSetTabEditWindow();
					setExistingWindow();
					break;
				}
			}		
		}
		
		if (callingBtn.equalsIgnoreCase("remove"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					int removeItem = Integer.parseInt(removeTxtField.getText());
					tabs[counter].removeItem(removeItem);
					tabInfo.setText(tabs[counter].toString());
					removeTxtField.setText("");
					break;
				}
				
				case "tabEdit":
				{
					int removeItem = Integer.parseInt(removeTxtField.getText());
					tempTab.removeItem(removeItem);
					tabInfo.setText(tempTab.toString());
					removeTxtField.setText("");
					break;
				}
			}	
		}
		
		if (callingBtn.equalsIgnoreCase("remove all items"))
		{
			switch(currentWindow)
			{
				case "menu":
				{	
					for (int i = tabs[counter].getCount(); i >= 1; i --)
					{
						tabs[counter].removeItem(i);
					}
					tabs[counter].setFoodTotal(0.00);
					tabInfo.setText(tabs[counter].toString());
					break;
				}
				
				case "tabEdit":
				{
					for (int i = tempTab.getCount(); i >= 1; i --)
					{
						tempTab.removeItem(i);
					}
					tabs[counter].setFoodTotal(0.00);
					tabInfo.setText(tempTab.toString());
					break;
				}
			}		
		}
		
		if  (callingBtn.equalsIgnoreCase("enter appetizer"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					enterAnItem(appMenu);
					break;
				}
				case "tabEdit":
				{
					enterMenuItem(tempTab, (String) appMenu.getSelectedItem(), appMenu);
					break;
				}	
			}	
		}
		
		if  (callingBtn.equalsIgnoreCase("enter salad"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					enterAnItem(saladMenu);
					break;
				}
				
				case "tabEdit":
				{	
					enterMenuItem(tempTab, (String) saladMenu.getSelectedItem(), saladMenu);
					break;
				}
			}
		}
		
		if  (callingBtn.equalsIgnoreCase("enter sandwich"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					
					enterAnItem(sandwichMenu);
					break;
				}
				case "tabEdit":
				{	
					enterMenuItem(tempTab, (String) sandwichMenu.getSelectedItem(), sandwichMenu);
					break;
				}	
			}	
		}
		
		if  (callingBtn.equalsIgnoreCase("enter flatbread"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					enterAnItem(flatbreadMenu);
					break;
				}
				case "tabEdit":
				{	
					enterMenuItem(tempTab, (String) flatbreadMenu.getSelectedItem(), flatbreadMenu);
					break;
				}	
			}	
		}
		
		if  (callingBtn.equalsIgnoreCase("enter taco or skillet"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					enterAnItem(tacoSkilletMenu);
					break;
				}
				
				case "tabEdit":
				{	
					enterMenuItem(tempTab, (String) tacoSkilletMenu.getSelectedItem(), tacoSkilletMenu);
					break;
				}	
			}	
		}
		
		if  (callingBtn.equalsIgnoreCase("enter beverage"))
		{
			switch (currentWindow)
			{
				case "menu":
				{	
					enterAnItem(beverageMenu);
					break;
				}
				
				case "tabEdit":
				{	
					enterMenuItem(tempTab, (String) beverageMenu.getSelectedItem(), beverageMenu);
					break;
				}	
			}	
		}
	
	//*****EXISTING WINDOW ACTIONS*****\\
		if (callingBtn.equalsIgnoreCase("empty"))
		{
			if (tabControlsOpen)
			{	
				unAddTabControls();
			}	
		}
		if (callingBtn.length() >= 4)
		{	
			if (callingBtn.substring(0, 3).equalsIgnoreCase("tab"))
			{
				String[] textParts = callingBtn.split(" ");
				int tabNum = Integer.parseInt(textParts[1]);
				int tabIndex = -1;
				
				for (int i = 0; i < 16; i++)
				{
					if (tabs[i] != null)
					{
						if (tabs[i].getTabNumber() == tabNum)
						{
							tabIndex = i;
						}
					}
				}
				if (tabIndex >= 0)
				{
					addTabControls(tabs[tabIndex]);
				}
			}
		}
		
		if (callingBtn.equalsIgnoreCase("edit Tab"))
		{
			String[] textParts = tabNumberLabel.getText().split(" ");
			int tabNum = Integer.parseInt(textParts[1]);
			int tabIndex = -1;
			for (int i = 0; i < 16; i++)
			{
				if (tabs[i] != null)
				{
					if (tabs[i].getTabNumber() == tabNum)
					{
						tabIndex = i;
					}
				}
			}
			if (tabIndex >= 0)
			{
				unSetExistingWindow();
				setTabEditWindow(tabIndex);
			}
		}
		
		if (callingBtn.equalsIgnoreCase("delete tab"))
		{
			String[] textParts = tabNumberLabel.getText().split(" ");
			int tabNum = Integer.parseInt(textParts[1]);
			int tabIndex = -1;
			for (int i = 0; i < 16; i++)
			{
				if (tabs[i] != null)
				{
					if (tabs[i].getTabNumber() == tabNum)
					{
						tabIndex = i;
					}
				}
			}
			if (tabIndex >= 0)
			{
				if (tabIndex < counter)
				{
					addToFirstNullSpot(deletedTabs, tabIndex);
				}
				tabs[tabIndex] = null;
				unSetExistingWindow();
				setExistingWindow();
			}
		}
		
		if (callingBtn.equalsIgnoreCase("pay tab"))
		{
			String[] textParts = tabNumberLabel.getText().split(" ");
			int tabNum = Integer.parseInt(textParts[1]);
			int tabIndex = -1;
			for (int i = 0; i < 16; i++)
			{
				if (tabs[i] != null)
				{
					if (tabs[i].getTabNumber() == tabNum)
					{
						tabIndex = i;
					}
				}
			}
			if (tabIndex >= 0)
			{
				if (tabIndex < counter)
				{
					addToFirstNullSpot(deletedTabs, tabIndex);
				}
				eodReport += tabs[tabIndex].toString() + "\n\n";
				eodTotal += tabs[tabIndex].getFoodTotal();
				tabs[tabIndex] = null;
				unSetExistingWindow();
				setExistingWindow();
			}			
		}
   	}
	

}