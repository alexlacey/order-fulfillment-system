
/**
 * SimpleProduct class
 * 
 *   A class that implements the Product interface.
 * 
 *   @author Alex Lacey
 *   @version 20171022
 */

import java.io.*;
import java.util.*;

public class OrderFulfillmentSystem {
	
	private Customer customer;	
	private ArrayList<SimpleProduct> productList = new ArrayList<SimpleProduct>();
	private SimpleProduct product;	
	private Queue<SimpleProduct> stockedQueue = new LinkedList<SimpleProduct>();
	private Stack<SimpleProduct> unstockedStack = new Stack<SimpleProduct>();	
	private double subtotal;
	private double preShippingTotal;
	private double shippingTotal;
	private double shippingPercent;
	private boolean safeToAdd;
	
	public OrderFulfillmentSystem() {
		
		// get file name
		String fileName = getFile();
		
		// place stuff in data structures
		putInDataStructures(fileName);	
		
		// print reports
		generateReportOne();
		generateReportTwo();
	}

	private String getFile(){	
		System.out.print("Enter database filename: ");
		Scanner inFile = new Scanner(System.in);		
		String fileName = inFile.nextLine();
		return fileName;
	}
	
	private void putInDataStructures(String fileName){
		int i = 0;
		try {
			Scanner input = new Scanner(new File(fileName));
			if ((i == 0) && (!input.hasNext())) {
				System.out.println("Error");
				System.exit(0);
			}
			while (input.hasNextLine()) {
				if (i >= 7) {
					product = new SimpleProduct(input);
					this.safeToAdd = true;
					for (int p = 0; p < productList.size(); p++) {
						if (product.equals(productList.get(p).getName(), productList.get(p).getType())) {
							this.safeToAdd = false;
						}
					}
					if (this.safeToAdd == true) {
						productList.add(product);
						if (product.getInStock() == true) {
							stockedQueue.add(product);
						} else { 
							unstockedStack.push(product);
						}
						i = i + 5;
					}
				} else {
					customer = new Customer(input);
					i = i + 7;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			System.exit(0);
		}
	}

	private void generateReportOne(){
		System.out.println();
		System.out.println("Shipping to:");
		generateCustomerAddress();		
		longDottedLine();	
		generateStockedQueueInfo();	
		shortDottedLine();
		System.out.format("  %-52s%5.2f\n", "Subtotal:", this.subtotal);     
		System.out.format("  %-52s%5.2f\n", "Sales tax: " + "(" + customer.getTax() + ")", (customer.getTax() * this.subtotal));	
		calculateShippingTotal();	
		System.out.format("  %-52s%5.2f\n", "Shipping:", this.shippingTotal); 
		shortDottedLine();		
		System.out.format("  %-52s%5.2f\n", "Total:", (this.shippingTotal + this.preShippingTotal));
		longDottedLine();	
	}
	
	private void generateReportTwo(){
		System.out.println();
		System.out.println("Orders Outstanding For:");
		generateCustomerAddress();
		longDottedLine();
		generateUnstockedStackInfo();	
		shortDottedLine();
		System.out.format("  %-51s%5.2f\n", "Outstanding Balance:", this.subtotal);
		longDottedLine();
	}
	
	private void generateCustomerAddress() {
		System.out.println("        " + customer.getFirst() + " " + customer.getLast());
		System.out.println("        " + customer.getStreet());
		System.out.println("        " + customer.getCity() + ", " + customer.getState() + " " + customer.getZip());
	}
	
	private double generateStockedQueueInfo(){
		this.subtotal = 0;	
		int stockedQueueSize = stockedQueue.size();
		SimpleProduct currentProduct;
		for (int i = 0; i < stockedQueueSize; i++) {
			currentProduct = stockedQueue.remove();	
			double amountPaidForProduct = currentProduct.getQuantity() * currentProduct.getPrice(); 
			System.out.format("  %d x %-33s%-15s%5.2f\n", currentProduct.getQuantity(), currentProduct.getName(), "(" + currentProduct.getType() + ")", currentProduct.getPrice());
			this.subtotal = amountPaidForProduct + this.subtotal;
		}
		return this.subtotal;
	}

	private void calculateShippingTotal() {
		this.preShippingTotal = (this.subtotal * customer.getTax()) + this.subtotal;
		
		if (this.preShippingTotal > 25) {
			this.shippingPercent = 0;
		} else if (this.preShippingTotal > 10) {
			this.shippingPercent = .05;
		} else {
			this.shippingPercent = .15;
		}	
		this.shippingTotal = this.shippingPercent * this.preShippingTotal;
	}

	private void generateUnstockedStackInfo() {
		this.subtotal = 0;
		int unstockedStackSize = unstockedStack.size();
		SimpleProduct currentProduct;
		double amountPaidForProduct; 
		for (int i = 0; i < unstockedStackSize; i++) {
			currentProduct = unstockedStack.pop();
			amountPaidForProduct = currentProduct.getQuantity() * currentProduct.getPrice(); 
			System.out.format("  %d x %-33s%-15s%5.2f\n", currentProduct.getQuantity(), currentProduct.getName(), "(" + currentProduct.getType() + ")", currentProduct.getPrice());
			this.subtotal = amountPaidForProduct + this.subtotal;
		}
	}
	
	private void longDottedLine() {
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	private void shortDottedLine() {
		System.out.println("  ---------------------------------------------------------------------------");
	}
	
	public static void main(String[] args) {
		OrderFulfillmentSystem project = new OrderFulfillmentSystem();
	}

}
