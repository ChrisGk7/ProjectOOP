package Project01;
import java.util.ArrayList;
public class RequestDonationList 
{
    // Πεδία
    protected ArrayList <RequestDonation> rdEntities = new ArrayList <RequestDonation> ();

    //Μέθοδοι
    public RequestDonation get(int id) // Returns specific RequestDonation by being given it's id
    {
        return rdEntities.get(id);
    }

    public ArrayList <RequestDonation> getrdEntities() // Returns rdEntities list
    {
        return rdEntities;
    }

    public void add(Organization o, RequestDonation rd) throws Exception
    {
       boolean flag=false;
       for(int i=0; i < rdEntities.size(); i++) // προσπέλαση του μεγέθους της λίστας rdEntities
       {
           RequestDonation current_rd = rdEntities.get(i); // ονομασία της συγκεκριμένης RequestDonation της λίστας rdEntities
           String[] details = (rd.getEntity().getDetails()).split(" "); // δημιουργία πίνακα details τύπου String, με τα getDetails() της RequestDonation rd χωρισμένα στον πίνακα
           if(current_rd.getEntity().getEntityInfo().equals(rd.getEntity().getEntityInfo())) // έλεγχος των getEntityInfo() των current_rd και rd, αν είναι ίδια
           {
               if (details[0].equals("Material")) // αν το details[0] = Material
               {
                    current_rd.setQuantity(current_rd.getQuantity()+rd.getQuantity()); // αλλαγή ποσότητας του current_rd
                    rdEntities.set( i, current_rd); // θέση της νέας RequestDonation και της αλλαγής ποσότητας στην rdEntities
                    flag = true;
                    break;
                }
            }
        }
       if(flag == false) // αν δεν υπάρχει στην rdEntities, προστίθεται στην λίστα
       {
           rdEntities.add(rd);
           return;
        }
       
       flag = false;
       for(int i=0; i < o.getEntityList().size(); i++) // ελέγχει αν το Entity υπάρχει στην EntityList του οργανισμού
       {
           if(rdEntities.get(i).getEntity().getEntityInfo().equals(rd.getEntity().getEntityInfo())) //αν τα getEntityInfo() τους είναι κοινά
           {
               flag = true;
               break;
           }
       }
       if(flag == false)
       {
           throw new Exception("None existent Entity");
        }
    }

    public void remove(RequestDonation rd) // αφαίρεση RequestDonation από την λίστα rdEntities
    {
        for(int i=0; i < rdEntities.size(); i++)
       {
           if(rdEntities.get(i).getEntity().getEntityInfo().equals(rd.getEntity().getEntityInfo())) // ελέγχει αν getEntityInfo() τους είναι κοινά
           {
                rdEntities.remove(i);
                break;
           }
       }
    }

    // modify: τροποποιεί το Quantity μιας RequestDonation (παίρνει throws Exception επειδή εγείρεται εξαίρεση στην modify της κλάση Requests, η οποία την καλή την modify με super)
    public void modify(RequestDonation rd, double newQuantity) throws Exception
    {
        for(int i=0; i < getrdEntities().size(); i++)
        {
            RequestDonation current_rd = getrdEntities().get(i);
            String[] details = (rd.getEntity().getDetails()).split(" ");
            if(current_rd.getEntity().getEntityInfo().equals(rd.getEntity().getEntityInfo()))
            {
                if (details[0].equals("Material")) // έλεγχος μόνο των Entity Material, επειδή μόνο αυτά έχουν Quantity
                { 
                    current_rd.setQuantity(newQuantity); // θέση της νέας ποσότητας στην current_rd
                    break;
                }
            } 
        }
    }

    public void monitor() // εκτυπώνει τα toString(), δηλαδή όλες τις πληροφορίες, των Entity όλων των rdEntities
    {
        for(int i=0; i < rdEntities.size(); i++)
       {
           System.out.println(rdEntities.get(i).getEntity().toString());
       }
    }

    public void reset() // καθαρίζει την λίστα rdEntities
    {
        rdEntities.clear();
    }

    public void setQuantity(int i, RequestDonation rd) // θέτει την ποσότητα μίας RequestDonation της rdEntities
    {
        rdEntities.set(i, rd);
    } 
}