package dev.unero.bukutabungan.ui.insert

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.databinding.FragmentInsertBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InsertFragment : Fragment() {
    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding as FragmentInsertBinding
    private val args by navArgs<InsertFragmentArgs>()

    private val viewModel: InsertViewModel by viewModel()

    private val calendar by lazy { Calendar.getInstance() }

    private val datePicker by lazy {
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(calendar)
        }
    }

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
            title = getCurrentTitle()
            setTitleTextColor(getTitleColor())
        }

        binding.etDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnSave.setOnClickListener {
            if (binding.etDate.text.isEmpty())
                binding.etDate.error = getString(R.string.error_empty_field)
            else if (binding.etAmount.text.toString().isEmpty())
                binding.etAmount.error = getString(R.string.error_empty_field)
            else if (binding.etDescription.text.toString().isEmpty())
                binding.etDescription.error = getString(R.string.error_empty_field)
            else {
                viewModel.insertRecord(newRecord())
                findNavController().popBackStack()
            }
        }
    }

    private fun updateLabel(calendar: Calendar) {
        val sdf = SimpleDateFormat(localePattern, Locale.getDefault())
        binding.etDate.setText(sdf.format(calendar.time))
    }

    private fun newRecord(): Record =
        Record(
            0,
            binding.etAmount.text.toString().toInt(),
            binding.etDescription.text.toString(),
            binding.etDate.text.toString(),
            args.isIncome
        )

    private fun getCurrentTitle(): String =
        if (args.isIncome) getString(R.string.add_income)
        else getString(R.string.add_outcome)

    private fun getTitleColor(): Int =
        if (args.isIncome) ContextCompat.getColor(requireContext(), R.color.green)
        else ContextCompat.getColor(requireContext(), R.color.red)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val localePattern = "dd-MM-yyyy"
    }
}