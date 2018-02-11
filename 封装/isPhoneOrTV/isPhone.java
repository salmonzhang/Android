package com.example.activitytest;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by salmonzhang on 2018/2/2.
 * 检测app是否是运行在手机上
 */

public class isPhone {

    private static boolean sIsChecked = false;
    private static boolean sIsPhoneRunCache = false;
    public static boolean isPhoneRunning(Context context) {
        if (!sIsChecked) {
            sIsPhoneRunCache = checkScreenIsPhone(context)
                    && checkSIMIsPhone(context)
                    && checkBatteryIsPhone(context);
            sIsChecked = true;
        }
        return sIsPhoneRunCache;
    }

    /**
     * 通过检查当前屏幕的物理尺寸
     * 如果小于6.4 则是手机，否则就是电视
     * @return
     */
    private static boolean checkScreenIsPhone(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        double x = Math.pow(metrics.widthPixels / metrics.xdpi, 2);
        double y = Math.pow(metrics.heightPixels / metrics.ydpi, 2);

        //屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        return screenInches < 6.5;
    }

    /**
     * 检查是否有SIM卡，如果有，则表示是手机
     * @param context
     * @return
     */
    private static boolean checkSIMIsPhone(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * 检查当前电源的接入状态，电视一定是AC交流电
     * @param context
     * @return
     */
    private static boolean checkBatteryIsPhone(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);
        //当前电池的状态
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isChanging = status == BatteryManager.BATTERY_STATUS_FULL;
        //当前充电的状态
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        //当电量是满的，且是AC交流接入，那么就是电视机
        return !(isChanging && acCharge);
    }

    /**
     * 这是一个官方给出的判断方式，但是在国内的环境下，并不可取。因为大部分厂商的智能电视，
     * 只是拿普通的 Android 系统改了改，其实并没有遵循 Google TV 的标准，所以这种方式在某些设备上可能会判断出错。
     */
    private static boolean checkUIModeIsPhone(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
        return uiModeManager.getCurrentModeType() != Configuration.UI_MODE_TYPE_TELEVISION;
    }
}
