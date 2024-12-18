package org.example;

abstract class Character {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int x, y;

    public Character(String name, int health, int attackPower, int x, int y) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.x = x;
        this.y = y;
    }

    public void move(char direction, int fieldSize) {
        switch (direction) {
            case 'w': if (x > 0) x--; break;
            case 's': if (x < fieldSize - 1) x++; break;
            case 'a': if (y > 0) y--; break;
            case 'd': if (y < fieldSize - 1) y++; break;
        }
    }

    public void attack(Character target) {
        if (target != null) {
            target.health -= this.attackPower;
            System.out.println(this.name + " attacked " + target.getName() + " for " + this.attackPower + " damage!");
            if (target.health <= 0) {
                System.out.println(target.getName() + " has been defeated!");
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
