package scripts.API.Requirements;

import com.sun.deploy.util.ArrayUtil;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EquipmentSetup {
    private ArrayList<EquipmentSlot> equipment = new ArrayList<EquipmentSlot>();

    public int getInventoryUsed(){
        int used = 0;
        ArrayList<EquipmentSlot.TYPE> slotsUsed = new ArrayList<EquipmentSlot.TYPE>();
        for(EquipmentSlot slot : equipment){
            if(slotsUsed.contains(slot.getType()))
                used++;
            else
                slotsUsed.add(slot.getType());
        }
        return used;
    }

    public ArrayList<EquipmentSlot> getEquipment() {
        return equipment;
    }

    public boolean equipmentCheck(){
        RSItem[] inventorySlots = Inventory.getAll();
        RSItem[] equipmentSlots = Equipment.getItems();

        for(EquipmentSlot slot : equipment){
            boolean found = false;
            for(int i = 0; i < inventorySlots.length; i++){
                if(inventorySlots[i] != null) {
                    if (slot.getName().equals(inventorySlots[i].getDefinition().getName())) {
                        if (inventorySlots[i].getStack() >= slot.getQuantity()) {
                            inventorySlots[i] = null;//this inventory slot has satisfied a requirement and can no longer be used
                            found = true;
                            break;
                        }
                    }
                }
            }

            for(int i = 0; i < equipmentSlots.length; i++){
                if(equipmentSlots[i] != null) {
                    if (slot.getName().equals(equipmentSlots[i].getDefinition().getName())) {
                        if (equipmentSlots[i].getStack() >= slot.getQuantity()) {
                            equipmentSlots[i] = null;//this inventory slot has satisfied a requirement and can no longer be used
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

    public EquipmentSetup(EquipmentSlot...slots){
        Collections.addAll(equipment, slots);
    }
}
