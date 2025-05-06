package com.example.randomgroupdivider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final List<TaskItem> taskItems;

    public TaskAdapter(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, taskMembers;

        public ViewHolder(View view) {
            super(view);
            taskName = view.findViewById(R.id.taskNameText);
            taskMembers = view.findViewById(R.id.taskMembersText);
        }
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItem item = taskItems.get(position);
        holder.taskName.setText(item.getTaskName());
        holder.taskMembers.setText(String.join(", ", item.getMembers()));
    }

    @Override
    public int getItemCount() {
        return taskItems.size();
    }
}
