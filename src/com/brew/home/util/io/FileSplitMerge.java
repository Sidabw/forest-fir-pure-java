package com.brew.home.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSplitMerge {

    public static void main(String[] args) {
        //split:将源文件按258MB做切割。比如一个1g的文件会切成4个文件片段
        //merge:反之，再合并起来...
    }

    public static void split() throws Exception {
        //split
        String filename = "风雨哈佛路.mkv";
        FileInputStream inputStream = new FileInputStream(String.format("/Volumes/HDD-1TB/电影/%s", filename));
        byte[] bytes = new byte[1024*20];
        int tail = -1;
        //258MB
        int splitThreshold = 1024*1024*258;
        int curWritePointer = 0;
        int writeIndex = 0;
        FileOutputStream outputStream = new FileOutputStream(String.format("/Volumes/HDD-1TB/电影/%s.%s", filename, ++writeIndex));
        while ((tail = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, tail);
            curWritePointer+=tail;
            if (curWritePointer >= splitThreshold) {
                outputStream.close();
                outputStream = new FileOutputStream(String.format("/Volumes/HDD-1TB/电影/%s.%s", filename, ++writeIndex));
                curWritePointer = 0;
            }
        }
        inputStream.close();
    }

    public static void merge() throws Exception {
        //merge
        String filename = "风雨哈佛路.mkv";
        byte[] bytes = new byte[1024*20];
        int tail = -1;
        int readIndex = 0;
        File file = new File(String.format("/Volumes/HDD-1TB/电影/%s.%s", filename, ++readIndex));
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(String.format("/Volumes/HDD-1TB/电影/%s", filename));
        while (true) {
            if ((tail = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, tail);
            } else {
                file = new File(String.format("/Volumes/HDD-1TB/电影/%s.%s", filename, ++readIndex));
                if (!file.exists()) {
                    outputStream.close();
                    inputStream.close();
                    return;
                }
                inputStream = new FileInputStream(file);
            }
        }
    }
}
