package com.example.timetrackerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.timetrackerapp.database.TaskEntity
import com.example.timetrackerapp.databinding.ItemTaskBinding
import javax.inject.Inject

class TaskAdapter(private val taskItemClickListener: TaskItemClickListener): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var binding: ItemTaskBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemTaskBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val differCurrentList = differ.currentList[position]
        holder.bind(differCurrentList)

        holder.itemView.setOnClickListener {
            taskItemClickListener.onTaskListItemClick(it, differCurrentList)
        }
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskEntity) {
            binding.apply {
                tvTasktitle.text = item.taskTitle
                tvTaskdesc.text = item.taskDesc
                tvTasktimer.text = item.taskTime.toString()
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}