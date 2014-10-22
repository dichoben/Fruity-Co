package com.si5.camash.fruityco.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 22/10/2014.
 */
public class DrawView extends View {
    Paint paint = new Paint();
    private List<Line> lines=new ArrayList<Line>();

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(15);
    }


    @Override
    public void onDraw(Canvas canvas) {
        for(Line line:lines){
            canvas.drawLine(line.x1, line.y1, line.x2, line.y2, paint);
        }

    }

    public void drawLine(float x1, float y1, float x2, float y2){
        lines.add(new Line(x1,y1,x2,y2));
        invalidate();
    }

    private class Line{
        public float x1;
        public float y1;
        public float x2;
        public float y2;

        private Line(float x1, float y1, float x2, float y2) {

            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

}