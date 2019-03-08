package be.cegeka.battle;

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
}