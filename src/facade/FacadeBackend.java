package facade;

import controllers.backend.CarController;
import controllers.backend.CityController;
import controllers.backend.FileController;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.Car;
import model.City;
import model.Road;
import model.exceptions.FileNameIsEmptyException;
import model.exceptions.OfflineException;
import util.Point;
import util.Settings.DefaultFile;
import util.Settings.SpritesCars;

/**
 *
 * @author uellington
 */
public class FacadeBackend {

    private static FacadeBackend facade;

    private CarController carController;
    private CityController cityController;
    private final FileController fileCoontroller;

    private FacadeBackend() {
        this.carController = new CarController();
        this.cityController = new CityController();
        this.fileCoontroller = new FileController();
    }

    public synchronized static FacadeBackend getInstance() {
        return (facade == null) ? facade = new FacadeBackend() : facade;
    }

    public void initialize() {
        this.cityController.createCity("Xina", 15, 15, 10, 10, 0);
    }

    public void createCity(String name, int height, int width, int x, int y, int prop) {
        this.cityController.createCity(name, x, y, height, width, prop);
    }

    public void loadCity(String cityName) throws IOException, FileNotFoundException, ClassNotFoundException {
        String cityPath = this.cityController.getLocationToSave(cityName);
        City city = (City) this.fileCoontroller.readObject(cityPath);
        this.cityController.setCity(city);
        city.loadPoints();
    }

    public Car createCar(SpritesCars selectedCar) {
        City city = this.cityController.getCity();
        Car car = this.carController.createCar(selectedCar);
        car.setMap(city.getGraph());
        return car;
    }

    public void setMainCar(Car car) {
        this.carController.setMainCar(car);
    }

    public Point putNewCar(String id, Point point) {
        return this.carController.putNewCar(id, point);
    }

    public Car getSelectedCar() {
        return this.carController.getMainCar();
    }

    public City getCity() {
        return this.cityController.getCity();
    }

    public int getCityDimensionX() {
        return this.cityController.getDx();
    }

    public int getCityDimensionY() {
        return this.cityController.getDy();
    }

    public double getCityHeight() {
        return this.cityController.getHeight();
    }

    public double getCityWidth() {
        return this.cityController.getWidth();
    }

    public String[] getAllCities() throws FileNameIsEmptyException {
        return this.fileCoontroller.getAllFilesInDirectory(DefaultFile.CITIES_DIRECTORY.get(), DefaultFile.DEFAULT_EXTENSION.get());
    }

    public Road putRoad(String spriteRoad, int x, int y) {
        return this.cityController.putRoad(spriteRoad, x, y);
    }

    public void updateSprite(String sprite, int x, int y) {
        this.cityController.updateSprite(sprite, x, y);
    }

    public void removeRoad(int x, int y) {
        this.cityController.removeRoad(x, y);
    }

    public void save() throws IOException {
        City city = this.cityController.getCity();
        String directory = this.cityController.getLocationToSave();
        this.fileCoontroller.writerObject(directory, city);
    }

    public double getPropotionX() {
        return this.cityController.getCity().getPropotionX();
    }

    public double getPropotionY() {
        return this.cityController.getCity().getPropotionY();
    }

    public void start() throws IOException, OfflineException {
        this.carController.getMainCar().start();
    }

    public void removeCar(String id) {
        this.carController.removeCar(id);
    }

    public void updatePosition(String id, Point point, int velocity) {
        this.carController.updatePosition(id, point, velocity);
    }

}
