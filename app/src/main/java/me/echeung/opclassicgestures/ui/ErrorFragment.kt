package me.echeung.opclassicgestures.ui

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.echeung.opclassicgestures.R
import me.echeung.opclassicgestures.databinding.FragmentErrorBinding
import me.echeung.opclassicgestures.databinding.FragmentSettingsBinding
import me.echeung.opclassicgestures.util.isClassicGestureSettingPresent

class ErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) {
            binding.text.text = getString(R.string.msg_must_be_android_10)
        }

        if (!isClassicGestureSettingPresent(requireContext())) {
            binding.text.text = getString(R.string.msg_setting_not_found)
        }
    }
}
