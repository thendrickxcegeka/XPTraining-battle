package be.cegeka.battle;

public enum WeaponType {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BARE_FIST(1);

    private final int strength;

    WeaponType(int strength) {
        this.strength = strength;
    }

    public boolean isStrongerOrEquallyStrong(WeaponType otherWeaponType) {
        return this.strength >= otherWeaponType.strength;
    }
}
