package dev.unero.bukutabungan.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.databinding.FragmentSettingsBinding
import dev.unero.bukutabungan.utils.UiHelper.createAlert
import dev.unero.bukutabungan.utils.UiHelper.showToast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding as FragmentSettingsBinding

    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnClickListener { findNavController().popBackStack() }

        binding.btnSave.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString()
            val newPassword = binding.etNewPassword.text.toString()
            viewModel.getOldPassword()

            if (oldPassword.isEmpty() || newPassword.isEmpty())
                createAlert(requireContext(), getString(R.string.error_password_field_empty))
                    .setNeutralButton("OK") { dialog, _ -> dialog.dismiss() }.show()
            else if (oldPassword == newPassword)
                createAlert(requireContext(), getString(R.string.error_password_same))
                    .setNeutralButton("OK") { dialog, _ -> dialog.dismiss() }.show()
            else if (oldPassword != viewModel.savedOldPassword)
                createAlert(requireContext(), getString(R.string.error_password_old_not_same))
                    .setNeutralButton("OK") { dialog, _ -> dialog.dismiss() }.show()
            else
                lifecycleScope.launch { viewModel.changePassword(newPassword) }

            clear()
        }

        viewModel.isPasswordChanged.observe(viewLifecycleOwner) {
            if (it) showToast(requireContext(), getString(R.string.password_success))
        }
    }

    private fun clear() {
        binding.apply {
            etOldPassword.setText("")
            etNewPassword.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}