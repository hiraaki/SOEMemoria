import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;

public class Trace implements Serializable {
    private String memoryAccess;
    private String operation;

    public Trace(String memoryAccess, String operation) {
        //parse de string de hexadecimal para binário

        String string = new BigInteger(memoryAccess, 16).toString(2);
        //Preenchendo zeros ignorados
        while (string.length()<32)
            string="0"+string;

        this.memoryAccess =string ;
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
