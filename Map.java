public class Map {
    public String glyph = "|___|";
    public String typeOnTile = null;
    public boolean yOccupied = false;
    public boolean xOccupied = false;


public void setTypeOnTile(String type){
    typeOnTile = type;
}

    public void changeGlyph(String glyph){
        this.glyph = glyph;
    }
    public void printGlyph(){
        System.out.print(this.glyph);
    }

    public void occupyX() {
        xOccupied = true;
    }
    public void occupyY() {
        yOccupied = true;
    }
}
