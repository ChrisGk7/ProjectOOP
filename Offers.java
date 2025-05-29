package Project01;
public class Offers extends RequestDonationList
{
    // Μέθοδοι
    // κάνει save τις προσφορές της rdEntities στο αντικείμενο CurrentDonations, τύπου RequestDonationList
    public void commit(Organization o) throws Exception
    {
        for (int i = 0; i < getrdEntities().size(); i++)
        {
            boolean flag = false;
            RequestDonation current_rd = getrdEntities().get(i);
            for (int j = 0; j < o.getCurrentDonations().getrdEntities().size(); j++)
            {
                RequestDonation current_cd = o.getCurrentDonations().getrdEntities().get(j);
                String[] details_cd = (current_cd.getEntity().getDetails()).split(" ");
                if(current_rd.getEntity().getEntityInfo().equals(current_cd.getEntity().getEntityInfo()))
                {
                    if (details_cd[0].equals("Service"))
                    {
                        getrdEntities().remove(i--);    // μικραίνουμε την λίστα rdEntites (out of bounds error), αφαιρούμε το Entity Service του current_cd
                        flag = true;
                        break;
                    }  
                    else // αν είναι Material, του δίνουμε νέα ποσότητα, το άθροισμα της υπάρχουσας ποσότητας του οργανισμού με την ποσότητα της RequestDonation της rdEntities λίστας που εξετάζω
                    {
                        double nq = current_cd.getQuantity() + current_rd.getQuantity();
                        super.modify(current_cd, nq);
                        getrdEntities().remove(i--); // μικραίνουμε την λίστα rdEntites (out of bounds error), αφαιρούμε το Entity Material του current_cd
                        flag = true;
                        break;
                    }
                }
            }
            if(flag == false)
            {   
                o.getCurrentDonations().getrdEntities().add(getrdEntities().get(i)); // αν δεν υπάρχει εκεί ήδη, κάνει add το entity στην currentDonations
            }
        }
    }
}