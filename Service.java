package Project01;
public class Service extends Entity
{
    //Μέθοδοι
    public Service(String name, String description) // Service's Constractor
    {
        this.name = name;
        this.description = description;
        id = count++;
    }

    public String getDetails()
    {
        return "Service";
    }
}