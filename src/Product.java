/**
 * Product
 * 
 *   A simple interface for a possible family of Product 
 *   classes.
 *   
 *   @author Alex Lacey
 *   @version 20171022
 */
import java.util.Scanner;

public interface Product {

	/*
	 * setName
	 *  @param name - new name for the product
	 */
	public void setName(String name);

	/*
	 * getName
	 *  @return the name of the product
	 */
	public String getName();

	/*
	 * setType
	 *  @param type - the type of the product
	 */
	public void setType(String type);

	/*
	 * getType
	 * @return - the product type
	 */
	public String getType();

	/*
	 * setPrice
	 * @param price - the price of the product
	 */
	public void setPrice(double price);

	/*
	 * getPrice
	 * @return the price of the product
	 */
	public double getPrice();

	/*
	 * setQuantity
	 * @param quantity - the number of this product in inventory
	 */
	public void setQuantity(int quantity);

	/*
	 * getQuantity
	 * @return the number of this product in inventory
	 */
	public int getQuantity();
	
	/*
	 * setInStock
	 * @param inStock - true if this product is in stock
	 */
	public void setInStock(boolean inStock);

	/*
	 * getQuantity
	 * @return true if this product is in stock
	 */
	public boolean getInStock();
	
	/*
	 * readNextProduct
	 * @param inFile - a Scanner containing product entries
	 * @return false if the product cannot be completely read,
	 * 			true otherwise
	 */
	public boolean readNextProduct(Scanner inFile);

}