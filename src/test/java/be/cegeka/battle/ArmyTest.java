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
}