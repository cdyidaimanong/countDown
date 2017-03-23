package com.mah.countdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mah.countdown.databinding.CountDownBinding;


/**
 * Created by admin on 2017/3/23, 023.
 */

public class CountDown extends FrameLayout {
    private CountDownBinding mBinding;
    private int hour, minute, seconds;
    private Runnable mRunnable;

    public CountDown(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CountDown(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountDown);
        hour = array.getInt(R.styleable.CountDown_hour, 0);
        minute = array.getInt(R.styleable.CountDown_minute, 0);
        seconds = array.getInt(R.styleable.CountDown_seconds, 0);
        array.recycle();
        init(context);
    }

    private void init(Context context) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.count_down, this, true);
        if (mRunnable == null) {
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        int c=  downCount(mBinding.tvSecondsLeft,mBinding.tvSecondsRight);
                         if(c==0){
                            int m = downCount(mBinding.tvMinuteLeft,mBinding.tvMinuteRight);
                             if(m==0){
                                downCountHour(mBinding.tvHourLeft,mBinding.tvHourRight);
                            }
                        }
                        postDelayed(mRunnable,1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            };
        }
    }

    private int downCount(TextView tvl,TextView tvr) {
        int flag = 1;
        int left = Integer.parseInt(tvl.getText().toString());
        int right = Integer.parseInt(tvr.getText().toString());
        right--;
        if(left==0&&right<0){
            flag=0;
        }
        if (right < 0) {
            tvr.setText(String.valueOf(9));
            left--;
            if (left < 0) {
                tvl.setText(String.valueOf(5));
            } else {
                tvl.setText(String.valueOf(left));
            }
        } else {
            tvr.setText(String.valueOf(right));
        }
        return  flag;
    }

    private void downCountHour(TextView tvl,TextView tvr) {
        int left = Integer.parseInt(tvl.getText().toString());
        int right = Integer.parseInt(tvr.getText().toString());
        right--;
        if (right < 0) {
            tvr.setText(String.valueOf(9));
            left--;
            if (left < 0) {
                tvl.setText(String.valueOf(0));
            } else {
                tvl.setText(String.valueOf(left));
            }
        } else {
            tvr.setText(String.valueOf(right));
        }
    }
    public void setHour(int mhour) {
        this.hour = mhour;
        if (hour > 9) {
            String s = Float.toString(hour / 10);
            mBinding.tvHourLeft.setText(s.substring(0, s.lastIndexOf(".")));
            mBinding.tvHourRight.setText(String.valueOf(hour % 10));
        } else {
            mBinding.tvHourLeft.setText(String.valueOf(0));
            mBinding.tvHourRight.setText(String.valueOf(hour));
        }
    }

    public void setMinute(int mminute) {
        this.minute = mminute;
        if (minute > 9) {
            String s = Float.toString(minute / 10);
            mBinding.tvMinuteLeft.setText(s.substring(0, s.lastIndexOf(".")));
            mBinding.tvMinuteRight.setText(String.valueOf(minute % 10));
        } else {
            mBinding.tvMinuteLeft.setText(String.valueOf(0));
            mBinding.tvMinuteRight.setText(String.valueOf(minute));
        }
    }

    public void setSeconds(int mseconds) {
        this.seconds = mseconds;
        if (seconds > 9) {
            String s = Float.toString(seconds / 10);
            mBinding.tvSecondsLeft.setText(s.substring(0, s.lastIndexOf(".")));
            mBinding.tvSecondsRight.setText(String.valueOf(seconds % 10));
        } else {
            mBinding.tvSecondsLeft.setText(String.valueOf(0));
            mBinding.tvSecondsRight.setText(String.valueOf(seconds));
        }
    }

    public void start() {
        post(mRunnable);
    }

    public void clear() {
        if (mRunnable != null) {
            mRunnable = null;
        }
    }
}
