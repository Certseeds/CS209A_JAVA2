package com.nanoseeds.week03;

import org.junit.jupiter.api.*;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-03-03 18:40:29
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-03-04 11:03:52
 */
public class Test_Code_Breaker_1 {
    static final String path_true = "src/main/java/com/nanoseeds/week03/secret.txt";
    static final String path_false = "src/main/java/com/nanoseeds/week03/secret.log";
    static final String path_dir = "src/main/java/com/nanoseeds/week03";
    static final String path_unsupport = "src/main/java/com/nanoseeds/week03/un.txt";

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static PrintStream originalOut = System.out;
    private static PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
    }

    @Test
    public void test_argument_main() {
        String[] arr = {path_true, path_dir, path_false, path_unsupport};
        assertDoesNotThrow(() -> {
            CodeBreaker_1.main(arr);
        });
        assertEquals(
                String.format("arguments' number should be 1 not %d\r\n", arr.length),
                outContent.toString());
    }

    @Test
    public void test_argument_number_1() {
        String[] arrat = {path_true, path_dir, path_false, path_unsupport};
        assertThrows(IllegalArgumentException.class, () -> {
            CodeBreaker_1.breaker(arrat);
        });
    }

    @Test
    public void test_breaker_true() {
        String[] arr = {path_true};
        CodeBreaker_1.breaker(arr);
        assertEquals(";48\r\n", outContent.toString());
    }

    @Test
    public void test_breaker_not_exist() {
        String[] arr = {path_false};
        CodeBreaker_1.breaker(arr);
        assertEquals(
                String.format("%s do not exist\r\n", arr[0]),
                outContent.toString());
    }

    @Test
    public void test_breaker_already_exist() {
        String[] arr = {path_dir};
        CodeBreaker_1.breaker(arr);
        assertEquals(
                String.format("%s is a folder and it exist\r\n", arr[0]),
                outContent.toString());
    }

    @Test
    public void test_read_file_success() {
        assertDoesNotThrow(() -> {
            CodeBreaker_1.readFile(path_true);
        });
    }

    @Test
    public void test_read_file_no_file() {
        assertThrows(FileNotFoundException.class, () -> {
            CodeBreaker_1.readFile(path_false);
        });
    }

    @Test
    public void test_read_file_exist_but_dict() {
        assertThrows(FileAlreadyExistsException.class, () -> {
            CodeBreaker_1.readFile(path_dir);
        });
    }

    @Test
    public void test_read_file_unsupproted_foramt() {
        assertDoesNotThrow(() -> {
            CodeBreaker_1.readFile(path_unsupport);
        });

    }
}
