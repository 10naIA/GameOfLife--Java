/* Klasse for aa oppdatere rutenettet med nye generasjoner. Sender med verden-rutenettet, listen med alle rutene i nettet og antall-teksten for aa
kunne oppdatere den. */

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

class OppdaterBrett implements Runnable{
    Verden verden;
    ArrayList<Rute> rutene;
    JLabel antall;

    public OppdaterBrett(Verden verden, ArrayList<Rute> rutene, JLabel antall) {
        this.verden = verden;
        this.rutene = rutene;
        this.antall = antall;
    }

    // Brukes av egen traad som lages for aa oppdatere rutenettet.
    public void run() {
        boolean ferdig = false;
        while(!ferdig) {
            try {
                // Nettet oppdateres hvert 2.sek. Itererer gjennom hele rutenettet, og oppdaterer hver tilknyttede celle.
                Thread.sleep(2000);
                verden.oppdatering();
                for(Rute rute : rutene) {
                    Celle celle = rute.hentCelle();
                    celle.oppdaterStatus();
                    if(celle.levende) {
                        rute.setText("O");
                        rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
                    }
                    else {
                        rute.setText("");
                    }
                }
                // Oppdaterer antall levende celler i GUI-vinduet.
                antall.setText("Antall levende: " + verden.hentRutenett().antallLevende());
                antall.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            }
            catch(InterruptedException i) {
                ferdig = true;
                return;
            }
        }
    }
}