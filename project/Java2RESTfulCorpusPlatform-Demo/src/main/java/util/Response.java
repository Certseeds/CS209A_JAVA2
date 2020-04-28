package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Response {
    static ObjectMapper objectMapper = new ObjectMapper();
    int code;
    String message;
    ObjectNode result;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        this.result = objectMapper.createObjectNode();
    }

    public Response(int code, String message, ObjectNode result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Response.objectMapper = objectMapper;
    }

    public int getCode() {
        return code;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public ObjectNode getResult() {
        return result;
    }

    public Response setResult(ObjectNode result) {
        this.result = result;
        return this;
    }
}
