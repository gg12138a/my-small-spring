package org.example.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemResource implements Resource {

    private final String path;

    public FileSystemResource(String path) {
        this.path = path;
    }

    public FileSystemResource(File file) {
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // return new FileInputStream(new File(path));
        return Files.newInputStream(Paths.get(path));
    }
}
