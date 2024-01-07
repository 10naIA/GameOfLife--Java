/*Klasse for aa lage et rute-objekt til rutenettet. Hver rute har en tilhoerende celle. Sender inn JLabel og rutenettet for aa kunne 
oppdatere antall levende celler i nettet. */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Rute extends JButton {
    Celle celle;
    JLabel antall;
    Rutenett rutenett;

    public Rute(JLabel antall, Celle celle, Rutenett rutenett) {
        this.antall = antall;
        this.celle = celle;
        this.rutenett = rutenett;
    }

    // Metode for aa legge til hva ruten skal gjoere naar man trykker paa den.
    public void initGUI() {
        addActionListener(new EndreStatus());
    }

    public Celle hentCelle() {
        return celle;
    }

    // Naar en rute klikkes paa, faar dens tilhoerende celle motsatt tilstand (doed/levende).
    class EndreStatus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Henter ruteobjektet, og avhengig om levende/doed celle endres ruteteksten.
            Rute rute = (Rute)e.getSource();
            if(celle.erLevende() == true) {
                rute.setText("");
                celle.settDoed();
            }
            else {
                rute.setText("O");
                // Setter oensket utseende paa skriften
                rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
                celle.settLevende();
            }
            // Oppdaterer antall levende celler.
            antall.setText("Antall levende: " + rutenett.antallLevende());
            antall.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        }
    }
} 