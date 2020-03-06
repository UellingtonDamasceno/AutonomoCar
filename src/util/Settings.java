package util;

import javafx.scene.paint.Color;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {

    public enum DefaultFile {
        CITIES_DIRECTORY("res/cities/"),
        DEFAULT_EXTENSION(".bin");

        private final String value;

        private DefaultFile(String value) {
            this.value = value;
        }

        public String get() {
            return this.value;
        }
    }

    public enum DefaultCity {
        HEIGHT("500"),
        WIDTH("500"),
        COLUMNS("5"),
        ROWS("5");

        private final String value;

        private DefaultCity(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum DefaultConnection {
        DEFAULT_IP("255.255.255.255"),
        DEFAULT_PACKET_SIZE("1024"),
        DEFAULT_PORT("10000");

        private final String value;

        private DefaultConnection(String value) {
            this.value = value;
        }

        public int getIntegerValue() {
            return Integer.valueOf(this.value);
        }

        public String getStringValue() {
            return this.value;
        }
    }

    public enum SpritesCars {
        DODGE("black", Color.BLACK, 80),
        SKYLINE("white", Color.WHITE, 40),
        FERRARI("red", Color.RED, 25),
        JAGUAR("blue", Color.BLUE, 35),
        MERCEDES("grey", Color.GREY, 25),
        CAMARO("yellow", Color.YELLOW, 60);

        private final String tiny;
        private final String real;
        private final String ORIGIN = "/res/sprites/cars/";
        private final Color color;
        private final String stringColor;
        private final int maximumVelocity;

        private SpritesCars(String sprite, Color color, int maximumVelocity) {
            this.stringColor = sprite;
            this.tiny = (ORIGIN + "small-" + sprite + ".png");
            this.real = (ORIGIN + sprite + ".png");
            this.color = color;
            this.maximumVelocity = maximumVelocity;
        }

        public String getSmall() {
            return this.tiny;
        }

        public String getBig() {
            return this.real;
        }

        public Color getColor() {
            return this.color;
        }

        public String getStringColor() {
            return this.stringColor;
        }

        public int getMaximumVelocity() {
            return this.maximumVelocity;
        }

        public static SpritesCars getCarWithSprite(String color) {
            switch (color) {
                case "black": {
                    return DODGE;
                }
                case "white": {
                    return SKYLINE;
                }
                case "red": {
                    return FERRARI;
                }
                case "grey": {
                    return MERCEDES;
                }
                case "blue": {
                    return JAGUAR;
                }
                default: {
                    return CAMARO;
                }
            }
        }

        public static Color getColor(String name) {
            switch (name) {
                case "black": {
                    return SpritesCars.DODGE.getColor();
                }
                case "white": {
                    return SpritesCars.SKYLINE.getColor();
                }
                case "red": {
                    return SpritesCars.FERRARI.getColor();
                }
                case "grey": {
                    return SpritesCars.MERCEDES.getColor();
                }
                case "blue": {
                    return SpritesCars.JAGUAR.getColor();
                }
                default: {
                    return SpritesCars.CAMARO.getColor();
                }
            }
        }
    }

    public enum SpritesCity {

        NULL_ROAD("nullRoad.png", false),
        RUA_V("road-v.jpg", true),
        RUA_H("road-h.jpg", true),
        CURVA_E("road-l-left.png", true),
        CURVA_D("road-l-right.png", true),
        CURVA_E_I("road-l-left-inverse.png", true),
        CURVA_D_I("road-l-right-inverse.png", true),
        RUA_T("road-t.png", true),
        RUA_T_LEFT("road-t-left.png", true),
        RUA_T_RIGHT("road-t-right.png", true),
        RUA_T_INVERSE("road-t-inverse.png", true),
        RUA_X("road-x.jpg", true),
        TERRA_1("terra.png", false),
        TERRA_2("terra2.png", false),
        PAREDE_2("parede2.png", false),
        POSTE_1("poste1.png", false),
        POSTE_2("poste2.png", false);

        private final String path;
        private final boolean isRoad;
        private final String ORIGIN = "/res/sprites/city/";

        private SpritesCity(String path, boolean isRoad) {
            this.path = (ORIGIN + path);
            this.isRoad = isRoad;
        }

        public String get() {
            return this.path;
        }

        public boolean isRoad() {
            return this.isRoad;
        }
    }

    public enum RoadTypes {

        SINGLE(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH),
        HORIZONTAL(Orientation.WEST, Orientation.WEST, Orientation.EAST, Orientation.EAST),
        VERTICAL(Orientation.SOUTH, Orientation.NORTH, Orientation.SOUTH, Orientation.NORTH),
        L_DOWN_RIGHT(Orientation.SOUTH, Orientation.NORTH, Orientation.EAST, Orientation.EAST),
        L_DOWN_LEFT(Orientation.WEST, Orientation.NORTH, Orientation.EAST, Orientation.NORTH),
        L_UP_RIGHT(Orientation.SOUTH, Orientation.WEST, Orientation.SOUTH, Orientation.EAST),
        L_UP_LEFT(Orientation.WEST, Orientation.WEST, Orientation.SOUTH, Orientation.NORTH),
        T(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH),
        T_RIGHT(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH),
        T_LEFT(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH),
        T_INVERSE(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH),
        X(Orientation.SOUTH, Orientation.WEST, Orientation.EAST, Orientation.NORTH);

        private Orientation[] orientation;

        private RoadTypes(Orientation o1, Orientation o2, Orientation o3, Orientation o4) {
            this.orientation = new Orientation[4];
            this.orientation[0] = o1;
            this.orientation[1] = o2;
            this.orientation[2] = o3;
            this.orientation[3] = o4;
        }

        public Orientation[] getOrientation() {
            return this.orientation;
        }

        public Orientation getOrientation(int value) {
            return this.orientation[value];
        }

    }

    public enum ActionBuilder {
        PUT,
        SELECT,
        ROTATE_LEFT,
        ROTATE_RIGHT;
    }

    public enum Orientation {
        NORTH("north", "south", "west"),
        SOUTH("south", "north", "east"),
        EAST("east", "west", "north"),
        WEST("west", "east", "south");

        private final String direction, oppositeDirection, forbidden;

        private Orientation(String direction, String oppositeDirection, String forbidden) {
            this.direction = direction;
            this.oppositeDirection = oppositeDirection;
            this.forbidden = forbidden;
        }

        public String getDirection() {
            return this.direction;
        }

        public String getOppositeDirection() {
            return this.oppositeDirection;
        }

        public boolean isForbidden(Orientation destiny) {
            return this.forbidden.equals(destiny.getDirection());
        }

        public static Orientation getOrientation(String string) {
            switch (string) {
                case "north": {
                    return Orientation.NORTH;
                }
                case "south": {
                    return Orientation.SOUTH;
                }
                case "east": {
                    return Orientation.EAST;
                }
                case "west": {
                    return Orientation.WEST;
                }
                default: {
                    System.err.println("Erro de digitação no método de conversão de Orientação.");
                    return null;
                }
            }
        }

        @Override
        public String toString() {
            return this.direction;
        }
    ;

    }

    public enum Scenes {
        MAIN("Main.fxml", "Menu", false),
        SELECT_CAR("SelectCar.fxml", "Escolha seu carro", false),
        SELECT_CITY("SelectCity.fxml", "Escolha sua cidade", false),
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
