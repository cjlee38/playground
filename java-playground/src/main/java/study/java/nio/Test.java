package study.java.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("path");
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read();


    }
}
