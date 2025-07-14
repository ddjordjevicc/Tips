import javax.swing.*;
import java.util.*;

public class SmenaAppGUI {
    static class Osoba {
        String ime;
        int nivo;
        int brojSmena;
        int ukupnoSmena;
        double zarada;

        Osoba(String ime) {
            this.ime = ime;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> pokreniAplikaciju());
    }

    public static void pokreniAplikaciju() {
        List<String> imena = Arrays.asList(
            "Alfonso", "Victor", "Max", "Kate", "Nikita", "Anna", "Brooke", "Amelia",
            "Dogukan", "Mihajlo", "Dusan", "Emilija", "Janja", "Milica", "Mateja B", "Mateja M",
            "Ema", "Lily", "Jaemasen", "Laci", "Isabella", "Gianna", "Gio", "Addison", "Cameron", "Jane", "Roman", "Cooper"
        );

        List<Osoba> osobe = new ArrayList<>();
        int ukupnoSmena = 0;

        for (String ime : imena) {
            int nivo = unesiBroj("Unesi nivo za: " + ime + " (1-4)", 1, 4);
            int smene = unesiBroj("Koliko smena ima: " + ime + "?", 0, 1000);

            Osoba o = new Osoba(ime);
            o.nivo = nivo;
            o.brojSmena = smene;
            o.ukupnoSmena = nivo * smene;
            osobe.add(o);
            ukupnoSmena += o.ukupnoSmena;
        }

        int ukupnoPara = unesiBroj("Unesi ukupno para u dolarima ($):", 0, 1_000_000);
        double paraPoSmeni = (double) ukupnoPara / ukupnoSmena;

        for (Osoba o : osobe) {
            o.zarada = paraPoSmeni * o.ukupnoSmena;
        }

        // Prikaz rezultata
        StringBuilder sb = new StringBuilder();
        sb.append("Ukupno smena: ").append(ukupnoSmena).append("\n");
        sb.append("Zarada po smeni: ").append(String.format("$%.2f", paraPoSmeni)).append("\n\n");

        for (Osoba o : osobe) {
            sb.append(o.ime)
              .append(" - ")
              .append(o.ukupnoSmena)
              .append(" smena - ")
              .append(String.format("$%.2f", o.zarada))
              .append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Rezultati", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int unesiBroj(String poruka, int min, int max) {
        while (true) {
            String unos = JOptionPane.showInputDialog(null, poruka);
            if (unos == null) {
                JOptionPane.showMessageDialog(null, "Prekid programa.");
                System.exit(0);
            }

            try {
                int broj = Integer.parseInt(unos.trim());
                if (broj >= min && broj <= max) {
                    return broj;
                } else {
                    JOptionPane.showMessageDialog(null, "Unesi broj između " + min + " i " + max);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Neispravan unos. Pokušaj ponovo.");
            }
        }
    }
}
