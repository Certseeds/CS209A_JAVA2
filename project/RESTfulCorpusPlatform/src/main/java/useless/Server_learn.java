//import dao.TextDao;
//import io.javalin.Javalin;
//import io.javalin.plugin.openapi.OpenApiOptions;
//import io.javalin.plugin.openapi.OpenApiPlugin;
//import io.javalin.plugin.openapi.ui.SwaggerOptions;
//import io.swagger.v3.oas.models.info.Info;
//import service.TextService;
//
//public class Server {
//    public static void main(String[] args) throws ClassNotFoundException {
//        TextDao.initial();
//        TextService service = new TextService();
//
//        Javalin app = Javalin.create(config -> {
//            config.registerPlugin(getConfiguredOpenApiPlugin());
//        }).start(7001); // I guess it is port number.
//        app.get("/", ctx -> ctx.result("Welcome to RESTful Corpus Platform"));
//        // handle exist
//        app.get("/files/:md5/exists", service::handleExists);
//        // handle upload
//        app.post("/files/:md5", service::handleUpload);
//        // handle compare
//        app.get("/files/:md51/compare/:md52", service::handleCompare);
//        // handle download
//        app.get("/files/:md5", service::handleDownload);
//    }
//
//    private static OpenApiPlugin getConfiguredOpenApiPlugin() {
//        Info info = new Info().version("1.0").description("RESTful Corpus Platform API");
//        OpenApiOptions options = new OpenApiOptions(info)
//                .activateAnnotationScanningFor("cn.edu.sustech.java2.RESTfulCorpusPlatform")
//                .path("/swagger-docs") // endpoint for OpenAPI json
//                .swagger(new SwaggerOptions("/swagger-ui")); // endpoint for swagger-ui
////                .reDoc(new ReDocOptions("/redoc")); // endpoint for redoc
//        return new OpenApiPlugin(options);
//    }
//
//    public static void main2() {
//        // This is a small quickstart guide to RESTful server with Javalin framework.
//        // It should have covered all the Javalin features you will be using in this project.
//        // Read more about Javalin at: https://javalin.io/documentation
//
//
//        // Handles a Get request at route /
////        app.get("/qishidas", ctx -> {
////            // ctx stands for context
////            // ctx.result method set the response body
////            ctx.result("Server Demo");
////        });
//        // By the way, `->` uses Lambda Expression
//        // You can read more about Lambda Expression here: https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
//
//        // You can also use methods to handle requests, using Method Reference (Class::Method)
////        app.get("/hi", ctx -> {
////            ctx.result("114514");
////        });
//
//        // You can read from path parameters
//        app.get("/greet/:name", ctx -> {
//            // just like a map
//            String name = ctx.pathParam("name");
//            System.out.println("Name is " + name);
//            ctx.result("Hello, " + name);
//        });
//
//
//        // even return objects in JSON
//        app.get("/jsonSample", ctx -> {
//            // requires ctx.json method
//            ctx.json(new Course("Java2", 4));
//        });
//
//        // In this project, you will use our predefined Response class
//        app.get("/response/success", ctx -> {
//            // If success, use SuccessResponse
//            Response response = new SuccessResponse();
//            // and put the items into the response by using getResult().put
//            // To those curious, it's operating Jackson's JSON node underneath.
//            response.getResult().put("score", "100");
//            // finally send out the response
//            ctx.json(response);
//        });
//
//        app.get("/response/failure", ctx -> {
//            // If failed, use Failure Response
//            // You may customize response code and failure message
//            Response response1 = new FailureResponse(233, "failure");
//            // Or use the predefined ones
//            Response response2 = new FailureResponse(FailureCause.ALREADY_EXIST);
//
//            ctx.json(response2);
//        });
//
//        // As well as read from request body in a post request
//        app.post("/bodySample", ctx -> {
//            String bodyString = ctx.body();
//            // or as byte[]
//            byte[] bytes = ctx.bodyAsBytes();
//            ctx.result("received byte length: " + bytes.length);
//        });
//
//        // That's the end of the server demo. Hope this helps!
//    }
//
//}
