/**
 * SimpleProduct class
 * 
 *   A class that implements the Product interface.
 * 
 *   @author Alex Lacey
 *   @version 20171022
 */

import java.util.Scanner;

public class SimpleProduct implements Product {

	// variables of each object in the class
	private String name;
	private String type;
	private double price;
	private int quantity;
	private boolean inStock;

	// constructor method
	public SimpleProduct(Scanner inFile) {
		readNextProduct(inFile);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public boolean getInStock() {
		return this.inStock;
	}


	public boolean readNextProduct(Scanner input) {
		int i = 0;
		while (input.hasNextLine()) {
			if (i > 4) return false;
			String line = input.nextLine().trim();
			if (line.length() <= 0) {
				System.out.println("Error: empty product field(s) in text file. Exiting program.");
				System.exit(0);
			}
			if (i == 0) {
				setName(line);
			} else if (i == 1) {
				setType(line);
			} else if (i == 2) {
				try {
					double price = Double.parseDouble(line);
					setPrice(price);
				} catch (Exception e) {
					System.out.println("Error: " + e);
					System.exit(0);
				}
			} else if (i == 3) {
				try {
					int quantity = Integer.parseInt(line);
					setQuantity(quantity);
				} catch (Exception e) {
					System.out.println("Error: " + e);
					System.exit(0);
				}
			} else if (i == 4) {
				try {
					setInStock(Boolean.valueOf(line));
				} catch (Exception e) {
					System.out.println("Error: " + e);
					System.exit(0);
				}
			}
			i++;
		}
		return true;
	}

	public boolean equals(String name, String type) {
		boolean output = (this.name.equals(name) && this.type.equals(type));
		return output;
	}

	public String toString() {
		String output = "(" + getName() + ", " + getType() + ", " + getPrice() + ", " + getQuantity()+ ")";
		return output;
	}
}