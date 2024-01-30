public class LargeDepositor implements  Comparable
{
    private int AFM; 
    private String firstName; 
    private String lastName; 
    private double savings; 
    private double taxedIncome; 

    public int key() 
    {
        return AFM;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public double getSavings() 
    {
        return savings;
    }
    
    public double getTaxedIncome() 
    {
        return taxedIncome;
    }

    public void setAFM(int AFM)
    {
        this.AFM = AFM;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setSavings(double savings)
    {
        this.savings = savings;
    }

    public void setTaxedIncome(double taxedIncome)
    {
        this.taxedIncome = taxedIncome;
    }
    
    public String toString()
    {
        return "First name: " + firstName + 
        "\nLast name: " + lastName +
        "\nSavings: " + savings +
        "\nTaxed Income: " + taxedIncome;
    }

	@Override
	public boolean CompareTo(LargeDepositor b)
	{
		if(this.getTaxedIncome()<8000 && b.getTaxedIncome()<8000)
		{
			//for consistent results
			if(this.getSavings() - this.getTaxedIncome() < b.getSavings() - b.getTaxedIncome())
			{
				return true;
			}
			else return false;
		}
		else if(this.getTaxedIncome()<8000 && b.getTaxedIncome()>8000)
		{
			return false;
		}
		
		else if (this.getTaxedIncome()>8000 && b.getTaxedIncome()<8000) 
		{
			return true;
		}
		else if(this.getSavings() - this.getTaxedIncome() < b.getSavings() - b.getTaxedIncome())
			return true;
		else return false;
		
	}
}
