package model;

public class Document {

    static final String Root_path = "./";
    static final String post_fix = ".txt";
    String Hash_MD5;
    String local_Path;

    public Document() {
        this.Hash_MD5 = "";
        this.local_Path = "";
    }

    public Document(String Md5) {
        this.Hash_MD5 = Md5;
        this.local_Path = "";
    }

    public Document(String Md5, String local_path) {
        this.Hash_MD5 = Md5;
        this.local_Path = local_path;
    }

    public String getHash_MD5() {
        return Hash_MD5;
    }

    public void setHash_MD5(String hash_MD5) {
        Hash_MD5 = hash_MD5;
    }

    public String getLocal_Path() {
        return local_Path;
    }

    public void setLocal_Path(String local_Path) {
        this.local_Path = local_Path;
    }

    @Override
    public String toString() {
        return "Document{" +
                "Hash_MD5='" + this.getHash_MD5() + '\'' +
                ", local_Path=" + this.getLocal_Path() + '}';

    }


}
