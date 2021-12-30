package com.example.androidbasic.data.preference

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.androidbasic.utils.Constant
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharePreferences = EncryptedSharedPreferences.create(
        Constant.Preferences.FILE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveStringValue(key: String, value: String) {
        sharePreferences.edit().putString(key, value).apply()
    }

    fun getStringValue(key: String, defaultValue: String?): String? {
        return sharePreferences.getString(key, defaultValue)
    }
}