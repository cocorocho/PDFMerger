package com.pdfmerger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        FileLoader fileloader = new FileLoader();
        fileloader.getAllFiles(args);
        String desktoppath = System.getProperty("user.home") + "/Desktop/";
        SimpleDateFormat dateformat = new SimpleDateFormat("HH-mm MM-dd");
        Date date = new Date();
        String currenttime = dateformat.format(date);
        String newfilename = String.format("%sPDFMerger %s.pdf", desktoppath.replace("\\", "/"), currenttime);
        System.out.println(fileloader.filesarray);

        if (!fileloader.filesarray.isEmpty()) {
            try {
                PDDocument basePdf = new PDDocument();

                for (int i=0; i < fileloader.filesarray.size(); i++){
                    // Iterate through files in args
                    File file = fileloader.filesarray.get(i);
                    PDDocument pdffile = PDDocument.load(file);
                    System.out.println("Document Loaded");

                    for (int pi=0; pi < pdffile.getNumberOfPages(); pi++) {
                        // Iterate through pages in pdffile
                        PDPage page = (PDPage) pdffile.getDocumentCatalog().getPages().get(pi);
                        basePdf.importPage(page);
                    }
                }
                basePdf.save(newfilename);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
