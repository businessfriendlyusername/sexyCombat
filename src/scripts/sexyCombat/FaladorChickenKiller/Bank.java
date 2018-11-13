package scripts.sexyCombat.FaladorChickenKiller;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.API.Node;

public class Bank extends Node {


    @Override
    public void execute() {
        if (!Banking.isBankScreenOpen()){
            if(!Banking.openBank())//we were unable to open the bank
                return;
        }
        Banking.depositAll();
        General.sleep(30, 70);
        Banking.depositEquipment();
    }

    @Override
    public boolean validate() {
        return Banking.isInBank() && Inventory.isFull();
    }
}
