package me.echeung.opclassicgestures.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import me.echeung.opclassicgestures.R
import me.echeung.opclassicgestures.databinding.FragmentSettingsBinding
import me.echeung.opclassicgestures.util.isClassicGestureEnabled
import me.echeung.opclassicgestures.util.isClassicGestureSettingPresent
import me.echeung.opclassicgestures.util.setClassicGestures

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private var meetsRequirements: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkRequirement(binding.iconReqAndroid10) { Build.VERSION.SDK_INT == Build.VERSION_CODES.Q }
        checkRequirement(binding.iconReqOneplusDevice) { Build.MANUFACTURER == "OnePlus" }
        checkRequirement(binding.iconReqSettingFound) { isClassicGestureSettingPresent(requireContext()) }
        binding.toggleSwitch.isEnabled = meetsRequirements

        binding.toggleSwitch.isChecked = isClassicGestureEnabled(requireContext())

        binding.toggleSwitch.setOnCheckedChangeListener { _, checked ->
            setClassicGestures(requireContext(), checked)
        }
    }

    private fun checkRequirement(view: ImageView, check: () -> Boolean) {
        if (check()) {
            view.setImageResource(R.drawable.ic_baseline_check_circle_outline_24dp)
            view.imageTintList = ColorStateList.valueOf(Color.GREEN)
        } else {
            view.setImageResource(R.drawable.ic_baseline_error_outline_24dp)
            view.imageTintList = ColorStateList.valueOf(Color.RED)

            meetsRequirements = false
        }
    }
}
