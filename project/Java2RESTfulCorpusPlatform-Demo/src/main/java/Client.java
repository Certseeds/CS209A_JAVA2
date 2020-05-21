import util.Operation;

import javax.xml.bind.DatatypeConverter;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Client {
    static String endpoint = "http://localhost:7001/files";
    static MessageDigest MD5_messageDigest;

    static {
        try {
            MD5_messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        while (true) {
            Scanner in = new Scanner(System.in);
            args = in.nextLine().split("\\s+");

            if (args.length < 2) {
                System.out.println("Simple Client");
                printUsage();
                return;
            }
            Operation operation = Operation.parseOperation(args[0]);
            if (operation == null) {
                System.err.println("Unknown operation");
                printUsage();
                return;
            }

            switch (operation) {
                case UPLOAD:
                    handleUpload(args);
                    break;
                case DOWNLOAD:
                    handleDownload(args);
                    break;
                case COMPARE:
                    handleCompare(args);
                    break;
                case EXISTS:
                    handleExists(args);
                    break;
            }
        }
    }


    private static void handleExists(String[] args) throws IOException {

    }

    private static void handleCompare(String[] args) throws IOException {

    }

    private static void handleDownload(String[] args) throws IOException {

    }

    private static void handleUpload(String[] args) throws IOException {

    }

    private static void printUsage() {
        System.out.println("Usage: [op] [params]");
        System.out.println("Available Operation: upload, download, compare, exists");
    }

    // source: https://www.baeldung.com/java-md5
    // source: https://stackoverflow.com/questions/7776116/java-calculate-md5-hash
    public static String calculateMD5(byte[] bytes) throws NoSuchAlgorithmException {
        MD5_messageDigest.update(bytes);
        return DatatypeConverter.printHexBinary(MD5_messageDigest.digest());
    }
}
