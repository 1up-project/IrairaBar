package net.dixq.irairabar;

/**
 * Created by 0174 on 2017/09/09.
 */

public class Vec {
    public float _x, _y;

    Vec(){
        _x = _y = 0;
    }

    Vec( float x, float y ){
        _x = x;
        _y = y;
    }

    //角度を取得する
    float getAngle(){
        return (float)Math.atan2(_y, _x);
    }

    //大きさを取得する
    float getLength(){
        return (float)Math.sqrt( _x*_x + _y*_y );
    }
}
