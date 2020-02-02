package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Car;
import model.Road;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class RoadsController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblCordinates;

    private int heigth;
    private int width;

    private List cars;

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

    private void spawn(String sprite, int h, int w, int x, int y) {
        Image image = new Image(sprite);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(h);
        imageView.setFitWidth(w);
        this.gridPane.add(imageView, x, y);
    }

    @FXML
    private void pushCar(MouseEvent event) {
        Car selectedCar = FacadeBackend.getInstance().getSelectedCar();

        int spawnX = (int) (event.getX() / this.heigth);
        int spawnY = (int) (event.getY() / this.width);
        
        System.out.println("Event x: "+ event.getX());
        System.out.println("Event y: "+ event.getY());
        System.out.println("Spawn x: "+ spawnX);
        System.out.println("Spawn y :"+ spawnY);
        System.out.println("dx: " +this.heigth);
        System.out.println("dy: "+this.width);
        
        if (FacadeBackend.getInstance().getCity().isRoad(spawnX, spawnY)) {
            Road road = FacadeBackend.getInstance().getCity().getRoad(spawnX, spawnY);
            
            selectedCar.setOriginPoint(event.getX(), event.getY());

            Circle car = new Circle(5);
            car.setFill(Color.BLUE);
            this.gridPane.getChildren().add(car);
            car.setTranslateX(event.getX());
            car.setTranslateY(event.getY());
        }else{
            System.out.println("Não é uma rua");
        }

    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CITY);
        } catch (Exception ex) {
            Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
