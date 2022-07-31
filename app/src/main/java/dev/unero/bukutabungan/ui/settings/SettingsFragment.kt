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
            val oldPass = viewModel.getOldPassword()

            if (oldPassword.isEmpty() || newPassword.isEmpty())
                showToast(requireContext(), getString(R.string.error_password_field_empty))
            else if (oldPassword == newPassword)
                showToast(requireContext(), getString(R.string.error_password_same))
            else if (oldPassword != oldPass)
                showToast(requireContext(), getString(R.string.error_password_old_not_same))
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