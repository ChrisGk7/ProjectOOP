package Project01;
import java.util.ArrayList;
public class Donator extends User
{
    //Πεδία
    private static boolean isAdmin = false;
    private ArrayList <Offers> offersList = new ArrayList <Offers>();
    
    //Μέθοδοι
    public Donator(String n, String p) // Donator's Constractor
    {
        name = n;
        phone = p;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public ArrayList <Offers> getOffersList()
    {
        return offersList;
    }

    public void setOffersList(ArrayList <Offers> o)
    {
        offersList = o;
    }
}