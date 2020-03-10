package com.nanoseeds.week04;

import org.junit.jupiter.api.*;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-03-10 16:28:30
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-03-10 16:28:30
 */
public class test_codeBreaker {

    @Test
    public void test_sort_strArray() {
        String str1 = "import java.io.ByteArrayOutputStream;\n" +
                "import java.io.FileNotFoundException;\n" +
                "import java.io.PrintStream;\n" +
                "import java.nio.file.FileAlreadyExistsException;\n" +
                "import java.util.Arrays;";
        String str2 = "import static org.junit.jupiter.api.Assertions.*;";
        System.out.println(str1.substring(0, 6).equals(str2.substring(0, 6)));
        StringBuffer sb = new StringBuffer();
        sb.append("114514");
        System.out.println(sb.indexOf(String.valueOf('a')));
    }
}
