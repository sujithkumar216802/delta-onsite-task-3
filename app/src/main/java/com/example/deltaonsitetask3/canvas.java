package com.example.deltaonsitetask3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class canvas extends View {

    static Paint red, blue, green;

    static Float red1;
    static Float red2;
    static Float green1;
    static Float green2;
    static Float blue1;
    static Float blue2;
    static Float width;
    static Integer active = 0;


    /*

    guide for active
    none = 0
    red1==11
    red2==12
    blue1==21
    blue2==22
    green1==31
    green2==32*/


    static List<Integer> pos, posxvalue;

    static Boolean initiate;

    static endviewmodel res;


    public canvas(Context context) {
        super(context);
        res = new ViewModelProvider((ViewModelStoreOwner) context).get(endviewmodel.class);
        res.getEnd().setValue(false);
        res.getStart().setValue(false);
        initiate = true;
        posxvalue = new ArrayList<>();
        pos = new ArrayList<>();
        pos.add(11);
        pos.add(12);
        pos.add(21);
        pos.add(22);
        pos.add(31);
        pos.add(32);


        red = new Paint();
        red.setStrokeWidth(16);
        red.setColor(Color.RED);

        blue = new Paint();
        blue.setStrokeWidth(16);
        blue.setColor(Color.BLUE);

        green = new Paint();
        green.setStrokeWidth(16);
        green.setColor(Color.GREEN);


        while (((pos.get(0) / 10) == (pos.get(1) / 10)) && ((pos.get(2) / 10) == (pos.get(3) / 10)) && ((pos.get(4) / 10) == (pos.get(5) / 10)))
            Collections.shuffle(pos, new Random());

    }

    public canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = (float) getWidth() / 7;

        if (initiate) {
            for (int i = 0; i < 6; i++) {
                switch (pos.get(i)) {
                    case 11:
                        red1 = (i + 1) * width;
                        break;
                    case 12:
                        red2 = (i + 1) * width;
                        break;
                    case 21:
                        blue1 = (i + 1) * width;
                        break;
                    case 22:
                        blue2 = (i + 1) * width;
                        break;
                    case 31:
                        green1 = (i + 1) * width;
                        break;
                    case 32:
                        green2 = (i + 1) * width;
                        break;
                }

            }
            initiate = false;
        }


        canvas.drawLine(red1, 12, red1, getHeight() - 12, red);
        canvas.drawLine(red2, 12, red2, getHeight() - 12, red);

        canvas.drawLine(blue1, 12, blue1, getHeight() - 12, blue);
        canvas.drawLine(blue2, 12, blue2, getHeight() - 12, blue);

        canvas.drawLine(green1, 12, green1, getHeight() - 12, green);
        canvas.drawLine(green2, 12, green2, getHeight() - 12, green);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!res.getEnd().getValue()) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN: {

                    if (res.getStart().getValue() == false)
                        res.getStart().setValue(true);

                    if (Math.abs(red1 - event.getX()) < 40) {
                        active = 11;
                        return true;
                    } else if (Math.abs(red2 - event.getX()) < 40) {
                        active = 12;
                        return true;
                    } else if (Math.abs(blue1 - event.getX()) < 40) {
                        active = 21;
                        return true;
                    } else if (Math.abs(blue2 - event.getX()) < 40) {
                        active = 22;
                        return true;
                    } else if (Math.abs(green1 - event.getX()) < 40) {
                        active = 31;
                        return true;
                    } else if (Math.abs(green2 - event.getX()) < 40) {
                        active = 32;
                        return true;
                    } else {
                        active = 0;
                        return false;
                    }


                }
                case MotionEvent.ACTION_MOVE: {


                    switch (active) {
                        case 11:
                            red1 = event.getX();
                            break;
                        case 12:
                            red2 = event.getX();
                            break;
                        case 21:
                            blue1 = event.getX();
                            break;
                        case 22:
                            blue2 = event.getX();
                            break;
                        case 31:
                            green1 = event.getX();
                            break;
                        case 32:
                            green2 = event.getX();
                            break;
                    }

                    postInvalidate();
                    return active != 0;

                }
                case MotionEvent.ACTION_UP: {
                    active = 0;
                }

            }

            postInvalidate();
            check();

        }

        return super.onTouchEvent(event);
    }


    private void check() {

        ArrayList<Float> temp = new ArrayList<>();
        temp.add(red1);
        temp.add(red2);
        temp.add(blue1);
        temp.add(blue2);
        temp.add(green1);
        temp.add(green2);

        Collections.sort(temp);

        if (((red1.equals(temp.get(0)) && red2.equals(temp.get(1))) || (red1.equals(temp.get(2)) && red2.equals(temp.get(3))) || (red1.equals(temp.get(4)) && red2.equals(temp.get(5))) || (red2.equals(temp.get(0)) && red1.equals(temp.get(1))) || (red2.equals(temp.get(2)) && red1.equals(temp.get(3))) || (red2.equals(temp.get(4)) && red1.equals(temp.get(5)))) && ((blue1.equals(temp.get(0)) && blue2.equals(temp.get(1))) || (blue1.equals(temp.get(2)) && blue2.equals(temp.get(3))) || (blue1.equals(temp.get(4)) && blue2.equals(temp.get(5))) || (blue2.equals(temp.get(0)) && blue1.equals(temp.get(1))) || (blue2.equals(temp.get(2)) && blue1.equals(temp.get(3))) || (blue2.equals(temp.get(4)) && blue1.equals(temp.get(5)))))
            res.getEnd().setValue(true);


    }


}
