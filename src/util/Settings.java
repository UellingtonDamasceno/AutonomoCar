package util;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {
    
    public enum DefaultCity{
        HEIGHT("500"),
        WIDTH("500"),
        COLUMNS("10"),
        ROWS("10");
        
        private final String value;
        
        private DefaultCity(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }

    public enum DefaultConnection {
        DEFAULT_IP("localhost"),
        DEFAULT_PACKET_SIZE("2048"),
        DEFAULT_PORT("9999"); 
        
        
        private final String value;
        
        private DefaultConnection(String value){
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

        RUA_V("calcada-vertical", true),
        RUA_H("calcada", true),
        ESCADA("escada", false),
        PAREDE("parede", false),
        PAREDE_2("parede2", false),
        POSTE_1("poste1", false),
        POSTE_2("poste2", false);
        //ROAD("road");

        private final String path;
        private final boolean isRoad;
        private final String ORIGIN = "/res/sprites/city/";

        private SpritesCity(String path, boolean isRoad) {
            this.path = (ORIGIN + path + ".png");
            this.isRoad = isRoad;
        }

        public String get() {
            return this.path;
        }
        
        public boolean isRoad(){
            return this.isRoad;
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
