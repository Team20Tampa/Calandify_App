package com.example.timemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> events = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        return new EventHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent = events.get(position);
        holder.textViewTitle.setText(currentEvent.getTitle());
        holder.textViewDescription.setText(currentEvent.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentEvent.getPriority()));
    }

    @Override
    public int getItemCount() {
        return events.size();

    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();

    }

    public Event getEventAt(int position) {
        return events.get(position);

    }

    class EventHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;


        public EventHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

        }
    }



}
