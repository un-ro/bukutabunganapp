package dev.unero.bukutabungan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import dev.unero.bukutabungan.R
import dev.unero.bukutabungan.data.model.Record
import dev.unero.bukutabungan.databinding.ItemRecordBinding

class RecordAdapter: RecyclerView.Adapter<RecordViewHolder>() {
    private var data = listOf<Record>()

    fun setData(newData: List<Record>) {
        val diffUtil = RecordDiffUtil(data, newData)
        val diffResults = calculateDiff(diffUtil)
        data = newData
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class RecordViewHolder(
    private val binding: ItemRecordBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Record){
        binding.apply {
            tvAmount.text = if (item.isIncome) "[ + ] ${item.amount}" else "[ - ] ${item.amount}"
            tvDescription.text = item.description
            tvDate.text = item.date

            binding.ivStatus.setImageResource(
                if (item.isIncome) R.drawable.ic_income else R.drawable.ic_outcome
            )
        }
    }
}

class RecordDiffUtil(
    private val oldList: List<Record>,
    private val newList: List<Record>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id ||
                oldList[oldItemPosition].date == newList[newItemPosition].date

}