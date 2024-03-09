package com.example.concurrencyplayground.vanilla;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.jupiter.api.Test;

class SynchronizedObservationTest {

    private static class Observation {
        private int count = 0;

        public void syncUp() {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }

        public void up() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    void test() throws Exception {
        Observation observation = new Observation();

        new Thread(() -> observation.syncUp()).start();
        new Thread(() -> observation.up()).start();

        Thread.sleep(1200);

        System.out.println("count = " + observation.count);

        ServerSocket socket = new ServerSocket();
    }

    class IoUringServerSocket extends ServerSocket {

        public IoUringServerSocket() throws IOException {
        }

        public IoUringServerSocket(int port) throws IOException {
            super(port);
        }

        public IoUringServerSocket(int port, int backlog) throws IOException {
            super(port, backlog);
        }

        public IoUringServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
            super(port, backlog, bindAddr);
        }


    }
}
