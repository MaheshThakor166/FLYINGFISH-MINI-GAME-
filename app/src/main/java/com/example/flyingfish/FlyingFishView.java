package com.example.flyingfish;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import  android.view.View;
import android.widget.Toast;

public class FlyingFishView  extends View  {
    private Bitmap fish[]= new Bitmap[2];
    private  int fishX=10;
    private  int fishY ;
    private  int fishSpeed ;
    private  int canvasHeight ,canvasWidth;

    private  boolean touch=false;
    private  Bitmap backgroundImage;

    private Paint scorepaint = new Paint();

    private  Bitmap life[]= new Bitmap[2];

    private  int yellowX,yellowY,yelllowSpeed=16;

    private  Paint yellowpaint =new Paint();

    private  int greenX,greenY,greenSpeed=20;

    private  Paint greenpaint = new Paint();


    private  int redX,redY,redSpeed=20;

    private  Paint redpaint = new Paint();


    private  int score,lifeCounterOffish;

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgroundImage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);
        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);
        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);
        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);
        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishY=550;
        score=0;
        lifeCounterOffish=3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight=canvas.getHeight();
        canvasWidth=canvas.getWidth();
        canvas.drawBitmap(backgroundImage,0,0,null);
           int minfishY=fish[0].getHeight();
           int maxfishY =canvasHeight-fish[0].getHeight()*3;
           fishY=fishY+fishSpeed;

           if(fishY<minfishY)
           {
               fishY=minfishY;
           }
        if(fishY>maxfishY)
        {
            fishY=maxfishY;
        }
        fishSpeed=fishSpeed+2;
        if(touch)
        {
             canvas.drawBitmap(fish[1],fishX,fishY,null);
             touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }

        yellowX=yellowX-yelllowSpeed;
        if(hitchecker(yellowX,yellowY))
        {
            score =score +10;
            yellowX=-100;
        }
        if(yellowX<0)
        {
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random() * (maxfishY-minfishY))+ minfishY;
        }
        canvas.drawCircle(yellowX,yellowY,25,yellowpaint);
        greenX=greenX-greenSpeed;
        if(hitchecker(greenX,greenY))
        {
            score =score + 20;
            greenX=-100;
        }
        if(greenX<0)
        {
            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math.random() * (maxfishY-minfishY))+ minfishY;
        }
        canvas.drawCircle(greenX,greenY,25,greenpaint);

        redX=redX-redSpeed;
        if(hitchecker(redX,redY))
        {
            score =score + 20;
            redX=-100;
            lifeCounterOffish--;

            if(lifeCounterOffish==0)
            {
                Toast.makeText(getContext(), "GameOver", Toast.LENGTH_SHORT).show();

                Intent gameOverIntent = new Intent(getContext(),GameOver.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score",score);
                getContext().startActivity(gameOverIntent);
            }
        }
        if(redX<0)
        {
            redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random() * (maxfishY-minfishY))+ minfishY;
        }

        canvas.drawCircle(redX,redY,30,redpaint);
        canvas.drawText("Score: "+score ,20,60,scorepaint);

        for(int i=0;i<3;i++)
        {
            int x=(int) (580 + life[0].getWidth()  *1.5 * i);
            int y=30;

            if(i<lifeCounterOffish)
            {
                canvas.drawBitmap(life[0],580,10,null);
                canvas.drawBitmap(life[0],580,10,null);
            }
            else {
                canvas.drawBitmap(life[1],680,10,null);
            }
        }




    }
     public boolean hitchecker(int x ,int y)
     {
        if(fishX<x && x<(fishX+fish[0].getWidth()) && fishY<y && y<(fishY+fish[0].getWidth()))
        {
          return true;
        }
        return  false;

     }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishSpeed=-22;
        }
        return  true;
    }
}
