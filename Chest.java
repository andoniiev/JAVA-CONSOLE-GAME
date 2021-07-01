public class Chest extends Potion {

    public String glyph = "|$$$|";
    public String type = setType();

    @Override
    public String setType() {
        double chanceOfType = Math.random();
        String type = " ";

        if (chanceOfType <= 0.5)
            type = "weapon";

        else if (chanceOfType > 0.5)
            type = "gold";

            return type;

    }
}
