package Project01;
import java.util.List;
public class Requests extends RequestDonationList 
{
    //Μέθοδοι
    // add: αφού ελέγξει τα α, β, καλεί την add της RequestDonationList
    public void add(Organization o, Beneficiary b, RequestDonation rd) throws Exception 
    {
        for (int i = 0; i < getrdEntities().size(); i++) // προσπέλαση του μεγέθους της λίστας rdEntities
        {
            RequestDonation current_rd = getrdEntities().get(i); // ονομασία της συγκεκριμένης RequestDonation της λίστας rdEntities
            if (current_rd.getQuantity() >= rd.getQuantity()) // αν το Quantity της rd υπάρχει στον οργανισμό (rd <= current_rd)
            {
                if (validRequestDonation(o, b, rd) == true) // αν ο Beneficiary δικαιούται την ποσότητα
                {
                    super.add(o, rd); // καλείται η add της RequestDonationList
                } 
                else 
                {
                    throw new Exception("Benefiaciary is Invalid."); // εξαίρεση του β
                }
                break;
            } 
            else 
            {
                throw new Exception("Entity is Unavailiable."); // εξαίρεση του α
            }
        }
        if(getrdEntities().size() == 0) // αν οι rdEntities λίστα είναι άδεια, να προστεθεί η RequestDonation σε αυτήν
        {
            rdEntities.add(rd);
        }
    }

    // λειτουργεί όπως η add, αλλά για την modify
    public void modify(Organization o, RequestDonation rd, Beneficiary b, double newQuantity) throws Exception 
    {
        for (int i = 0; i < getrdEntities().size(); i++) 
        {
            RequestDonation current_rd = getrdEntities().get(i);
            if (current_rd.getQuantity() >= rd.getQuantity()) 
            {
                if (validRequestDonation(o, b, rd) == true) 
                {
                    super.modify(rd, newQuantity);
                } 
                else 
                {
                    throw new Exception("Benefiaciary is Invalid.");
                }
                break;
            } 
            else 
            {
                throw new Exception("Entity is Unavailiable.");
            }
        }
    }

    // validRequestDonation: ελέγχει αν ο Beneficiary δικαιούται, σύμφωνα με τον αριθμό μελών στην οικογένειά του, την ποσότητα που ζητά
    public boolean validRequestDonation(Organization o, Beneficiary b, RequestDonation rd) 
    {
        double allowedQuantity, requestedQuantity;
        String[] details = (rd.getEntity().getDetails()).split(" ");
        if (details[0].equals("Service")) 
        {
            List<Entity> eList = o.getEntityList(); // δημιουργία λίστας eList από Entity στην οποία εισάγουμε την EntityList της Organization
            for (int i = 0; i < eList.size(); i++) // προσπέλαση του μεγέθους της λίστας eList
            {
                if (eList.get(i).getEntityInfo().equals(rd.getEntity().getEntityInfo())) // αν τα EntityInfo των eList και των Entity της rd είναι κοινά
                {
                    return true; // δεν χρειάζεται να ελέγξει αν ο Beneficiary την δικαιούται, αφού η Service δεν έχει ποσότητα
                }
            }
        }
        if (b.getnoPersons() == 1) // αν ο Beneficiary ανήκει στο level1
        {
            allowedQuantity = Double.parseDouble(details[1]); // θέση της ποσότητας που ζήτησε ο Beneficiary στην allowedQuantity, Double.parseDouble(details[1]) -> μετατροπή του String περιεχομένου του πίνακα details σε double
        } 
        else if ((b.getnoPersons() > 1) && (b.getnoPersons() < 5))  // αν ο Beneficiary ανήκει στο level2
        {
            allowedQuantity = Double.parseDouble(details[2]); // θέση της ποσότητας που ζήτησε ο Beneficiary στην allowedQuantity
        } 
        else  // αν ο Beneficiary ανήκει στο level3
        {
            allowedQuantity = Double.parseDouble(details[3]);  // θέση της ποσότητας που ζήτησε ο Beneficiary στην allowedQuantity
        }
        requestedQuantity = rd.getQuantity(); // θέση της requestedQuantity ίση με το Quantity της rd
        System.out.println("\nAllowed Quantity = " + allowedQuantity);
        System.out.println("Requested Quantity = " + requestedQuantity + "\n");
        if (requestedQuantity > allowedQuantity) // αν ζητήσει ο Beneficiary περισσότερα από όσα μπορεί να πάρει να επιστρέψει η validRequestDonation false, αλλιώς true
        {
            return false;
        } 
        else 
        {
            return true;
        }
    }

    // ελέγχει εκ νέου επειδή ο Beneficiary, μπορεί να κάνει δυο Requests και να του επιτρέπεται να πάρει το πρώτο, αλλά να μην μπορεί πλέον με τις αλλαγές στην ποσότητα, να μπορεί να πάρει το δεύτερο
    // ουσιαστικά η commit κάνει save τα Requests που ζητά και μπορεί να πάρει και τα διαγράφει από την λίστα με RequestDonations
    public void commit(Organization o, Beneficiary b) throws Exception
    {
        boolean flag = false;
        for (int i = 0; i < getrdEntities().size(); i++)
        {
            RequestDonation current_rd = getrdEntities().get(i);
            String[] current_details = (current_rd.getEntity().getDetails()).split(" ");
            for(int j=0; j < o.getCurrentDonations().getrdEntities().size(); j++) // προσπέλαση του μεγέθους της λίστας rdEntities του συγκεκριμένου CurrentDonations, τύπου RequestDonationList
            {
                RequestDonation o_rd = o.getCurrentDonations().getrdEntities().get(j);
                //String[] details = (o_rd.getEntity().getDetails()).split(" ");
                if(current_rd.getEntity().getEntityInfo().equals(o_rd.getEntity().getEntityInfo()))
                {
                    if (current_details[0].equals("Service")) // αν είναι Service
                    {
                        flag = true;
                        System.out.println(current_rd.getEntity().getName()); // να εκτυπωθεί το όνομα του Entity του current_rd, δηλαδή του συγκεκριμένου RequestDonation της getrdEntities()
                        break;
                    }
                    if (current_details[0].equals("Material")) // αν είναι Material
                    {
                        if (current_rd.getQuantity() <= o_rd.getQuantity()) // αν ισχύουν οι α, β έλεγχοι
                        {
                            if(validRequestDonation(o, b, current_rd) == true)
                            {
                                double nq = o_rd.getQuantity() - current_rd.getQuantity(); // αφαίρεση της current_rd ποσότητας από την o_rd ποσότητα
                                o.getCurrentDonations().modify(o_rd, nq); // κλήση της modify της RequestDonationList μέσω της getCurrentDonations() του οργανισμού
                                getrdEntities().remove(current_rd); // αφαίρεση της current_rd από την rdEntities λίστα
                            }
                            else 
                            {
                                throw new Exception("Benefiaciary is Invalid."); // εξαίρεση του β
                            }
                            flag = true;
                            break;
                        } 
                        else 
                        {
                            throw new Exception("Entity is Unavailiable."); // εξαίρεση του α
                        }
                    }
                }
            }    
        }
    }
}