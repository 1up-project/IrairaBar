package com.example.a0174.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by 0174 on 2017/09/11.
 * onUpdateとonDrawを継承して使いました。
 * 文字の描画には描画設定を記憶した_paintを使用します。
 * 実際に描画しているのはdrawText部です。
 */

public class FpsController extends Task{

    private long _startTime = 0;                 // 測定開始時刻
    private int _cnt = 0;                         // カウンタ
    private Paint _paint = new Paint();           // paint情報
    private float _fps;                           // fps
    private final static int N = 60;             // 平均をとるサンプル数
    private final static int FONT_SIZE = 20; // フォントサイズ

    public FpsController(){
        _paint.setColor(Color.BLUE);     // フォントの色を青に設定
        _paint.setTextSize(FONT_SIZE);  // フォントのサイズを設定
    }

    @Override
    public boolean onUpdate(){
        if (_cnt == 0){ // 1フレーム目なら時刻を記録
            _startTime = System.currentTimeMillis();
        }
        if (_cnt == N){ // 60フレーム目なら平均を計算する
            long t =System.currentTimeMillis();
            _fps = 1000.f/((t-_startTime)/(float)N);
            _cnt = 0;
            _startTime = t;
        }
        _cnt++;
        return  true;
    }

    @Override
    public void onDraw(Canvas c){
        c.drawText(String.format("%.1f", _fps), 0, FONT_SIZE-2, _paint);
    }
}
