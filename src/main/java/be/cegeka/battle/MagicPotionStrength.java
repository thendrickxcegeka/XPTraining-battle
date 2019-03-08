package be.cegeka.battle;

public class MagicPotionStrength implements Strength {

    @Override
    public int getStrength(Strength otherStrength) {
        if(otherStrength.getStrength(this) % 2 == 0) {
            return 10;
        }
        return 0;
    }
}
