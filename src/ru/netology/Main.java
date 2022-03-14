package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int SPECIALIST_COUNT = 33;
        List<Thread> threadList = new ArrayList<>();

        CallCenter callCenter = new CallCenter();
        Thread thread = new Thread(null, callCenter::ats, "АТС");
        thread.start();
        threadList.add(thread);
        for (int i = 0; i < SPECIALIST_COUNT; i++) {
            thread = new Thread(null, callCenter::specialist, "Специалист " + i);
            thread.start();
            threadList.add(thread);
        }
        for (Thread th : threadList) {
            th.join();
        }
    }
}
