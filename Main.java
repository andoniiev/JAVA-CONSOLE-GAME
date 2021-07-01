import java.util.*;

public class Main {


    public static void main(String[] args) {

        int height = 15, width = 15, numOfObjects = 10;
        String direction;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Before we starting please enter your name");
        String heroName = scanner.nextLine();
        Player player = new Player(heroName);


        //Definition of main hero class
        String heroType = " ";
        System.out.println("Welcome " + heroName + "!\nTo start please choose your character's class:");
        System.out.println("Knight - melee warrior - HP:15 Damage:12");
        System.out.println("Ranger - middle range warrior - HP:12 Damage:8.");
        System.out.println("Wizard - HP:7 Damage:16.");
        while (!heroType.trim().toLowerCase().equals("wizard") && !heroType.trim().toLowerCase().equals("knight") && !heroType.trim().toLowerCase().equals("ranger")) {
            heroType = scanner.nextLine();
            player.setHeroType(heroType);
            if (!heroType.trim().toLowerCase().equals("wizard") && !heroType.trim().toLowerCase().equals("knight") && !heroType.trim().toLowerCase().equals("ranger")) {
                System.out.println("Please enter the appropriate class");
            }
        }

        //Initialising map
        Map[][] map = new Map[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                map[i][j] = new Map();
        }
        //Setting up chests
        Chest[] chest = new Chest[numOfObjects];
        for (int i = 0; i < chest.length; i++) {
            chest[i] = new Chest();
            chest[i].setType();
        }
        //Setting up potions
        Potion[] potion = new Potion[numOfObjects];
        for (int i = 0; i < potion.length; i++) {
            potion[i] = new Potion();
            potion[i].setType();
        }

        //Setting up enemies
        Enemy[] enemy = new Enemy[numOfObjects];
        for (int i = 0; i < enemy.length; i++) {
            enemy[i] = new Enemy();
            enemy[i].setUp(enemy[i].type);
        }

        //Setting up the map
        int a = 0, b = 0, c = 0;//a - for enemies,b - for potions,c - for weapons
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                //Placing enemies on the map
                while (a < enemy.length) {
                    enemy[a].x = Enemy.setX();
                    enemy[a].y = Enemy.setY();
                    while (!(map[enemy[a].x][enemy[a].y].xOccupied && map[enemy[a].x][enemy[a].y].yOccupied)) {
                        enemy[a].x = Enemy.setY();
                        enemy[a].y = Enemy.setX();
                        map[enemy[a].x][enemy[a].y].occupyX();
                        map[enemy[a].x][enemy[a].y].occupyY();
                        map[enemy[a].x][enemy[a].y].changeGlyph(enemy[a].glyph);
                        map[enemy[a].x][enemy[a].y].setTypeOnTile(enemy[a].type);
                    }
                    a++;
                }
            }
        }
        //Placing potions on the map
        while (b < potion.length) {
            potion[b].x = Potion.setX();
            potion[b].y = Potion.setY();
            while (!(map[potion[b].x][potion[b].y].xOccupied && map[potion[b].x][potion[b].y].yOccupied)) {
                potion[b].x = Potion.setY();
                potion[b].y = Potion.setX();
                map[potion[b].x][potion[b].y].occupyX();
                map[potion[b].x][potion[b].y].occupyY();
                map[potion[b].x][potion[b].y].changeGlyph(potion[b].glyph);
                map[potion[b].x][potion[b].y].setTypeOnTile(potion[b].type);
            }
            b++;
        }
        //Placing chests on the map
        while (c < chest.length) {
            chest[c].x = Chest.setX();
            chest[c].y = Chest.setY();
            while (!(map[chest[c].x][chest[c].y].xOccupied && map[chest[c].x][chest[c].y].yOccupied)) {
                chest[c].x = Chest.setY();
                chest[c].y = Chest.setX();
                map[chest[c].x][chest[c].y].occupyX();
                map[chest[c].x][chest[c].y].occupyY();
                map[chest[c].x][chest[c].y].changeGlyph(chest[c].glyph);
                map[chest[c].x][chest[c].y].setTypeOnTile(chest[c].type);
            }
            c++;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (player.getX() == i && player.getY() == j)
                    map[i][j].changeGlyph(player.getGlyph());

                if (i == height - 1 && j == width - 1)
                    map[i][j].changeGlyph("|_X_|");
            }

        }

        while (true) {
            if (player.level == 5) {

                System.out.println("Congratulations!!!You won!!!");
                System.exit(1);
            } else {

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        map[i][j].printGlyph();
                    }
                    System.out.println();
                    clearCon();
                }

                clearCon();
                System.out.println("What direction are we moving?\n    W\n A     D\n    S\n\nE - exit\nI - info");

                direction = scanner.nextLine();
                direction = direction.trim().toLowerCase();
                while (!direction.equals("w") && !direction.equals("s") && !direction.equals("a") && !direction.equals("d") && !direction.equals("e") && !direction.equals("i")) {
                    System.out.println("Please enter the appropriate value");
                    direction = scanner.nextLine();
                }
                while (!(player.getX() > 0 || player.getX() < width || player.getY() > 0 || player.getY() < height)) {
                    System.out.println("Please enter the appropriate value");
                    direction = scanner.nextLine();
                }

                switch (direction) {
                    case "w" -> {
                        clearCon();
                        map[player.getX()][player.getY()].changeGlyph("|___|");
                        player.moveUp();
                        if(player.getX() == width -1 && player.getY() == height - 1){
                            player.level++;
                        }
                        else {
                            if (map[player.getX()][player.getY()].typeOnTile != null) {
                                switch (map[player.getX()][player.getY()].typeOnTile) {
                                    case "ogre" -> {
                                        player.health -= 3;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat an ogre\n HP: " + player.health);
                                    }
                                    case "mage" -> {
                                        player.health -= 10;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a mage\n HP: " + player.health);
                                    }
                                    case "bulky_ogre" -> {
                                        player.health -= 7;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a bulky ogre\n HP: " + player.health);
                                    }
                                    case "bard" -> {
                                        System.out.println("Hi there! Don't mind if I tell you a joke?");
                                        Enemy.tellAnecdote();
                                        player.goldAmount += 10;
                                        System.out.println("Funny,isn't it? Ok take some gold from me\n You gained 10 gold\nYour gold amount is: " + player.goldAmount);

                                    }

                                    case "trickster" -> {
                                        System.out.println("Hello wanderer!And goodbye!\n*intense giggling*\nYou lost all your gold\nYour gold amount is: 0");
                                        player.goldAmount = 0;
                                    }

                                    case "small_health" -> {
                                        System.out.println("You found small health potion!!!");
                                        player.health += 3;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "small_power" -> {
                                        System.out.println("You found small power potion!!!");
                                        player.buffDamage += 3;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "big_health" -> {
                                        System.out.println("You found big health potion!!!");
                                        player.health += 7;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "big_power" -> {
                                        System.out.println("You found big power potion!!!");
                                        player.buffDamage += 7;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "weapon" -> {
                                        if (player.getType().equals("knight")) {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("WOW!You found ultra epic mega rare King Arthur's sword!!!");
                                                player.damage = 35 + player.buffDamage;
                                                player.currentWeapon = "King Arthur's sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Augmented sword found!!!");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Augmented sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Mace found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Mace";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Satori found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Satori";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        } else if (player.getType() == "ranger") {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("Wake the fuck up samurai, Skippy has been found");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Skippy";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Shotgun found!!!");
                                                player.damage = 10 + player.buffDamage;
                                                player.currentWeapon = "Shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Assault rifle found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Assault rifle";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Powerful shotgun found!!!");
                                                player.damage = 16 + player.buffDamage;
                                                player.currentWeapon = "Powerful shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }

                                        } else if (player.getType().equals("wizard")) {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.5) {
                                                System.out.println("Staff of power found!!!");
                                                player.damage = 25 + player.buffDamage;
                                                player.currentWeapon = "Staff of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.5 && chanceOfType <= 0.75) {
                                                System.out.println("Ring of power found!!!");
                                                player.damage = 30 + player.buffDamage;
                                                player.currentWeapon = "Ring of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.75) {
                                                System.out.println("Magic rod found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Magic rod";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        }
                                    }
                                    case "gold" -> {
                                        player.goldAmount+=50;
                                        System.out.println("Lucky you! You found 50 gold in a chest!!!\nYour gold amount now is: " + player.goldAmount);
                                    }                                }
                            }
                            map[player.getX()][player.getY()].changeGlyph("|_P_|");
                        }
                    }
                    case "a" -> {
                        clearCon();
                        map[player.getX()][player.getY()].changeGlyph("|___|");
                        player.moveLeft();
                        if(player.getX() == width -1 && player.getY() == height - 1){
                            player.level++;
                        }
                        else {
                            if (map[player.getX()][player.getY()].typeOnTile != null) {
                                switch (map[player.getX()][player.getY()].typeOnTile) {
                                    case "ogre" -> {
                                        player.health -= 3;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat an ogre\n HP: " + player.health);
                                    }
                                    case "mage" -> {
                                        player.health -= 10;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a mage\n HP: " + player.health);
                                    }
                                    case "bulky_ogre" -> {
                                        player.health -= 7;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a bulky ogre\n HP: " + player.health);
                                    }
                                    case "bard" -> {
                                        System.out.println("Hi there! Don't mind if I tell you a joke?");
                                        Enemy.tellAnecdote();
                                        player.goldAmount += 10;
                                        System.out.println("Funny,isn't it? Ok take some gold from me\n You gained 10 gold\nYour gold amount is: " + player.goldAmount);

                                    }

                                    case "trickster" -> {
                                        System.out.println("Hello wanderer!And goodbye!\n*intense giggling*\nYou lost all your gold\nYour gold amount is: 0");
                                        player.goldAmount = 0;
                                    }

                                    case "small_health" -> {
                                        System.out.println("You found small health potion!!!");
                                        player.health += 3;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "small_power" -> {
                                        System.out.println("You found small power potion!!!");
                                        player.buffDamage += 3;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "big_health" -> {
                                        System.out.println("You found big health potion!!!");
                                        player.health += 7;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "big_power" -> {
                                        System.out.println("You found big power potion!!!");
                                        player.buffDamage += 7;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "weapon" -> {
                                        if (player.getType() == "knight") {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("WOW!You found ultra epic mega rare King Arthur's sword!!!");
                                                player.damage = 35 + player.buffDamage;
                                                player.currentWeapon = "King Arthur's sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Augmented sword found!!!");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Augmented sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Mace found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Mace";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Satori found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Satori";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        } else if (player.getType() == "ranger") {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("Wake the fuck up samurai, Skippy has been found");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Skippy";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Shotgun found!!!");
                                                player.damage = 10 + player.buffDamage;
                                                player.currentWeapon = "Shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Assault rifle found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Assault rifle";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Powerful shotgun found!!!");
                                                player.damage = 16 + player.buffDamage;
                                                player.currentWeapon = "Powerful shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }

                                        } else if (player.getType().equals("wizard")) {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.5) {
                                                System.out.println("Staff of power found!!!");
                                                player.damage = 25 + player.buffDamage;
                                                player.currentWeapon = "Staff of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.5 && chanceOfType <= 0.75) {
                                                System.out.println("Ring of power found!!!");
                                                player.damage = 30 + player.buffDamage;
                                                player.currentWeapon = "Ring of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.75) {
                                                System.out.println("Magic rod found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Magic rod";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        }
                                    }
                                    case "gold" -> {
                                        player.goldAmount+=50;
                                        System.out.println("Lucky you! You found 50 gold in a chest!!!\nYour gold amount now is: " + player.goldAmount);
                                    }
                                }
                            }
                            map[player.getX()][player.getY()].changeGlyph("|_P_|");
                        }
                    }
                    case "s" -> {
                        clearCon();
                        map[player.getX()][player.getY()].changeGlyph("|___|");
                        player.moveDown();
                        if(player.getX() == width -1 && player.getY() == height - 1){
                            player.level++;
                        }
                        else {
                            if (map[player.getX()][player.getY()].typeOnTile != null) {
                                switch (map[player.getX()][player.getY()].typeOnTile) {
                                    case "ogre" -> {
                                        player.health -= 3;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat an ogre\n HP: " + player.health);
                                    }
                                    case "mage" -> {
                                        player.health -= 10;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a mage\n HP: " + player.health);
                                    }
                                    case "bulky_ogre" -> {
                                        player.health -= 7;
                                        if (player.health <= 0) {
                                            System.out.println("You died.Game over....");
                                            System.exit(1);
                                        } else
                                            System.out.println("You beat a bulky ogre\n HP: " + player.health);
                                    }
                                    case "bard" -> {
                                        System.out.println("Hi there! Don't mind if I tell you a joke?");
                                        Enemy.tellAnecdote();
                                        player.goldAmount += 10;
                                        System.out.println("Funny,isn't it? Ok take some gold from me\n You gained 10 gold\nYour gold amount is: " + player.goldAmount);

                                    }

                                    case "trickster" -> {
                                        System.out.println("Hello wanderer!And goodbye!\n*intense giggling*\nYou lost all your gold\nYour gold amount is: 0");
                                        player.goldAmount = 0;
                                    }

                                    case "small_health" -> {
                                        System.out.println("You found small health potion!!!");
                                        player.health += 3;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "small_power" -> {
                                        System.out.println("You found small power potion!!!");
                                        player.buffDamage += 3;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "big_health" -> {
                                        System.out.println("You found big health potion!!!");
                                        player.health += 7;
                                        System.out.println("Your HP now is: " + player.health);
                                    }
                                    case "big_power" -> {
                                        System.out.println("You found big power potion!!!");
                                        player.buffDamage += 7;
                                        player.damage += player.buffDamage;
                                        System.out.println("Your damage now is: " + player.damage);
                                    }
                                    case "weapon" -> {
                                        if (player.getType() == "knight") {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("WOW!You found ultra epic mega rare King Arthur's sword!!!");
                                                player.damage = 35 + player.buffDamage;
                                                player.currentWeapon = "King Arthur's sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Augmented sword found!!!");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Augmented sword";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Mace found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Mace";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Satori found!!!");
                                                player.damage = 18 + player.buffDamage;
                                                player.currentWeapon = "Satori";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        } else if (player.getType() == "ranger") {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.05) {
                                                System.out.println("Wake the fuck up samurai, Skippy has been found");
                                                player.damage = 15 + player.buffDamage;
                                                player.currentWeapon = "Skippy";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                                System.out.println("Shotgun found!!!");
                                                player.damage = 10 + player.buffDamage;
                                                player.currentWeapon = "Shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                                System.out.println("Assault rifle found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Assault rifle";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.8) {
                                                System.out.println("Powerful shotgun found!!!");
                                                player.damage = 16 + player.buffDamage;
                                                player.currentWeapon = "Powerful shotgun";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }

                                        } else if (player.getType().equals("wizard")) {
                                            double chanceOfType = Math.random();
                                            if (chanceOfType <= 0.5) {
                                                System.out.println("Staff of power found!!!");
                                                player.damage = 25 + player.buffDamage;
                                                player.currentWeapon = "Staff of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.5 && chanceOfType <= 0.75) {
                                                System.out.println("Ring of power found!!!");
                                                player.damage = 30 + player.buffDamage;
                                                player.currentWeapon = "Ring of power";
                                                System.out.println("Your current damage is: " + player.damage);
                                            } else if (chanceOfType > 0.75) {
                                                System.out.println("Magic rod found!!!");
                                                player.damage = 13 + player.buffDamage;
                                                player.currentWeapon = "Magic rod";
                                                System.out.println("Your current damage is: " + player.damage);
                                            }
                                        }
                                    }
                                    case "gold" -> {
                                        player.goldAmount+=50;
                                        System.out.println("Lucky you! You found 50 gold in a chest!!!\nYour gold amount now is: " + player.goldAmount);
                                    }
                                }
                            }
                            map[player.getX()][player.getY()].changeGlyph("|_P_|");
                        }
                    }
                    case "d" -> {
                        clearCon();
                        map[player.getX()][player.getY()].changeGlyph("|___|");
                        player.moveRight();
                        if(player.getX() == width -1 && player.getY() == height - 1){
                            player.level++;
                        }
                        else{
                        if (map[player.getX()][player.getY()].typeOnTile != null) {
                            switch (map[player.getX()][player.getY()].typeOnTile) {
                                case "ogre" -> {
                                    player.health -= 3;
                                    if (player.health <= 0) {
                                        System.out.println("You died.Game over....");
                                        System.exit(1);
                                    } else
                                        System.out.println("You beat an ogre\n HP: " + player.health);
                                }
                                case "mage" -> {
                                    player.health -= 10;
                                    if (player.health <= 0) {
                                        System.out.println("You died.Game over....");
                                        System.exit(1);
                                    } else
                                        System.out.println("You beat a mage\n HP: " + player.health);
                                }
                                case "bulky_ogre" -> {
                                    player.health -= 7;
                                    if (player.health <= 0) {
                                        System.out.println("You died.Game over....");
                                        System.exit(1);
                                    } else
                                        System.out.println("You beat a bulky ogre\n HP: " + player.health);
                                }
                                case "bard" -> {
                                    System.out.println("Hi there! Don't mind if I tell you a joke?");
                                    Enemy.tellAnecdote();
                                    player.goldAmount += 10;
                                    System.out.println("Funny,isn't it? Ok take some gold from me\n You gained 10 gold\nYour gold amount is: " + player.goldAmount);

                                }

                                case "trickster" -> {
                                    System.out.println("Hello wanderer!And goodbye!\n*intense giggling*\nYou lost all your gold\nYour gold amount is: 0");
                                    player.goldAmount = 0;
                                }

                                case "small_health" -> {
                                    System.out.println("You found small health potion!!!");
                                    player.health += 3;
                                    System.out.println("Your HP now is: " + player.health);
                                }
                                case "small_power" -> {
                                    System.out.println("You found small power potion!!!");
                                    player.buffDamage += 3;
                                    player.damage += player.buffDamage;
                                    System.out.println("Your damage now is: " + player.damage);
                                }
                                case "big_health" -> {
                                    System.out.println("You found big health potion!!!");
                                    player.health += 7;
                                    System.out.println("Your HP now is: " + player.health);
                                }
                                case "big_power" -> {
                                    System.out.println("You found big power potion!!!");
                                    player.buffDamage += 7;
                                    player.damage += player.buffDamage;
                                    System.out.println("Your damage now is: " + player.damage);
                                }
                                case "weapon" -> {
                                    if (player.getType() == "knight") {
                                        double chanceOfType = Math.random();
                                        if (chanceOfType <= 0.05) {
                                            System.out.println("WOW!You found ultra epic mega rare King Arthur's sword!!!");
                                            player.damage = 35 + player.buffDamage;
                                            player.currentWeapon = "King Arthur's sword";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                            System.out.println("Augmented sword found!!!");
                                            player.damage = 15 + player.buffDamage;
                                            player.currentWeapon = "Augmented sword";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                            System.out.println("Mace found!!!");
                                            player.damage = 18 + player.buffDamage;
                                            player.currentWeapon = "Mace";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.8) {
                                            System.out.println("Satori found!!!");
                                            player.damage = 18 + player.buffDamage;
                                            player.currentWeapon = "Satori";
                                            System.out.println("Your current damage is: " + player.damage);
                                        }
                                    } else if (player.getType() == "ranger") {
                                        double chanceOfType = Math.random();
                                        if (chanceOfType <= 0.05) {
                                            System.out.println("Wake the fuck up samurai, Skippy has been found");
                                            player.damage = 15 + player.buffDamage;
                                            player.currentWeapon = "Skippy";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.05 && chanceOfType <= 0.55) {
                                            System.out.println("Shotgun found!!!");
                                            player.damage = 10 + player.buffDamage;
                                            player.currentWeapon = "Shotgun";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.55 && chanceOfType <= 0.8) {
                                            System.out.println("Assault rifle found!!!");
                                            player.damage = 13 + player.buffDamage;
                                            player.currentWeapon = "Assault rifle";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.8) {
                                            System.out.println("Powerful shotgun found!!!");
                                            player.damage = 16 + player.buffDamage;
                                            player.currentWeapon = "Powerful shotgun";
                                            System.out.println("Your current damage is: " + player.damage);
                                        }

                                    } else if (player.getType().equals("wizard")) {
                                        double chanceOfType = Math.random();
                                        if (chanceOfType <= 0.5) {
                                            System.out.println("Staff of power found!!!");
                                            player.damage = 25 + player.buffDamage;
                                            player.currentWeapon = "Staff of power";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.5 && chanceOfType <= 0.75) {
                                            System.out.println("Ring of power found!!!");
                                            player.damage = 30 + player.buffDamage;
                                            player.currentWeapon = "Ring of power";
                                            System.out.println("Your current damage is: " + player.damage);
                                        } else if (chanceOfType > 0.75) {
                                            System.out.println("Magic rod found!!!");
                                            player.damage = 13 + player.buffDamage;
                                            player.currentWeapon = "Magic rod";
                                            System.out.println("Your current damage is: " + player.damage);
                                        }
                                    }
                                }
                                case "gold" -> {
                                    player.goldAmount+=50;
                                    System.out.println("Lucky you! You found 50 gold in a chest!!!\nYour gold amount now is: " + player.goldAmount);
                                }
                            }
                        }
                        map[player.getX()][player.getY()].changeGlyph("|_P_|");}
                    }
                    case "e" -> System.exit(1);
                    case "i" -> showInfo(player);
                }


            }
        }
    }

    private static void showInfo(Player player) {
        System.out.println("Name: " + player.name);
        System.out.println("Current level: " + player.level);
        System.out.println("Current weapon: " + player.currentWeapon);
        System.out.println("HP: " + player.health);
        System.out.println("Damage: " + player.damage);
        System.out.println("Gold amount: " + player.goldAmount);
    }

    private static void clearCon(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
