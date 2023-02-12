package ru.vk.randomnumbers.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import ru.vk.randomnumbers.IMainViewModel;

public class MatrixGenerator implements IMatrix {
    private static final int SEC = 1000;
    private static final int VERTICAL_LIST_SIZE = 100;
    private static final int HORIZONTAL_LIST_SIZE = 10;

    private static final Random random = new Random();

    private IMainViewModel iMainViewModel;
    private final List<List<Integer>> matrix = new LinkedList<>();

    private Timer secondTimer;

    public MatrixGenerator(IMainViewModel iMainViewModel) {
        this.iMainViewModel = iMainViewModel;
        for (int i = 0; i < VERTICAL_LIST_SIZE; i++) {
            matrix.add(createHorizontalList());
        }
        startTimer();
    }

    private List<Integer> createHorizontalList() {
        List<Integer> horizontalList = new LinkedList<>();
        for (int i = 0; i < HORIZONTAL_LIST_SIZE; i++) {
            int randomValue = random.nextInt();
            horizontalList.add(randomValue);
        }
        return horizontalList;
    }

    private void startTimer() {
        secondTimer = new Timer();

        secondTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                tick.run();
            }
        }, SEC, SEC);
    }

    private final TimerTask tick = new TimerTask() {
        @Override
        public void run() {
            List<List<Integer>> updatedMatrix = getUpdatedMatrix();
            iMainViewModel.postValue(updatedMatrix);
        }
    };

    private List<List<Integer>> getUpdatedMatrix() {
        List<Integer> item = matrix.get(random.nextInt(matrix.size()));
        Collections.swap(matrix, matrix.indexOf(item), random.nextInt(matrix.size()));

        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> itemList = matrix.get(i);
            Integer randomIndex = itemList.get(random.nextInt(itemList.size()));
            Integer randomNumber = random.nextInt();
            itemList.set(itemList.indexOf(randomIndex), randomNumber);
        }

        return new ArrayList<>(matrix);
    }

    @Override
    public void stopGenerator() {
        if (secondTimer != null) secondTimer.cancel();
        secondTimer = null;

        iMainViewModel = null;
    }
}
