package be.cegeka.battle;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class Army {

    private Set<Soldier> soldiers = new LinkedHashSet<>();

    private IHeadquarters headquarters;

    public Army(IHeadquarters headquarters) {
        this.headquarters = headquarters;
    }

    public void enrollSoldier(Soldier soldier) {
        int id = headquarters.reportEnlistment(soldier.getName());
        soldier.setId(id);
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

        boolean isWinner = this.getFrontMan().isPresent();
        if(isWinner){
            headquarters.reportVictory(soldiers.size());
        } else {
            attackee.headquarters.reportVictory(attackee.soldiers.size());
        }

        return isWinner;
    }

    private void removeFrontman() {
        this.headquarters.reportCasualty(getFrontMan().get().getId());
        this.soldiers.remove(getFrontMan().get());
    }
}
