package cn.edu.sustech.java2.DocumentPreprocessing;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileEntry {
    File file;
    String source;
    String field;
    LocalDate date;
    String encoding;
    int length;
    String newPath;

    public FileEntry(File file, String source, String field, LocalDate date, String encoding) {
        this.file = file;
        this.source = source;
        this.field = field;
        this.date = date;
        this.encoding = encoding;
        this.length = 0;
        this.newPath = null;
    }

    public String toSummaryString(){
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        return newPath+","+source+","+field+","+outputFormat.format(date)+","+length+","+encoding;
    }

    @Override
    public String toString() {
        return "FileEntry{" +
                "file=" + file +
                ", source='" + source + '\'' +
                ", field='" + field + '\'' +
                ", date=" + date +
                ", encoding='" + encoding + '\'' +
                ", length=" + length +
                ", newPath='" + newPath + '\'' +
                '}';
    }

    public FileEntry(){ }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
