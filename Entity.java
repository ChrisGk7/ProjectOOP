package Project01;
public abstract class Entity 
{
    //Πεδία
    protected String name;
    protected String description;
    protected int id = 0;
    protected static int count = 0;

    //Μέθοδοι
    public String getName()
    {
        return name;
    }
    
    public String getEntityInfo() 
    {
        return name + " " + description + " " + id;
    }

    public abstract String getDetails();

    public String toString()
    {
        return getEntityInfo() + " " + getDetails();
    }
}