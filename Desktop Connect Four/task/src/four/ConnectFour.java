package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    public String obecnyGracz = "X"; // Służy do rozpoznania ruchu
    public final JButton[][] Przyciski = new JButton[6][7]; // tablica przyciskow
    int RZEDY = 6;
    int KOLUMNY = 7;
    public boolean czySkonczona = false; // Sprawdza czy gra skonczona

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLayout(new BorderLayout());
        setTitle("Connect Four");

        JPanel panelGry = new JPanel();
        panelGry.setLayout(new GridLayout(6, 7));

        for (int i = RZEDY; i > 0; i--) {
            for (int j = 0; j < KOLUMNY; j++) {
                JButton Przycisk = new JButton(" ");
                Przycisk.setFocusPainted(false);
                Przycisk.setName("Button" + (char) ('A' + j) + "" + (char) ('0' + i)); // Nadanie nazwy polom (A1-G6)
                Przycisk.setBackground(Color.GRAY);
                Przycisk.setFont(new Font("Courier", Font.PLAIN, 16));
                Przycisk.addActionListener(e -> { // Lambda która wpisuje do gry OX oraz sprawdza czy wygrana
                    if (czySkonczona) {
                        return;
                    }
                    JButton zrodloKlika = (JButton) e.getSource();
                    JButton emptyCell = znajdzPustyWKolumnie(zrodloKlika);

                    if (emptyCell != null) {
                        emptyCell.setText(obecnyGracz);
                        ktoWygra();
                        obecnyGracz = obecnyGracz.equals("X") ? "O" : "X";
                    }
                });
                Przyciski[i - 1][j] = Przycisk;
                panelGry.add(Przycisk);
            }
        }

        add(panelGry, BorderLayout.CENTER);

        // Panel do resetowania gry
        JPanel panelDoResetowania = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(e -> resetPrzyciskow());
        panelDoResetowania.setLayout(new BorderLayout());
        panelDoResetowania.add(resetButton, BorderLayout.EAST);
        add(panelDoResetowania, BorderLayout.SOUTH);
    }
    public void ktoWygra() { // sprawdza czy wygrana
        if (sprawdzRzad()) {
            czySkonczona = true;
            return;
        }

        if (sprawdzKolumne()) {
            czySkonczona = true;
            return;
        }
        if (sprawdzSkosy()) {
            czySkonczona = true;
        }
    }
    // Sprawdza każdą kolumne w poszukiwaniu 4 O/X z rzędu
    public boolean sprawdzKolumne() {
        for (int i = 0; i < RZEDY - 3; i++) {
            for (int j = 0; j < KOLUMNY; j++) {
                if (obecnyGracz.equals(Przyciski[i][j].getText()) && obecnyGracz.equals(Przyciski[i + 1][j].getText()) &&
                        obecnyGracz.equals(Przyciski[i + 2][j].getText()) && obecnyGracz.equals(Przyciski[i + 3][j].getText())) {
                    Przyciski[i][j].setBackground(Color.CYAN);
                    Przyciski[i + 1][j].setBackground(Color.CYAN);
                    Przyciski[i + 2][j].setBackground(Color.CYAN);
                    Przyciski[i + 3][j].setBackground(Color.CYAN);
                    return true;
                }
            }
        }
        return false;
    }
    // Sprawdza każdy rząd w poszukiwaniu 4   O/X z rzędu
    public boolean sprawdzRzad() {
        for (int i = 0; i < RZEDY; i++) {
            for (int j = 0; j < KOLUMNY - 3; j++) {
                if (obecnyGracz.equals(Przyciski[i][j].getText()) && obecnyGracz.equals(Przyciski[i][j + 1].getText()) &&
                        obecnyGracz.equals(Przyciski[i][j + 2].getText()) && obecnyGracz.equals(Przyciski[i][j + 3].getText())) {
                    Przyciski[i][j].setBackground(Color.CYAN);
                    Przyciski[i][j + 1].setBackground(Color.CYAN);
                    Przyciski[i][j + 2].setBackground(Color.CYAN);
                    Przyciski[i][j + 3].setBackground(Color.CYAN);
                    return true;
                }
            }
        }
        return false;
    }
    // Sprawdza skosy w poszukiwaniu 4 O/X z rzędu
    public boolean sprawdzSkosy() {
        for (int i = 0; i < RZEDY - 3; i++) {
            for (int j = 0; j < KOLUMNY - 3; j++) {
                if (obecnyGracz.equals(Przyciski[i][j].getText()) && obecnyGracz.equals(Przyciski[i + 1][j + 1].getText()) &&
                        obecnyGracz.equals(Przyciski[i + 2][j + 2].getText()) && obecnyGracz.equals(Przyciski[i + 3][j + 3].getText())) {
                    Przyciski[i][j].setBackground(Color.CYAN);
                    Przyciski[i + 1][j + 1].setBackground(Color.CYAN);
                    Przyciski[i + 2][j + 2].setBackground(Color.CYAN);
                    Przyciski[i + 3][j + 3].setBackground(Color.CYAN);
                    return true;
                }
            }
        }
        for (int i = 0; i < RZEDY - 3; i++) {
            for (int j = KOLUMNY - 1; j >= 3; j--) {
                if (obecnyGracz.equals(Przyciski[i][j].getText()) && obecnyGracz.equals(Przyciski[i + 1][j - 1].getText()) &&
                        obecnyGracz.equals(Przyciski[i + 2][j - 2].getText()) && obecnyGracz.equals(Przyciski[i + 3][j - 3].getText())) {
                    Przyciski[i][j].setBackground(Color.CYAN);
                    Przyciski[i + 1][j - 1].setBackground(Color.CYAN);
                    Przyciski[i + 2][j - 2].setBackground(Color.CYAN);
                    Przyciski[i + 3][j - 3].setBackground(Color.CYAN);

                    return true;
                }
            }
        } return false;
    }

    public void resetPrzyciskow() {
        for (int i = 0; i < RZEDY; i++) {
            for (int j = 0; j < KOLUMNY; j++) {
                Przyciski[i][j].setText(" "); // Resetuje przyciski z OX na " "
                Przyciski[i][j].setBackground(Color.GRAY);
                czySkonczona = false;
                obecnyGracz = "X";
            }
        }
    }

    public JButton znajdzPustyWKolumnie(JButton przycisk) {
        int kolumna = 0;
        for (int i = 0; i < RZEDY; i++) {
            for (int j = 0; j < KOLUMNY; j++) {
                if (Przyciski[i][j].equals(przycisk)) {
                    kolumna = j;
                    break;
                }
            }
        }
        for (int i = 0; i < RZEDY; i++) {
            if (Przyciski[i][kolumna].getText().equals(" ")) {
                return Przyciski[i][kolumna];
            }
        }
        return null;
    }
}