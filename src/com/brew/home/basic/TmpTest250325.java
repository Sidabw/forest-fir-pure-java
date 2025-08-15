package com.brew.home.basic;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author shaogz
 * @since 2025/3/25 09:43
 */
public class TmpTest250325 {

    public static void main(String[] args) throws IOException {
        int i = Integer.parseInt("000001");
        System.out.println(i);
        List<String> l1 = Arrays.asList("002", "001", "02");
        Collections.sort(l1);
        System.out.println(l1);
    }
}
