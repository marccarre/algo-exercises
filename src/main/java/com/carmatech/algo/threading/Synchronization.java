package com.carmatech.algo.threading;

import com.google.common.util.concurrent.Uninterruptibles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Synchronization {
    public static List<Integer> sequence(final int max) {
        final List<Integer> sequence = new ArrayList<>();
        final Semaphore oddReady = new Semaphore(1);
        final Semaphore evenReady = new Semaphore(1);

        final Thread even = new Thread(() -> {
            for (int i = 2; i <= max; i += 2) {
                try {
                    evenReady.acquire();
                    sequence.add(i);
                } catch (InterruptedException e) {
                    evenReady.release();
                    return;
                } finally {
                    oddReady.release();
                }
            }
        });

        final Thread odd = new Thread(() -> {
            for (int i = 1; i <= max; i += 2) {
                try {
                    oddReady.acquire();
                    sequence.add(i);
                } catch (InterruptedException e) {
                    oddReady.release();
                    return;
                } finally {
                    evenReady.release();
                }
            }
        });

        try {
            evenReady.acquire();
        } catch (InterruptedException e) {
            return sequence;
        }
        odd.start();
        even.start();
        Uninterruptibles.joinUninterruptibly(odd);
        Uninterruptibles.joinUninterruptibly(even);
        return sequence;
    }
}
