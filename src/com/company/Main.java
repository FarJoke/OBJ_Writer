package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Model m1 = new Model();
        ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
        Vector3f f1 = new Vector3f(2, 3, 3);
        Vector3f f2 = new Vector3f(4, 6, 6);
        Vector3f f3 = new Vector3f(4, 7, 6);
        Vector3f f4 = new Vector3f(4, 8, 6);
        Vector3f f5 = new Vector3f(4, 3, 6);

        vertices.add(f1);
        vertices.add(f2);
        vertices.add(f3);
        vertices.add(f4);
        vertices.add(f5);
        m1.vertices = vertices;

        String fileSeparator = System.getProperty("file.separator");
        String pn = "E:" + fileSeparator + "textf3.obj";
        try {
            ObjWriter.createObjFile(pn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(pn);

        try {
            ObjWriter.writeToFile(m1, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
