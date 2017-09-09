package net.dixq.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by 0174 on 2017/09/09.
 */

public class Player extends Task {
    private final static float SIZE = 20;           //自機の大きさ
    private Circle _cir = null;             //自機の円
    private Paint _paint = new Paint();     //描画設定
    private Vec _vec = new Vec();           //自機の移動ベクトル

    public Player(){
        _cir = new Circle( 240, 0, SIZE );//(240,0)の位置にSIZEの大きさの円を作る
        _paint.setColor(Color.BLUE);      //色を青に設定
        _paint.setAntiAlias(true);        //エイリアスをオン
        _vec._y = 2;                      //移動ベクトルを下に向ける
    }

    @Override
    public boolean onUpdate(){
        _cir._x += _vec._x;     //移動ベクトル_vecが指す方向に移動させる
        _cir._y += _vec._y;
        return true;
    }

    @Override
    public void onDraw( Canvas c ){
        c.drawCircle(_cir._x, _cir._y, _cir._r, _paint);
    }
}
