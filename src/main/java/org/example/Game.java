package org.example;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arena arena = new Arena();

        // Custom Character Names
        Character warrior = new Warrior("Arthur");
        Character mage = new Mage("Poopa");
        Character archer = new Archer("Kamaz");
        Character enemy = new Enemy("Monster", 3, 3);

        // Add characters to the arena
        arena.addCharacter(warrior);
        arena.addCharacter(mage);
        arena.addCharacter(archer);
        arena.addCharacter(enemy);

        // Game loop
        while (true) {
            arena.displayCharactersInfo();
            arena.displayField();

            System.out.println("Введіть літери (w/a/s/d щоб пересуватися, attack <ціль> для атаки, і exit для виходу):");
            String input = scanner.nextLine();

            if (input.equals("exit")) break;

            if (input.startsWith("attack")) {
                String targetName = input.substring(7); // Extract target name after "attack "
                System.out.println("Хто атакує (вкажіть ім'я)? (Warrior Arthur/Mage Poopa/Archer Kamaz):");
                String attackerName = scanner.nextLine();

                Character attacker = arena.characters.stream()
                        .filter(c -> c.getName().equals(attackerName))
                        .findFirst()
                        .orElse(null);

                if (attacker != null) {
                    arena.coordinateAttack(attacker, targetName);
                } else {
                    System.out.println("Такого персонажа нема.");
                }
            } else if (input.length() == 1 && "wasd".contains(input)) {
                System.out.println("Хто ходить (вкажіть ім'я англійською)? (Warrior Arthur/Mage Poopa/Archer Kamaz):");
                String moverName = scanner.nextLine();

                Character mover = arena.characters.stream()
                        .filter(c -> c.getName().equals(moverName))
                        .findFirst()
                        .orElse(null);

                if (mover != null) {
                    mover.move(input.charAt(0), arena.getFieldSize());
                    arena.updateField();
                } else {
                    System.out.println("Персонажа не знайдено.");
                }
            } else {
                System.out.println("Неправильна команда.");
            }
        }

        scanner.close();
    }
}