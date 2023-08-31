package model;


import java.util.*;

public class Tarjan {
    private static int brueckenIndexZeitId;
    private static int artikulationIndexZeitId;
    private static int komponenteIndexZeitId;
    private static int quellKnotenKantenZaehler;
    private static StringBuffer sb;

    // BrückenSuche-----------------------------------------------------------------------------------------------------
    public static ArrayList<Kante> brueckenSuche(Graph graph) throws GraphException {
        if (graph != null) {
            ArrayList<Knoten> besuchteKnoten = new ArrayList<>();
            ArrayList<Kante> bruecken = new ArrayList<>();
            for (Knoten knotenAktuell : graph.getKnoten()) {
                if (!besuchteKnoten.contains(knotenAktuell)) {
                    dfs(knotenAktuell, null, besuchteKnoten, bruecken);
                }
            }
            return bruecken;
        } else
            throw new GraphException("Fehler bei brueckenSuche(): Graph null");
    }
    private static void dfs(Knoten knotenAktuell, Knoten ursprungKnoten, ArrayList<Knoten> besuchteKnoten, ArrayList<Kante> bruecken) throws GraphException {
        if (knotenAktuell != null && besuchteKnoten != null && bruecken != null) {
            besuchteKnoten.add(knotenAktuell);
            knotenAktuell.setZeitNummer(brueckenIndexZeitId);
            knotenAktuell.setIdNummer(brueckenIndexZeitId);
            brueckenIndexZeitId++;
            for (Kante kante : knotenAktuell.getKanten()) {
                Knoten endKnoten = kante.getEnde();
                if (endKnoten.equals(ursprungKnoten))
                    continue; // Skip the edge to the parent vertex
                if (!besuchteKnoten.contains(endKnoten)) {
                    dfs(endKnoten, knotenAktuell, besuchteKnoten, bruecken);
                    knotenAktuell.setZeitNummer(Math.min(knotenAktuell.getZeitNummer(), endKnoten.getZeitNummer()));
                    if (knotenAktuell.getIdNummer() < endKnoten.getZeitNummer())
                        bruecken.add(kante);
                } else {
                    knotenAktuell.setZeitNummer(Math.min(knotenAktuell.getZeitNummer(), endKnoten.getIdNummer()));
                }
            }
        } else
            throw new GraphException("Fehler bei dfs(): Parameter null");
    }

    // ArtikulationsSuche------------------------------------------------------------------------------------------------
    public static ArrayList<Knoten> artikulationsKnotenSuche(Graph graph) throws GraphException {
        if (graph != null) {
            ArrayList<Knoten> besuchteKnoten = new ArrayList<>();
            ArrayList<Knoten> artikulationsKnoten = new ArrayList<>();
            for (Knoten knotenAktuell: graph.getKnoten()) {
                if (!besuchteKnoten.contains(knotenAktuell)) {
                    quellKnotenKantenZaehler = 0;
                    dfsArtikulationen(knotenAktuell, knotenAktuell, null, besuchteKnoten, artikulationsKnoten);
                    if (quellKnotenKantenZaehler <= 1){
                        artikulationsKnoten.remove(knotenAktuell);
                    }
                }
            }
            return artikulationsKnoten;

        } else
            throw new GraphException("Fehler bei artikulationsKnotenSuche(): Graph null");
    }
    private static void dfsArtikulationen(Knoten quellKnoten, Knoten aktuellerKnoten, Knoten ursprungKnoten, ArrayList<Knoten> knotenBesuchtListe, ArrayList<Knoten> artikulationsPunkteListe) throws GraphException {
        if (aktuellerKnoten != null && knotenBesuchtListe != null && artikulationsPunkteListe != null) {
            if (ursprungKnoten == quellKnoten)
                quellKnotenKantenZaehler++;
            knotenBesuchtListe.add(aktuellerKnoten);
            aktuellerKnoten.setZeitNummer(artikulationIndexZeitId);
            aktuellerKnoten.setIdNummer(artikulationIndexZeitId);
            artikulationIndexZeitId++;
            for (Kante kante : aktuellerKnoten.getKanten()) {
                Knoten endKnoten = kante.getEnde();
                if (endKnoten == ursprungKnoten)
                    continue; // Skip the edge endKnoten the ursprungKnoten vertex
                if (!knotenBesuchtListe.contains(endKnoten)) {
                    dfsArtikulationen(quellKnoten, endKnoten, aktuellerKnoten, knotenBesuchtListe, artikulationsPunkteListe);
                    aktuellerKnoten.setZeitNummer(Math.min(aktuellerKnoten.getZeitNummer(), endKnoten.getZeitNummer()));
                    if (aktuellerKnoten.getIdNummer() <= endKnoten.getZeitNummer()) {
                        if (!artikulationsPunkteListe.contains(aktuellerKnoten))
                            artikulationsPunkteListe.add(aktuellerKnoten);
                    }
                } else {
                    aktuellerKnoten.setZeitNummer(Math.min(aktuellerKnoten.getZeitNummer(), endKnoten.getIdNummer()));
                }
            }
        } else
            throw new GraphException("Fehler bei dfsArtikulationen(): Parameter null");
    }

    // KomponentenSuche-------------------------------------------------------------------------------------------------
    public static String komponentenSuche(Graph g) throws GraphException {
        for (Knoten knoten : g.getKnoten()) {
            knoten.setZeitNummer(-1);
        }
        int counter = 0;
        String str = null;
        for (int i = 0; i < g.getKnoten().size(); i++) {
            if (g.getKnoten().get(i).getZeitNummer() == -1) {
                //dfsKomponentenSuche(g.getKnoten().get(i), g.getKnoten().get(i).getZeitNummer());
                str = dfsKomponentenSuche(g.getKnoten().get(i), g.getKnoten().get(i).getZeitNummer());
                counter++;
                sb.append("----------> ");
            }
        }
        return str;
    }

    private static String dfsKomponentenSuche(Knoten knoten, int low) throws GraphException {
        knoten.setZeitNummer(1);
        sb.append(knoten.getKnotenBezeichnung()).append(", ");
        for (Kante kante : knoten.getKanten()) {
            if (kante.getEnde().getZeitNummer() == -1){
                dfsKomponentenSuche(kante.getEnde(), kante.getEnde().getZeitNummer());
            }
        }
        return sb.toString();
    }

    // Stark zusammenhängende KomponentenSuche--------------------------------------------------------------------------
    public static String sZKomponentenSuche(Graph graph) throws GraphException {
        if (graph != null) {
            ArrayList<Knoten> besuchteKnotenListe = new ArrayList<>();
            for (Knoten v : graph.getKnoten()) {
                if (!besuchteKnotenListe.contains(v)) {
                    dfsSZKomponenten(v, null, besuchteKnotenListe);
                }
            }

            Map<Integer, ArrayList<Knoten>> knotenSubListe = new HashMap<>();
            int counter = 0;
            for (Knoten v : besuchteKnotenListe ) {
                if (!knotenSubListe.containsKey(v.getZeitNummer())) {
                    counter++;
                    knotenSubListe.put(v.getZeitNummer(), new ArrayList<>());
                }
                knotenSubListe.get(v.getZeitNummer()).add(v);
            }
            StringBuffer sb = new StringBuffer();
            int subListenIndex = 1;
            for (List<Knoten> sublist : knotenSubListe.values()) {
                for (Knoten v :sublist) {
                    sb.append(v.getKnotenBezeichnung()).append(", ");
                }
                sb.append("\n");
                subListenIndex++;
            }
            System.out.println();
            return sb.toString();

        } else
            throw new GraphException("Fehler bei sZKomponentenSuche(): Graph null");
    }

    private static void dfsSZKomponenten(Knoten aktuellerKnoten, Knoten ursprungsKnoten, ArrayList<Knoten> besuchteKnotenListe) throws GraphException {
        if (aktuellerKnoten != null && besuchteKnotenListe != null) {
            besuchteKnotenListe.add(aktuellerKnoten);
            aktuellerKnoten.setZeitNummer(komponenteIndexZeitId);
            aktuellerKnoten.setIdNummer(komponenteIndexZeitId);
            komponenteIndexZeitId++;
            for (Kante kante : aktuellerKnoten.getKanten()) {
                Knoten endKnoten = kante.getEnde();
                if (endKnoten.equals(ursprungsKnoten))
                    continue;
                if (!besuchteKnotenListe.contains(endKnoten)) {
                    dfsSZKomponenten(endKnoten, aktuellerKnoten, besuchteKnotenListe);
                    aktuellerKnoten.setZeitNummer(Math.min(aktuellerKnoten.getZeitNummer(), endKnoten.getZeitNummer()));
                } else {
                    aktuellerKnoten.setZeitNummer(Math.min(aktuellerKnoten.getZeitNummer(), endKnoten.getIdNummer()));
                }
            }
        } else
            throw new GraphException("dfsSZKomponenten(): Parameter null");
    }

    public void print(){
        System.out.println(this);
    }


    public static String brueckenToString(Graph g) throws GraphException {
        ArrayList<Kante> bruecke = brueckenSuche(g);
        StringBuilder stringBuilder = new StringBuilder();
        for (Kante b : bruecke) {
            stringBuilder.append(b.getStart().getKnotenBezeichnung())
                    .append(" -- ")
                    .append(b.getEnde().getKnotenBezeichnung())
                    .append(", ");
        }
        return stringBuilder.toString();
    }

    public static String artikulationenToString(Graph g) throws GraphException {
        ArrayList<Knoten> artikulation = artikulationsKnotenSuche(g);
        StringBuilder stringBuilder = new StringBuilder();
        for (Knoten k : artikulation) {
            stringBuilder.append(k.getKnotenBezeichnung()).append(", ");
        }
        return stringBuilder.toString();
    }

    public static String komponentenToString(Graph g) throws GraphException {
        sb = new StringBuffer();
        String str = null;
        str = komponentenSuche(g);
        String sub = str.substring(0, str.length() - 2);
        return str;
    }

    public static String sZKomponentenToString(Graph g) throws GraphException {
        return sZKomponentenSuche(g);
    }


}