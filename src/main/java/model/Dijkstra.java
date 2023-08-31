package model;

import java.util.*;

public class Dijkstra {
    public static void dijkstra(Graph graph, Knoten startKnoten) {
        for (Knoten vertex : graph.getKnoten()) {
            if (vertex.equals(startKnoten))
                vertex.setEntfernungNummer(0);
            else
                vertex.setEntfernungNummer(Integer.MAX_VALUE);
        }
        PriorityQueue<Knoten> queue = new PriorityQueue<>(Comparator.comparingInt(Knoten::getEntfernungNummer));
        queue.addAll(graph.getKnoten());
        while (!queue.isEmpty()) {
            Knoten current = queue.poll();
            for (Kante edge : current.getKanten()) {
                Knoten neighbor = edge.getEnde();
                int distanceTroughCurrent = current.getEntfernungNummer() + edge.getKnotenGewicht();
                if (distanceTroughCurrent < neighbor.getEntfernungNummer()) {
                    queue.remove(neighbor);
                    neighbor.setEntfernungNummer(distanceTroughCurrent);
                    queue.offer(neighbor);
                }
            }
        }
    }
    public static int calculateEccentricity(Graph graph, Knoten vertex) throws GraphException {
        dijkstra(graph, vertex);
        int eccentricity = graph.getKnoten().stream().mapToInt(Knoten::getEntfernungNummer).max().orElse(0);
       // resetDistances(graph);
        return eccentricity;
    }
    private static void resetDistances(Graph graph) throws GraphException {
        for (Knoten vertex : graph.getKnoten()) {
            vertex.setEntfernungNummer(Integer.MAX_VALUE);
        }
    }
    public static int calculateRadius(Graph graph) throws GraphException {
        int radius = Integer.MAX_VALUE;
        for (Knoten vertex : graph.getKnoten()) {
            int eccentricity = calculateEccentricity(graph, vertex);
            if (eccentricity < radius) {
                radius = eccentricity;
            }
        }
        return radius;
    }
    public static int calculateDiameter(Graph graph) throws GraphException {
        int diameter = 0;
        for (Knoten vertex : graph.getKnoten()) {
            int eccentricity = calculateEccentricity(graph, vertex);
            if (eccentricity > diameter) {
                diameter = eccentricity;
            }
        }
        return diameter;
    }
    public static List<String> calculateCenter(Graph graph) throws GraphException {
        int radius = calculateRadius(graph);
        List<String> centerVertices = new ArrayList<>();
        for (Knoten vertex : graph.getKnoten()) {
            int eccentricity = calculateEccentricity(graph, vertex);
            if (eccentricity == radius) {
                centerVertices.add(vertex.getKnotenBezeichnung());
            }
        }
        return centerVertices;
    }
    public static String toStringExzentrizitaet(Graph g){
        StringBuffer sb = new StringBuffer();
        StringJoiner joiner1 = new StringJoiner(", ");
        int eccentricityCount = 0;
        int totalEccentricities = g.getKnoten().size();

        for (Knoten knoten : g.getKnoten())
        {
            try
            {
                int eccentricity = calculateEccentricity(g, knoten);
                String entry = "[" + knoten.getKnotenBezeichnung() + "]:" + "[" + eccentricity + "]";
                joiner1.add(entry);
                eccentricityCount++;

                if (eccentricityCount == totalEccentricities / 2)
                {
                    sb.append(joiner1).append("\n");
                    joiner1 = new StringJoiner(", ");
                }
            }
            catch (GraphException e)
            {
                sb.append("Error calculating eccentricity for vertex ")
                        .append(knoten.getKnotenBezeichnung()).append(": ")
                        .append(e.getMessage()).append("\n");
            }
        }
        sb.append(joiner1).append("\n");
        return sb.toString();
    }
    public static String toStringRadius(Graph graph) {
        StringBuilder sb = new StringBuilder();
        try {
            int radius = calculateRadius(graph);
            sb.append(radius);
        } catch (GraphException e) {
            sb.append("Error calculating radius, diameter, or center vertices: ").append(e.getMessage()).append("\n");
        }
        return sb.toString();
    }
    public static String toStringDurchmesser(Graph graph){
        StringBuilder sb = new StringBuilder();
        try{
            int diameter = calculateDiameter(graph);
            sb.append(diameter);
        } catch (GraphException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static String toStringZentrum(Graph graph){
        StringBuilder sb = new StringBuilder();
        try{
            List<String> diameter = calculateCenter(graph);
            for (String s : diameter) {
                sb.append(s).append(", ");
            }
        } catch (GraphException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
