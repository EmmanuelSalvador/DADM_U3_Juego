package mx.edu.ittepic.dadm_u3_juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

class Lienzo extends View {
    Imagen nave, boton;
    Alien alien1, alien2, alien3, asteroide1, asteroide2;
    Bala bala;
    BalaAlien balaAlien1, balaAlien2, balaAlien3;
    Context con;
    int i, randNum ;
    Random rand;
    public Lienzo(Context context) {
        super(context);
        con=context;
        nave=new Imagen(R.drawable.nave,500,1235,this);
        rand = new Random();
        randNum = rand.nextInt(950) + 1;
        if (randNum<=200){
            randNum=250;
        }
        alien1=new Alien(R.drawable.alien,randNum,200,this);
        randNum = rand.nextInt(950) + 1;
        if (randNum<=200){
            randNum=250;
        }
        alien2=new Alien(R.drawable.alien,randNum,100,this);
        randNum = rand.nextInt(950) + 1;
        if (randNum<=200){
            randNum=250;
        }
        alien3=new Alien(R.drawable.alien,randNum,500,this);
        bala = new Bala(R.drawable.bala,nave.getX()+35,1240,this,nave);
        balaAlien1 = new BalaAlien(R.drawable.bola,alien1.getX()+80,alien1.getY()+210,this, alien1);
        balaAlien2 = new BalaAlien(R.drawable.bola,alien2.getX()+80,alien2.getY()+210,this, alien2);
        balaAlien3 = new BalaAlien(R.drawable.bola,alien3.getX()+80,alien3.getY()+210,this, alien3);
        boton=new Imagen(300,200,800,400);
        randNum = rand.nextInt(950) + 1;
        if (randNum<=200){
            randNum=250;
        }
        asteroide1=new Alien(R.drawable.asteroide,randNum, -100,this);
        randNum = rand.nextInt(950) + 1;
        if (randNum<=200){
            randNum=250;
        }
        asteroide2=new Alien(R.drawable.asteroide,randNum, -100,this);
        alien1.mover(6);
        alien2.mover(4);
        alien3.mover(5);
        bala.mover(30);
        balaAlien1.mover(9);
        balaAlien2.mover(7);
        balaAlien3.mover(8);
        asteroide1.mover(4);
        asteroide2.mover(5);

    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        Paint p = new Paint();
        c.drawColor(Color.parseColor("#343e4f"));
        p.setColor(Color.WHITE);
        bala.pintar(c,p);
        nave.pintar(c,p);
        balaAlien1.pintar(c,p);
        balaAlien2.pintar(c,p);
        balaAlien3.pintar(c,p);
        alien1.pintar(c,p);
        alien2.pintar(c,p);
        alien3.pintar(c,p);
        if (alien1.colision(nave)==true||alien2.colision(nave)==true||alien3.colision(nave)==true ||balaAlien1.colision(nave)==true||balaAlien2.colision(nave)==true||balaAlien3 .colision(nave)==true){
            p.setTextSize(120);
            i=1;
            c.drawText("Game over", 250,100,p);
            boton.pintarCuadro(c,p);
            p.setColor(Color.BLACK);
            c.drawText("Reiniciar",325,325,p);
            alien1.timer.cancel();
            alien2.timer.cancel();
            alien3.timer.cancel();
            bala.timer.cancel();
            balaAlien1.timer.cancel();
            balaAlien2.timer.cancel();
            balaAlien3.timer.cancel();
            asteroide1.timer.cancel();
            asteroide2.timer.cancel();
        }
        if (bala.colision(alien1)==true){
            alien1.setY(-200);
            randNum=rand.nextInt(950) + 1;
            if (randNum<=200){
                randNum=250;
            }
            alien1.setX(randNum);
            bala.setY(1240);
            bala.setX(nave.getX()+35);
            bala.timer.cancel();
            bala.timer.start();
        }
        if(bala.colision(alien2)==true) {
            alien2.setY(-200);randNum=rand.nextInt(950) + 1;
            if (randNum<=200){
                randNum=250;
            }
            alien2.setX(randNum);
            bala.setY(1240);
            bala.setX(nave.getX()+35);
            bala.timer.cancel();
            bala.timer.start();
        }
        if (bala.colision(alien3)==true){
            alien3.setY(-200);randNum=rand.nextInt(950) + 1;
            if (randNum<=200){
                randNum=250;
            }
            alien3.setX(randNum);
            bala.setY(1240);
            bala.setX(nave.getX()+35);
            bala.timer.cancel();
            bala.timer.start();
        }
        asteroide1.pintar(c,p);
        asteroide2.pintar(c,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xp=event.getX();
        float yp=event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (boton.estaEnAreaCuadro(xp, yp)) {
                    Intent ventana = new Intent(con,MainActivity.class);
                    con.startActivity(ventana);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (i!=1) nave.mover(xp); bala.timer.cancel();bala.timer.start();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
