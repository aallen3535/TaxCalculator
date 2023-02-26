package taxCalculator3;
import java.util.*;

public class TaxCalculator {
	
	public static void main(String[] args) {
		int ssn = 0, dependents = 0, income = 0;
		int select = 0;		//menu variable
		double taxAmount = 0;
		
		Profile prof = new Profile();		//profile initialization
		
		HashMap<Integer, Profile> profileMap = new HashMap<Integer, Profile>(); //hashmap to store created user profiles
																				//<KEY = SSN, VALUE = user profile object>
		Scanner std = new Scanner(System.in);	//read input
		
		System.out.println("Welcome to the simple tax calculator...\n");
		System.out.println("Valid incomes range between 0 - 1000000");
		System.out.println("Please enter no more than 10 dependents");
		//menu loop		
		
		while(select != 3) {
			
			menuOp();
			select = std.nextInt();
			
			if(select == 1) {		//if menu option 1 selected, prompt user to enter profile information
				System.out.print("Enter SSN: ");
				ssn = std.nextInt();
				System.out.printf("Enter income: ");
				income = std.nextInt();
				System.out.printf("Enter dependents: ");
				dependents = std.nextInt();
				
				taxAmount = calculateTax(income, dependents);		//user's input sent to tax calc function and returned
																	//-1 is returned if invalid
				if(taxAmount == -1) {			//parameters of tax calculator violated, send user back to menu
					System.out.println("\nInvalid entry\nReturning to menu\n");
					continueOp();
				}else {
					prof = new Profile(ssn, dependents, income, taxAmount);		//create profile
					profileMap.put(ssn, prof);									//store profile in hashmap
					
					System.out.println("\nProfile successfully created and stored\n");
					System.out.printf("Tax amount: $%,.2f\n", prof.getTaxAmount());
					continueOp();
				}

			}else if(select == 2) {		//if menu option 2 selected, prompt user for profile id (SSN)
				int ssnSearch;
				
				System.out.println("Please enter SSN:\n");
				ssnSearch = std.nextInt();
				
				Profile tempProf = profileMap.get(ssnSearch);	//retrieve profile from hashmap
				
				if(tempProf == null) {		//if entered profile does not exist, return to menu
					System.out.println("\nProfile not found\nReturning to menu\n");
					continueOp();
				}else		//entered profile exists, display to console
				displayProfile(tempProf);
				continueOp();

			}else if(select == 3) { 	//if menu option 3 selected, exit program
				System.out.println("\nGoodbye...");
				return;
			}else{		
				System.out.println("\nInvalid command");
				continueOp();
			}
		}
	}
		public static void menuOp() {	//print statements to display menu options
			System.out.println("Type the number associated with "
					+ "the desired function followed by the enter key...\n");
			System.out.println("1. Add profile");
			System.out.println("2. Retrieve profile");
			System.out.println("3. Exit\n\n");
		}
		
		public static void continueOp() {	//"press enter to continue" logic
			System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        } 
	        catch(Exception e)
	        {}
		}
	
		public static double calculateTax(int income, int dependents){ //logic to calculate tax rate
		  double tax = 0, taxRate = 0;
		  if(income < 0 || income > 1000000 || dependents < 0 || dependents > 10){	
	    	  return -1 ;					//if input is outside of parameters, return error code
	      }

	      else{
	    	  if(income >= 0 && income < 25000){		//check for first tax bracket
		    	  taxRate=0;
		      }else if(income <= 100000){
	             if(dependents < 5)			
	            	 taxRate=0.22;
	             else
	            	 taxRate=0.20;

	           } else if(income <= 1000000 ){	//check for second tax bracket
	        	   	if(dependents < 3)
	        	   		taxRate=0.27;
			        else
			        	taxRate=0.25;
	           }

	       tax = income * taxRate;
	      
	       return tax;
	       }
	}

		public static void displayProfile(Profile tempProf) {	//print statements to display profile info
			System.out.printf("                  SSN: %d\n", tempProf.getSsn());
			System.out.printf("           Dependents: %d\n", tempProf.getDependents());
			System.out.printf("               Income: $%,d\n", tempProf.getIncome());
			System.out.printf("Calculated tax amount: $%,.2f\n\n", tempProf.getTaxAmount());
		}
}	

