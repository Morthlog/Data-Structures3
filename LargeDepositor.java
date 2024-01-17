public class LargeDepositor 
{
    private int AFM; 
    private String firstName; 
    private String lastName; 
    private double savings; 
    private double taxedIncome; 

    int key() 
    {
        return AFM;
    }

    String getFirstName() 
    {
        return firstName;
    }

    String getLastName() 
    {
        return lastName;
    }

    double getSavings() 
    {
        return savings;
    }
    
    double getTaxedIncome() 
    {
        return taxedIncome;
    }

    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    void setSavings(double savings)
    {
        this.savings = savings;
    }

    void setTaxedIncome(double taxedIncome)
    {
        this.taxedIncome = taxedIncome;
    }
}
