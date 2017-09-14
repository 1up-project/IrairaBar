package com.example.a0174.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.LinkedList;

/**
 * Created by 0174 on 2017/09/11.今後自機や敵、背景、エフェクト等他のタスクが増えたとしても、このGameMgrには
 * タスクは _taskList に追加していきます。
 * _taskList.add( new XXXX );
 * この部分だけ追加していけば後は何も変更しなくて良いです。
 */

public class GameMgr {

    private LinkedList<Task> _taskList = new LinkedList<Task>(); // タスクリスト

    GameMgr(){
        _taskList.add(new Player());
        _taskList.add(new FpsController());
    }

    public boolean onUpdate() {
        for (int i=0; i<_taskList.size(); i++) {
            if (!_taskList.get(i).onUpdate()) { // 更新失敗なら
                _taskList.remove(i);                    // そのタスクを消す
                i--;
            }
        }
        return true;
    }

    public void onDraw(Canvas c){
        c.drawColor(Color.WHITE);      // 白で塗りつぶす
        for (int i=0; i<_taskList.size(); i++){
            _taskList.get(i).onDraw(c);  // 描画
        }
    }
}
