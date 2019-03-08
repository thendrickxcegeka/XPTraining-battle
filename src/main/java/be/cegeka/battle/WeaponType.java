package be.cegeka.battle;

public enum WeaponType {
    AXE(new BasicStrength(3)),
    SWORD(new BasicStrength(2)),
    SPEAR(new BasicStrength(2)),
    BARE_FIST(new BasicStrength(1)),
    TWO_HANDED_SWORD(new BasicStrength(5)),
    BROAD_AXE(new BasicStrength(5)),
    TRIDENT(new BasicStrength(6)),
    MAGIC_POTION(new MagicPotionStrength())
    ;

    private final Strength strength;

    WeaponType(Strength strength) {
        this.strength = strength;
    }

    protected int getStrength(Strength otherStrength) {
        return strength.getStrength(otherStrength);
    }

    public boolean isStrongerOrEquallyStrong(WeaponType otherWeaponType) {
        if(this.equals(otherWeaponType)) {
            return true;
        }
        return this.getStrength(otherWeaponType.strength) >= otherWeaponType.getStrength(this.strength);
    }
}
