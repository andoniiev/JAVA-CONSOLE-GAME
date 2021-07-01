public class Enemy extends Creature{

    public String type = setEnemyType();


    public void setDamage(int damage){this.damage = damage;}
    public void setHealth(int health){this.health = health;}
    public static void tellAnecdote(){
        String[] anecdotes = new String[10];
        anecdotes[0] = "\"I'm on a seafood diet. I see food and I eat it.\"";
        anecdotes[1] = "\"I made a pencil with two erasers. It was pointless.\"";
        anecdotes[2] = "\"How do lawyers say goodbye? We'll be suing ya!\"";
        anecdotes[3] = "\"What concert costs just 45 cents? 50 Cent featuring Nickelback!\"";
        anecdotes[4] = "\"Why couldn't the bicycle stand up by itself? It was two tired!\"";
        anecdotes[5] = "\"Did you hear about the restaurant on the moon? Great food, no atmosphere!\"";
        anecdotes[6] = "\"Did you hear the rumor about butter? Well, I'm not going to spread it!\"";
        anecdotes[7] = "\"What happens when you go to the bathroom in France? European.\"";
        anecdotes[8] = "\"Last night I had a dream that I weighed less than a thousandth of a gram. I was like, 0mg.\"";
        anecdotes[9] = "\"Why did the old man fall in the well? Because he couldn't see that well!\"";

        System.out.println(anecdotes[(int) (Math.random() * 10)]);
    }


    public String setEnemyType(){
        double chanceOfType = Math.random();
        String type = " ";

        if(chanceOfType <= 0.6)
            type = "ogre";
        else if(chanceOfType > 0.6 && chanceOfType <= 0.65)
            type ="trickster";
        else if(chanceOfType > 0.65 && chanceOfType <= 0.75)
            type ="bard";
        else if(chanceOfType > 0.75 && chanceOfType <= 0.9)
            type ="mage";
        else if(chanceOfType > 0.9 )
            type ="bulky_ogre";

        return type;
    }

    public void setUp(String type){
        switch (type) {
            case "ogre" -> {
                setDamage(3);
                setHealth(8);
                this.glyph ="|_O_|";
            }
            case "mage" -> {
                setDamage(10);
                setHealth(5);
                this.glyph = "|_M_|";
            }
            case "bulky_ogre" -> {
                setDamage(7);
                setHealth(15);
                this.glyph = "|_K_|";
            }
            case "bard" -> this.glyph = "|_B_|";

            case "trickster" -> this.glyph = "|_T_|";
        }

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
