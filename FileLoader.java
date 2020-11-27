package com.pdfmerger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    List<File> filesarray = new ArrayList<File>();

    private boolean checkExtension(String file) {
        return (file.endsWith(".pdf")) ? true: false;
    }

    public void addFileToArray(String file) {
        // Need full path of file
        if (checkExtension(file)) filesarray.add(new File(file));
    }

    public void getAllFiles(String[] args) {
        for (int i=0; i < args.length; i++) {
            addFileToArray(args[i]);
        }
    }

    public List getFiles() {
        return filesarray;
    }
}
