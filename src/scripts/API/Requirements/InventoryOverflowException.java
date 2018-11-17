package scripts.API.Requirements;

public class InventoryOverflowException extends Exception{
    public InventoryOverflowException(String message){
        super(message);
    }
}
