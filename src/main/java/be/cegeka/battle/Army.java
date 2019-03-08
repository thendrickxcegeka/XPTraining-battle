package be.cegeka.battle;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

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

    public boolean attack(Army attackee) {
        checkArgument(!soldiers.isEmpty(), "Attacker army cannot be empty");

        while (this.getFrontMan().isPresent() && attackee.getFrontMan().isPresent()){
            Soldier attackerSoldier = this.getFrontMan().get();
            Soldier attackeeSoldier = attackee.getFrontMan().get();

            boolean attackerWins = attackerSoldier.attack(attackeeSoldier);
            if(attackerWins){
                attackee.removeFrontman();
            } else {
                this.removeFrontman();
            }
        }

        return this.getFrontMan().isPresent();
    }

    private void removeFrontman() {
        this.soldiers.remove(getFrontMan().get());
    }
}
