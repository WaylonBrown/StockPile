package com.waylonbrown.stockpile;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by waylon.brown on 9/30/15.
 *
 * A more flexible SharedPreferences with less boilerplate and the ability to store and retrieve objects using Gson.
 *
 */
public class StockPile {

    private static final String PREFS_KEY = "prefs_key";
    private static SharedPreferences prefs;
    private static final IllegalStateException exception =
            new IllegalStateException("StockPile hasn't been initialized. Before using, call StockPile.initialize(Context);");

    public static void initialize(Context context){
        prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    public static boolean store(String key, Object object) {
        return store(key, object, false);
    }

    public static boolean store(String key, Object object, boolean asynchronous){
        if(prefs == null){
            throw exception;
        }

        SharedPreferences.Editor editor = prefs.edit();

        if(object instanceof String){
            editor.putString(key, String.valueOf(object));
        } else if(object instanceof Boolean){
            editor.putBoolean(key, ((Boolean) object).booleanValue());
        } else if(object instanceof Float){
            editor.putFloat(key, ((Float) object).floatValue());
        } else if(object instanceof Integer){
            editor.putInt(key, (int)object);
        } else if(object instanceof Long){
            editor.putLong(key, (long)object);
        } else {
            editor.putString(key, toGsonString(object));
        }

        if(asynchronous){
            editor.apply();
            return true;
        } else {
            return editor.commit();
        }
    }

    private static String toGsonString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String getString(String key){
        return getString(key, null);
    }

    public static boolean getBoolean(String key){
        return getBoolean(key, false);
    }

    public static float getFloat(String key){
        return getFloat(key, 0F);
    }

    public static int getInt(String key){
        return getInt(key, 0);
    }

    public static long getLong(String key){
        return getLong(key, 0L);
    }

    public static Object getObject(String key, Class<?> cls){
        Gson gson = new Gson();
        String objectJson = prefs.getString(key, "");
        return gson.fromJson(objectJson, cls);
    }

    public static String getString(String key, String defaultVal){
        if(prefs == null){
            throw exception;
        }
        return prefs.getString(key, defaultVal);
    }

    public static boolean getBoolean(String key, boolean defaultVal){
        if(prefs == null){
            throw exception;
        }
        return prefs.getBoolean(key, defaultVal);
    }

    public static float getFloat(String key, float defaultVal){
        if(prefs == null){
            throw exception;
        }
        return prefs.getFloat(key, defaultVal);
    }

    public static int getInt(String key, int defaultVal){
        if(prefs == null){
            throw exception;
        }
        return prefs.getInt(key, defaultVal);
    }

    public static long getLong(String key, long defaultVal){
        if(prefs == null){
            throw exception;
        }
        return prefs.getLong(key, defaultVal);
    }
}
