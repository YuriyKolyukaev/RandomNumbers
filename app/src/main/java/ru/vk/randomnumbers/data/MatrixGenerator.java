package ru.vk.randomnumbers.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ru.vk.randomnumbers.IMainViewModel;

public class MatrixGenerator {
    private static final int SEC = 1000;

    private static final Random random = new Random();

    private final IMainViewModel iMainViewModel;

    private Timer secondTimer;
    private List<LinkedList<Integer>> matrix;

    public MatrixGenerator(IMainViewModel iMainViewModel, List<LinkedList<Integer>> data) {
        this.iMainViewModel = iMainViewModel;
        matrix = data;
        startTimer();
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
            LinkedList<LinkedList<Integer>> updatedMatrix = getUpdatedMatrix();
            iMainViewModel.postValue(updatedMatrix);
        }
    };

    private LinkedList<LinkedList<Integer>> getUpdatedMatrix() {
        LinkedList<LinkedList<Integer>> updatedMatrix = new LinkedList<>();
        for (int i = 0; i < matrix.size(); i++) {
            LinkedList<Integer> list = matrix.get(i);
            updatedMatrix.add(getUpdatedSmallList(list));
        }

        int randCurrentPosition = random.nextInt(matrix.size());

        LinkedList<Integer> currentList = updatedMatrix.get(randCurrentPosition);
        updatedMatrix.remove(randCurrentPosition);

        int randomFuturePosition = random.nextInt(matrix.size());

        updatedMatrix.add(randomFuturePosition, currentList);
        return updatedMatrix;
    }

    private LinkedList<Integer> getUpdatedSmallList(LinkedList<Integer> list) {
        int randPosition = random.nextInt(list.size());
        int randNumber = random.nextInt();

        list.set(randPosition, randNumber);

        return list;
    }

    public static LinkedList<LinkedList<Integer>> getRandomMatrix() {
        LinkedList<LinkedList<Integer>> randomMassive = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            randomMassive.add(i, getRandomList());
        }
        return randomMassive;
    }

    private static LinkedList<Integer> getRandomList() {
        LinkedList<Integer> randomList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            randomList.add(random.nextInt());
        }
        return randomList;
    }
}
