package Project01;
class Admin extends User
{
    //Πεδία
    private static boolean isAdmin = true;

    //Admin Constractor 
    public Admin(String n, String p)
    {
        name = n;
        phone = p;
    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }
}