package ru.vk.randomnumbers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ExternalAdapter extends RecyclerView.Adapter<ExternalAdapter.ExternalViewHolder> {

    private List<List<Integer>> matrix;

    private Context context;

    public void setData(List<List<Integer>> data) {
        matrix = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExternalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        int externalItem = R.layout.external_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(externalItem, parent, false);

        return new ExternalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExternalViewHolder holder, int position) {
        NestedAdapter nestedAdapter = new NestedAdapter(matrix.get(position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(nestedAdapter);
    }

    @Override
    public int getItemCount() {
        return matrix.size();
    }

    protected static class ExternalViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;

        public ExternalViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.rvNested);
            recyclerView.setNestedScrollingEnabled(true);
        }
    }

}