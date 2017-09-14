package com.example.a0174.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 0174 on 2017/09/11.今後自機や敵、背景、エフェクト等他のタスクが増えたとしても、このGameMgrには
 * タスクは _taskList に追加していきます。
 * _taskList.add( new XXXX );
 * この部分だけ追加していけば後は何も変更しなくて良いです。
 */

public class GameMgr {

    private enum eStatus{//状態
        NORMAL,//普通
        GAMEOVER,//ゲームオーバー
        GAMECLEAR//ゲームクリア
    };

    private static final float PI = (float) Math.PI;
    private ArrayList<Barricade> _barrList = new ArrayList<Barricade>();//障害物リスト
    private LinkedList<Task> _taskList = new LinkedList<Task>(); // タスクリスト
    private eStatus _status = eStatus.NORMAL;//状態
    private Player _player;

    GameMgr(){
//        _barrList.add(new BarricadeSquare(  0,  0,480, 20, null));// 画面4隅に四角形を配置
//        _barrList.add(new BarricadeSquare(  0,  0, 20,800, null));// コンフィグを特に設定しない時はnullを渡すとデフォルト設定になる
//        _barrList.add(new BarricadeSquare(460,  0, 20,800, null));
//        _barrList.add(new BarricadeSquare(  0,780,480, 20, null));

        _barrList.add(new BarricadeSquare(0, 390, 480, 20, new BConf(+PI/180)));// 左側の回転するバー(時計回り)
        _barrList.add(new BarricadeSquare(0, 390, 480, 20, new BConf(-PI/180)));// 左側の回転するバー(反時計回り)

        _barrList.add(new BarricadeSquare(700, 1200, 480, 20, new BConf(+PI/180)));// 右側の回転するバー(時計回り)
        _barrList.add(new BarricadeSquare(700, 1200, 480, 20, new BConf(-PI/180)));// 右側の回転するバー(反時計回り)

        //              _barrList.add(new BarricadeSquare(0, 390, 480, 20, new BConf(+PI / 180)));// 中央に回転するバー(時計回り)
        //              _barrList.add(new BarricadeSquare(0, 390, 480, 20, new BConf(-PI / 180)));// 中央に回転するバー(反時計回り)
        _barrList.add(new BarricadeTriangle(240, 1400, 200, null));// 中央に回転する三角形(時計回り)
        for (Barricade bar : _barrList) {
            _taskList.add(bar);     //タスクリストに障害物を追加
        }

        _player = new Player();
        _taskList.add(_player);
        _taskList.add(new Player());
        _taskList.add(new FpsController());
    }

    private boolean Collision(){//衝突判定
        Vec vec = new Vec();
        final Circle cir = _player.getPt();//プレイヤーの中心円を取得
        for(Barricade barr : _barrList){//障害物の数だけループ
            Def.eHitCode code = barr.isHit(cir, vec);//接触判定
            switch(code){
                case OUT://接触したものが「アウト」なら
                    _status = eStatus.GAMEOVER;//アウト状態に
                    return true;
            }
        }
        return false;
    }

    public boolean onUpdate() {
        if( _status != eStatus.NORMAL ){ //ゲームの状態が通常でないなら計算しない
            return true;
        }
        if( Collision() ){ //衝突判定　衝突したならメソッドを抜ける
            return true;
        }
        for (int i=0; i<_taskList.size(); i++) {
            if (!_taskList.get(i).onUpdate()) { // 更新失敗なら
                _taskList.remove(i);                    // そのタスクを消す
                i--;
            }
        }
        return true;
    }

    private void drawStatus(Canvas c){//状態を表示する
        switch( _status ){
            case GAMEOVER:
                Paint paint = new Paint();
                paint.setTextSize(80);
                paint.setColor(Color.BLACK);
                c.drawText("GameOver", 40, 100, paint);
                break;
        }
    }

    public void onDraw(Canvas c){
        c.drawColor(Color.WHITE);      // 白で塗りつぶす
        for (Task task : _taskList){
            task.onDraw(c);  // 描画
        }
        drawStatus(c);//状態を表示する
    }
}
