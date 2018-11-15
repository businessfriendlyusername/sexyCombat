package scripts.sexyCombat.FaladorChickenKiller;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import scripts.API.AntiBan;
import scripts.API.Node;

public class MurderChickens extends Node {

    public MurderChickens(String weapon, String shield){
        this.shield = shield;
        this.weapon = weapon;
    }

    private String weapon;

    private String shield;


    @Override
    public void execute(){
        GameTab.open(GameTab.TABS.INVENTORY);
        RSNPC[] chickens = NPCs.findNearest("Chicken");
        if(chickens.length < 1) {
            System.out.println("There are no chickens");
            return;
        }
        RSNPC fightingChicken = null;

        for(RSNPC chicken : chickens){
            if(chicken.isInteractingWithMe() && chicken.isInCombat())
                fightingChicken = chicken;
        }

        if(fightingChicken == null){
            RSNPC chickenToMurder = AntiBan.selectNextTarget(chickens);
            if(!DynamicClicking.clickRSNPC(chickenToMurder, "Attack")) {
                Camera.turnToTile(chickenToMurder);
                DynamicClicking.clickRSNPC(chickenToMurder, "Attack");
            }
            General.sleep(3500, 5000);
        }
        else{
            while(fightingChicken.isInCombat() && Player.getRSPlayer().isInCombat()){
                General.sleep(100, 150);
                if(fightingChicken.getAnimation() == 5389)
                    break;
                AntiBan.timedActions();
            }
        }
    }

    public boolean validate(){
        if(!Inventory.isFull() && Equipment.isEquipped(shield) && Equipment.isEquipped(weapon)){
            return true;
        }
        else {
            return false;
        }
    }
}//3015 3289
