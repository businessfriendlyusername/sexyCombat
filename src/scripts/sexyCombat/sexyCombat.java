package scripts.sexyCombat;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.script.Script;
import scripts.API.Node;
import scripts.sexyCombat.FaladorChickenKiller.ChangeSetup;
import scripts.sexyCombat.FaladorChickenKiller.MurderChickens;

import java.util.Collections;
import java.util.LinkedList;

public class sexyCombat extends Script {

    LinkedList<Node> nodes = new LinkedList<Node>();



    @Override
    public void run(){
        Collections.addAll(nodes, new MurderChickens("Bronze sword", "Wooden shield"), new ChangeSetup());
        while(true){
            for(Node node : nodes){
                if(node.validate())
                    node.execute();
            }
        }
    }
}
