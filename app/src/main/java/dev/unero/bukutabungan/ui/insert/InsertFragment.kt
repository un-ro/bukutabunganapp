package dev.unero.bukutabungan.ui.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {
    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding as FragmentInsertBinding

    private val args by navArgs<InsertFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setNavigationOnClickListener { findNavController().popBackStack() }
            title = currentAction()
        }
    }

    private fun currentAction(): String =
        if (args.isIncome) getString(R.string.add_income)
        else getString(R.string.add_outcome)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}