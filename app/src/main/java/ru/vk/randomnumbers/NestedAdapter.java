package ru.vk.randomnumbers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    private final LinkedList<Integer> numbers;

    public NestedAdapter(LinkedList<Integer> data) {
        numbers = data;
    }

    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int nestedItem = R.layout.nested_item;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(nestedItem, parent, false);

        return new NestedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(numbers.get(position)));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    protected static class NestedViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvNumber);
        }
    }
}
