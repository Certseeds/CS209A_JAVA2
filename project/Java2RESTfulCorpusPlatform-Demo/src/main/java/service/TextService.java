package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TextDao;
import io.javalin.http.Context;
import model.Document;
import model.summary;
import util.FailureCause;
import util.FailureResponse;
import util.Response;
import util.SuccessResponse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static dao.TextDao.insert;
import static util.Utils.calculateMD5;
import static util.Utils.get_LevennshteinDistance;
import static util.Utils.get_simple_similarity;
import static util.Utils.readFile;
import static util.Utils.store_file;

public class TextService {

    static final String location = "./";
    static final String post_fix = ".txt";
    static TextDao dao = new TextDao();

    public void handleExists(Context ctx) {
        String md5 = ctx.pathParam("md5");
        boolean exists = TextDao.find_md5(md5);
        Response response = new Response(0, "");
        response.getResult().put("exists", exists);
        ctx.json(response);
    }

    public void handleUpload(Context ctx) throws IOException {
        String recieve_md5 = ctx.pathParam("md5");
        byte[] bytes = ctx.bodyAsBytes();
        String cal_md5 = calculateMD5(bytes);
        Response response;
        if (!cal_md5.equals(recieve_md5)) {
            response = new FailureResponse(FailureCause.HASH_NOT_MATCH);
            response.getResult().put("success", false);
            ctx.json(response);
            return;
        } else if (TextDao.find_md5(cal_md5)) {
            response = new FailureResponse(FailureCause.ALREADY_EXIST);
            response.getResult().put("success", false);
            ctx.json(response);
            return;
        }
        Document doc = new Document(cal_md5, location + cal_md5 + post_fix);
        store_file(doc.getLocal_Path(), bytes);
        insert(doc);
        response = new SuccessResponse();
        response.getResult().put("success", true);
        ctx.json(response);
    }

    public void handleDownload(Context ctx) {
        String md5 = ctx.pathParam("md5");
        Response response;
        if (!TextDao.find_md5(md5)) {
            response = new FailureResponse(FailureCause.FILE_NOT_FOUND);
            response.getResult().put("content", "");
            ctx.json(response);
            return;
        }
        response = new SuccessResponse();
        Document doc1 = TextDao.get_md5(md5);
        String str1 = readFile(new File(doc1.getLocal_Path()));
        response.getResult().put("content", str1);
        ctx.json(response);
    }

    public void handleCompare(Context ctx) {
        String md51 = ctx.pathParam("md51");
        String md52 = ctx.pathParam("md52");
        Response response;
        if (!TextDao.find_md5(md51) || !TextDao.find_md5(md52)) {
            response = new FailureResponse(FailureCause.FILE_NOT_FOUND);
            response.getResult().put("simple_similarity", 0);
            response.getResult().put("levenshtein_distance", 0);
            ctx.json(response);
            return;
        }
        response = new SuccessResponse();
        Document doc1 = TextDao.get_md5(md51);
        Document doc2 = TextDao.get_md5(md52);
        String str1 = readFile(new File(doc1.getLocal_Path()));
        String str2 = readFile(new File(doc2.getLocal_Path()));
        response.getResult().put("simple_similarity", get_simple_similarity(str1, str2));
        response.getResult().put("levenshtein_distance", get_LevennshteinDistance(str1, str2));
        ctx.json(response);
    }

    /**
     * Handle list files construction.
     *
     * @param ctx the ctx
     */
    public void handle_files(Context ctx) {
        List<Document> doc_list = TextDao.get_files();
        List<summary> sum_list = new LinkedList<>();
        for (Document i : doc_list) {
            String str = readFile(new File(i.getLocal_Path()));
            int leng = str.length();
            String preview = str.substring(0, Math.min(leng, 100));
            sum_list.add(new summary(i.getHash_MD5(), leng, preview));
        }
        Response response = new SuccessResponse();
        response.getResult().set("files", new ObjectMapper().valueToTree(sum_list));
        ctx.json(response);
    }
}

