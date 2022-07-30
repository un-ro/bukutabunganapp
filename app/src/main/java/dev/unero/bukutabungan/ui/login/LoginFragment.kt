package dev.unero.bukutabungan.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.databinding.FragmentLoginBinding
import dev.unero.bukutabungan.utils.UiHelper.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding as FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    private val username by lazy { binding.etUsername.text.toString() }
    private val password by lazy { binding.etPassword.text.toString() }

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
            lifecycleScope.launch(Dispatchers.IO) { viewModel.login(username, password) }
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(LoginFragmentDirections.toDashboard())
            else {
                clearInput()
                showToast(requireContext(), getString(R.string.error_wrong_credential))
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