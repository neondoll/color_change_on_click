package com.example.colorchangeonclick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    int count = 0;

    DrawThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    public void setNewColor() {
        count++;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    switch (count % 3) {
                        case 0:
                            canvas.drawColor(Color.RED);
                            break;
                        case 1:
                            canvas.drawColor(Color.GREEN);
                            break;
                        case 2:
                            canvas.drawColor(Color.BLUE);
                            break;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}