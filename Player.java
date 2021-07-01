public class Player extends Creature{

    public int goldAmount = 0;
    public int buffDamage = 0;
    public int level = 0;
    public String type;
    public String currentWeapon;

    public Player(String name){
        this.name = name;
        this.glyph = "|_P_|";
        this.x = 0;
        this.y = 0;
    }

    public String getGlyph(){
        return this.glyph;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public String getType(){
        return this.type;
    }


    public void setDamage(int damage){this.damage = damage;}
    public void setHealth(int health){this.health = health;}



    public void setHeroType(String type){

        if(type.trim().toLowerCase().equals("knight")){
            this.type = "knight";
            this.currentWeapon = "sword";
            setDamage(12);
            setHealth(15);
        }
        else if(type.trim().toLowerCase().equals("ranger")){
            this.type = "ranger";
            this.currentWeapon = "Revolver";
            setDamage(8);
            setHealth(12);
        }
        else if(type.trim().toLowerCase().equals("wizard")){
            this.type = "wizard";
            this.currentWeapon = "Magic stick";
            setDamage(7);
            setHealth(16);
        }
    }



    public void moveDown(){
        this.x++;
    }

    public void moveUp(){
        this.x--;
    }

    public void moveRight(){
        this.y++;
    }

    public void moveLeft(){
        this.y--;
    }
}
