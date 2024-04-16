package GreviAbel;

public class ListaXY{
    private int datoX;
    private int datoY;
    private ListaXY sig;
    public ListaXY(){
        datoX = 0;
        datoY = 0;
        sig = null;
    }
    public boolean esVacio(){
        return sig == null;
    }
    
    public void añadir(int datoX, int datoY){
        if(esVacio()){
            this.datoX = datoX;
            this.datoY = datoY;
            this.sig = new ListaXY();
        }else{
            this.sig.añadir(datoX, datoY);
        }
    }
    
    public int tamaño(){
        int res=0;
        for(ListaXY i=this; !i.esVacio(); i=i.sig){
            res++;
        }
        return res;
    }
    
    public void eliminar(int i){
        if(esVacio()) return;
        if(i == 0){
            this.datoX = sig.datoX;
            this.datoY = sig.datoY;
            this.sig = sig.sig;
        }else{
            sig.eliminar(i-1);
        }
    }
    
    public int[] dar(int i){
        if(esVacio()) return null; //throw new RuntimeException("Lista Vacía");
        if(i == 0){
            int[] datos = {datoX, datoY};
            return datos;
        }else{
            return sig.dar(i-1);
        }
    }
    
    @Override
    public String toString(){
        if(esVacio()){
            return "";
        }else{
            return "(" + datoX +", " + datoY + "), " + sig.toString(); 
        }
    }
}