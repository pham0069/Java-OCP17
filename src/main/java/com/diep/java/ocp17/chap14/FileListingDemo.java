package com.diep.java.ocp17.chap14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileListingDemo {

    public static void main(String[] args) throws IOException {
        fileListing("/Users/diep/Documents/Java-OCP17");

        fileListingWithNio("/Users/diep/Documents/Java-OCP17");
    }
    public static void fileListing(String path) {
        var file = new File(path);
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("Size: " + file.length());
                System.out.println("Last modified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println("\t" + subfile.getName());
                }
            }
        }
    }

    public static void fileListingWithNio(String pathName) throws IOException {
        var path = Path.of(pathName);
        if (Files.exists(path)) {
            System.out.println("Absolute Path: " + path.toAbsolutePath());
            System.out.println("Is Directory: " + Files.isDirectory(path));
            System.out.println("Parent Path: " + path.getParent());
            if (Files.isRegularFile(path)) {
                System.out.println("Size: " + Files.size(path));
                System.out.println("Last modified: " + Files.getLastModifiedTime(path));
            } else {
                try (Stream<Path> stream = Files.list(path)) {
                    stream.forEach(p -> System.out.println(" " + p.getName(p.getNameCount() - 1)));
                }
            }
        }
    }
}
