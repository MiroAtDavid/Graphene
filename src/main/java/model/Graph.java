package model;

import java.io.*;
import java.util.*;

public class Graph {

    private final ArrayList<Knoten> knoten;
    private boolean gewichtet;
    private boolean gerichtet;


    // Konstruktor -----------------------------------------------------------------------------------------------------
    public Graph(boolean gewichtet){
        knoten = new ArrayList<>();
        setGewichtet(gewichtet);
        setGerichtet(gerichtet);
    }

    // Getter ---- -----------------------------------------------------------------------------------------------------
    public ArrayList<Knoten> getKnoten(){
        return knoten;
    }
    public boolean isGewichtet(){
        return gewichtet;
    }
    public boolean isGerichtet() {
        return gerichtet;
    }
    public Knoten getKnotenBezeichnung(String knotenBezeichnung){
        for (Knoten k : knoten) {
            if(k.getKnotenBezeichnung().equals(knotenBezeichnung))
                return k;
        }
        return null;
    }

    // Setter ---- -----------------------------------------------------------------------------------------------------
    public void setGewichtet(boolean gewichtet){
        this.gewichtet = gewichtet;
    }
    public void setGerichtet(boolean gerichtet) {
        this.gerichtet = gerichtet;
    }
    // Andere Methoden -------------------------------------------------------------------------------------------------
    public Knoten knotenHinzufuegen(String knotenBezeichnung) {
        Knoten knotenNeu = new Knoten(knotenBezeichnung);
        knoten.add(knotenNeu);
        return knotenNeu;
    }
    public void kanteHinzufuegen(Knoten startKnoten, Knoten endKnoten, int gewicht) {
        if (!gewichtet)
            gewicht = 1;
        startKnoten.kanteHinzufuegen(endKnoten, gewicht);
        if (!gerichtet)
            endKnoten.kanteHinzufuegen(startKnoten,gewicht);
    }
    public void kanteEntfernen(Knoten startKnoten, Knoten endKnoten) {
        startKnoten.kanteEntfernen(endKnoten);
        if (!gerichtet)
            endKnoten.kanteEntfernen(startKnoten);
    }
    public void knotenEntfernen(Knoten knotenZuEntfernen) {
        knoten.remove(knotenZuEntfernen);
    }

    // Graph von CSV Datei laden ---------------------------------------------------------------------------------------
    public static Graph graphCSV(String pfad) throws GraphException {
        Graph graph = new Graph(true);
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            String zeile;
            int reihe = 0;
            while ((zeile = br.readLine()) != null) {
                String[] werte = zeile.split(";");
                int laenge = werte.length;
                if (reihe == 0) {
                    // Create vertices based on the first row of the CSV
                    for (int i = 0; i < laenge; i++) {
                        graph.knoten.add(new Knoten(String.valueOf(i + 1)));
                    }
                }
                Knoten startKnoten = graph.knoten.get(reihe);
                for (int spalte = 0; spalte < werte.length; spalte++) {
                    String wert = werte[spalte].trim();
                    if (wert.equals("1") && spalte <= reihe) { // Assuming '1' represents an edge in the adjacency matrix
                        Knoten endKnoten = graph.knoten.get(spalte);
                        graph.kanteHinzufuegen(startKnoten, endKnoten, 1);
                    }
                }
                reihe++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
    public static Graph graphCSVauto() {
        Graph graph = new Graph(true);
        try (BufferedReader br = new BufferedReader(new FileReader("/home/hu/data.csv"))) {
            String zeile;
            int reihe = 0;
            while ((zeile = br.readLine()) != null) {
                String[] werte = zeile.split(";");
                int laenge = werte.length;
                if (reihe == 0) {
                    // Create vertices based on the first row of the CSV
                    for (int i = 0; i < laenge; i++) {
                        graph.knoten.add(new Knoten(String.valueOf(i + 1)));
                    }
                }
                Knoten startKnoten = graph.knoten.get(reihe);
                for (int spalte = 0; spalte < werte.length; spalte++) {
                    String wert = werte[spalte].trim();
                    if (wert.equals("1") && spalte <= reihe) { // Assuming '1' represents an edge in the adjacency matrix
                        Knoten endKnoten = graph.knoten.get(spalte);
                        graph.kanteHinzufuegen(startKnoten, endKnoten, 1);
                    }
                }
                reihe++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
    public static String graphCSVview(File file) throws GraphException {
        Graph graph = new Graph(true);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String zeile;
            int reihe = 0;
            while ((zeile = br.readLine()) != null) {
                sb.append(zeile).append("\n");
                reihe++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // String ausgabe --------------------------------------------------------------------------------------------------
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCenter (Z(G)): ");
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        sb.append(joiner).append("\n");
        return sb.toString();
    }
    public void print(){
        System.out.println(this);
    }

}
