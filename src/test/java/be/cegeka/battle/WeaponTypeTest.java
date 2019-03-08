package be.cegeka.battle;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeaponTypeTest {

    @Test
    public void isStrongerOrEquallyStrong_givenEqualWeaponType_thenIsStronger() {
        assertThat(WeaponType.AXE.isStrongerOrEquallyStrong(WeaponType.AXE)).isTrue();
    }

    @Test
    public void isStrongerOrEquallyStrong_givenEqualStrength_thenIsStronger() {
        assertThat(WeaponType.SWORD.isStrongerOrEquallyStrong(WeaponType.SPEAR)).isTrue();

    }

    @Test
    public void isStrongerOrEquallyStrong_givenLessStrength_thenIsNotStronger() {
        assertThat(WeaponType.AXE.isStrongerOrEquallyStrong(WeaponType.BROAD_AXE)).isFalse();
    }

    @Test
    public void isStrongerOrEquallyStrong_givenMagicPotionAndOponentHasEvenDamage_thenIsStronger() {
        assertThat(WeaponType.MAGIC_POTION.isStrongerOrEquallyStrong(WeaponType.SPEAR)).isTrue();
    }

    @Test
    public void isStrongerOrEquallyStrong_givenMagicPotionAndOponentHasOddDamage_thenIsNotStronger() {
        assertThat(WeaponType.MAGIC_POTION.isStrongerOrEquallyStrong(WeaponType.TWO_HANDED_SWORD)).isFalse();
    }

    @Test
    public void isStrongerOrEquallyStrong_givenAxesOverSpears_thenBonusOf3() {
        assertThat(WeaponType.AXE.getStrengths(WeaponType.SPEAR)).isEqualTo(Pair.of(6, 2));
    }

    @Test
    public void isStrongerOrEquallyStrong_givenSpearsOverSwords_thenBonusOf3() {
        assertThat(WeaponType.SPEAR.getStrengths(WeaponType.SWORD)).isEqualTo(Pair.of(5, 2));
    }

    @Test
    public void isStrongerOrEquallyStrong_givenSwordsOverAxes_thenBonusOf3() {
        assertThat(WeaponType.SWORD.getStrengths(WeaponType.AXE)).isEqualTo(Pair.of(5, 3));
    }

    @Test
    public void isStrongerOrEquallyStrong_givenSwordsOverAxesButAttackee_thenNoBonus() {
        assertThat(WeaponType.AXE.getStrengths(WeaponType.SWORD)).isEqualTo(Pair.of(3, 2));
    }

}