package dev.unero.bukutabungan.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.LimitLine
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.databinding.FragmentDashboardBinding
import dev.unero.bukutabungan.utils.UiHelper.convertCurrency
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding as FragmentDashboardBinding

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonClick()

        viewModel.getRecords().observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                var incomeTotal = 0
                var outcomeTotal = 0

                data.forEach {
                    if (it.isIncome) incomeTotal += it.amount
                    else outcomeTotal += it.amount
                }

                binding.tvIncome.text = getString(R.string.total_income, convertCurrency(incomeTotal))
                binding.tvOutcome.text = getString(R.string.total_outcome, convertCurrency(outcomeTotal))
            } else {
                binding.tvIncome.text = getString(R.string.total_income, convertCurrency(0))
                binding.tvOutcome.text = getString(R.string.total_outcome, convertCurrency(0))
            }
        }
    }

    private fun setupButtonClick() {
        binding.apply {
            btnAddIncome.setOnClickListener { findNavController().navigate(INCOME) }
            btnAddOutcome.setOnClickListener { findNavController().navigate(OUTCOME) }
            btnList.setOnClickListener { findNavController().navigate(HISTORY) }
            btnSetting.setOnClickListener { findNavController().navigate(SETTINGS) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val INCOME = DashboardFragmentDirections.toInsert(true)
        private val OUTCOME = DashboardFragmentDirections.toInsert(false)
        private val HISTORY = DashboardFragmentDirections.toHistory()
        private val SETTINGS = DashboardFragmentDirections.toSettings()
    }
}