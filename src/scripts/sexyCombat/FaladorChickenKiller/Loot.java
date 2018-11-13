package scripts.sexyCombat.FaladorChickenKiller;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSGroundItem;
import scripts.API.Node;

import java.util.function.BooleanSupplier;

public class Loot extends Node {

    @Override
    public void execute() {
        RSGroundItem[] feathers = GroundItems.find("Feather");
        if(feathers.length < 1)
            return;
        if(Inventory.getCount("Feather") < 650) {

            if (feathers.length < 1)
                return;
            for (RSGroundItem feather : feathers) {
                if (!DynamicClicking.clickRSGroundItem(feather, "Take")) {
                    Camera.turnToTile(feather);
                    DynamicClicking.clickRSGroundItem(feather, "Take");
                }
                int featherCount = Inventory.getCount("Feather");
                Timing.waitCondition(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return featherCount != Inventory.getCount("Feather");
                    }
                }, General.random(10000, 12000));
            }
        }
        else{
            RSGroundItem[] bones = GroundItems.find("Bones");
            if(bones.length < 1)
                return;
            for (RSGroundItem bone : bones) {
                if (!DynamicClicking.clickRSGroundItem(bone, "Take")) {
                    Camera.turnToTile(bone);
                    DynamicClicking.clickRSGroundItem(bone, "Take");
                }
                int featherCount = Inventory.getCount("Bones");
                Timing.waitCondition(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return featherCount != Inventory.getCount("Bones");
                    }
                }, General.random(10000, 12000));
            }
        }
    }

    @Override
    public boolean validate() {
        RSGroundItem[] feathers = GroundItems.find("Feather");
        return (feathers.length < 1 || Inventory.getCount("Feather") > 650 && !Inventory.isFull());
    }
}
