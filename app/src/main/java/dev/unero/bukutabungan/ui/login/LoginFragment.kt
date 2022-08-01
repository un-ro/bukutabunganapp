package dev.unero.bukutabungan.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding as FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            lifecycleScope.launch { viewModel.login(username, password) }
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(LoginFragmentDirections.toDashboard())
            else {
                clearInput()
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.error_title_login))
                    .setMessage(getString(R.string.error_wrong_credential))
                    .setNeutralButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }

    private fun clearInput() {
        binding.apply {
            etUsername.setText("")
            etPassword.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}