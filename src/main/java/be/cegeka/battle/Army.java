package be.cegeka.battle;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Army {

    private Set<Soldier> soldiers = new LinkedHashSet<>();

    public void enrollSoldier(Soldier soldier) {
        soldiers.add(soldier);
    }

    public boolean isEnrolled(Soldier soldier) {
        return soldiers.contains(soldier);
    }

    public Optional<Soldier> getFrontMan() {
        return soldiers.stream().findFirst();
    }
}
