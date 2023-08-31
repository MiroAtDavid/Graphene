//package model;
//
//import java.util.Scanner;
//
//public class TestGraph {
//
//    public static void main(String[] args) {
//        try {
////            testGraphTwo();
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Option wählen:");
//            System.out.println("1. Test Graph eins");
//            System.out.println("2. Test Graph zwei");
//            System.out.println("3. Test Weighted Graph drei");
//            System.out.println("4. Graph aus CSV Datei einlesen:");
//            System.out.print("Bitte Wahl eingeben (1 or 2 or 3 or 4): ");
//            System.out.println();
//            int wahl = scanner.nextInt();
//            if (wahl == 1) {
//                testGraphEins();
//            } else if (wahl == 2) {
//                testGraphZwei();
//            } else if (wahl == 3) {
//                testGraphDrei();
//            } else if (wahl == 4) {
//                testGraphGeladen();
//            } else {
//                System.out.println("Ungültige Wahl, Programm wird beendet.");
//                scanner.close();
//                return;
//            }
//            scanner.close();
//        } catch (GraphException e){
//            System.out.println("Exception in main() " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//    public static void testGraphGeladen() throws GraphException {
//        try {
//            Graph testGraph = Graph.graphCSV("csvMatrix.csv");
//            Knoten starKnoten = testGraph.getKnoten().get(0);
//            System.out.println(Dijkstra.calculateEccentricity(testGraph, starKnoten));
//
//        } catch (GraphException e){
//            System.out.println("Exception: " + e.getMessage());
//        }
//    }
//    public static void testGraphEins() throws GraphException {
//
//        Graph testGraph = new Graph(true);
//
//        Knoten a = testGraph.knotenHinzufuegen("a");
//        Knoten b = testGraph.knotenHinzufuegen("b");
//        Knoten c = testGraph.knotenHinzufuegen("c");
//        Knoten d = testGraph.knotenHinzufuegen("d");
//        Knoten e = testGraph.knotenHinzufuegen("e");
//        Knoten f = testGraph.knotenHinzufuegen("f");
//        Knoten g = testGraph.knotenHinzufuegen("g");
//        Knoten h = testGraph.knotenHinzufuegen("h");
//        Knoten i = testGraph.knotenHinzufuegen("i");
//        Knoten j = testGraph.knotenHinzufuegen("j");
//        Knoten k = testGraph.knotenHinzufuegen("k");
//        Knoten l = testGraph.knotenHinzufuegen("l");
//
//        testGraph.kanteHinzufuegen(a, b, 1);
//        testGraph.kanteHinzufuegen(a, c, 1);
//        testGraph.kanteHinzufuegen(b, c, 1);
//        testGraph.kanteHinzufuegen(c, d, 1);
//        testGraph.kanteHinzufuegen(d, e, 1);
//        testGraph.kanteHinzufuegen(d, f, 1);
//        testGraph.kanteHinzufuegen(e, f, 1);
//        testGraph.kanteHinzufuegen(f, g, 1);
//        testGraph.kanteHinzufuegen(c, h, 1);
//        testGraph.kanteHinzufuegen(h, i, 1);
//        testGraph.kanteHinzufuegen(h, j, 1);
//        testGraph.kanteHinzufuegen(i, j, 1);
//        testGraph.kanteHinzufuegen(j, k, 1);
//        testGraph.kanteHinzufuegen(j, l, 1);
//
//        Knoten starKnoten = testGraph.getKnoten().get(0);
//        System.out.println(Dijkstra.calculateEccentricity(testGraph, starKnoten));
//        Tarjan.brueckenSuche(testGraph);
//        Tarjan.artikulationsKnotenSuche(testGraph);
//        Tarjan.komponentenSuche(testGraph);
//        Tarjan.sZKomponentenSuche(testGraph);
//
//    }
//    public static void testGraphZwei() throws GraphException {
//        Graph testGraph = new Graph(true);
//
//        Knoten a1 = testGraph.knotenHinzufuegen("1");
//        Knoten b2 = testGraph.knotenHinzufuegen("2");
//        Knoten c3 = testGraph.knotenHinzufuegen("3");
//        Knoten d4 = testGraph.knotenHinzufuegen("4");
//        Knoten e5 = testGraph.knotenHinzufuegen("5");
//        Knoten f6 = testGraph.knotenHinzufuegen("6");
//        Knoten g7 = testGraph.knotenHinzufuegen("7");
//        Knoten h8 = testGraph.knotenHinzufuegen("8");
//        Knoten i9 = testGraph.knotenHinzufuegen("9");
//        Knoten j10 = testGraph.knotenHinzufuegen("10");
//
//
//        //Add Edges
//        testGraph.kanteHinzufuegen(a1,b2,1);
//        testGraph.kanteHinzufuegen(b2,c3,1);
//        testGraph.kanteHinzufuegen(b2,i9,1);
//        testGraph.kanteHinzufuegen(b2,e5,1);
//        testGraph.kanteHinzufuegen(c3,f6,1);
//        testGraph.kanteHinzufuegen(f6,i9,1);
//        testGraph.kanteHinzufuegen(e5,d4,1);
//        testGraph.kanteHinzufuegen(d4,h8,1);
//        testGraph.kanteHinzufuegen(h8,g7,1);
//        testGraph.kanteHinzufuegen(g7,e5,1);
//        testGraph.kanteHinzufuegen(g7,j10,1);
//
//        Knoten starKnoten = testGraph.getKnoten().get(0);
//        System.out.println(Dijkstra.calculateEccentricity(testGraph, starKnoten));
//
//    }
//    public static void testGraphDrei() throws GraphException {
//        Graph testGraph = new Graph(true);
//
//        Knoten a = testGraph.knotenHinzufuegen("a");
//        Knoten b = testGraph.knotenHinzufuegen("b");
//        Knoten c = testGraph.knotenHinzufuegen("c");
//        Knoten d = testGraph.knotenHinzufuegen("d");
//        Knoten e = testGraph.knotenHinzufuegen("e");
//        Knoten f = testGraph.knotenHinzufuegen("f");
//
//        testGraph.kanteHinzufuegen(a, b, 3);
//        testGraph.kanteHinzufuegen(a, c, 6);
//        testGraph.kanteHinzufuegen(b, c, 8);
//        testGraph.kanteHinzufuegen(c, d, 1);
//        testGraph.kanteHinzufuegen(d, e, 2);
//        testGraph.kanteHinzufuegen(d, f, 11);
//        testGraph.kanteHinzufuegen(e, f, 20);
//
//        Knoten starKnoten = testGraph.getKnoten().get(0);
//        System.out.println(Dijkstra.calculateEccentricity(testGraph, starKnoten));
//
//
//    }
//}{
//        Graph testGraph = new Graph(true);
//
//        Knoten a1 = testGraph.knotenHinzufuegen("1");
//        Knoten b2 = testGraph.knotenHinzufuegen("2");
//        Knoten c3 = testGraph.knotenHinzufuegen("3");
//        Knoten d4 = testGraph.knotenHinzufuegen("4");
//        Knoten e5 = testGraph.knotenHinzufuegen("5");
//        Knoten f6 = testGraph.knotenHinzufuegen("6");
//        Knoten g7 = testGraph.knotenHinzufuegen("7");
//        Knoten h8 = testGraph.knotenHinzufuegen("8");
//        Knoten i9 = testGraph.knotenHinzufuegen("9");
//        Knoten j10 = testGraph.knotenHinzufuegen("10");
//
//
//        //Add Edges
//        testGraph.kanteHinzufuegen(a1,b2,1);
//        testGraph.kanteHinzufuegen(b2,c3,1);
//        testGraph.kanteHinzufuegen(b2,i9,1);
//        testGraph.kanteHinzufuegen(b2,e5,1);
//        testGraph.kanteHinzufuegen(c3,f6,1);
//        testGraph.kanteHinzufuegen(f6,i9,1);
//        testGraph.kanteHinzufuegen(e5,d4,1);
//        testGraph.kanteHinzufuegen(d4,h8,1);
//        testGraph.kanteHinzufuegen(h8,g7,1);
//        testGraph.kanteHinzufuegen(g7,e5,1);
//        testGraph.kanteHinzufuegen(g7,j10,1);
//
//        Knoten starKnoten = testGraph.getKnoten().get(0);
//        System.out.println(Dijkstra.calculateEccentricity(testGraph, starKnoten));
//
//    }
