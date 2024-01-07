// Hovedprogrammet for spillet. Importerer Scanner-klasse saa bruker kan gi antall rader & kolonner. 

import java.util.Scanner;

class GameOfLife {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Hvor mange rader?: ");
        int rad = inp.nextInt();
        System.out.print("Hvor mange kolonner?: ");
        int kol = inp.nextInt();
        inp.close();
        
        // Lager rutenettet. Tegner nettet. Deretter oppdaterer og tegner i 3 generasjoner.
        Verden verden = new Verden(rad, kol);
        
        verden.tegn();
        for(int i = 0; i < 10; i++) {
            verden.oppdatering();
            verden.tegn();
        }
    }
}