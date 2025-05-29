package Project01;
public class RequestDonation
{
    //Πεδία
    private Entity entity;
    private double quantity = 1;

    //Μέθοδοι
    public RequestDonation(Entity e, double q) // RequestDonation's Constractor 1
    {
        entity = e;
        quantity = q;
    }

    public RequestDonation(Entity e) // RequestDonation's Constractor 2
    {
        entity = e;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setQuantity(double q)
    {
        quantity = q;
    }

    public double getQuantity()
    {
        return quantity;
    }
}
