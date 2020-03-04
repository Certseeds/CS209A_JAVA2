package com.nanoseeds.week03;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;

/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-03-03 18:21:57
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-03-04 11:04:02
 */
public class CodeBreaker_1 {
    public static void main(String[] args) {
        try {
            breaker(args);
        } catch (IllegalArgumentException iae) {
            System.out.println(String.format("arguments' number should be 1 not %d", args.length));
        }
    }

    public static void breaker(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }
        StringBuilder strb = new StringBuilder();
        try {
            strb = readFile(args[0]);
        } catch (FileAlreadyExistsException fee) {
            System.out.println(fee.getMessage() + " is a folder and it exist");
            return;
        } catch (FileNotFoundException ffe) {
            System.out.println(String.format("%s do not exist", args[0]));
            return;
        } catch (IOException e) {
            System.out.println("unknown IO exception");
            e.printStackTrace();
            return;
        }
        HashMap<String, Integer> fres = new HashMap<>();
        for (int i = 0; i < strb.length() - 3; i++) {
            //System.out.println(strb.substring(i, i + 3));
            fres.put(strb.substring(i, i + 3),
                    fres.getOrDefault(strb.substring(i, i + 3), 0) + 1);
        }
        int max_v = 0xffffffff;
        String max_key = "";
        for (String key : fres.keySet()) {
            //System.out.println(key + "=" + fres.get(key));
            max_key = (max_v > fres.get(key) ? max_key : key);
            max_v = Math.max(max_v, fres.get(key));
        }
        System.out.println(max_key);
    }

    public static StringBuilder readFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else if (file.exists() && file.isDirectory()) {
            throw new FileAlreadyExistsException(path);
        }
        StringBuilder strb = new StringBuilder();
        FileInputStream fis = new FileInputStream(file);
        try (InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            int n = 0;
            while ((n = isr.read()) != -1) {
                strb.append((char) n);
            }
            //System.out.println(strb);
        } catch (UnsupportedEncodingException uee) {
            System.out.println("unsupported format of file");
        }

        return strb;
    }
}
