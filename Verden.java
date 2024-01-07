// Denne klassen lager et rutenett med ferdig fylte celler, skriver ut nettet, samt oppdaterer det for hver generasjon.

class Verden {
    private int antRader;
    private int antKolonner;
    private Rutenett rutenett;
    public int genNr;

    // Valgfritt antall rader & kolonner. Lager nettet, fyller med celler og kobler dem sammen. Starter paa generasjon 0.
    public Verden(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        rutenett = new Rutenett(antRader, antKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller(); 
        genNr = 0;
    }

    // Tegner rutenettet med rutenett-metoden.
    public void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjon nr: " + genNr);
        System.out.println("Antall levende celler: " + rutenett.antallLevende());

    }
    // Gaar gjennom rutenettet to ganger. Foerst for aa telle naboene til hver celle, saa for aa oppdatere hver celles levestatus. Oeker generasjonen.
    public void oppdatering() {
        for(int rad = 0; rad < antRader; rad++) {
            for(int kol = 0; kol < antKolonner; kol++) {
                rutenett.hentCelle(rad, kol).tellLevendeNaboer(); {
                }
            }
        }
        for(int rad = 0; rad < antRader; rad++) {
            for(int kol = 0; kol < antKolonner; kol++) {
                rutenett.hentCelle(rad, kol).oppdaterStatus();
            }
        }
        genNr++;
    }

    // Ny metode til bruk i GUI.
    public Rutenett hentRutenett() {
        return rutenett;
    }
}