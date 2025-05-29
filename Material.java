package Project01;
public class Material extends Entity
{
    //Πεδία
    private static double level1;
    private static double level2;
    private static double level3;
    
    //Μέθοδοι
    public Material(String name, String description, double lvl1, double lvl2, double lvl3) // Material's Constractor
    {
        this.name = name;
        this.description = description;
        level1 = lvl1;
        level2 = lvl2;
        level3 = lvl3;
        id = count++;
    } 
    
    public String getDetails()
    {
        return "Material" + " " + level1 + " " + level2 + " " + level3;
    }
}