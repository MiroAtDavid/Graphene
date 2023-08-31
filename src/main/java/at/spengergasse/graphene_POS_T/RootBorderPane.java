package at.spengergasse.graphene_POS_T;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class RootBorderPane extends BorderPane {

		private MenuBar menuBar;
		private Menu menuDatei, menuHilfe;
		private MenuItem menuItemLaden, menuItemBeenden, menuItemInfo;
		private static TextArea display;
		private TextArea textFieldStakeKomponenten;
		private Graph graph;
		private VBox vBoxRechts, vBoxLinks;
		private FlowPane flowPaneUnten;
		private Label labelUntenRadioButtons, labelEczentrizitaet, labelGraph, labelRadius, labelDurchmesser, labelZentrum;
		private Label labelBruecken, labelArtikualtionen, labelKomponenten, labelStarkeKomponenten;
		private GridPane gridPane;
		private TextField textAreaExentrizizaet, textFieldRadius, textFieldDurchmesser, textFieldZentrum;
		private TextField textFieldBruecken, textFieldArtikulationen, textFieldKomponenten;
		private Button buttonReload;
		private Color color;
		private Circle circle, circle1, circle2, circle3;
		private Random rand;
		private Group gruppe;
		private Text text;


	public RootBorderPane() {
			initComponents();
			addComponents();
			addHandlers();
		}
	private void initComponents() {

		Graph testgraph = new Graph(true);
		menuBar = new MenuBar();
		menuDatei = new Menu("Datei");
		menuHilfe = new Menu("Hilfe");
		menuItemInfo = new MenuItem("Info");
		menuItemLaden = new MenuItem("Laden");
		menuItemBeenden = new MenuItem("Beenden");


		gruppe = new Group();

		vBoxRechts = new VBox();
		vBoxRechts.setPrefWidth(600);
		vBoxRechts.setAlignment(Pos.CENTER);
		vBoxRechts.setPadding(new Insets(50,50,0,50));
		gridPane = new GridPane();
		labelGraph = new Label("Graph: ");
		labelGraph.setPadding(new Insets(10));
		display = new TextArea();
		display.maxWidth(200);
		display.setPadding(new Insets(10));
		labelEczentrizitaet = new Label("Exzentrizitäten: ");
		labelEczentrizitaet.setPadding(new Insets(10));
		textAreaExentrizizaet = new TextField();
		labelRadius = new Label("Radius: ");
		labelRadius.setPadding(new Insets(10));
		textFieldRadius = new TextField();
		labelDurchmesser = new Label("Durchmesser: ");
		labelDurchmesser.setPadding(new Insets(10));
		textFieldDurchmesser = new TextField();
		labelZentrum = new Label("Zentrum: ");
		labelZentrum.setPadding(new Insets(10));
		textFieldZentrum = new TextField();
		labelBruecken = new Label("Brücken: ");
		labelBruecken.setPadding(new Insets(10));
		textFieldBruecken = new TextField();
		labelArtikualtionen = new Label("Artikulationen: ");
		labelArtikualtionen.setPadding(new Insets(10));
		textFieldArtikulationen = new TextField();
		labelKomponenten = new Label("Komponenten");
		labelKomponenten.setPadding(new Insets(10));
		textFieldKomponenten = new TextField();
		labelStarkeKomponenten = new Label("SZ Komponenten: ");
		labelStarkeKomponenten.setPadding(new Insets(10));
		textFieldStakeKomponenten = new TextArea();

		flowPaneUnten = new FlowPane();
		flowPaneUnten.setHgap(5);
		flowPaneUnten.setAlignment(Pos.CENTER);
		flowPaneUnten.setPadding(new Insets(10));
		buttonReload = new Button("Reload");
		buttonReload.setPadding(new Insets(5));
		buttonReload.setPrefWidth(150);


		setTop(menuBar);
		setCenter(gridPane);
		setRight(vBoxRechts);
		setBottom(flowPaneUnten);

	}
	private void addComponents() {
		menuBar.getMenus().setAll(menuDatei, menuHilfe);
		menuDatei.getItems().addAll(menuItemLaden, new SeparatorMenuItem(), menuItemBeenden);
		menuHilfe.getItems().addAll(menuItemInfo);
		flowPaneUnten.getChildren().addAll(buttonReload);
		//vBoxRechts.getChildren().add();


		gridPane.add(labelGraph, 0,0);
		gridPane.add(display, 1, 0);
		gridPane.add(labelEczentrizitaet, 0,1);
		gridPane.add(textAreaExentrizizaet, 1,1);
		gridPane.add(labelRadius, 0, 2);
		gridPane.add(textFieldRadius, 1,2);
		gridPane.add(labelDurchmesser, 0, 3);
		gridPane.add(textFieldDurchmesser, 1,3);
		gridPane.add(labelZentrum, 0, 4);
		gridPane.add(textFieldZentrum, 1,4);
		gridPane.add(labelBruecken, 0, 5);
		gridPane.add(textFieldBruecken, 1,5);
		gridPane.add(labelArtikualtionen, 0, 6);
		gridPane.add(textFieldArtikulationen, 1,6);
		gridPane.add(labelKomponenten, 0, 7);
		gridPane.add(textFieldKomponenten, 1,7);
		gridPane.add(labelStarkeKomponenten, 0, 8);
		gridPane.add(textFieldStakeKomponenten, 1, 8);
		vBoxRechts.getChildren().add(gruppe);


	}
	private void addHandlers() {
		menuItemLaden.setOnAction(event -> laden());
		menuItemBeenden.setOnAction(event -> beenden());
		menuItemInfo.setOnAction(event -> info());
		buttonReload.setOnAction(event -> reload());

	}

	//------------------------------------------------ Handler-Methoden -----------------------------------------
	public void laden() {
		FileChooser fileChooser = new FileChooser();
		File initDir = new File("/home/hu/IdeaProjects/Graphene_POS_T/src/main/java/test/");
		if(initDir.exists())
			fileChooser.setInitialDirectory(initDir);
		File selected = fileChooser.showOpenDialog(null);
		if (selected != null) {
			try {
				Graph.graphCSVview(selected);
				display.setText(Graph.graphCSVview(selected));
				Graph g = Graph.graphCSV(String.valueOf(selected));
				String exzentrizitaet = Dijkstra.toStringExzentrizitaet(g);
				textAreaExentrizizaet.setText(exzentrizitaet);
				String druchmesser = Dijkstra.toStringDurchmesser(g);
				textFieldDurchmesser.setText(druchmesser);
				String radius = Dijkstra.toStringRadius(g);
				textFieldRadius.setText(radius);
				String zentrum = Dijkstra.toStringZentrum(g);
				textFieldZentrum.setText(zentrum);
				String bruecken = Tarjan.brueckenToString(g);
				textFieldBruecken.setText(bruecken);
				String artikulation = Tarjan.artikulationenToString(g);
				textFieldArtikulationen.setText(artikulation);
				String komponenten = Tarjan.komponentenToString(g);
				textFieldKomponenten.setText(komponenten);
				if (komponenten.contains("-----")){
					textFieldRadius.setText("∞");
					textAreaExentrizizaet.setText("∞");
					textFieldDurchmesser.setText("∞");
					textFieldZentrum.setText("∞");
				}
				String starkeKomponenten = Tarjan.sZKomponentenToString(g);
				textFieldStakeKomponenten.setText(starkeKomponenten);
				draw(g);
				reloadCircles(g);
			} catch (GraphException e) {
				Main.showAlert(Alert.AlertType.ERROR, e.getMessage());
			}
		}
		else
			Main.showAlert(Alert.AlertType.INFORMATION, "Keine Datei zum Laden ausgewaehlt");
		}
	public void autoLaden() {
		try {
			Graph g = Graph.graphCSVauto();
			String exzentrizitaet = Dijkstra.toStringExzentrizitaet(g);
			textAreaExentrizizaet.setText(exzentrizitaet);
			String druchmesser = Dijkstra.toStringDurchmesser(g);
			textFieldDurchmesser.setText(druchmesser);
			String radius = Dijkstra.toStringRadius(g);
			textFieldRadius.setText(radius);
			String zentrum = Dijkstra.toStringZentrum(g);
			textFieldZentrum.setText(zentrum);
			String bruecken = Tarjan.brueckenToString(g);
			textFieldBruecken.setText(bruecken);
			String artikulation = Tarjan.artikulationenToString(g);
			textFieldArtikulationen.setText(artikulation);
			String komponenten = Tarjan.komponentenToString(g);
			textFieldKomponenten.setText(komponenten);
			if (komponenten.contains("-----")) {
				textFieldRadius.setText("∞");
				textAreaExentrizizaet.setText("∞");
				textFieldDurchmesser.setText("∞");
				textFieldZentrum.setText("∞");
			}
			String starkeKomponenten = Tarjan.sZKomponentenToString(g);
			textFieldStakeKomponenten.setText(starkeKomponenten);
			draw(g);
			reloadCircles(g);
		} catch (GraphException e) {
			throw new RuntimeException(e);
		}
	}
	public static void saveAsCSV() {
		String text = display.getText();
		String fileName = "data.csv";
		String filePath = "/home/hu/" + fileName;
		File file = new File(filePath);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loeschen(){
			textAreaExentrizizaet.clear();
			textFieldDurchmesser.clear();
			textFieldRadius.clear();
			textFieldZentrum.clear();
			textFieldBruecken.clear();
			textFieldArtikulationen.clear();
		}public void reload(){
			getDisplay();
			loeschen();
			autoLaden();
		}
	public static void getDisplay() {
		saveAsCSV();
		Graph.graphCSVauto();
	}
	public void beenden(){
		Platform.exit();
	}
	public void info(){
		Main.showAlert(Alert.AlertType.INFORMATION, "Graphene v0.1");
	}
	private void draw(Graph graph) throws GraphException {

		gruppe.minHeight( 580);
		gruppe.maxWidth(740);
		ArrayList<Knoten> artikulation = Tarjan.artikulationsKnotenSuche(graph);
		int position = 40;
		int nextPosition = 580 / graph.getKnoten().size();

		for (Knoten knoten : graph.getKnoten()){
			float x = (float)(Math.random() * 460);
			float y = (float) (position += nextPosition);
			float r = (float) (15);
			Circle circle;
			if ( artikulation.contains(knoten)) {
				circle = new Circle(x, y, r, Color.CYAN);
			} else {
				circle = new Circle(x, y, r, Color.SILVER);
			}
			circle.setAccessibleText(knoten.getKnotenBezeichnung() + "");
			knoten.setCircle(circle);
			text = new Text(knoten.getKnotenBezeichnung() + "");
			text.setFill(Color.GREY);
			text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			text.setX(circle.getCenterX() - 4);
			text.setY(circle.getCenterY() + 4);
			gruppe.getChildren().add(circle);
			gruppe.getChildren().add(text);
		}

		ArrayList<Kante> bruecke = Tarjan.brueckenSuche(graph);

		for (Knoten k : graph.getKnoten()) {
			Line linie = null;
			for (Kante kante : k.getKanten()) {
				Circle circleEnd = kante.getEnde().getCircle();
				Circle circleStart = kante.getStart().getCircle();
				linie = new Line();
				kante.setLine(linie);
				if (bruecke.contains(kante))
					linie.setStroke(Color.RED);
				else
					linie.setStroke(Color.GREY);
				linie.setStrokeWidth(1.5);
				linie.setStartX(circleStart.getCenterX());
				linie.setStartY(circleStart.getCenterY());
				linie.setEndX(circleEnd.getCenterX());
				linie.setEndY(circleEnd.getCenterY());
				gruppe.getChildren().add(linie);
			}
		}
	}
	private void reloadCircles(Graph g) throws GraphException {
		gruppe.getChildren().clear();
		draw(g);
	}

}
