package com.example.a0174.irairabar;

/**
 * Created by 0174 on 2017/09/14.
 */

public class BConf {
    public float speed = (float)Math.PI/180;
    public Barricade.eType type = Barricade.eType.OUT;

    public BConf(Barricade.eType atype){
        type = atype;
    }

    public BConf(float aspeed){
        speed = aspeed;
    }

}
