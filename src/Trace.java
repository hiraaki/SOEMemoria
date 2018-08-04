import java.io.Serializable;
import java.util.ArrayList;

public class Trace implements Serializable {
    private String memoryAccess;
    private String operation;

    public Trace(String memoryAccess, String operation) {
        this.memoryAccess = memoryAccess;
        this.operation = operation;
    }

    public String getMemoryAccess() {
        return memoryAccess;
    }

    public void setMemoryAccess(String memoryAccess) {
        this.memoryAccess = memoryAccess;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return (this.memoryAccess+" "+this.operation);
    }
}
