# JAVA-CONSOLE-GAME
University Java project. Fundamentals of Java programming course

FEATURES
•	Random generation of types of objects: chests, enemies, potions, e.g., ogre, mage, small health potion, big health potion etc.
•	Different classes of hero get different random weapons from chests
•	Random generation of location of objects. Objects do not overlap
•	Player can move along the map
•	Player can get all info at the current moment of the game (Main class method showInfo() ) or exit the game
•	Player can interact with NPCs. For example, some NPCs (Trickster) steal all player’s money, some (Bard) tell the random joke and give money, some of them player can kill (Enemy class)
•	If player reaches right bottom corner he moves to the next level, if player reaches 5th level, he wins. If player’s HP <= 0, he loses.
•	Player can upgrade or restore his HP with small and big health potions and upgrade his damage with power potions



Main character:
•	Knight (melee weapon) 15 hp 12 damage. From the dead
•	Ranger (ranger's musket) 12 hp 8 damage. Powerful shot
•	Wizard (wizard's stave) 7 hp 16 damage. Invincibility

Side characters:
•	Billy Bong (Trickster) - if you don't run away and start a conversation with him, you lose all your money (5% chance)
•	Ogre - 8 hp 3 damage. A regular ogre (60%)
•	Bard - friendly character. Tells a joke and gives 10 gold (10%)
•	Evil Mage - 5 hp 10 damage (15%)
•	Bulky ogre - 15 hp 7 damage (10%)

Potions:
•	Health pot - + 3 hp (40%) (extending character's hp if character wasn't damaged)
•	Strengthen pot - + 3 damage (40%)
•	Big health pot - + 7 hp (10%) (extending character's hp if character wasn't damaged)
•	Big strengthen pot - + 7 damage (10%)

Chest:
•	50% - weapon
•	50% - + 50 gold

Weapons:
•	Knight:
o	King Arthur's sword - 35 damage. 5%
o	Augmented sword - 15 damage. 50%
o	Mace - 18 damage. 25%
o	Satori - 20 damage. 20%

•	Ranger:
o	Skippy (Easter egg) - 15 damage. 5%
o	Shotgun - 10 damage. 50%
o	Assault rifle - 13 damage. 25%
o	Powerful shotgun - 16 damage.20%

•	Wizard:
o	Staff of power - 25 damage. 50%
o	Ring of power - 30 damage. 25%
o	Magic rod - 33 damage. 25%

