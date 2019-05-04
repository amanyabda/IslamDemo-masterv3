package com.hassan.islamicdemo.PrayersService;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.AlarmManagerCompat;
import android.widget.Toast;

import com.hassan.islamicdemo.Base.App;
import com.hassan.islamicdemo.Home.PrayerTime;

import java.util.Calendar;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    boolean isRepeat = true;


    @Override
    public void onReceive(Context context, Intent intent) {
        Long id = Long.parseLong(intent.getStringExtra("id"));
        PrayerTime time = ((App) context.getApplicationContext()).getDaoSession().getPrayerTimeDao().load(id);

        Intent i = new Intent("my_app_alarm_receiver");
        Calendar calendar = Calendar.getInstance();
        intent.putExtra("id", time.getId().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(time.getId().toString()), i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long startupTime = calendar.getTimeInMillis();
        startupTime = startupTime + 24 * 60 * 60 * 1000;


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (alarmManager != null) {
//                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
//                        startupTime, pendingIntent);
//            }
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (alarmManager != null) {
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                        startupTime, pendingIntent);
//            }
//        } else {
//
//            if (alarmManager != null) {
//
//                alarmManager.set(AlarmManager.RTC_WAKEUP,
//                        startupTime, pendingIntent);
//            }
//
//
//        }

        if (alarmManager != null) {


            if (!isRepeat) {

                setExactAndAllowWhileIdle(alarmManager,AlarmManager.RTC_WAKEUP,
                        startupTime, pendingIntent);

            } else {

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startupTime, startupTime, pendingIntent);

            }

        }

        Intent intent1 = new Intent(context,AlarmActivity.class);
        intent1.putExtra("time",time);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }



    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int type,
                                                 long triggerAtMillis, @NonNull PendingIntent operation) {
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(type, triggerAtMillis, operation);
        } else {
            AlarmManagerCompat.setExact(alarmManager, type, triggerAtMillis, operation);
        }
    }

    public static void setExact(@NonNull AlarmManager alarmManager, int type, long triggerAtMillis,
                                @NonNull PendingIntent operation) {
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(type, triggerAtMillis, operation);
        } else {
            alarmManager.set(type, triggerAtMillis, operation);
        }
    }


}
