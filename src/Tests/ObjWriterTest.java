package OBJ.Writer;


import com.company.Model;
import com.company.Polygon;
import com.company.Vector2f;
import com.company.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ObjWriterTest {

    @Test
    void getVertexes() {
        Model m = new Model();
        ArrayList<Vector3f> vs = new ArrayList<>();
        Vector3f v1 = new Vector3f(1.0F, 2.2F, 4.3F);
        vs.add(v1);
        m.vertices = vs;
        String res = ObjWriter.writeVertexes(m);
        String expected = "v  1.0000 2.2000 4.3000\n" + "# 1 vertices" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getTextureVertexes() {
        Model m = new Model();
        ArrayList<Vector2f> vt = new ArrayList<>();
        Vector2f v1 = new Vector2f(1.0F, 2.2F);
        vt.add(v1);
        m.textureVertices = vt;
        String res = ObjWriter.writeTextureVertexes(m);
        String expected = "vt 1.0000 2.2000 0.0000\n" + "# 1 texture coords" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getNormals() {
        Model m = new Model();
        ArrayList<Vector3f> vn = new ArrayList<>();
        Vector3f v1 = new Vector3f(1.0F, 2.2F, 5.23F);
        vn.add(v1);
        m.normals = vn;
        String res = ObjWriter.writeNormals(m);
        String expected = "vn  1.0000 2.2000 5.2300\n" + "# 1 normals" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getPolygons1() {
        Model m = new Model();

        ArrayList<Polygon> pols = new ArrayList<>();
        Polygon p1 = new Polygon();
        p1.addVertexIndex(0);
        p1.addVertexIndex(1);
        p1.addVertexIndex(2);
        pols.add(p1);
        m.polygons = pols;

        String res = ObjWriter.writePolygons(m);
        String expected = "f 1 2 3 \n" + "# 1 polygons" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getPolygons2() {
        Model m = new Model();

        ArrayList<Polygon> pols = new ArrayList<>();
        Polygon p1 = new Polygon();
        p1.addVertexIndex(0);
        p1.addVertexIndex(1);
        p1.addVertexIndex(2);
        p1.addNormalIndex(0);
        p1.addNormalIndex(1);
        p1.addNormalIndex(2);

        pols.add(p1);
        m.polygons = pols;

        String res = ObjWriter.writePolygons(m);
        String expected = "f 1//1 2//2 3//3 \n" + "# 1 polygons" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getPolygons3() {
        Model m = new Model();

        ArrayList<Polygon> pols = new ArrayList<>();
        Polygon p1 = new Polygon();
        p1.addVertexIndex(0);
        p1.addVertexIndex(1);
        p1.addVertexIndex(2);
        p1.addNormalIndex(0);
        p1.addNormalIndex(1);
        p1.addNormalIndex(2);
        p1.addTextureVertexIndex(0);
        p1.addTextureVertexIndex(1);
        p1.addTextureVertexIndex(2);

        pols.add(p1);
        m.polygons = pols;

        String res = ObjWriter.writePolygons(m);
        String expected = "f 1/1/1 2/2/2 3/3/3 \n" + "# 1 polygons" + "\n\n";
        Assertions.assertEquals(expected, res);
    }

    @Test
    void getPolygons4() {
        Model m = new Model();

        ArrayList<Polygon> pols = new ArrayList<>();
        Polygon p1 = new Polygon();
        p1.addVertexIndex(0);
        p1.addVertexIndex(1);
        p1.addVertexIndex(2);
        p1.addTextureVertexIndex(0);
        p1.addTextureVertexIndex(1);
        p1.addTextureVertexIndex(2);

        pols.add(p1);
        m.polygons = pols;

        String res = ObjWriter.writePolygons(m);
        String expected = "f 1/1 2/2 3/3 \n" + "# 1 polygons" + "\n\n";
        Assertions.assertEquals(expected, res);
    }
}