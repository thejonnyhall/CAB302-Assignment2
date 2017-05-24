package asgn2Pizzas;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	
	private int pizzaQuantity;
	protected LocalTime pizzaOrderTime;
	protected LocalTime pizzaDeliveryTime;
	private String pizzaType;
	private double pizzaPrice;
	private double pizzaCost;
	private double totalCost;
	private double totalPrice;
	private double profit;
	protected List<PizzaTopping> pizzaToppings = new ArrayList<PizzaTopping>();
	
	
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		if (quantity > 10) {				
			throw new PizzaException("Too many pizzas ordered");
		}
		else if (quantity < 1){			
			throw new PizzaException("No pizzas ordered");
		}
		else if (orderTime == deliveryTime){			
			throw new PizzaException("Cannot deliver a pizza instantaneously");
		}
		else if (orderTime.isBefore(deliveryTime)){			
			throw new PizzaException("Cannot deliver pizza before it was ordered");
		}
		else if (deliveryTime.getMinute() - orderTime.getMinute() < 10){			
			throw new PizzaException("Must allow 10 minutes to cook pizza");
		}
		else if (deliveryTime.getHour() - orderTime.getHour() > 1){			
			throw new PizzaException("Pizza has expired and must be thrown out");
		}
		else if (orderTime.getHour() < 7){			
			throw new PizzaException("Kitchen is not yet open at this time");
		}
		else if (orderTime.getHour() >= 23){			
			throw new PizzaException("Kitchen is now closed");
		}	
			pizzaQuantity = quantity;
			pizzaOrderTime = orderTime;
			pizzaDeliveryTime = deliveryTime;
			pizzaType = type;
		}

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		for	(int i = 0; i < pizzaToppings.size(); i++){			
			pizzaCost += pizzaToppings.get(i).getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		return pizzaCost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		return pizzaPrice;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		totalCost = pizzaCost * pizzaQuantity;
		return totalCost;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		totalPrice = pizzaPrice * pizzaQuantity;
		return totalPrice;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		profit = pizzaPrice - pizzaCost;
		return profit;
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		for	(int i = 0; i < pizzaToppings.size(); i++){
			
			if (pizzaToppings.get(i) == topping){			
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return pizzaQuantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return pizzaType;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}
}
