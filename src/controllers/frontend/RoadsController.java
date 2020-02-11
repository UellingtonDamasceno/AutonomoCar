package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
import org.json.JSONObject;
import util.Point;
import util.Settings.Scenes;
import util.Settings.SpritesCars;

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

    private Map<String, Circle> cars;

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

        this.cars = new HashMap<>();
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
            Road road = FacadeBackend.getInstance().getCity().getRoad(spawnX, spawnY);
            if (!road.isCriticalArea()) {
                Car selectedCar = FacadeBackend.getInstance().getSelectedCar();

                Point spawnPoint = this.getStartPoint(event, road);

                Vertex vertex = FacadeBackend.getInstance().getCity().getGraph().getVertex(road);

                selectedCar.setOriginVertex(vertex);
                selectedCar.setOriginPoint(spawnPoint);

                selectedCar.initialize();
                if (this.gridPane.getChildren().contains(carFrontend)) {
                    this.bindPoint(selectedCar.getCurrentPosition());
                } else {
                    this.bindPoint(selectedCar.getCurrentPosition());
                    this.gridPane.getChildren().add(carFrontend);
                }
            } else {
                System.err.println("Não é possivel por o carro em uma zona critica!");
            }
        } else {
            System.err.println("Não é uma rua");
        }

    }

    private Point getStartPoint(MouseEvent event, Road road) {
        Point clickedPoint = new Point(event.getX(), event.getY());
        Point spawnPoint = new Point();

        double distanceMax = Double.MAX_VALUE;
        double calculateDistance;

        for (Point sectorRoad : road.getSectors()) {
            calculateDistance = Point.distance(clickedPoint, sectorRoad);
            if (calculateDistance < distanceMax) {
                spawnPoint = new Point(sectorRoad.getX().get(), sectorRoad.getY().get() - 50);
                distanceMax = calculateDistance;
            }
        }
        return spawnPoint;
    }

    private void bindPoint(Point position) {
        Platform.runLater(() -> {
            carFrontend.centerXProperty().bind(position.getX());
            carFrontend.centerYProperty().bind(position.getY());
            carFrontend.translateXProperty().bind(position.getX());
            carFrontend.translateYProperty().bind(position.getY());
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
        JSONObject request = (JSONObject) arg;
        switch (request.getString("request")) {
            case "PUT/CAR": {
                String ip = request.getString("ip");
                String color = request.getString("color");
                double x = request.getDouble("x");
                double y = request.getDouble("y");

                Circle circle = new Circle(x, y, 5, SpritesCars.getColor(color));
//                this.bindPoint(position);;
                this.cars.put(ip, circle);
                break;
            }
            case "DELETE/CAR": {
                String ip = request.getString("ip");
                Circle remove = this.cars.remove(ip);
                
                System.out.println("Carro removido!");
            }
        }
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
