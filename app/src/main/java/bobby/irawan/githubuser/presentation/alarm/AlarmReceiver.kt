package bobby.irawan.githubuser.presentation.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.AlarmManager.RTC_WAKEUP
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.widget.Toast
import androidx.core.app.NotificationCompat
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.presentation.home.view.HomeActivity
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setContentTitle(context.getString(R.string.alarm_notification_title))
            .setContentText(context.getString(R.string.alarm_notification_message))
            .setSound(alarmSound)
            .setAutoCancel(true)

        if (SDK_INT >= O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME, IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(REQUEST_CODE_ALARM, notification)
    }

    fun setAlarm(context: Context) {
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1)
            }
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_ALARM, intent, 0
        )
        alarmManager.setInexactRepeating(
            RTC_WAKEUP,
            calendar.timeInMillis,
            INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(context, context.getString(R.string.set_alarm), Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_ALARM, intent, 0
        )
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, context.getString(R.string.cancel_alarm), Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQUEST_CODE_ALARM = 100
        const val CHANNEL_ID = "Channel_Reminder"
        const val CHANNEL_NAME = "Reminder Channel"
    }
}
