/* (OBS: Jeg har hentet noe inspirasjon fra Python-versjonen jeg lagde av denne klassen i in1000 i fjor.)
Med denne klassen kan man lage en celle, se/endre levestatus, legge til og telle dens naboer. */ 

class Celle {

    // Definerer instansvariablene.
    public boolean levende;
    private Celle [] naboer;
    public int antNaboer;
    public int antLevendeNaboer;


    // Konstruktoeren. En celle starter alltid som doed, og har en tom liste med plass til 8 naboceller.
    public Celle() {
        levende = false;
        naboer = new Celle [8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    // De to neste metodene endrer status til doed/levende.
    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }

    // Angir cellens symbol i rutenettet.
    public char hentStatusTegn() {
        if(levende) {
            return 'O';
        } else {
            return '.';
        }
    }

    // Sjekker at antall naboer er faerre enn listens lengde, og legger evt til ny Celle-nabo.
    public void leggTilNabo(Celle nabo) {
        if(antNaboer < naboer.length) {
            naboer[antNaboer] = nabo;
            antNaboer++;
        } else {
            System.out.println("Listen er full.");
        }
    }

    /* Gaar gjennom nabolisten. Sjekker at det er en celle paa indeksen (hvis ikke --> evt NullPointerException-error), 
    og hvis levende, oekes telleren. */
    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for(int i = 0; i < naboer.length; i++) {
            if(naboer[i] != null && naboer[i].erLevende()) {
                antLevendeNaboer++;
                }
            } 
        }
        
    // Cellen doer hvis under 2 eller over 3 naboer. Den blir levende hvis akkurat 3 naboer.
    public void oppdaterStatus() {
        if(levende && antLevendeNaboer <= 1) {
            settDoed();
        }
        else if(levende && antLevendeNaboer >3) {
            settDoed();
        }
        else if(!levende && antLevendeNaboer == 3) {
            settLevende();
       }
    }
}
