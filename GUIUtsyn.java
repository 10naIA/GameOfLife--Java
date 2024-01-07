/* Klasse for aa opprette GUI-vindu til Game of Life, med egen metode for implementasjon (kontroller). Har de indre klassene Start og Stopp 
med tilhoerende action events.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Har JLabelen 'antall' som instansvariabel fordi den sendes inn i Start-klassens actionPerformed.
class GUIUtsyn {
    int rad;
    int kol;
    Verden verden;
    JLabel antall;
    ArrayList<Rute> rutene = new ArrayList<>();
    boolean kjoerer = false;

    public GUIUtsyn(int rad, int kol, Verden verden) {
        this.rad = rad;
        this.kol = kol;
        this.verden = verden;
        antall = new JLabel("Antall levende: " + verden.hentRutenett().antallLevende());
    }

    // Startknappen oppdaterer nettet med nye generasjoner. Legger inn sjekk saa ikke flere traader lages om man trykker paa start flere ganger.
    class Start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Thread traad;
            OppdaterBrett brett = new OppdaterBrett(verden, rutene, antall);
            if(!kjoerer) {
                traad = new Thread(brett);
                traad.start();
                kjoerer = true;
            }
        }
    }

    // Stopp-knappen avslutter programmet og lukker GUI-vinduet.
    class Stopp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Metode som lager GUI-implementasjonen.
    public void kontroller() {
        // Gir GUI et utseende som vil funke paa alle maskiner.
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e){
            System.exit(1);
        }

        // Lager vinduet. Hvis vinduet lukkes, avsluttes programmet.
        JFrame vindu = new JFrame("Game of life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Lager hovedpanelet, med oensket stoerrelse ved programstart. Legges oppaa vinduet.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400,400));
        vindu.add(panel);

        // Lager eget panel for knapper (start, avslutt), og setter den oeverst paa hovedpanelet.
        JPanel knapper = new JPanel();
        panel.add(knapper, BorderLayout.NORTH);

        // Setter oensket tekst-utseende paa JLabelen antall, og legger til knapper-panelet.
        antall.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        knapper.add(antall);

        // Start-knappen faar oensket tekst-utseende og farge (mintgroenn), og tilknyttes sitt action event.
        JButton startKnapp = new JButton("Start");
        startKnapp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        startKnapp.setBackground(new Color(152,255,152));
        startKnapp.addActionListener(new Start());
        knapper.add(startKnapp);

        // Avsluttknappen faar rosa farge.
        JButton avsluttKnapp = new JButton("Avslutt");
        avsluttKnapp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        avsluttKnapp.setBackground(new Color(246,153,205));
        avsluttKnapp.addActionListener(new Stopp());
        knapper.add(avsluttKnapp);

        // Lager panel for rutenettet, og setter det nederst i hovedpanelet. Lager rutenettet ut ifra brukerens input.
        JPanel rutenettPanel = new JPanel();
        panel.add(rutenettPanel, BorderLayout.SOUTH);
        rutenettPanel.setLayout(new GridLayout(rad, kol));

        // Iterer gjennom rutenettet og listen med alle cellene. Lager ny rute, som faar én celle.
        Celle [][] cellene = verden.hentRutenett().hentAlleCeller();
        for(int x = 0; x < rad; x++) {
            for(int y = 0; y < kol; y++) {
                Celle celle = cellene[x][y];
                Rute rute = new Rute(antall, celle, verden.hentRutenett());
                // Kobler knappen med dens action event i Rute-klassen.
                rute.initGUI();
                rutenettPanel.add(rute);
                // Setter fiolette kantlinjer rundt ruten.
                rute.setBorder(BorderFactory.createLineBorder(new Color(177, 156, 217)));

                // Legger rute i liste med alle rutene. Listen brukes i Start-knappen sitt action event.
                rutene.add(rute);
                if(celle.erLevende() == true) {
                    rute.setText("O");
                    rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
                }
            }
        }
        // Legger rutenettet til hovedpanelet, gjoer klart til visning og setter vinduet til midten av skjermen (null).
        panel.add(rutenettPanel);
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
}
