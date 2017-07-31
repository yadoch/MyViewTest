package tw.com.abc.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View{
    private LinkedList<LinkedList<HashMap<String,Float>>> lines,recycle;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //建構式
        setBackgroundColor(Color.GREEN);
        // 比較Touch用
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("geoff","A");
            }
        });

        lines=new LinkedList<>();
        recycle=new LinkedList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // 設定筆
        Paint paint = new Paint();

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);

        for(LinkedList<HashMap<String,Float>> line: lines){
            for(int i=1; i<line.size();i++){
                HashMap<String,Float> p0=line.get(i-1);
                HashMap<String,Float> p1=line.get(i);
                canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
            }
        //paint.setColor(Color.RED);
        //paint.setStrokeWidth(4);
/*
        //畫圓
        canvas.drawCircle(100,100,30,paint);
        //畫線段
        canvas.drawLine(0,0,400,400,paint);
*/
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float ex = event.getX();
        float ey =event.getY();
/*
        if(event.getAction() == MotionEvent.ACTION_UP){
            Log.i("geoff","ACTION_UP X:"+ex+" Y:"+ey);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i("geoff","ACTION_MOVE X:"+ex+" Y:"+ey);
        }else if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("geoff","ACTION_DOWN X:"+ex+" Y:"+ey);
        }
*/
/*
有Bug
        if (event.getAction() == MotionEvent.ACTION_MOVE ||
                event.getAction() == MotionEvent.ACTION_DOWN){
                HashMap<String,Float> point = new HashMap<>();
                point.put("x", ex);
                point.put("y", ey);
                line.add(point);
                Log.i("geoff", ex + " x " + ey);
             }
             */
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LinkedList<HashMap<String,Float>> line = new LinkedList<>();
                HashMap<String,Float> point = new HashMap<>();
                point.put("x", ex);
                point.put("y", ey);
                line.add(point);
                lines.add(line);

                break;
            case MotionEvent.ACTION_MOVE:
                HashMap<String,Float> point2 = new HashMap<>();
                point2.put("x", ex);
                point2.put("y", ey);
                lines.getLast().add(point2);
                // Log.i("geoff", ex + " x " + ey);
                invalidate();
                break;
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void clear(){
        lines.clear();
        invalidate();
    }

    public void undo(){
        if (lines.size()>0) {
            recycle.add(lines.removeLast());
            invalidate();
        }
    }
    public void redo(){
        if (recycle.size()>0) {
            lines.add(recycle.removeLast());
            invalidate();
        }
    }

}
