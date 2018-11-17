package scripts.API.Requirements;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class InventorySetup {
    private ArrayList<InventorySlot> inventory;
    private int numEmpty;

    public InventorySetup(int numEmpty, Supply...supplies) throws InventoryOverflowException{
        this.numEmpty = numEmpty;
        ArrayList<InventorySlot> inventory = new ArrayList<InventorySlot>();
        for(Supply supply : supplies){
            if(supply.isStackable()){
                inventory.add(new InventorySlot(supply));
            }
            else{
                for(int i = 0; i < supply.getAmount(); i++){
                    inventory.add(new InventorySlot(supply));
                }
            }
        }
        if(inventory.size() > 28)
            throw new InventoryOverflowException("Inventory can only hold 28 items");
        else
            this.inventory = inventory;
    }

    public boolean inventoryCheck(){
        RSItem[] inventoryToCheck = Inventory.getAll();
        if(numEmpty + inventoryToCheck.length > 28)//we don't have enough empty slots
            return false;
        for(InventorySlot slot : inventory){
            boolean found = false;
            for(int i = 0; i < inventoryToCheck.length; i++){
                if(inventoryToCheck[i] != null) {
                    if (slot.getName().equals(inventoryToCheck[i].getDefinition().getName())) {
                        if (inventoryToCheck[i].getStack() >= slot.getAmount()) {
                            inventoryToCheck[i] = null;//this inventory slot has satisfied a requirement and can no longer be used
                            found = true;
                            break;
                        }
                    }
                }
            }
            if(!found)//we didn't find the item in our inventory, return false
                return false;
        }
        return true;//we checked every item in the setup requirement
    }

    public ArrayList<InventorySlot> getInventory() {
        return inventory;
    }

    public int getNumEmpty() {
        return numEmpty;
    }
}
