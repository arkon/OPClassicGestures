package me.echeung.opclassicgestures.util

import android.content.Context
import android.provider.Settings

private const val SIDE_SETTING_KEY = "op_gesture_button_side_enabled"

fun isClassicGestureSettingPresent(context: Context): Boolean {
    try {
        Settings.System.getInt(context.contentResolver, SIDE_SETTING_KEY)
        return true
    } catch (e: Exception) {
        return false
    }
}

fun isClassicGestureEnabled(context: Context): Boolean {
    return Settings.System.getInt(context.contentResolver, SIDE_SETTING_KEY) == 0
}

fun setClassicGestures(context: Context, enabled: Boolean) {
    Settings.System.putInt(context.contentResolver, SIDE_SETTING_KEY, if (enabled) 0 else 1)
}
