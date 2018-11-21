package mx.edu.ittepic.dadm_u3_juego;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Imagen {
    private Bitmap icono;
    private float x,y, izquierda, arriba, derecha, abajo;
    private boolean visible;
    public Imagen (int resource, float _x, float _y, Lienzo l){
        icono = BitmapFactory.decodeResource(l.getResources(),resource);

        x=_x;
        y=_y;
        visible=true;
    }
    public Imagen (float left , float top, float right, float bottom){
        izquierda=left;
        arriba=top;
        derecha=right;
        abajo=bottom;
        visible=true;
    }
    public void pintar(Canvas c, Paint p) {
        if (visible)c.drawBitmap(icono,x,y,p);
    }
    public void pintarCuadro (Canvas c,Paint p){
        if (visible) c.drawRect(izquierda,arriba,derecha,abajo,p);
    }
    public boolean estaEnArea(float xp, float yp) {
        if (!visible) return false;
        float x2,y2;
        x2=x+icono.getWidth();
        y2=y+icono.getHeight();

        if (xp>=x && xp<=x2){
            if (yp>=y && yp<=y2){
                return true;
            }
        }
        return false;
    }
    public boolean estaEnAreaCuadro(float xp, float yp) {
        if (!visible) return false;
        if (xp>=izquierda && xp<=derecha){
            if (yp>=arriba && yp<=abajo){
                return true;
            }
        }
        return false;
    }
    public void mover(float xp){
        x=xp-icono.getHeight()/2;
    }
    public  void hacerVisible (boolean v){
        visible=v;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
