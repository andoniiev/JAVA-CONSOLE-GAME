public class Potion {
    public int x;
    public int y;
    public String type = setType();
    public String glyph;



    public String setType(){
        double chanceOfType = Math.random();
        String type = " ";

        if(chanceOfType <= 0.4) {
            type = "small_health";//+3hp
            this.glyph = "|_+_|";
        }
        else if(chanceOfType > 0.4 && chanceOfType <= 0.8) {
            type = "small_power";//+3 damage
            this.glyph = "|_*_|";
        }
        else if(chanceOfType > 8 && chanceOfType <= 0.9) {
            type = "big_health";//+7hp
            this.glyph = "|+++|";
        }
        else if(chanceOfType > 0.9 ) {
            type = "big_power";//+7 damage
            this.glyph = "|***|";
        }
        return type;
    }

    public static int setY(){
        int height = 15;
        return (int)(Math.random() * height );
    }
    public  static int setX(){
        int width = 15;
        return (int)(Math.random() * width );
    }
}
