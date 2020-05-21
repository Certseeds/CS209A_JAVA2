package dao;

import model.Document;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class TextDao {

    static Sql2o sql2o = new Sql2o("jdbc:sqlite:demo.sqlite3", null, null);

    public static void initial() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        // Then create a Sql2o object.
        // Just like using JDBC, if the database does not exist, it will be created.
        final String initial_sql = "CREATE TABLE IF NOT EXISTS DOCUMENTS (" +
                "Hash_MD5 TEXT NOT NULL UNIQUE," +
                "local_Path TEXT NOT NULL);";
        try (Connection con = sql2o.open()) {
            con.createQuery(initial_sql).executeUpdate();
        }
    }

    public static void insert(Document doc) {
        final String insertSQL = "INSERT INTO DOCUMENTS (Hash_MD5,local_Path) values (:md5, :path)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL).addParameter("courseName", doc.getHash_MD5()).executeUpdate();
            // Then replace the variables with actual values
        }
    }

    public static boolean find_md5(String md5) {
        String findSQL = "SELECT Hash_MD5,local_Path FROM DOCUMENTS WHERE Hash_MD5 = :md5";
        List<Document> doc_list = null;
        try (Connection con = sql2o.open()) {
            doc_list = con.createQuery(findSQL)
                    .addParameter("md5", md5)
                    .executeAndFetch(Document.class);
        }
        return !doc_list.isEmpty();
    }

    public static Document get_md5(String md5) {
        String findSQL = "SELECT (Hash_MD5,local_Path) FROM DOCUMENTS WHERE Hash_MD5 = :md5";
        List<Document> doc_list = null;
        try (Connection con = sql2o.open()) {
            doc_list = con.createQuery(findSQL)
                    .addParameter("md5", md5)
                    .executeAndFetch(Document.class);
        }
        if (doc_list.isEmpty()) {
            return null;
        }
        return doc_list.get(0);
    }

    public static List<Document> get_files() {
        String find_all_SQL = "SELECT (Hash_MD5,local_Path) FROM DOCUMENTS";
        List<Document> doc_list = null;
        try (Connection con = sql2o.open()) {
            doc_list = con.createQuery(find_all_SQL)
                    .executeAndFetch(Document.class);
        }
        return doc_list;
    }

}
