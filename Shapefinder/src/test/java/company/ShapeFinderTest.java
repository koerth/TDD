package company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFinderTest {
    private ShapeFinder shapeFinder;
    private ArrayList<Cords> cords;



    @BeforeEach
    void setup() {
        cords = new ArrayList<>();
    }

    @Test
    void should_ReturnNone_when_ListEmpty() {
        shapeFinder = new ShapeFinder(cords);

        String expected = "None";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnDot_when_OneCord() {
        cords.add(new Cords(1, 0, 0));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Dot";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnLine_when_TwoCords() {
        cords.add(new Cords(1, 0, 0));
        cords.add(new Cords(2, 0, 0));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Line";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnTriangle_when_3Cords() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(2, 2, 0));
        cords.add(new Cords(3, 1, 0));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Triangle";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnSquare_when_4CordsAndSameGap() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(1, 4, 0));
        cords.add(new Cords(4, 4, 0));
        cords.add(new Cords(4, 1, 0));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Square";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnRectangle_when_4CordsAnd2SameGaps() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(7, 1, 0));
        cords.add(new Cords(7, 4, 0));
        cords.add(new Cords(1, 4, 0));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Rectangle";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnPyramid_when_OneZCordIsDifferent() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(1, 4, 0));
        cords.add(new Cords(4, 4, 0));
        cords.add(new Cords(4, 1, 0));
        cords.add(new Cords(9, 5, 4));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Pyramid";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnCube_when_6SquaresWith8Cords() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(1, 4, 0));
        cords.add(new Cords(4, 4, 0));
        cords.add(new Cords(4, 1, 0));
        cords.add(new Cords(1, 1, 3));
        cords.add(new Cords(1, 4, 3));
        cords.add(new Cords(4, 4, 3));
        cords.add(new Cords(4, 1, 3));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Cube";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnRectangularPrism_when_3DRectangle() {
        cords.add(new Cords(1, 1, 0));
        cords.add(new Cords(1, 5, 0));
        cords.add(new Cords(5, 5, 0));
        cords.add(new Cords(5, 1, 0));
        cords.add(new Cords(1, 1, 3));
        cords.add(new Cords(1, 5, 3));
        cords.add(new Cords(5, 5, 3));
        cords.add(new Cords(5, 1, 3));
        shapeFinder = new ShapeFinder(cords);

        String expected = "Rectangular Prism";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }

    @Test
    void should_Return3DShape_when_3DShapeWithNoMatchingName() {
        cords.add(new Cords(7, 0, 3));
        cords.add(new Cords(11, 0, 3));
        cords.add(new Cords(11, 1, 7));
        cords.add(new Cords(7, 1, 7));
        cords.add(new Cords(7, 2, 3));
        cords.add(new Cords(11, 2, 3));
        cords.add(new Cords(11, 4, 7));
        cords.add(new Cords(7, 4, 7));
        shapeFinder = new ShapeFinder(cords);

        String expected = "3D Shape";
        String actual = shapeFinder.name();

        assertEquals(expected, actual);
    }
}