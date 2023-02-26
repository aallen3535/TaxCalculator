package taxCalculator3;

public class Profile {
	private int ssn, dependents, income;
	private double taxAmount;
	
	Profile(){
		
	}
	
	Profile(int ssn, int dependents, int income, double taxAmount){
		this.ssn = ssn;
		this.dependents = dependents;
		this.income = income;
		this.taxAmount = taxAmount;
	}
	
	public int getSsn(){
		return ssn;
	}
	
	public int getDependents(){
		return dependents;
	}
	
	public int getIncome(){
		return income;
	}
	
	public double getTaxAmount() {
		return taxAmount;
	}

	public String toString() {		//for testing
		return ssn + "\n" + dependents + "\n" 
				+ income + "\n" + taxAmount + "\n";
	}
}