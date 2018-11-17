package scripts.API.Requirements;

public class Supply {
    private String name;
    private int amount;
    private boolean stackable = false;

    public Supply(String name, int amount) throws InventoryOverflowException{
        if(amount > 28)
            throw new InventoryOverflowException("Amount of " + name + "greater than 28 must be flagged stackable");
        else {
            this.name = name;
            this.amount = amount;
        }
    }

    Supply(String name, int amount, boolean stackable) throws InventoryOverflowException{
        if(amount > 28 && !stackable)
            throw new InventoryOverflowException("Amount of " + name + "greater than 28 must be flagged stackable");
        else {
            this.name = name;
            this.amount = amount;
            this.stackable = stackable;
        }
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isStackable() {
        return stackable;
    }
}
