package com.example.broadcastbeat;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//管理所有活动
public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<Activity>();


    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finshAll() {

        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }

}
