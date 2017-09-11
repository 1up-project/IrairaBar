import android.graphics.Canvas;

/**
 * Created by 0174 on 2017/09/11.
 */

public abstract class Task {

    public boolean onUpdate() {
        return true;
    }
    
    public void onDraw(Canvas c) {
    }
}
