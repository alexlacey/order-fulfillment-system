
/**
 * Customer class
 * 
 *   A class that encapsulates the customer.
 * 
 *   @author Alex Lacey
 *   @version 20171022
 */

import java.util.Scanner;

public class Customer {
	
	private String last;
	private String first;
	private String street;
	private String city;
	private String state;
	private String zip;
	private double tax;
	

	public Customer(Scanner inFile) {
		readNextCustomerInfo(inFile);
	}
	
	public String getLast(){
		return this.last;
	}
	
	public void setLast(String last){
		this.last = last;
	}
	
	public String getFirst(){
		return this.first;
	}
	
	public void setFirst(String first){
		this.first = first;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public void setCity(String city){
		this.city = city;
	}

	public String getStreet(){
		return this.street;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getState(){
		return this.state;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getZip(){
		return this.zip;
	}

	public void setZip(String zip){
		this.zip = zip;
	}

	public double getTax(){
		return this.tax;
	}

	public void setTax(double tax) {
		if ((tax >= 0) && (tax <= 1)) {
			this.tax = tax;
		} else {
			System.out.println("Invalid entry");
		}
	}
	
	public boolean readNextCustomerInfo(Scanner input) {
		int i = 0;
		while (input.hasNextLine()) {
			if (i > 6) {
				return false;
			}
			String line = input.nextLine().trim();
			if (line.length() <= 0){
				System.out.println("Error");
				System.exit(0);	
			}			
			if (i == 0) {
				setLast(line);
			} else if (i == 1) {
				setFirst(line);
			} else if (i == 2) {
				setStreet(line);
			} else if (i == 3) {
				setCity(line);
			} else if (i == 4) {
				setState(line);
			} else if (i == 5) {
				setZip(line);
			} else if (i == 6){
				try {
					double tax = Double.parseDouble(line);
					setTax(tax);
				} catch (Exception e) {
					System.out.println("Error: " + e);
					System.exit(0);
				}
			}
			i++;
		}
		return true;
	}

}