package ru.vk.randomnumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.vk.randomnumbers.data.IMatrix;
import ru.vk.randomnumbers.data.MatrixGenerator;

public class MainViewModel extends ViewModel implements IMainViewModel {

    private final MutableLiveData<List<List<Integer>>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<List<Integer>>> liveData = mutableLiveData;
    private IMatrix matrixGenerator = new MatrixGenerator(this);

    @Override
    public void postValue(List<List<Integer>> data) {
        mutableLiveData.postValue(data);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        matrixGenerator.stopGenerator();
        matrixGenerator = null;
    }
}
