package scripts.API.Requirements;

public class EquipmentSlot {
    private boolean empty = false;
    private String name = null;
    private TYPE type;
    private int quantity = 1;

    public enum TYPE{
        AMMO,
        BODY,
        BOOT,
        CAPE,
        GLOVE,
        HEAD,
        LEG,
        NECK,
        RING,
        SHIELD,
        WEAPON;
    }
    public EquipmentSlot(TYPE type){//initialize empty slot requirement
        this.type = type;
    }

    public EquipmentSlot(TYPE type, String name) throws AmmoException{
        if(type == TYPE.AMMO)
            throw new AmmoException(name + " Ammunition slot requirement must be initialized with an ammo quantity");
        this.empty = false;
        this.name = name;
    }

    public EquipmentSlot(TYPE type, String name, int quantity) throws AmmoException{
        if(type != TYPE.AMMO && type !=TYPE.WEAPON)
            throw new AmmoException(name + " Only ammo and weapon slots can be initialized with a quantity");
        this.type = type;
        this.quantity = quantity;
        this.name = name;
    }

    public boolean isEmpty() {
        return empty;
    }

    public String getName() {
        return name;
    }

    public TYPE getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
