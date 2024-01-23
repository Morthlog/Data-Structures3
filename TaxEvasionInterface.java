public interface TaxEvasionInterface 
{
    public void insert(LargeDepositor item);
    public void load(String filename);
    public void updateSavings(int AFM, double savings);
    public LargeDepositor searchByAFM(int AFM);
    public List<LargeDepositor> searchByLastName(String last_name);
    public void remove(int AFM);
    public double getMeanSavings();
    public void printTopLargeDepositors(int k);
    public void printByAFM();
}
