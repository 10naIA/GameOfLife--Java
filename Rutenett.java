/* (OBS: Jeg har hentet noe inspirasjon fra Python-versjonen jeg lagde av denne klassen i in1000 i fjor.) 
Denne klassen lager et rutenett for instanser av typen Celle, med valgfritt antall rader & kolonner. */

class Rutenett {
    public int antRader;
    public int antKolonner;
    public Celle [][] rutene;

    // Konstruktoer. Setter instansvariablene lik parameterne med this. Lager rutenettet.
    public Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    // Lager celle, 1/3 sjanse for at den blir levende. Settes inn i rutenettet.
    public void lagCelle(int rad, int kol) {
        Celle celle = new Celle();
        if(Math.random() <=0.3333) {
            celle.settLevende();
        }
        rutene[rad][kol] = celle;
    }

    // Gaar gjennom rutenettet, og fyller med celler.
    public void fyllMedTilfeldigeCeller() {
        for(int rad = 0; rad < antRader; rad++) {
            for(int kol = 0; kol < antKolonner; kol++) {
                lagCelle(rad, kol);
            }
        }
    }
    // Henter celle paa angitt indeks, men sjekker foerst at gyldig indeks.
    public Celle hentCelle(int rad, int kol) {
        if((rad >= 0 && rad < antRader) && (kol >= 0 && kol < antKolonner)) {
            return rutene[rad][kol];
        } 
        else {
            return null;
        }
    }
    // Gaar gjennom rutenettet, og skriver ut cellenes symboler. 
    public void tegnRutenett() {
        System.out.println("\n\n\n\n\n");
        for(int rad = 0; rad < antRader; rad++) {
            System.out.println();
            for(int kol = 0; kol < antKolonner; kol++) {
                System.out.print(rutene[rad][kol].hentStatusTegn() + " ");
            }
        }
        System.out.println("\n");
    }
    /* Setter naboer for cellen paa angitt indeks. 'hentCelle' returnerer en celle, og 'leggTilNabo' kan dermed brukes paa denne, 
    med nabocelle som argument. (Finnes sikkert en mer effektiv maate aa skrive denne metoden paa!). */ 
    public void settNaboer(int rad, int kol) {
        if(hentCelle(rad-1, kol-1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol-1));
        }
        if(hentCelle(rad-1, kol) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol));
        }
        if(hentCelle(rad-1, kol+1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad-1, kol+1));
        }
        if(hentCelle(rad, kol-1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad, kol-1));
        }
        if(hentCelle(rad, kol+1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad, kol+1));
        }
        if(hentCelle(rad+1, kol-1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol-1));
        }
        if(hentCelle(rad+1, kol) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol));
        }
        if(hentCelle(rad+1, kol+1) != null) {
            hentCelle(rad, kol).leggTilNabo(hentCelle(rad+1, kol+1));
        }
    }
    // Gaar gjennom rutenettet, og setter alle naboene.
    public void kobleAlleCeller() {
        for(int rad = 0; rad < antRader; rad++) {
            for(int kol = 0; kol < antKolonner; kol++) {
                settNaboer(rad, kol);
            }
        }
    }
    // Teller antall levende celler i nettet. Setter teller til 0, for aa ikke faa feil hvis metoden kalles paa flere ganger.
    public int antallLevende() {
        int antLevende = 0;
        for(int rad = 0; rad < antRader; rad++) {
            for(int kol = 0; kol < antKolonner; kol++) {
                if(rutene[rad][kol].erLevende()) {
                    antLevende++;
                }
            }
        }
        return antLevende;
    }

    // Ny metode til bruk i GUI.
    public Celle[][] hentAlleCeller() {
        return rutene;
    }
}