package com.diep.java.ocp17.chap14;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Both file and path represents a location on disk (either file or directory)
 * File is concrete class
 * Path is interface
 */
public class FileDemo {
    public static void main(String[] args) {
        //printFileSeparator();

        //createFile();

        createPath();
    }

    public static void printFileSeparator() {
        System.out.print("Local separator = " + System.getProperty("file.separator"));
    }

    public static void createFile() {
        File zooFile1 = new File("/home/tiger/data/stripes.txt");
        File zooFile2 = new File("/home/tiger", "data/stripes.txt");
        File parent = new File("/home/tiger");
        File zooFile3 = new File(parent, "data/stripes.txt");
        System.out.println(zooFile1.exists());
    }

    /**
     * Path is interface, so it's created by static factory method
     * Path.of() and Paths.get() both are shortcut to FileSystems.getDefault().getPath()
     */
    public static void createPath() {
        Path zooPath1 = Path.of("/home/tiger/data/stripes.txt");
        Path zooPath2 = Path.of("/home", "tiger", "data", "stripes.txt");
        Path zooPath3 = Paths.get("/home/tiger/data/stripes.txt");
        Path zooPath4 = Paths.get("/home", "tiger", "data", "stripes.txt");
        System.out.println(Files.exists(zooPath1));
    }

    public static void fileToPath() {
        File file = new File("rabbit");
        Path nowPath = file.toPath();
        File backToFile = nowPath.toFile();
    }

    public static void getPathFromFileSystems() {
        Path zooPath1 = FileSystems.getDefault()
                .getPath("/home/tiger/data/stripes.txt");
        Path zooPath2 = FileSystems.getDefault()
                .getPath("/home", "tiger", "data", "stripes.txt");
    }

}
