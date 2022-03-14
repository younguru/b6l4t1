package ru.netology;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    private final ConcurrentLinkedQueue<Boolean> callQueue = new ConcurrentLinkedQueue<>();
    private final int CALLS_PER_SECOND = 60;
    private final int CALL_WORK_TIME = 3000;
    private final int WORKING_TIME = 3;
    private final int ATS_CALLS_CREATION_TIME = 1000;

    public void ats() {
        int wt = 0;
        while (wt < WORKING_TIME) {
            try {
                for (int i = 0; i < CALLS_PER_SECOND; i++) {
                    callQueue.add(true);
                }
                System.out.println("Всего обращений: " + callQueue.size());
                Thread.sleep(ATS_CALLS_CREATION_TIME);
            } catch (InterruptedException e) {
            } finally {
                wt += 1;
            }
        }
    }

    public void specialist() {
        while (callQueue.size() != 0) {
            try {
                if (callQueue.poll() != null) {
                    System.out.println(Thread.currentThread().getName() + " обрабатывает зонок");
                    Thread.sleep(CALL_WORK_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
