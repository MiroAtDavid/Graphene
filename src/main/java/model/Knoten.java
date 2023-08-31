package model;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Knoten {

    private String knotenBezeichnung;
    private int idNummer;
    private int zeitNummer;
    private int entfernungNummer;
    private Circle circle;
    private final ArrayList<Kante> kanten;


    public Knoten(String knotenBezeichnung) {
        setKnotenBezeichnung(knotenBezeichnung);
        kanten = new ArrayList<>();
        setIdNummer(idNummer);
        setZeitNummer(zeitNummer);
        setCircle(circle);
    }

    public String getKnotenBezeichnung(){
        return knotenBezeichnung;
    }
    public ArrayList<Kante> getKanten(){
        return kanten;
    }
    public int getIdNummer() {
        return idNummer;
    }
    public int getZeitNummer() {
        return zeitNummer;
    }
    public int getEntfernungNummer(){return entfernungNummer;}
    public Circle getCircle() {
        return circle;
    }
    public void setCircle(Circle circle) {
        this.circle = circle;
    }
    public void setEntfernungNummer(int entfernungNummer){this.entfernungNummer = entfernungNummer;}
    public void setKnotenBezeichnung(String knotenBezeichnung) {
        this.knotenBezeichnung = knotenBezeichnung;
    }
    public void setIdNummer(int idNummer) {
        this.idNummer = idNummer;
    }
    public void setZeitNummer(int zeitNummer) {
        this.zeitNummer = zeitNummer;
    }
    public void kanteHinzufuegen(Knoten endKnoten, int gewicht){
        kanten.add(new Kante(this, endKnoten, gewicht));
    }
    public void kanteEntfernen(Knoten endKnoten){
        for (Kante kante : kanten) {
            if (kante.getEnde().equals(endKnoten))
                kanten.remove(kante);
        }
   }
    // toString---------------------------------------------------------------------------------------------------------
    public String toString(boolean showWeight) {
        StringBuilder message = new StringBuilder();
        if (kanten.size() == 0) {
            message.append(this.knotenBezeichnung).append(" -->");
            return message.toString();
        }
        for (int i = 0; i < kanten.size(); i++) {
            if (i == 0)
                message.append(kanten.get(i).getStart().knotenBezeichnung).append(" --> ");

            message.append(kanten.get(i).getEnde().knotenBezeichnung);
            if (showWeight)
                message.append(" (").append(kanten.get(i).getKnotenGewicht()).append(")");
            if (i != kanten.size() - 1)
                message.append(", ");
        }
        return message.toString();
    }
    public void print(){
        System.out.println(toString(true));
    }
}
