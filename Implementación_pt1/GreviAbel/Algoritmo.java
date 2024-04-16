package GreviAbel;

public class Algoritmo {
    public static ListaXY bresenham(int xi, int yi, int xd, int yd){
        int dx = (xd - xi), dy = (yd - yi), p, inE, inNE, dirX = 1, dirY = 1; //en lugar de utilizar x, y, utilizaremos xi y yi
        ListaXY res = new ListaXY();
        boolean contrario = false;
                
        if(dx < 0){
            dx = -dx;
            dirX = -1;
        }
        if(dy < 0){
            dy = -dy;
            dirY = -1;
        }
        res.añadir(xi, yi);  //Lista
        if(dx < dy){
            contrario = true;
            int aux;
            aux = xi; xi = yi; yi = aux;
            xd = yd;
            aux = dirX; dirX = dirY; dirY = aux;
            aux = dx; dx = dy; dy = aux;
        }
        p = (2*dy) - dx;
        inE = 2*dy;
        inNE = 2*(dy-dx);
        while(xi != xd){
            xi = xi + dirX;
            if(p<0) p = p+inE;
            else{
                yi = yi + dirY;
                p = p + inNE;
            }
            if(contrario) res.añadir(yi, xi);
            else res.añadir(xi, yi);
        }
        return res;
    }
    
    
    public static ListaXY dda(int x0, int y0, int x1, int y1){
        ListaXY res = new ListaXY();
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        int step;
        
        step = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy);
        float xIncre = (float) dx/step;
        float yIncre = (float) dy/step;
        
        float x = x0;
        float y = y0;
        res.añadir( (int)x, (int)y );
        
        for(int i=0; i<step ; i++){
            res.añadir((int)x, (int)y);
            x += xIncre;
            y += yIncre;
        }
        return res;
    }
    
    public static ListaXY midPoint(int x0, int y0, int x1, int y1){
        ListaXY res = new ListaXY();        
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        int d = dy - (dx/2);
        int x = x0, y = y0;
        
        res.añadir(x, y);
        
        while(x < x1){
            x++;
            if(d<0) d = d + dy;
            else {
                d += (dy - dx);
                y++;
            }
            res.añadir(x, y);
        }
        return res;
    }
}