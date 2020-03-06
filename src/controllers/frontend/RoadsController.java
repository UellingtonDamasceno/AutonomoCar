package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Car;
import model.Road;
import model.Sector;
import model.Vertex;
import model.exceptions.EdgeNotExistException;
import model.exceptions.OfflineException;
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
    private boolean initialized;

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
        this.initialized = false;
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
        if (!this.initialized) {
            int spawnX = (int) (event.getX() / this.heigth);
            int spawnY = (int) (event.getY() / this.width);

            if (FacadeBackend.getInstance().getCity().isRoad(spawnX, spawnY)) {
                Road road = FacadeBackend.getInstance().getCity().getRoad(spawnX, spawnY);
                if (!road.isCriticalArea()) {

                    Point clickedPoint = new Point(event.getX(), event.getY());

                    Sector sector = road.getNearestSector(clickedPoint);
                    Vertex<Sector> vertex = FacadeBackend.getInstance().getCity().getGraph().getVertex(sector);

                    Point spawnPoint = new Point();
                    spawnPoint.setY(sector.getPoint().getDoublePropertyY().get() - 50);
                    spawnPoint.setX(sector.getPoint().getDoublePropertyX().get() - 5);
                    Car selectedCar = FacadeBackend.getInstance().getSelectedCar();
                    try {
                        selectedCar.initialize(vertex, spawnPoint);
                        this.initialized = true;
//                    System.out.println("Ip da maquina: " + selectedCar.getId());
                    } catch (EdgeNotExistException ex) {
                        System.err.println("Erro ao inicializar o carro!");
                    } catch (SocketException ex) {
                        Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException | VertexNotExistException | OfflineException ex) {
                        Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!this.gridPane.getChildren().contains(carFrontend)) {
                        this.bindPoint(carFrontend, selectedCar.getCurrentPosition());
                        this.gridPane.getChildren().add(carFrontend);
                    }
                } else {
                    System.err.println("Não é possivel por o carro em uma zona critica!");
                }
            } else {
                System.err.println("Não é uma rua");
            }
        }
    }

    public void showNewCar(String id, Point point, SpritesCars sprite) {
        Platform.runLater(() -> {
            Color color = sprite.getColor();
            Circle circle = new Circle(point.getX(), point.getY(), 8, color);
            this.cars.put(id, circle);
            this.bindPoint(circle, point);
            this.gridPane.getChildren().add(circle);
        });
    }

    public void removeCar(String id) {
        Circle removed = this.cars.remove(id);
        Platform.runLater(() -> {
            this.gridPane.getChildren().remove(removed);
            removed.translateXProperty().unbind();
            removed.translateYProperty().unbind();
        });
    }

    public void startAll() {
//        this.cars.values().forEach((car) -> {
//            try {
//                car.start();
//            } catch (IOException | OfflineException ex) {
//                Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }

    private void bindPoint(Circle car, Point point) {
        Platform.runLater(() -> {
            car.translateXProperty().bind(point.getDoublePropertyX());
            car.translateYProperty().bind(point.getDoublePropertyY());
        });
    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeBackend.getInstance().getSelectedCar().setStop(false);
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
//                this.cars.put(ip, circle);
                break;
            }
            case "DELETE/CAR": {
                String ip = request.getString("ip");
//                Car remove = this.cars.remove(ip);

                System.out.println("Carro removido!");
            }
        }
    }

    @FXML
    private void start(ActionEvent event) {
        try {
            FacadeBackend.getInstance().getSelectedCar().start();
        } catch (IOException | OfflineException ex) {
            Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stop(ActionEvent event) {
        FacadeBackend.getInstance().getSelectedCar().setStop(false);
    }

}
