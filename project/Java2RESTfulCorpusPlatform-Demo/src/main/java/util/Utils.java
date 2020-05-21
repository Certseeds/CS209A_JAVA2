package util;

import org.apache.commons.text.similarity.LevenshteinDistance;

import javax.xml.bind.DatatypeConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Utils.
 */
public class Utils {
    /**
     * initial it for save time, use for produce levenshtein distance.
     */
    private static final LevenshteinDistance LD = new LevenshteinDistance();
    /**
     * initial it for save time
     */
    private static MessageDigest MD5_messageDigest;

    static {
        try {
            MD5_messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate md5 of the input bytes.
     *
     * @param bytes the bytes
     * @return MD5 of the input bytes
     * @
     * @Source: https ://www.baeldung.com/java-md5
     */
    public static String calculateMD5(byte[] bytes) {
        MD5_messageDigest.update(bytes);
        return DatatypeConverter.printHexBinary(MD5_messageDigest.digest());
    }

    /**
     * Calculate md5 of a string.
     *
     * @param str the str
     * @return MD5 of the string
     */
    public static String calculateMD5(String str) {
        return calculateMD5(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Gets levennshtein distance of two file.
     *
     * @param f1 File 1
     * @param f2 File 2
     * @return the levennshtein distance : a int value.
     */
    public static int get_LevennshteinDistance(File f1, File f2) {
        String file1 = readFile(f1);
        String file2 = readFile(f2);
        return get_LevennshteinDistance(file1, file2);
    }

    /**
     * Gets levennshtein distance.
     *
     * @param f1 the string 1
     * @param f2 the string 2
     * @return the levennshtein distance : a int value.
     */
    public static int get_LevennshteinDistance(String f1, String f2) {
        return LD.apply(f1, f2);
    }

    public static double get_simple_similarity(String f1, String f2) {
        int max_length = Math.max(Math.max(f1.length(), f2.length()), 1);
        int common_size = 0;
        for (int i = 0; i < Math.min(f1.length(), f2.length()); i++) {
            common_size += (f1.charAt(i) == f2.charAt(i)) ? 1 : 0;
        }
        return (double) common_size / max_length;
    }

    public static String readFile(File file) {
        StringBuilder strb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new
                     InputStreamReader(fis, StandardCharsets.UTF_8)) {
            int n = 0;
            while ((n = isr.read()) != -1) {
                strb.append((char) n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strb.toString();
    }

    public static void store_file(String Path, byte[] bytes) {
        File file = new File(Path);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(bytes.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
