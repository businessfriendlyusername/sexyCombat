package scripts.API.Requirements;

import org.tribot.api2007.Inventory;

public class InventorySlot {//slots for inventory requirements
    private String name = null;
    private int amount = 1;

    public InventorySlot(String name, int amount) {
        this.name = name;
        this.amount = amount;

    }

    public InventorySlot(Supply supply) {
        this.name = supply.getName();
        if (supply.isStackable())
            this.amount = supply.getAmount();
        else
            this.amount = 1;

    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
