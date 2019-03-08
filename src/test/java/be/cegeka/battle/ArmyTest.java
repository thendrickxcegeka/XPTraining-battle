package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ArmyTest {

    @Test
    public void enrollSoldier() {
        Soldier soldier = new Soldier("name");
        Army army = new Army();
        army.enrollSoldier(soldier);

        Assertions.assertThat(army.isEnrolled(soldier)).isTrue();
    }

    @Test
    public void enrollSoldier_notifiesHeadquarters() {
        Soldier soldier = new Soldier("name");
        Army army = new Army();
        army.enrollSoldier(soldier);


    }

    @Test
    public void getFrontMan_givenEnrolledSoldier() {
        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name2");
        Army army = new Army();
        army.enrollSoldier(soldier);
        army.enrollSoldier(soldier2);

        Assertions.assertThat(army.getFrontMan().get()).isEqualTo(soldier);
    }

    @Test
    public void getFrontMan_givenNoSoldier_EmptyOptional() {
        Army army = new Army();

        Assertions.assertThat(army.getFrontMan()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void attack_givenEmptyArmyAttacks_throwsException() {
        Army attacker = new Army();

        Soldier soldier = new Soldier("name");
        Army attackee = new Army();
        attackee.enrollSoldier(soldier);

        attacker.attack(attackee);
    }

    @Test
    public void attack_givenArmyAttacksEmptyArmy_attackerWins() {
        Army attackee = new Army();

        Soldier soldier = new Soldier("name");
        Army attacker = new Army();
        attacker.enrollSoldier(soldier);

        Assertions.assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenArmyAttacksWeakerArmy_attackerWins() {
        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name");
        Army attackee = new Army();
        attackee.enrollSoldier(soldier);
        attackee.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name", WeaponType.AXE);
        Army attacker = new Army();
        attacker.enrollSoldier(soldier3);

        Assertions.assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenArmyAttacksStrongerArmy_attackerLoses() {
        Soldier soldier = new Soldier("name", WeaponType.BARE_FIST);
        Soldier soldier2 = new Soldier("name", WeaponType.SPEAR);
        Army attacker = new Army();
        attacker.enrollSoldier(soldier);
        attacker.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name", WeaponType.AXE);
        Army attackee = new Army();
        attackee.enrollSoldier(soldier3);

        Assertions.assertThat(attacker.attack(attackee)).isFalse();
    }




}