package fr.p8.m2ise.androidfacecamrecog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by nizar on 28/12/16.
 */

public class GrapheUtils extends View {
    int size;
    Paint paint;
    ArrayList<Integer> graphPoints;
    String time;

    public GrapheUtils(Context context, int maxSize) {
        super(context);

        this.size = maxSize;

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(50);
        graphPoints = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (graphPoints.isEmpty()) {
            canvas.drawText("rien à afficher", (float) 100, (float) 100, paint);
            return;
        }
        int maxValue = graphPoints.get(0);
        for (int i = 1; i < graphPoints.size(); ++i) {
            if (graphPoints.get(i) > maxValue) maxValue = graphPoints.get(i);
        }

        int width = 0;
        int height = graphPoints.get(0) * canvas.getHeight() / maxValue;
        Path path = new Path();
        path.moveTo(width, canvas.getHeight() - height);
        for (int i = 1; i < graphPoints.size(); ++i) {
            width = i * (canvas.getWidth() / (graphPoints.size() - 1));
            height = graphPoints.get(i) * canvas.getHeight() / maxValue;
            path.lineTo(width, canvas.getHeight() - height);
            canvas.drawText(this.time, width, height, paint);
        }

        canvas.drawPath(path, paint);
    }

    public void setTime(String time) {
        this.time = time;
    }

    void addGraphPoint(int pt) {
        if (graphPoints.size() == size) {
            graphPoints.remove(0);
        }
        graphPoints.add(pt);
    }

    void clear() {
        graphPoints.clear();
    }

}
