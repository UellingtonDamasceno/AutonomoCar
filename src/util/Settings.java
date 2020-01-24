package util;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {

    public enum Connection {
        DEFAULT_IP("localhost"),
        DEFAULT_PACKET_SIZE("2048"),
        DEFAULT_PORT("9999"); 
        
        
        private final String value;
        
        private Connection(String value){
            this.value = value;
        }
        
        public int getIntegerValue(){
            return Integer.valueOf(this.value);
        }
        
        public String getStringValue(){
            return this.value;
        }
    }
    
    public enum SpritesCars {
        DODGE("black"),
        SKYLINE("white"),
        FERRARI("red"),
        JAGUAR("blue"),
        MERCEDES("grey"),
        CAMARO("yellow");

        private final String tiny;
        private final String real;
        private final String ORIGIN = "/res/sprites/cars/";

        private SpritesCars(String color) {
            this.tiny = (ORIGIN + "small-" + color + ".png");
            this.real = (ORIGIN + color + ".png");
        }

        public String getSmall() {
            return this.tiny;
        }

        public String getBig() {
            return this.real;
        }
    }

    public enum SpritesCity {

        CALCADA("calcada"),
        FIM_CALCADA("calcada-vertical"),
        ESCADA("escada"),
        PAREDE("parede"),
        PAREDE_2("parede2"),
        POSTE_1("poste1"),
        POSTE_2("poste2");
        //ROAD("road");

        private final String path;
        private final String ORIGIN = "/res/sprites/city/";

        private SpritesCity(String path) {
            this.path = (ORIGIN + path + ".png");
        }

        public String get() {
            return this.path;
        }
    }

    public enum CityLocation {
        CHINA("china");

        private final String name;

        private CityLocation(String name) {
            this.name = name;
        }
    }

    public enum RoadsTypes {
        
        H(2),
        L(2),
        T(3),
        V(2),
        X(4);
        
        public int maxConnections;

        private RoadsTypes(int maxConnections) {
            this.maxConnections = maxConnections;
        }
        
        public int getMaxConnections(){
            return this.maxConnections;
        }
    }
    
    public enum ActionBuilder{
        CLICK,
        ROTATE_LEFT,
        ROTATE_RIGHT;
    }

    public enum Scenes {
        MAIN("Main.fxml", "Menu", false),
        SELECT_CAR("SelectCar.fxml", "Escolha seu carro", false),
        BUILDER("Builder.fxml", "Construtor de cidades", false),
        BUILDER_SETTINGS("BuilderSettings.fxml", "Configurações", false),
        ROADS("Roads.fxml", "Cidade", false);
        
        private final String name;
        private final String title;
        private final boolean cache;

        private Scenes(String value, String title, boolean cache) {
            this.name = "/view/" + value;
            this.title = title;
            this.cache = cache;
        }

        public String getTitle() {
            return this.title;
        }

        public boolean isCache() {
            return this.cache;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
