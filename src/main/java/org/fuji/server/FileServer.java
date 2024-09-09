package org.fuji.server;

import org.fuji.model.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileServer {
    private final Map<String, File> fileStorage= new HashMap<>();
    private final ReentrantReadWriteLock rwLock= new ReentrantReadWriteLock();

    public void writeFile(String fileName, byte[] data) {
        Lock writeLock= rwLock.writeLock();
        writeLock.lock();

        try {
            File file= new File(fileName, data);
            fileStorage.put(fileName, file);
            System.out.println("file written: "+fileName);
        } finally {
            writeLock.unlock();
        }
    }

    public File readFile(String fileName) {
        Lock readLock= rwLock.readLock();
        readLock.lock();

        try {
            File file = fileStorage.get(fileName);
            if (!Objects.isNull(file)) {
                System.out.println("file read: "+fileName);
            } else {
                System.out.println("file not found: "+fileName);
            }

            return file;
        } finally {
            readLock.unlock();
        }
    }
}
