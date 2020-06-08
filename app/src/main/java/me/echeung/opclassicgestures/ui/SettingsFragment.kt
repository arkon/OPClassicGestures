package me.echeung.opclassicgestures.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.echeung.opclassicgestures.databinding.FragmentSettingsBinding
import me.echeung.opclassicgestures.util.isClassicGestureEnabled
import me.echeung.opclassicgestures.util.setClassicGestures

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

        binding.toggleSwitch.isChecked = isClassicGestureEnabled(requireContext())

        binding.toggleSwitch.setOnCheckedChangeListener { _, checked ->
            setClassicGestures(requireContext(), checked)
        }
    }
}
