package Project01;
import java.util.ArrayList;
public class Main
{
    public static void main(String args[]) throws Exception
    {
        // τα Material
        Material rice = new Material("rice", "rice", 1, 2, 3);
        Material milk = new Material("milk", "milk", 1, 2, 3);
        Material sugar = new Material("sugar", "sugar", 1, 2, 3);

        // οι Services
        Service BabySitting = new Service("Babysitting", "Babysitting description");
        Service NurserySupport = new Service("Nursery Support", "Nursery Support description");
        Service MedicalSupport = new Service("Medical Support", "Medical Support description");

        // Δημιουργία και γέμισμα της entityList, τύπου Entity με τα Material και Services
        ArrayList <Entity> entityList = new ArrayList<Entity>();
        entityList.add(rice);
        entityList.add(milk);
        entityList.add(sugar);
        entityList.add(BabySitting);
        entityList.add(NurserySupport);
        entityList.add(MedicalSupport);

        // Δημιουργία του Οργανισμού
        Organization RedCross = new Organization("RedCross", entityList);
        System.out.println("Welcome to the Project!");

        // Δημιουργία του Admin
        Admin A = new Admin("George", "1"); 
        
        // Δημιουργία πίνακα τεσταρίσματος του πρότζεκτ
        ArrayList <Requests> testRequestedList = new ArrayList <Requests> ();

        // Δημιουργία των Beneficiary
        Beneficiary B1 = new Beneficiary("Maria", "2", 3);
        RedCross.insertBeneficiary(B1);
        B1.setRequestedList(testRequestedList);
        Beneficiary B2 = new Beneficiary("Jim", "3", 5);
        RedCross.insertBeneficiary(B2);

        // Δημιουργία της testRequest, τύπου Requests, για την διευκόλυνση τεσταρίσματος των Requests των Beneficiaries
        Requests testRequest = new Requests();
        testRequest.add(RedCross, B1, new RequestDonation(rice, 1));
        testRequest.add(RedCross, B1, new RequestDonation(BabySitting));
        testRequestedList.add(testRequest);

        // Δημιουργία του Donator
        Donator D = new Donator("Kate", "5");
        RedCross.insertDonator(D);

        // Δημιουργία της testOffer και της λίστας testOffersList, τύπου Offers, για την διευκόλυνση τεσταρίσματος των Offers του Donator
        Offers testOffer = new Offers();
        ArrayList <Offers> testOffersList = new ArrayList <Offers> ();
        testOffer.add(RedCross, new RequestDonation(rice, 20));
        testOffer.add(RedCross, new RequestDonation(milk, 10));
        testOffer.add(RedCross, new RequestDonation(BabySitting));
        testOffer.add(RedCross, new RequestDonation(NurserySupport));
        testOffersList.add(testOffer);
        D.setOffersList(testOffersList);
        for(int i=0; i < testOffersList.size(); i++)
        {
            RedCross.setTestCurrentDonations(testOffersList.get(i));
        }

        // Θέση του Admin A στον οργανισμό
        RedCross.setAdmin(A);

        // Δημιουργία Menu του Οργανισμού
        new Menu(RedCross);
    }
}