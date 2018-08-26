package com.jrr.user.myfragment;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by user on 2018/8/20.
 */

public class ImageViewOnMultiTouchListener implements View.OnTouchListener{
    private final int NONE = 0;
    private final int MOVE = 1;
    private final int ZOOM = 2;
    private final int DRAG = 3;

    private int mode = NONE;
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDistance = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView view = (ImageView) v;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                matrix.set(view.getImageMatrix());
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());

                mode = DRAG;

                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDistance = spacing(event);
                if (oldDistance > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);

                    mode = ZOOM;
                }

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;

                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDistance;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }

                break;
        }

        view.setImageMatrix(matrix);
        view.setScaleType(ImageView.ScaleType.MATRIX);
        view.setPadding(3, 5, 3, 5);

        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);

        point.set(x / 2, y / 2);
    }
}
