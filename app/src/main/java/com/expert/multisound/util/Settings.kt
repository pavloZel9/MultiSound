package com.expert.multisound.util

import android.content.Context


object Settings {
    fun saveSettingB(ctx: Context, Name: String?, value: Boolean?) {
        val editor = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE).edit()
        editor.putBoolean(Name, value!!)
        editor.apply()
    }

    fun saveStrSetting(ctx: Context, Name: String?, value: String?) {
        val editor = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE).edit()
        editor.putString(Name, value)
        editor.apply()
    }

    fun saveAUTH(ctx: Context, Name: String?, value: String?) {
        val editor = ctx.getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
        editor.putString(Name, value)
        editor.apply()
    }

    fun openAUTH(ctx: Context, Name: String?): String? {
        val prefs = ctx.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return prefs.getString(Name, "-1")
    }

    fun openSettingStr(ctx: Context, Name: String?): String? {
        val prefs = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE)
        return prefs.getString(Name, "")
    }

    fun openSettingBoolean(ctx: Context, Name: String?): Boolean {
        val prefs = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE)
        return prefs.getBoolean(Name, false)
    }

    fun saveSetting(ctx: Context, Name: String?, value: Int) {
        val editor = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE).edit()
        editor.putInt(Name, value)
        editor.apply()
    }

    fun openSettingInt(ctx: Context, Name: String?, defaultt: Int): Int {
        val prefs = ctx.getSharedPreferences("setting", Context.MODE_PRIVATE)
        return prefs.getInt(Name, defaultt)
    }
}