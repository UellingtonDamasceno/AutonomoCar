package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import model.Car;
import model.Road;
import model.Vertex;
import model.exceptions.VertexNotExistException;
import util.Point;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class RoadsController implements Initializable, Observer {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblCordinates;

    private double heigth;
    private double width;

    private List cars;
    @FXML
    private BorderPane root;
    private Circle carFrontend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();

        gridPane.setPrefHeight(FacadeBackend.getInstance().getCityHeight());
        gridPane.setPrefWidth(FacadeBackend.getInstance().getCityWidth());

        this.heigth = FacadeBackend.getInstance().getPropotionY();
        this.width = FacadeBackend.getInstance().getPropotionX();

        this.addNColumns(FacadeBackend.getInstance().getCityDimensionX());
        this.addNRows(FacadeBackend.getInstance().getCityDimensionY());

        this.cars = new LinkedList();
        this.carFrontend = new Circle(8);
        this.carFrontend.setFill(FacadeBackend.getInstance().getSelectedCar().getColor());

        this.render();
    }

    @FXML
    private void updateCoordinate(MouseEvent event) {
        lblCordinates.setText("X = " + event.getX() + " :: " + "Y = " + event.getY());
    }

    private void render() {
        Road road;
        int dx = FacadeBackend.getInstance().getCityDimensionX();
        int dy = FacadeBackend.getInstance().getCityDimensionY();
        for (int x = 0; x < dx; x++) {
            for (int y = 0; y < dy; y++) {
                road = FacadeBackend.getInstance().getCity().getRoad(x, y);
                this.spawn(road.getSprite(), this.heigth, this.width, x, y);
            }
        }
    }

    private void addNColumns(int columns) {
        for (int x = 0; x < columns; x++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints());
        }
    }

    private void addNRows(int rows) {
        for (int y = 0; y < rows; y++) {
            this.gridPane.getRowConstraints().add(new RowConstraints());
        }
    }

    private void spawn(String sprite, double h, double w, int x, int y) {
        Image image = new Image(sprite);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(h);
        imageView.setFitWidth(w);
        this.gridPane.add(imageView, x, y);
    }

    @FXML
    private void pushCar(MouseEvent event) throws VertexNotExistException {

        int spawnX = (int) (event.getX() / this.heigth);
        int spawnY = (int) (event.getY() / this.width);

        if (FacadeBackend.getInstance().getCity().isRoad(spawnX, spawnY)) {
            Car selectedCar = FacadeBackend.getInstance().getSelectedCar();
            selectedCar.addObserver(this);

            Point clickedPoint = new Point(event.getX(), event.getY());

            Point spawnPoint = new Point();
            double distanceMax = Double.MAX_VALUE;
            double calculateDistance;

            Road road = FacadeBackend.getInstance().getCity().getRoad(spawnX, spawnY);
            Vertex vertex = FacadeBackend.getInstance().getCity().getGraph().getVertex(road);

            System.out.println("Clicked point: " + clickedPoint);
            for (Point sectorRoad : road.getSectors()) {
                calculateDistance = Point.distance(clickedPoint, sectorRoad);
                if (calculateDistance < distanceMax) {
                    spawnPoint = new Point(sectorRoad.getX(), sectorRoad.getY());
                    distanceMax = calculateDistance;
                }
                System.out.println("==============================");
            }

            selectedCar.setOriginPoint(spawnPoint.getX(), spawnPoint.getY());

            selectedCar.setCurrentPosition(spawnPoint.getX(), spawnPoint.getY());
            selectedCar.setCurrentVertex(vertex);

            
            if (this.gridPane.getChildren().contains(carFrontend)) {
                this.updatePositionCar(selectedCar.getCurrentPosition());
            } else {
                this.updatePositionCar(selectedCar.getCurrentPosition());
                this.gridPane.getChildren().add(carFrontend);
            }
        } else {
            System.out.println("Não é uma rua");
        }

    }

    private void updatePositionCar(Point position) {
        Platform.runLater(() -> {
            carFrontend.setCenterX(position.getX());
            carFrontend.setCenterY(position.getY());
            carFrontend.setTranslateX(position.getX());
            carFrontend.setTranslateY(position.getY());
        });

    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeBackend.getInstance().getSelectedCar().setStop(true);
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CITY);
        } catch (Exception ex) {
            Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Point point = (Point) arg;
        this.updatePositionCar(point);
    }

    @FXML
    private void start(ActionEvent event) {
        FacadeBackend.getInstance().getSelectedCar().start();
    }

    @FXML
    private void stop(ActionEvent event) {
        FacadeBackend.getInstance().getSelectedCar().setStop(true);
    }

}
