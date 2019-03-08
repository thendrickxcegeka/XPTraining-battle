package be.cegeka.battle;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ArmyTest {

    private IHeadquarters headquarters = mock(IHeadquarters.class);
    private IHeadquarters headquarters2 = mock(IHeadquarters.class);

    @Test
    public void enrollSoldier() {
        Soldier soldier = new Soldier("name");
        Army army = new Army(headquarters);
        army.enrollSoldier(soldier);

        Assertions.assertThat(army.isEnrolled(soldier)).isTrue();
    }

    @Test
    public void enrollSoldier_notifiesHeadquarters() {
        Soldier soldier = new Soldier("name");
        Army army = new Army(headquarters);
        army.enrollSoldier(soldier);

        verify(headquarters).reportEnlistment("name");
    }

    @Test
    public void getFrontMan_givenEnrolledSoldier() {
        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name2");
        Army army = new Army(headquarters);
        army.enrollSoldier(soldier);
        army.enrollSoldier(soldier2);

        Assertions.assertThat(army.getFrontMan().get()).isEqualTo(soldier);
    }

    @Test
    public void getFrontMan_givenNoSoldier_EmptyOptional() {
        Army army = new Army(headquarters);

        Assertions.assertThat(army.getFrontMan()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void attack_givenEmptyArmyAttacks_throwsException() {
        Army attacker = new Army(headquarters);

        Soldier soldier = new Soldier("name");
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier);

        attacker.attack(attackee);
    }

    @Test
    public void attack_givenArmyAttacksEmptyArmy_attackerWins() {
        Army attackee = new Army(headquarters);

        Soldier soldier = new Soldier("name");
        Army attacker = new Army(headquarters);
        attacker.enrollSoldier(soldier);

        Assertions.assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenArmyAttacksWeakerArmy_attackerWins() {
        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name");
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier);
        attackee.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name", WeaponType.AXE);
        Army attacker = new Army(headquarters);
        attacker.enrollSoldier(soldier3);

        Assertions.assertThat(attacker.attack(attackee)).isTrue();
    }

    @Test
    public void attack_givenArmyAttacksWeakerArmy_casualtiesReported() {
        when(headquarters.reportEnlistment("name")).thenReturn(1);
        when(headquarters.reportEnlistment("name2")).thenReturn(2);

        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name2");
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier);
        attackee.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name3", WeaponType.AXE);
        Army attacker = new Army(headquarters);
        attacker.enrollSoldier(soldier3);
        attacker.attack(attackee);

        verify(headquarters).reportCasualty(1);
        verify(headquarters).reportCasualty(2);
    }

    @Test
    public void attack_givenArmyAttacksWeakerArmy_victoryReported() {
        when(headquarters.reportEnlistment("name")).thenReturn(1);
        when(headquarters.reportEnlistment("name2")).thenReturn(2);

        Soldier soldier = new Soldier("name");
        Soldier soldier2 = new Soldier("name2");
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier);
        attackee.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name3", WeaponType.AXE);
        Army attacker = new Army(headquarters2);
        attacker.enrollSoldier(soldier3);
        attacker.attack(attackee);

        verify(headquarters2).reportVictory(1);
    }

    @Test
    public void attack_givenArmyAttacksStrongerArmy_victoryReported() {
        when(headquarters.reportEnlistment("name")).thenReturn(1);
        when(headquarters.reportEnlistment("name2")).thenReturn(2);

        Soldier soldier = new Soldier("name", WeaponType.AXE);
        Soldier soldier2 = new Soldier("name2");
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier);
        attackee.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name3", WeaponType.SPEAR);
        Army attacker = new Army(headquarters2);
        attacker.enrollSoldier(soldier3);
        attacker.attack(attackee);

        verify(headquarters).reportVictory(2);
    }

    @Test
    public void attack_givenArmyAttacksStrongerArmy_attackerLoses() {
        Soldier soldier = new Soldier("name", WeaponType.BARE_FIST);
        Soldier soldier2 = new Soldier("name", WeaponType.SPEAR);
        Army attacker = new Army(headquarters);
        attacker.enrollSoldier(soldier);
        attacker.enrollSoldier(soldier2);

        Soldier soldier3 = new Soldier("name", WeaponType.AXE);
        Army attackee = new Army(headquarters);
        attackee.enrollSoldier(soldier3);

        Assertions.assertThat(attacker.attack(attackee)).isFalse();
    }






}