package bobby.irawan.githubuser.presentation.setting.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import bobby.irawan.githubuser.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setView()
    }

    private fun setView() {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.frameLayoutSetting.id,
                SettingFragment()
            )
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }
}