package com.mk.diy.bigbigweb.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MultipleUploadPart {
    private Map<String, File> fileParts = new HashMap();
    private Map<String, String> strParts = new HashMap();

    public MultipleUploadPart() {
    }

    public Map<String, File> getFileParts() {
        return this.fileParts;
    }

    public void setFileParts(Map<String, File> fileParts) {
        this.fileParts = fileParts;
    }

    public Map<String, String> getStrParts() {
        return this.strParts;
    }

    public void setStrParts(Map<String, String> strParts) {
        this.strParts = strParts;
    }

    public void addFilePart(String name, File f) {
        this.getFileParts().put(name, f);
    }

    public void addStrPart(String name, String str) {
        this.getStrParts().put(name, str);
    }

    public File getFilePart(String name) {
        return this.getFilePart(name);
    }

    public String getStrPart(String name) {
        return this.getStrPart(name);
    }
}