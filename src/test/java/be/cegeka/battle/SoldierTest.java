package be.cegeka.battle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SoldierTest {

    @Test
    public void construction_ASoldierMustHaveAName() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getName()).isEqualTo("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeNull() {
        new Soldier(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeEmpty() {
        new Soldier("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_ASoldierMustHaveAName_CannotBeBlank() {
        new Soldier("   ");
    }

    @Test
    public void construction_ASoldierHasBareFistWeaponByDefault() {
        Soldier soldier = new Soldier("name");

        assertThat(soldier.getWeaponType()).isEqualTo(WeaponType.BARE_FIST);
    }

    @Test
    public void attack_givenASoldierAttacksSoldierWithSameWeapon_thenAttackerWins() {
        Soldier attacker = new Soldier("attacker");
        Soldier attackee = new Soldier("attackee");

        assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenASoldierAttacksSoldierWithWeakerWeapon_thenAttackerWins() {
        Soldier attacker = new Soldier("attacker", WeaponType.SPEAR);
        Soldier attackee = new Soldier("attackee");

        assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenASoldierAttacksSoldierWithStrongerWeapon_thenAttackerLoses() {
        Soldier attacker = new Soldier("attackee");
        Soldier attackee = new Soldier("attacker", WeaponType.SPEAR);

        assertThat(attacker.attack(attackee)).isFalse();
    }
}
