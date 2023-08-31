package model;

import javafx.scene.shape.Line;

public class Kante {

    private Knoten startKnoten;
    private Knoten endKnoten;
    private int knotenGewicht;
    private Line line;


    public Kante(Knoten startKnoten, Knoten endKnoten, int gewicht) {
        this.startKnoten = startKnoten;
        this.endKnoten = endKnoten;
        this.knotenGewicht = gewicht;
        setLine(line);
    }

    public Line getLine() {
        return line;
    }
    public Knoten getStart() {
        return startKnoten;
    }
    public Knoten getEnde() {
        return endKnoten;
    }
    public int getKnotenGewicht() {
        return knotenGewicht;
    }
    public void setLine(Line line) {
        this.line = line;
    }
    public void setStartKnoten(Knoten startKnoten) {
        this.startKnoten = startKnoten;
    }
    public void setEndKnoten(Knoten endKnoten) {
        this.endKnoten = endKnoten;
    }
    public void setKnotenGewicht(int knotenGewicht) {
        this.knotenGewicht = knotenGewicht;
    }
}
