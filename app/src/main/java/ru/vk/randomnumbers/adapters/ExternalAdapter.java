package ru.vk.randomnumbers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import ru.vk.randomnumbers.R;

public class ExternalAdapter extends RecyclerView.Adapter<ExternalAdapter.ExternalViewHolder> {

    private final List<List<Integer>> matrix = new LinkedList<>();

    public void setData(List<List<Integer>> data) {
        matrix.clear();
        matrix.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExternalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int externalItem = R.layout.external_item;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(externalItem, parent, false);
        return new ExternalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExternalViewHolder holder, int position) {
        holder.adapter.setData(matrix.get(position));
    }

    @Override
    public int getItemCount() {
        return matrix.size();
    }

    protected static class ExternalViewHolder extends RecyclerView.ViewHolder {

        public NestedAdapter adapter = new NestedAdapter();

        public ExternalViewHolder(@NonNull View itemView) {
            super(itemView);
            RecyclerView recyclerView = itemView.findViewById(R.id.rvNested);

            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setAdapter(adapter);
        }
    }

}