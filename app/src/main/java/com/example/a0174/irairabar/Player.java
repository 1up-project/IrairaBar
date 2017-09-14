package com.example.a0174.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by 0174 on 2017/09/14.
 */

public class Player extends Task {

    private final static float MAX_SPEED = 20;     //移動する最大スピード
    private final static float SIZE = 20;           //自機の大きさ
    private Circle _cir = null;             //自機の円
    private Paint _paint = new Paint();     //描画設定
    private Vec _vec = new Vec();           //自機の移動ベクトル
    private Vec _sensorVec = new Vec();     //センサーのベクトル

    public Player(){
        _cir = new Circle( 240, 0, SIZE );//(240,0)の位置にSIZEの大きさの円を作る
        _paint.setColor(Color.MAGENTA);      //色を赤に設定
        _paint.setAntiAlias(true);        //エイリアスをオン
//        _vec._y = 2;                      //移動ベクトルを下に向ける
    }

    // ベクトルをセットする
    private void setVec(){
        float x = -AcSensor.Inst().getX()*2;    //加速度センサーの情報を取得
        float y =  AcSensor.Inst().getY()*2;
        _sensorVec._x = x < 0 ? -x*x : x*x;     //2乗して変化を大袈裟にする
        _sensorVec._y = y < 0 ? -y*y : y*y;     //2乗すると+になるので、負ならマイナスを付ける
        _sensorVec.setLengthCap(MAX_SPEED);     //ベクトルの大きさが最大スピード以上にならないようにする
        _vec.blend( _sensorVec, 0.05f );        //センサーのベクトル方向に実際の移動ベクトルを5%近づける
    }

    // 移動ベクトルの向いている方に動かす
    private void Move(){
        _cir._x += _vec._x;     //移動ベクトル_vecが指す方向に移動させる
        _cir._y += _vec._y;
    }

    @Override
    public boolean onUpdate(){
        setVec();       //移動ベクトルをセットする
        Move();         //移動ベクトルが向いている方に動かす
        return true;
    }

    @Override
    public void onDraw( Canvas c ){
        c.drawCircle(_cir._x, _cir._y, _cir._r, _paint);
    }

}