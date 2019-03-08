package be.cegeka.battle;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public enum WeaponType {
    AXE(3),
    SWORD(2),
    SPEAR(2),
    BARE_FIST(1),
    TWO_HANDED_SWORD(5),
    BROAD_AXE(2 + AXE.strength.get()),
    TRIDENT(3 + SPEAR.strength.get()),
    MAGIC_POTION()
    ;

    private final Optional<Integer> strength;

    WeaponType(int strength) {
        this.strength = Optional.of(strength);
    }

    WeaponType() {
        this.strength = Optional.empty();
    }

    protected Optional<Integer> getStrength() {
        return strength;
    }

    public Pair<Integer, Integer> getStrengths(WeaponType otherWeaponType) {
        int thisStrength = getBasicStrength(this);
        int otherStrength = getBasicStrength(otherWeaponType);

        thisStrength += getPotionStrength(this, otherWeaponType);
        otherStrength += getPotionStrength(otherWeaponType, this);

        thisStrength += getSpecificationStrength(this, otherWeaponType);

        return Pair.of(thisStrength, otherStrength);
    }

    private int getSpecificationStrength(WeaponType weaponType, WeaponType otherWeaponType) {
        if(AXE.equals(weaponType) && SPEAR.equals(otherWeaponType)) {
            return 3;
        }
        if(SPEAR.equals(weaponType) && SWORD.equals(otherWeaponType)) {
            return 3;
        }
        if(SWORD.equals(weaponType) && AXE.equals(otherWeaponType)) {
            return 3;
        }
        return 0;
    }

    public boolean isStrongerOrEquallyStrong(WeaponType otherWeaponType) {
        if(this.equals(otherWeaponType)) {
            return true;
        }

        Pair<Integer, Integer> strengths = getStrengths(otherWeaponType);

        return strengths.getLeft() >= strengths.getRight();
    }

    private int getBasicStrength(WeaponType weaponType) {
        return weaponType.getStrength().orElse(0);
    }

    private int getPotionStrength(WeaponType weaponType, WeaponType otherWeaponType) {
        if(MAGIC_POTION.equals(weaponType)) {
            if(otherWeaponType.getStrength().orElse(0) % 2 == 0) {
                return 10;
            }
        }
        return 0;
    }
}
