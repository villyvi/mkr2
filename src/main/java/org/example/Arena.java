package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Arena {
    final List<Character> characters = new ArrayList<>();
    private final int fieldSize = 5;
    private final char[][] field = new char[fieldSize][fieldSize];

    public void addCharacter(Character character) {
        characters.add(character);
        updateField();
    }

    public void updateField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = '.';
            }
        }
        for (Character character : characters) {
            if (character instanceof Warrior) field[character.getX()][character.getY()] = 'W';
            else if (character instanceof Mage) field[character.getX()][character.getY()] = 'M';
            else if (character instanceof Archer) field[character.getX()][character.getY()] = 'A';
            else if (character instanceof Enemy) field[character.getX()][character.getY()] = 'E';
        }
    }

    public void displayField() {
        System.out.println("Arena:");
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayCharactersInfo() {
        for (Character character : characters) {
            System.out.println(character.getName() + " - Health: " + character.getHealth() + ", Attack: " + character.attackPower + ", Position: (" + character.getX() + ", " + character.getY() + ")");
        }
        System.out.println();
    }

    public void coordinateAttack(Character attacker, String targetName) {
        Character target = characters.stream()
                .filter(c -> c.getName().equals(targetName))
                .findFirst()
                .orElse(null);

        if (target != null) {
            attacker.attack(target);
            if (target.getHealth() <= 0) {
                characters.remove(target);
                System.out.println(target.getName() + " has been removed from the arena!");
            }
            updateField();
        } else {
            System.out.println("Target not found in the arena.");
        }
    }

    public int getFieldSize() {
        return fieldSize;
    }
}