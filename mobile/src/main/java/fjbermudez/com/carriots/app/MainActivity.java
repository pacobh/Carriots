package fjbermudez.com.carriots.app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fjbermudez.com.carriots.Injection;
import fjbermudez.com.carriots.R;

public class MainActivity extends AppCompatActivity implements TemperatureView{

    @BindView(R.id.etTermostate)
    EditText etTermostate;
    @BindView(R.id.tvRoom)
    TextView tvRoom;
    @BindView(R.id.tvDecimalsTermostate)
    TextView tvDecimalsTermostate;
    @BindView(R.id.ivReduceTemperature)
    ImageView ivReduceTemperature;
    @BindView(R.id.ivIncreaseTemperature)
    ImageView ivIncreaseTemperature;
    @BindView(R.id.rlHomeActivity)
    RelativeLayout rlHomeActivity;
    @BindView(R.id.rlCircularBackground)
    RelativeLayout rlCircularBackground;
    @BindView(R.id.arcSeekBar)
    ArcSeekBar arcSeekBar;
    @BindView(R.id.tvInitialMessage)
    TextView tvInitialMessage;

    @BindView(R.id.ivRefresh)
    ImageView ivRefresh;

    @BindView(R.id.btSendData)
    Button btSendData;

    @BindView(R.id.rlLoading)
    RelativeLayout rlLoading;


    @OnClick(R.id.ivReduceTemperature)
    void reduceTermostateTemperature() {
        hideKeyboard();
        mPresenter.reduceTemperature(etTermostate.getText().toString());
    }


    @OnClick(R.id.ivIncreaseTemperature)
    void increaseTermostateTemperature() {
        hideKeyboard();
        mPresenter.increaseTemperature(etTermostate.getText().toString());
    }

    @OnClick(R.id.btSendData)
    void sendData() { // Envío de datos
        mPresenter.changeTemperature(etTermostate.getText().toString());
    }

    private TemperaturePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new TemperaturePresenterImp(Injection.provideGetTemperatureUseCase(getApplicationContext()),
                Injection.provideSetTempreatureUseCase(getApplicationContext()),
                this,
                Injection.provideUseCaseHandler());

        initChangeListener();

    }

    private void initChangeListener() {
        etTermostate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mPresenter.validateTemperature(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void updateView(double tempConsigna, int tempRoomIntegerPart,int tempRoomDecimal, boolean boilerOn) {

        etTermostate.setText(String.valueOf(tempConsigna));
        tvInitialMessage.setVisibility(View.GONE);
        tvRoom.setText(tempRoomIntegerPart);
        tvDecimalsTermostate.setText(tempRoomDecimal);
        arcSeekBar.setProgress(tempRoomIntegerPart);
        ProgressBarAnimation animation = new ProgressBarAnimation(arcSeekBar, 0, tempRoomIntegerPart);
        animation.setDuration(1000);
        arcSeekBar.startAnimation(animation);
        startCountIntegerPartAnimation(tempRoomIntegerPart);
        startCountDecimalPartAnimation(tempRoomIntegerPart);
    }

    @Override
    public void showError(String error) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getString(R.string.error_title))
                .setMessage(error)
                .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void startCountIntegerPartAnimation(int number) {
        ValueAnimator animator = ValueAnimator.ofInt(0, number);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                tvRoom.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }

    private void startCountDecimalPartAnimation(int number) {
        ValueAnimator animator = ValueAnimator.ofInt(0, number);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                tvDecimalsTermostate.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
    @Override
    public void showSendButton() {
        btSendData.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeTempConsigna(String newTemperature) {

        etTermostate.setText(newTemperature);

    }

    @Override
    public void hideSendButton() {
        btSendData.setVisibility(View.GONE);

    }

    private void hideKeyboard() {

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etTermostate.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.getTemperature();
    }
}
