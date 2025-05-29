package Project01;
import java.util.ArrayList;
class Beneficiary extends User
{
    //Πεδία
    private int noPersons = 1;
    private static boolean isAdmin = false;
    private ArrayList <RequestDonationList> receivedList = new ArrayList <RequestDonationList>();
    private ArrayList <Requests> requestedList = new ArrayList <Requests>();

    //Μέθοδοι
    public Beneficiary(String n, String p, int noP) // Beneficiary's Constractor
    {
        name = n;
        phone = p;
        noPersons = noP;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public int getnoPersons()
    {
        return noPersons;
    }
    
    public ArrayList <RequestDonationList> getReceivedList()
    {
        return receivedList;
    }

    public ArrayList <Requests> getRequestedList()
    {
        return requestedList;
    }
    
    public void setRequestedList(ArrayList <Requests> r)  // used for initializing requested list for testing
    {
        requestedList = r;
    }
}