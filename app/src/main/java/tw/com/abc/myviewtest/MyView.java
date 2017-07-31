package tw.com.abc.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //建構式
        setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 設定筆
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);

        //畫圓
        canvas.drawCircle(100,100,30,paint);
        canvas.drawLine(0,0,400,400,paint);
    }
}
