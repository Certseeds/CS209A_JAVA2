package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import util.Operation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import static util.Utils.calculateMD5;
import static util.Utils.readFile;

public class DemoClient {
    static final String endpoint = "http://localhost:7002";
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        //test_javalin();
        Scanner in = new Scanner(System.in);
        // That's the end of the client demo. Hope this helps!
        while (true) {
            args = in.nextLine().split("\\s+");
            if (args.length < 2) {
                System.out.println("parameter should bigger than 2");
                printUsage();
                continue;
            }
            Operation operation = Operation.parseOperation(args[0]);
            if (operation == Operation.NOT_ANY_ONE) {
                System.err.println("Unknown operation,input once again");
                printUsage();
                continue;
            }
            switch (operation) {
                case UPLOAD: {
                    handleUpload(args);
                    break;
                }
                case DOWNLOAD: {
                    handleDownload(args);
                    break;
                }
                case COMPARE: {
                    handleCompare(args);
                    break;
                }
                case EXISTS: {
                    handleExists(args);
                    break;
                }
                case FILES: {
                    handle_Files(args);
                    break;
                }
                case BREAK: {
                    System.out.println("client end");
                    return;
                }
            }
        }
    }

    private static void test_javalin() throws IOException {
        // Before your study you can read :http://www.ruanyifeng.com/blog/2011/09/restful.html to understand
        // the RESTful.
        // Read http://www.ruanyifeng.com/blog/2014/05/restful_api.html to know more detail how the RESTful works.

        // This is a small quickstart guide of using Apache HttpComponent fluent APIs to perform web requests.
        // You can read more at: https://hc.apache.org/httpcomponents-client-ga/tutorial/html/fluent.html
        // Before moving on, please make sure `DemoServer` is running on localhost:7002


        // To get from a certain url, just use the `Get` method.
        String responseString = Request.Get(endpoint + "/").execute().returnContent().asString();
        System.out.println("Content at / : " + responseString);

        // To get from a certain url, just use the `Get` method.
        responseString = Request.Get(endpoint + "/hi").execute().returnContent().asString();
        System.out.println("Content at /hi : " + responseString);

        //
        String name = "Adam";
        responseString = Request.Get(endpoint + "/greet" + "/" + name).execute().returnContent().asString();
        System.out.println("Content at /greet : " + responseString);

        // You can also read json from response, with a little help from ObjectMapper
        responseString = Request.Get(endpoint + "/jsonSample").execute().returnContent().asString();

        Course course = objectMapper.readValue(responseString, Course.class);
        System.out.println(course);

        // To read our predefined `Response`, you might need to covert it to a map
        responseString = Request.Get(endpoint + "/response/success").execute().returnContent().asString();
        Map<String, Object> successResponse = (Map<String, Object>) objectMapper.readValue(responseString, Map.class);
        Map<String, Object> result = (Map<String, Object>) successResponse.get("result");
        System.out.println("Score is " + result.get("score"));

        // Beside `Get`, you can also perform `Post`
        String postContent = "Greeting from client!";
        byte[] postBytes = postContent.getBytes(StandardCharsets.UTF_8);
        responseString = Request.Post(endpoint + "/bodySample").bodyByteArray(postBytes).execute().returnContent().asString();
        System.out.println("Server response: " + responseString);

    }


    /**
     * Handle exists.
     *
     * @param args the args, String array, should have >= 2 Strings, but only 2nd will be use
     * @throws IOException the io exception
     */
    public static void handleExists(String[] args) throws IOException {
        System.out.println("begin Exists");
        String Path = args[1];
        System.out.println(Path);
        File file = new File(Path);
        if (!(file.exists() && file.isFile() && file.canRead())) {
            System.out.printf("Wrong Path of %s", Path);
            return;
        }
        String str = readFile(file);
        String md5 = calculateMD5(str);
        String responseString = null;
        try {
            responseString = Request.Get(endpoint + "/files/" + md5 + "/exists")
                    .execute()
                    .returnContent()
                    .asString();
            Map<String, Object> response = (Map<String, Object>) objectMapper.readValue(responseString, Map.class);
            Map<String, Object> result = (Map<String, Object>) response.get("result");
            if (result.get("exists").equals(true)) {
                System.out.printf("The file in %s is in database.\n", Path);
            } else {
                System.out.printf("The file in %s not in database.\n", Path);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // System.out.println("Content at / : " + responseString);
    }

    /**
     * Handle upload.
     *
     * @param args the args, String array, it should have >=2 Strings, all will be
     * @throws IOException the io exception
     */
    public static void handleUpload(String[] args) throws IOException {
        System.out.println("begin Upload");
        String Path = args[1];
        System.out.println(Path);
        File file = new File(Path);
        if (!(file.exists() && file.isFile() && file.canRead())) {
            System.out.printf("Wrong Path of %s", Path);
            return;
        }
        String str = readFile(file);
        // TODO transfer other format to UTF-8.
        String md5 = calculateMD5(str);
        String responseString = null;
        try {
            responseString = Request.Post(endpoint + "/files/" + md5)
                    .bodyByteArray(str.getBytes())
                    .execute()
                    .returnContent()
                    .asString();
            Map<String, Object> response = (Map<String, Object>) objectMapper.readValue(responseString, Map.class);
            Map<String, Object> result = (Map<String, Object>) response.get("result");
            if (result.get("success").equals(true) && response.get("code").equals(0)) {
                System.out.printf("The file in %s is upload database.\n", Path);
            } else {
                System.out.printf("The file in %s happen error,\n" +
                        " error code is %d,\n" +
                        "message is %s \n", Path, response.get("code"), response.get("message"));
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Handle compare.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public static void handleCompare(String[] args) throws IOException {

    }

    /**
     * Handle download.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public static void handleDownload(String[] args) throws IOException {

    }


    /**
     * Handle files.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public static void handle_Files(String[] args) throws IOException {
    }

    /**
     * Print usage.
     */
    public static void printUsage() {
        System.out.println("Usage: [op] [params]");
        System.out.println("Available Operation: upload, download, compare, exists");
    }

}
