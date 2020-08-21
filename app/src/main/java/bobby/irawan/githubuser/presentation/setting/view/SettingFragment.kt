package bobby.irawan.githubuser.presentation.setting.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import androidx.preference.SwitchPreference
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.presentation.alarm.AlarmReceiver
import bobby.irawan.githubuser.utils.orFalse
import org.koin.android.ext.android.get

/**
 * Created by bobbyirawan09 on 28/07/20.
 */
class SettingFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val alarmReceiver = get<AlarmReceiver>()

    private lateinit var LANGUAGE: String
    private lateinit var ALARM: String

    private lateinit var languagePreference: PreferenceScreen
    private lateinit var alarmPreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun init() {
        LANGUAGE = resources.getString(R.string.key_language)
        ALARM = resources.getString(R.string.key_alarm)

        languagePreference = findPreference<PreferenceScreen>(LANGUAGE) as PreferenceScreen
        alarmPreference = findPreference<SwitchPreference>(ALARM) as SwitchPreference
    }

    private fun setSummaries() {
        with(preferenceManager.sharedPreferences) {
            alarmPreference.isChecked = getBoolean(
                ALARM,
                DEFAULT_ALARM
            )
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key.orEmpty()) {
            ALARM -> {
                val changeValue = sharedPreferences?.getBoolean(
                    ALARM,
                    DEFAULT_ALARM
                )
                onChangeAlarmState(changeValue)
            }
        }
    }

    private fun onChangeAlarmState(changeValue: Boolean?) {
        if (changeValue.orFalse()) {
            alarmReceiver.setAlarm(requireActivity())
        } else {
            alarmReceiver.cancelAlarm(requireActivity())
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val key = preference?.key.orEmpty()
        when (key) {
            LANGUAGE -> {
                val mIntent = Intent(ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onPreferenceTreeClick(preference)
    }

    companion object {
        const val DEFAULT_ALARM = false
    }
}