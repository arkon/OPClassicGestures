package me.echeung.opclassicgestures.ui

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_REPO)))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private companion object {
        private const val CODE_WRITE_SETTINGS_PERMISSION = 101
        private const val GITHUB_REPO = "https://github.com/arkon/OPClassicGestures"
    }
}
