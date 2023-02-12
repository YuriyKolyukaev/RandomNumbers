package ru.vk.randomnumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import ru.vk.randomnumbers.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ExternalAdapter externalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        initView();

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.createMatrix();
        viewModel.liveData.observe(this, matrix -> {
            Log.d("KOLUS", "onCreate: matrix = " + matrix.get(0));
            externalAdapter.setData(matrix);
        });
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        externalAdapter = new ExternalAdapter();
        binding.rvExternal.setLayoutManager(layoutManager);
        binding.rvExternal.setHasFixedSize(true);
        binding.rvExternal.setAdapter(externalAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        externalAdapter = null;
    }
}