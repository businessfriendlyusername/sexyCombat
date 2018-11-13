package scripts.sexyCombat.FaladorChickenKiller;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.API.BInventory;
import scripts.API.Node;

import java.util.function.BooleanSupplier;

public class WalkToBank extends Node {

    @Override
    public void execute() {
        WebWalking.walkToBank(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.get(595, 37) != null;//if we accidentaly open the world map
            }
        }, 500);
        if(Interfaces.get(595, 37) != null)
            Interfaces.get(595, 37).click("Close");
    }

    @Override
    public boolean validate() {
        return Inventory.isFull() && !Banking.isInBank();
    }
}
