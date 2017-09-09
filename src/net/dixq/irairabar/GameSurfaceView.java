package net.dixq.irairabar;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
	Thread _thread;

	public GameSurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//解像度情報変更通知 
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		_thread = new Thread(this);		//別スレッドでメインループを作る
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		_thread = null;
	}

	@Override
	public void run() {
		while (_thread!=null) {	//メインループ
			onDraw(getHolder());
		}
	}

	private void onDraw(SurfaceHolder holder) {
		Canvas c = holder.lockCanvas();
		//ここにゲームの描画処理を書く
		holder.unlockCanvasAndPost(c);
	}
}

