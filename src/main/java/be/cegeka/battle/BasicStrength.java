package be.cegeka.battle;

public class BasicStrength implements Strength {
    private int strength;

    public BasicStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getStrength(Strength otherStrength) {
        return strength;
    }
}
