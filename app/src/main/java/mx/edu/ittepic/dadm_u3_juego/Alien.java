package mx.edu.ittepic.dadm_u3_juego;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

public class Alien {
    private Bitmap icono;
    private float x, y;
    private boolean visible, coli;
    int desplazamiento;
    CountDownTimer timer;
    public Alien(int resource, float _x, float _y, final Lienzo l) {
        icono = BitmapFactory.decodeResource(l.getResources(), resource);
        x = _x;
        y = _y;
        visible = true;
        timer=new CountDownTimer(1000,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                y+=desplazamiento;
                if (y>=l.getHeight()){
                    y=-200;


                }
                if (coli==true){
                    timer.cancel();
                }
                l.invalidate();
            }

            @Override
            public void onFinish() {
                start();
            }
        };
    }

    public void pintar(Canvas c, Paint p) {
        if (visible) c.drawBitmap(icono, x, y, p);
    }

    public boolean estaEnArea(float xp, float yp) {
        if (!visible) return false;
        float x2, y2;
        x2 = x + icono.getWidth();
        y2 = y + icono.getHeight();

        if (xp >= x && xp <= x2) {
            if (yp >= y && yp <= y2) {
                return true;
            }
        }
        return false;
    }
    public void mover (int desplaza){
        desplazamiento=desplaza;
        timer.start();
    }
    public boolean colision (Imagen objetob){
        float x2=x+icono.getWidth();
        float y2=y+icono.getHeight();
        if (objetob.estaEnArea(x2,y)){
            coli=true;
            return true;
        }
        if (objetob.estaEnArea(x,y)){
            coli=true;
            return true;
        }
        if (objetob.estaEnArea(x2,y2)){
            coli=true;
            return true;
        }
        if (objetob.estaEnArea(x,y2)){
            coli=true;
            return true;
        }
        coli=false;
        return false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }
}