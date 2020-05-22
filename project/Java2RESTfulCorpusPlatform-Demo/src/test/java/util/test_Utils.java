/*  Java2RESTfulCorpusPlatform-Demo 
    Copyright (C) 2020 nanoseeds
    
    Java2RESTfulCorpusPlatform-Demo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Java2RESTfulCorpusPlatform-Demo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/Java2RESTfulCorpusPlatform-Demo
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-20 22:53:45
 * @LastEditors : nanoseeds
 */
package util;

import dao.TextDao;
import demo.DemoClient;
import demo.DemoServer;
import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class test_Utils {
    private static final int upload_number = 18;
    private static final int un_upload_number = 8;
    private static ByteArrayInputStream in;
    private static ByteArrayOutputStream std_out;
    private static ByteArrayOutputStream std_err;
    private static Utils util;
    private static DemoServer DS;
    private static Random random = new Random(System.currentTimeMillis());
    private static CharsetDetector detector = new CharsetDetector();
    StringBuilder sb;

    @BeforeAll
    private static void initial() throws ClassNotFoundException, IOException {
        util = new Utils();
        PrintStream console = System.out;
        // 获取System.out 输出流的句柄
        forceMkdir(new File("./download/"));
        forceMkdir(new File("./upload/"));
        TextDao.delete_sql();
        DS = new DemoServer();
        DS.main(new String[0]);
    }

    @AfterAll
    private static void after_all() throws IOException {
        deleteDirectory(new File("./download/"));
        deleteDirectory(new File("./upload/"));
    }

    private static void setOutput() {
        std_out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(std_out));
        std_err = new ByteArrayOutputStream();
        System.setErr(new PrintStream(std_err));
    }

    @BeforeEach
    public void before_each() {
        sb = new StringBuilder();
        setOutput();
    }

    @Test
    @Order(1)
    public void test_get_simple_similarity() {
        assertEquals(1.0f / 7.0f, Double.valueOf(util.get_simple_similarity("114514", "1234567")), 1.0f / 1000.0f);
    }

    // way come form https://kknews.cc/code/xq6a3pr.html
    @Test
    @Order(2)
    public void test_exists_not_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("exists materials_format/%d.txt\n", i));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertTrue(std_out.toString().contains("not in database"));
        assertFalse(std_out.toString().contains("is in database"));
    }

    @Test
    @Order(3)
    public void test_upload() throws IOException {
        for (int i = 0; i < 10; i++) {
            sb.append(String.format("upload materials_format/%d.txt\n", i));
        }
        sb.append("upload ");
        for (int i = 10; i < upload_number; i++) {
            sb.append(String.format("materials_format/%d.txt ", i));
        }
        sb.append("\n");
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertTrue(std_out.toString().contains("is upload database"));
        assertFalse(std_err.toString().contains("happen error"));
    }

    @Test
    @Order(4)
    public void test_exists_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("exists materials_format/%d.txt\n", i));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertTrue(std_out.toString().contains("is in database"));
        assertFalse(std_out.toString().contains("not in database"));
    }

    @Test
    @Order(5)
    public void test_upload_all_exists() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("upload materials_format/%d.txt\n", i));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertFalse(std_out.toString().contains("is upload database"));
        assertTrue(std_err.toString().contains("happen error"));
    }

    @Test
    @Order(6)
    public void test_compare_all_file_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("compare " +
                            "materials_format/%d.txt " +
                            "materials_format/%d.txt\n",
                    random.nextInt(upload_number), random.nextInt(upload_number)));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertTrue(std_out.toString().contains("distance between"));
        assertFalse(std_err.toString().contains("Happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(7)
    public void test_compare_2nd_file_not_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("compare " +
                            "materials_format/%d.txt " +
                            "materials_format_not_upload/%d.txt\n",
                    random.nextInt(upload_number), random.nextInt(un_upload_number)));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertFalse(std_out.toString().contains("distance between"));
        assertTrue(std_err.toString().contains("Happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertTrue(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(8)
    public void test_compare_1st_file_not_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("compare " +
                            "materials_format_not_upload/%d.txt " +
                            "materials_format/%d.txt\n",
                    random.nextInt(un_upload_number), random.nextInt(upload_number)));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertFalse(std_out.toString().contains("distance between"));
        assertTrue(std_err.toString().contains("Happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertTrue(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(9)
    public void test_compare_all_file_not_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("compare " +
                            "materials_format_not_upload/%d.txt " +
                            "materials_format_not_upload/%d.txt\n",
                    random.nextInt(un_upload_number), random.nextInt(un_upload_number)));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertFalse(std_out.toString().contains("distance between"));
        assertTrue(std_err.toString().contains("Happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertTrue(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(10)
    public void test_download_all_file_in() throws IOException {
        for (int i = 0; i < upload_number; i++) {
            sb.append(String.format("download materials_format/%d.txt\n", i));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertTrue(std_out.toString().contains("is download in"));
        assertFalse(std_err.toString().contains("Happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(11)
    public void test_download_all_file_not_in() throws IOException {
        for (int i = 0; i < un_upload_number; i++) {
            sb.append(String.format("download materials_format_not_upload/%d.txt\n", i));
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        assertFalse(std_out.toString().contains("is download in"));
        assertTrue(std_err.toString().contains("happen error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertTrue(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(12)
    public void test_files() throws IOException {
        String inputMessage = "list \n" + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        for (int i = 0; i < upload_number; i++) {
            assertTrue(std_out.toString().contains(String.format("file %d :", i)));
        }
    }

    @Test
    @Order(13)
    public void test_upload_download_file_format() throws IOException {
        for (File f : new File("./upload/").listFiles()) {
            detector.setText(Files.readAllBytes(f.toPath()));
            CharsetMatch charsetMatch = detector.detect();
            assertEquals("UTF-8", charsetMatch.getName());
        }
        for (File f : new File("./download/").listFiles()) {
            detector.setText(Files.readAllBytes(f.toPath()));
            CharsetMatch charsetMatch = detector.detect();
            assertEquals("UTF-8", charsetMatch.getName());
        }
    }

    @Test
    @Order(14)
    public void test_argument_number_error_zero() throws IOException {
        for (int i = 0; i < un_upload_number; i++) {
            sb.append(" \n");
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        String temp = std_out.toString();
        assertTrue(std_err.toString().contains("should have parameter"));
        assertFalse(std_err.toString().contains("error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(15)
    public void test_argument_number_error_one() throws IOException {
        for (int i = 0; i < un_upload_number; i++) {
            sb.append(UUID.randomUUID().toString() + " \n");
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        String temp = std_err.toString();
        assertTrue(std_err.toString().contains("Unknown operation,input once again"));
        assertFalse(std_err.toString().contains("error"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(16)
    public void test_argument_number_error_one_para() throws IOException {
        for (int i = 0; i < un_upload_number; i++) {
            sb.append("exists " + " \n");
            sb.append("upload " + " \n");
            sb.append("download " + " \n");
            sb.append("compare " + " \n");
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        String temp = std_err.toString();
        assertFalse(std_err.toString().contains("Unknown operation,input once again"));
        assertTrue(std_err.toString().contains("parameter should bigger than 1"));
        assertTrue(std_err.toString().contains("parameter should bigger than 2"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
    }

    @Test
    @Order(17)
    public void test_argument_number_error_two_para() throws IOException {
        for (int i = 0; i < un_upload_number; i++) {
            sb.append("exists " + UUID.randomUUID() + " \n");
            sb.append("upload " + UUID.randomUUID() + " \n");
            sb.append("download " + UUID.randomUUID() + " \n");
            sb.append("compare " + UUID.randomUUID() + " \n");
        }
        String inputMessage = sb.toString() + "break\n";
        setInput(inputMessage);
        DemoClient.main(new String[0]);
        String temp = std_err.toString();
        assertTrue(std_err.toString().contains("Wrong Path"));
        assertFalse(std_err.toString().contains("Unknown operation,input once again"));
        assertFalse(std_err.toString().contains("parameter should bigger than 1"));
        assertTrue(std_err.toString().contains("parameter should bigger than 2"));
        assertFalse(std_err.toString().contains("error code is 0"));
        assertFalse(std_err.toString().contains("error code is 1"));
        assertFalse(std_err.toString().contains("error code is 2"));
        assertFalse(std_err.toString().contains("error code is 3"));
        assertFalse(std_out.toString().contains("database"));
        assertFalse(std_out.toString().contains("error"));
    }

    @AfterEach
    public void after_each() throws IOException {
        assertFalse(std_out.toString().contains("error code is 0"));
        assertFalse(std_out.toString().contains("error code is 1"));
        assertFalse(std_out.toString().contains("error code is 2"));
        assertFalse(std_out.toString().contains("error code is 3"));
        assertFalse(std_err.toString().contains("database"));
        assertFalse(std_err.toString().contains("download"));
        assertFalse(std_err.toString().contains("length is"));
        assertFalse(std_err.toString().contains("distance between"));
        std_out.close();
        std_err.close();
    }

    private void setInput(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
