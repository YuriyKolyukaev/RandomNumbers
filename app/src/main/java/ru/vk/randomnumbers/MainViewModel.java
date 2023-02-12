package ru.vk.randomnumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;
import java.util.List;

import ru.vk.randomnumbers.data.MatrixGenerator;

public class MainViewModel extends ViewModel implements IMainViewModel {

    private final MutableLiveData<List<LinkedList<Integer>>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<LinkedList<Integer>>> liveData = mutableLiveData;

    public void createMatrix() {
        if (liveData.getValue() == null) {
            LinkedList<LinkedList<Integer>> randomMatrix = MatrixGenerator.getRandomMatrix();
            mutableLiveData.setValue(randomMatrix);
        }
        startMatrixGenerator();
    }

    private void startMatrixGenerator() {
        new MatrixGenerator(this, liveData.getValue());
    }

    @Override
    public void setValue(List<LinkedList<Integer>> data) {
        mutableLiveData.setValue(data);
    }

    @Override
    public void postValue(List<LinkedList<Integer>> data) {
        mutableLiveData.postValue(data);
    }
}
