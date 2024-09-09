package org.fuji.clients;

import lombok.RequiredArgsConstructor;
import org.fuji.server.FileServer;

@RequiredArgsConstructor
public class Client implements Runnable{
    private final FileServer fileServer;
    private final String operation;
    private final String filename;
    private final byte[] data;

    @Override
    public void run() {
        if ("write".equalsIgnoreCase(operation)) {
            fileServer.writeFile(filename, data);
        } else if ("read".equalsIgnoreCase(operation)) {
            fileServer.readFile(filename);
        }
    }
}
