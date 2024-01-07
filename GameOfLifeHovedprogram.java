// Hovedprogrammet for spillet Game of Life. Bruker angir str paa rutenettet. Oppretter GUI-vindu med brukerens valgte dimensjoner. 
import java.util.Scanner;

class GameOfLifeHovedprogram {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.print("Hvor mange rader?: ");
        int rad = inp.nextInt();
        System.out.print("Hvor mange kolonner?: ");
        int kol = inp.nextInt();
        inp.close();

        Verden verden = new Verden(rad, kol);
        GUIUtsyn gui = new GUIUtsyn(rad, kol, verden);
        gui.kontroller();  
    }
}