package ru.vk.randomnumbers;

import java.util.LinkedList;
import java.util.List;

public interface IMainViewModel {
    void setValue(List<LinkedList<Integer>> data);
    void postValue(List<LinkedList<Integer>> data);
}
