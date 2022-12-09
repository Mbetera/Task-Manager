package com.example.kennethndungu.lab2class;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    Context context;
    List<Tasks> TasksList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TvTitleDashboard,TvStartdateDashboard,TvDuedateDashboard;
        public ViewHolder(View itemView) {
            super(itemView);
            TvTitleDashboard=(TextView)itemView.findViewById(R.id.TvTitleDashboard);
            TvStartdateDashboard=(TextView)itemView.findViewById(R.id.TvStartdateDashboard);
            TvDuedateDashboard=(TextView)itemView.findViewById(R.id.TvDuedateDashboard);
        }
    }

    public TasksAdapter(List<Tasks> TasksList, Context context){
        super();
        this.TasksList = TasksList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_dashboard, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tasks tasks =  TasksList.get(position);
        holder.TvTitleDashboard.setText(tasks.getTitle());
        holder.TvStartdateDashboard.setText(tasks.getStartDate());
        holder.TvDuedateDashboard.setText(tasks.getDueDate());
    }
    @Override
    public int getItemCount() {
        return TasksList.size();
    }
}
