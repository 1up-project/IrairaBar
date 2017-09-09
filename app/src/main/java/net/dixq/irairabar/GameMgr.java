package net.dixq.irairabar;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.LinkedList;

/**
 * Created by 0174 on 2017/09/09.
 */

public class GameMgr {
    private LinkedList<Task> _taskList = new LinkedList<Task>(); // タスクリスト
    GameMgr(){
        _taskList.add( new Player() );
        _taskList.add(new FpsController());
    }

    public boolean onUpdate(){
        for (int i = 0; i< _taskList.size(); i++){
            if (_taskList.get(i).onUpdate() == false){
                _taskList.remove(i);
                i--;
            }
        }
        return true;
    }

    public void onDraw(Canvas c){
        c.drawColor(Color.WHITE);    // 白で塗りつぶす
        for (int i = 0; i<_taskList.size(); i++){
            _taskList.get(i).onDraw(c); // 描画
        }
    }
}
