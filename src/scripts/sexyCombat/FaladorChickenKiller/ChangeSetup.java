package scripts.sexyCombat.FaladorChickenKiller;

import org.tribot.api.General;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Game;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Skills;
import scripts.API.Node;

public class ChangeSetup extends Node {




    private boolean levelDescrepancy(int descrepancy){
        int att = Skills.getActualLevel(Skills.SKILLS.ATTACK);
        int str = Skills.getActualLevel(Skills.SKILLS.STRENGTH);
        if(Math.abs(att - str) >= descrepancy)
            return true;
        else
            return false;
    }

    @Override
    public void execute() {

        if (Skills.getActualLevel(Skills.SKILLS.ATTACK) > Skills.getActualLevel(Skills.SKILLS.STRENGTH)) {
            GameTab.open(GameTab.TABS.COMBAT);
            Combat.selectAttackAction("Lunge");
        }
        else {
            GameTab.open(GameTab.TABS.COMBAT);
            Combat.selectAttackAction("Stab");
        }
        General.sleep(700, 1500);
    }

    @Override
    public boolean validate() {
        return levelDescrepancy(2);
    }
}
