//import util.Operation;
//
//import javax.xml.bind.DatatypeConverter;
//
//import java.io.IOException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
//
//public class Client {
//    static String endpoint = "http://localhost:7001/files";
//    static MessageDigest MD5_messageDigest;
//
//    static {
//        try {
//            MD5_messageDigest = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException {
//
//        while (true) {
//            Scanner in = new Scanner(System.in);
//            args = in.nextLine().split("\\s+");
//
//            if (args.length < 2) {
//                System.out.println("Simple Client");
//                printUsage();
//                return;
//            }
//            Operation operation = Operation.parseOperation(args[0]);
//            if (operation == null) {
//                System.err.println("Unknown operation");
//                printUsage();
//                return;
//            }
//
//            switch (operation) {
//                case UPLOAD:
//                    handleUpload(args);
//                    break;
//                case DOWNLOAD:
//                    handleDownload(args);
//                    break;
//                case COMPARE:
//                    handleCompare(args);
//                    break;
//                case EXISTS:
//                    handleExists(args);
//                    break;
//            }
//        }
//    }
//
//
//    private static void handleExists(String[] args) throws IOException {
//
//    }
//
//    private static void handleCompare(String[] args) throws IOException {
//
//    }
//
//    private static void handleDownload(String[] args) throws IOException {
//
//    }
//
//    private static void handleUpload(String[] args) throws IOException {
//
//    }
//
//    private static void printUsage() {
//        System.out.println("Usage: [op] [params]");
//        System.out.println("Available Operation: upload, download, compare, exists");
//    }
//
//    // source: https://www.baeldung.com/java-md5
//    // source: https://stackoverflow.com/questions/7776116/java-calculate-md5-hash
//    public static String calculateMD5(byte[] bytes) throws NoSuchAlgorithmException {
//        MD5_messageDigest.update(bytes);
//        return DatatypeConverter.printHexBinary(MD5_messageDigest.digest());
//    }
//}

//private static void test_javalin() throws IOException {
//        // Before your study you can read :http://www.ruanyifeng.com/blog/2011/09/restful.html to understand
//        // the RESTful.
//        // Read http://www.ruanyifeng.com/blog/2014/05/restful_api.html to know more detail how the RESTful works.
//
//        // This is a small quickstart guide of using Apache HttpComponent fluent APIs to perform web requests.
//        // You can read more at: https://hc.apache.org/httpcomponents-client-ga/tutorial/html/fluent.html
//        // Before moving on, please make sure `DemoServer` is running on localhost:7002
//
//
//        // To get from a certain url, just use the `Get` method.
//        String responseString = Request.Get(endpoint + "/").execute().returnContent().asString();
//        System.out.println("Content at / : " + responseString);
//
//        // To get from a certain url, just use the `Get` method.
//        responseString = Request.Get(endpoint + "/hi").execute().returnContent().asString();
//        System.out.println("Content at /hi : " + responseString);
//
//        //
//        String name = "Adam";
//        responseString = Request.Get(endpoint + "/greet" + "/" + name).execute().returnContent().asString();
//        System.out.println("Content at /greet : " + responseString);
//
//        // You can also read json from response, with a little help from ObjectMapper
//        responseString = Request.Get(endpoint + "/jsonSample").execute().returnContent().asString();
//
//        Course course = objectMapper.readValue(responseString, Course.class);
//        System.out.println(course);
//
//        // To read our predefined `Response`, you might need to covert it to a map
//        responseString = Request.Get(endpoint + "/response/success").execute().returnContent().asString();
//        Map<String, Object> successResponse = (Map<String, Object>) objectMapper.readValue(responseString, Map.class);
//        Map<String, Object> result = (Map<String, Object>) successResponse.get("result");
//        System.out.println("Score is " + result.get("score"));
//
//        // Beside `Get`, you can also perform `Post`
//        String postContent = "Greeting from client!";
//        byte[] postBytes = postContent.getBytes(StandardCharsets.UTF_8);
//        responseString = Request.Post(endpoint + "/bodySample").bodyByteArray(postBytes).execute().returnContent().asString();
//        System.out.println("Server response: " + responseString);
//
//        }
