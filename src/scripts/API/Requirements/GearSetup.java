package scripts.API.Requirements;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

public class GearSetup {
    private EquipmentSetup equipment;
    private InventorySetup inventory;

    public GearSetup(EquipmentSetup equipment, InventorySetup inventory) throws InventoryOverflowException{
        this.equipment = equipment;
        this.inventory = inventory;
        if(this.equipment.getInventoryUsed() + this.inventory.getInventory().size() > 28)
            throw new InventoryOverflowException("Combined equipment and inventory uses more than 28 slots");
    }

    public boolean gearCheck(){
        RSItem[] inventoryToCheck = Inventory.getAll();
        if(inventory.getNumEmpty() + inventoryToCheck.length + equipment.getInventoryUsed() > 28)//we don't have enough empty slots
            return false;
        return (equipment.equipmentCheck() && inventory.inventoryCheck());
    }
}
