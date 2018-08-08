import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fifo {
    ArrayList<Trace> list;
    int nFalhadePagina;

    public Fifo(ArrayList<Trace> list) {
        this.list = list;
        this.nFalhadePagina = 0;
    }
    public void executar(int nFrames){

        ArrayList<String> framesMemoria = new ArrayList<>();
        for(Trace trace: list){
            if(!framesMemoria.contains(trace.getPage())){
                if(framesMemoria.size()<nFrames){
                    framesMemoria.add(trace.getPage());
                }else {
                    framesMemoria.remove(0);
                    framesMemoria.add(trace.getPage());
                    this.nFalhadePagina++;
                }
            }
        }
    }
}
