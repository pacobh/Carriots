package fjbermudez.com.carriots.app;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

import com.marcinmoskala.arcseekbar.ArcSeekBar;

/**
 * Created by franciscojose.bermud on 23/07/2018.
 */


public class ProgressBarAnimation extends Animation {

    private ArcSeekBar arcSeekBar;
    private float from;
    private float  to;

    public ProgressBarAnimation(ArcSeekBar arcSeekBar, float from, float to) {
        super();
        this.arcSeekBar = arcSeekBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        arcSeekBar.setProgress((int) value);
    }

}


