package util;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {

    public enum SpritesCars{
        DODGE("black"),
        SKYLINE("white"),
        FERRARI("red"),
        JAGUAR("blue"),
        MERCEDES("grey"),
        CAMARO("yellow");
       
        private final String tiny;
        private final String real;
        private final String ORIGIN = "/res/sprites/cars/";
        
        private SpritesCars(String color){
            this.tiny = (ORIGIN+"small-"+color+".png");
            this.real = (ORIGIN+color +".png");
        }
        
        public String getSmall(){
            return this.tiny;
        }
        
        public String getBig(){
            return this.real;
        }
    }
    
    public enum SpritesCity{
        ROAD("road");
        
        private final String path;
        private final String ORIGIN = "/res/sprites/city/";
        
        private SpritesCity(String path){
            this.path = (ORIGIN+path+".jpg");
        }
        
        public String get(){
            return this.path;
        }
    }
    
    private enum CityLocation{
        CHINA("china");
        
        private final String name;
        
        private CityLocation(String name){
            this.name = name;
        }
    }
    
    public enum Scenes {
        SELECT_CAR("SelectCar.fxml", "Escolha seu carro", false),
        ROADS("Roads.fxml", "China", false);
        
        private final String name;
        private final String title;
        private final boolean cache;
        
        private Scenes(String value, String title, boolean cache) {
            this.name = "/view/"+value;
            this.title = title;
            this.cache = cache;
        }

        public String getTitle(){
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
