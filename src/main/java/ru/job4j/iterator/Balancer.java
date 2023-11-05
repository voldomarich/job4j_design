package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (source.hasNext() && i != nodes.size() - 1) {
                nodes.get(i).add(index, source.next());
            } else if (source.hasNext() && i == nodes.size() - 1) {
                nodes.get(i).add(index, source.next());
                index++;
                i = -1;
            }
        }
    }
}
