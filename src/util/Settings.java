package util;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {

    public enum Cars{
        DODGE("carro-preto", "carro-preto-grande"),
        SKYLINE("carro-branco", "carro-branco-grande"),
        FERRARI("carro-vermelho", "carro-vermelho-grande"),
        JAGUAR("carro-azul", "carro-azul-grande"),
        MERCEDES("carro-cinza", "carro-cinza-grande"),
        CAMARO("carro-amarelo", "carro-amarelo-grande");
       
        private final String tiny;
        private final String real;
        
        private Cars(String tiny, String real){
            this.tiny = ("/images/"+tiny+".png");
            this.real = ("/images/"+real+".png");
        }
        
        public String getTiny(){
            return this.tiny;
        }
        
        public String getReal(){
            return this.real;
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
