package middleware;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Car;
import model.exceptions.OfflineException;
import org.json.JSONObject;
import util.Point;
import util.Settings.SpritesCars;

/**
 *
 * @author Uellington Conceição
 */
public class Router implements Observer {

    private static Router router;

    private Router() {
    }

    public static Router getInstance() {
        return (router == null) ? router = new Router() : router;
    }

    private void process(JSONObject request) {
        String command = request.getString("command");
        switch (command) {
            case "START": {
                try {
                    FacadeBackend.getInstance().start();
                    break;
                } catch (IOException | OfflineException ex) {
                    Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "POST/CAR": {
                String typeCar = request.getString("type");
                double x = request.getDouble("positionX");
                double y = request.getDouble("positionY");
                Point origin = new Point(x, y);

                SpritesCars sprite = SpritesCars.getCarWithSprite(typeCar);
                FacadeBackend.getInstance().putNewCar(request.getString("id"), origin);

                FacadeFrontend.getInstance().putNewCar(request.getString("id"), origin, sprite);
                break;
            }
            case "DISCONNECT": {
                String id = request.getString("id");
                FacadeFrontend.getInstance().removeCar(id);
                FacadeBackend.getInstance().removeCar(id);
                break;
            }
            case "UPDATE/POSITION": {
                String id = request.getString("id");
                double x = request.getDouble("positionX");
                double y = request.getDouble("positionY");
                int velocity = request.getInt("velocity");
                Point point = new Point(x, y);

                FacadeBackend.getInstance().updatePosition(id, point, velocity);
                break;
            }default:{
                System.out.println("Comando: "+ request.getString("command") + "não existe");
            }
        }
    }

    @Override
    public void update(Observable o, Object o1) {
//        System.out.println("Processando a solicitação");
        if (o1 instanceof JSONObject) {
            this.process((JSONObject) o1);
        } else {
            System.out.println("Não é um Json");
        }
    }
}
