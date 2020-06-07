package me.echeung.opclassicgestures

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.echeung.opclassicgestures.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

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

        binding.toggleSwitch.isChecked =
            Settings.System.getInt(requireContext().contentResolver, SIDE_SETTING_KEY) == 0

        binding.toggleSwitch.setOnCheckedChangeListener { _, checked ->
            Settings.System.putInt(requireContext().contentResolver, SIDE_SETTING_KEY, if (checked) 0 else 1)
        }
    }

    private companion object {
        private const val SIDE_SETTING_KEY = "op_gesture_button_side_enabled"
    }
}
