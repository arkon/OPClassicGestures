package me.echeung.opclassicgestures.ui

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import me.echeung.opclassicgestures.R
import me.echeung.opclassicgestures.databinding.ActivityMainBinding
import me.echeung.opclassicgestures.util.isClassicGestureSettingPresent
import me.echeung.opclassicgestures.util.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q || !isClassicGestureSettingPresent(this)) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_SettingsFragment_to_ErrorFragment)
            return
        }

        checkSettingsPermission(this)
    }

    @TargetApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_WRITE_SETTINGS_PERMISSION && Settings.System.canWrite(this)) {
            toast(R.string.permission_granted)
        }
    }

    @TargetApi(Build.VERSION_CODES.Q)
    private fun checkSettingsPermission(context: Activity) {
        if (Settings.System.canWrite(context)) {
            return
        }

        toast(R.string.permission_required, Toast.LENGTH_LONG)

        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
            data = Uri.parse("package:" + context.packageName)
        }
        context.startActivityForResult(intent, CODE_WRITE_SETTINGS_PERMISSION)
    }

    private companion object {
        private const val CODE_WRITE_SETTINGS_PERMISSION = 101
    }
}
