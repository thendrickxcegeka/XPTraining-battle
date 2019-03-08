package be.cegeka.battle;

import org.apache.commons.lang3.Validate;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Soldier {

    private final String name;
    private final WeaponType weaponType;

    public Soldier(String name) {
        this(name, WeaponType.BARE_FIST);
    }

    public Soldier(String name, WeaponType weaponType) {
        Validate.isTrue(isNotBlank(name));
        this.name = name;
        this.weaponType = weaponType;
    }

    String getName() {
        return this.name;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public boolean attack(Soldier attackee) {
        return this.getWeaponType().isStrongerOrEquallyStrong(attackee.getWeaponType());
    }
}
