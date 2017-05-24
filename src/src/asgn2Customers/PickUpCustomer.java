package asgn2Customers;

import asgn2Exceptions.CustomerException;

/** A class that represents a customer that has chosen to collect their pizza at the restaurant. 
 *  This class extends the abstract Customer class. Since the customer is at the restaurant the delivery 
 *  distance should be zero.  A description of the class's fields and their constraints is provided 
 *  in Section 5.2 of the Assignment Specification.
 *
 * @author Person B
 *
 */
public class PickUpCustomer extends Customer {

	/**
	 *  This class represents a customer of the Pizza Palace restaurant that has chosen to pickup their pizza at the restaurant. 
	 *  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment Specification are violated. 
	 *  
	 * <P> PRE: TRUE
 	 * <P> POST: All field values are set
	 *  
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY  The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @throws CustomerException if supplied parameters are invalid
	 * 
	 */
	public PickUpCustomer(String name, String mobileNumber, int locationX,  int locationY) throws CustomerException {
		
		super(name, mobileNumber, locationX, locationY, "Pick Up");
		
		int firstDigit = Integer.parseInt(mobileNumber.substring(0, 1));
		int lengthNumber = Integer.valueOf(mobileNumber).toString().length();
		if (name.length() < 1 || name.length() > 20) {
			throw new CustomerException("The customers name needs to be been 1 character and 20 characters long");
		} else if (firstDigit != 0) {
			throw new CustomerException("The first digit needs to be a 0");
		} else if (lengthNumber != 10) {
			throw new CustomerException("The length of the phoneNumber needs to be equal to 10");
		} 	
	}

	/**
	 *  Overrides DeliveryDistance() in Customer. The customer has no delivery distance, because its pickup. 
	 *  
     * @return The value 0
	 */
	@Override
	public double getDeliveryDistance() {
		return 0;	
	}
}
