package com.example.a0174.irairabar;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 0174 on 2017/09/11.
 * 【memo】
 * ●SurfaceHolder.Callback
 * これは、サーフェスが生成された、破棄された等のイベントをコールバックしてくれるインターフェイスです。
 * 上で実装している void surfaceCreated() で生成されたことが、
 * void surfaceDestroyed() で破棄されたこと等が分かるようになっています。
 * ●Runnable
 * これは他の章でも解説している通り、別スレッドで処理をする為に必要なインターフェイスです。
 * 実際に別スレッドで処理をする内容はvoid run() に記載することになります。
 */

class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    GameMgr _gameMgr = new GameMgr();
    Thread _thread;

    public GameSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 解像度情報変更通知
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread = new Thread(this);      // 別スレッドでメインループをつくる
        _thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        _thread = null;
    }

    @Override
    public void run() {
        while (_thread != null) { // main roop
            _gameMgr.onUpdate();
            onDraw(getHolder());
        }
    }

    private void onDraw (SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        if (c == null){
            return;
        }
        // ここにゲームの描画処理を書いていく
        _gameMgr.onDraw(c);
        holder.unlockCanvasAndPost(c);
    }
}
