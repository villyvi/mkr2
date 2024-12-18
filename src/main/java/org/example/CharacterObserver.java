package org.example;

class CharacterObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("[Observer] " + message);
    }
}
