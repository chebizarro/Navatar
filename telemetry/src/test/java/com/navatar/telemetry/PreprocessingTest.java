package com.navatar.telemetry;

import static org.junit.Assert.*;

import com.referencepoint.proto.BuildingOuterClass.Building;
import com.referencepoint.proto.FloorOuterClass.Floor;
import com.referencepoint.proto.LandmarkOuterClass.Landmark;
import com.referencepoint.proto.NavigableSpaceOuterClass.NavigableSpace;
import com.referencepoint.proto.MinimapOuterClass.Minimap;
import com.referencepoint.proto.MinimapOuterClass.Minimap.Tile;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.List;


public class PreprocessingTest {

    /**
     * Checks that the generated minimap has the correct values stored.
     *
     * @param map  The map used to generate the minimap.
     * @param data The correct values to compare with the generated ones.
     */
    private void validateMinimap(Building map, Object[] data) {
        map = Preprocessing.generateParticles(map);
        map = Preprocessing.generateMinimap(map, (double) data[0]);
        Minimap minimap = map.getFloors(0).getMinimap();
        assertNotNull("The minimap was not generated.", minimap);
        assertEquals("Tile size is incorrect.", (double) data[0], minimap.getSideSize(), 0.00001);
        assertEquals("The number of minimap rows is incorrect.", (int) data[1], minimap.getRows());
        assertEquals("The number of minimap columns is incorrect.", (int) data[2],
                minimap.getColumns());
        List<Tile> tiles = minimap.getTilesList();
        assertEquals("Minimap does not have the correct number of tiles.", (int) data[3], tiles.size());
        assertEquals("The minimum X coordinate is incorrect.", (double) data[4],
                minimap.getMinCoordinates().getX(), 0.00001);
        assertEquals("The minimum Y coordinate is incorrect.", (double) data[5],
                minimap.getMinCoordinates().getY(), 0.00001);

        int i = 6;

        if (data.length == 7) {
            for (Tile tile : tiles) {
                assertEquals("The number of landmarks is not correct.", (int) data[i],
                        tile.getLandmarksCount());
            }
        } else if (data.length - 6 == tiles.size()) {
            //assertEquals("The length of data is less than the number of landmarks", tiles.size() + 6, data.length);
            for (Tile tile : tiles) {
                assertEquals("The number of landmarks is not correct.", (int) data[i++],
                        tile.getLandmarksCount());
            }
        }
    }

    /**
     * Tests if the static function generateMinimap correctly generates the minimap of an accessible
     * convex map.
     */
    @Test
    public void testGenerateMinimapAccessibleMapWithoutRings() {
        Building map = MapBuilder.generateConvexMap();
        Object[] data = {3.0, 1, 2, 2, 1.0, 2.0, 4};
        validateMinimap(map, data);
    }

    /**
     * Tests if the static function generateMinimap correctly generates the minimap of a map with two
     * disconnected accessible areas.
     */
    @Ignore
    @Test
    public void testGenerateMinimapMapWithTwoAccessibleAreas() {
        Building map = MapBuilder.generateMapTwoAccessibleAreas();
        Object[] data = {3.0, 2, 4, 8, 0.0, 0.0, 4};
        validateMinimap(map, data);
    }

    /**
     * Tests if the static function generateMinimap correctly generates the minimap of an accessible
     * concave map.
     */
    @Test
    public void testGenerateMinimapAccessibleConcaveMap() {
        Building map = MapBuilder.generateConcaveMap();
        Object[] data = {3.0, 3, 3, 8, 0.0, 0.0, 5, 5, 3, 5, 5, 5, 5, 1};
        validateMinimap(map, data);
    }

    /**
     * Tests if the static function generateMinimap correctly generates the minimap of an accessible
     * map with inaccessible inner rings.
     */
    @Test
    public void testGenerateMinimapAccessibleMapWithRings() {
        Building map = MapBuilder.generateMapWithRings();
        Object[] data = {3.0, 5, 6, 28, 0.0, 0.0, 3, 2, 2, 2, 2, 2};
        validateMinimap(map, data);
    }

    /**
     * Tests if the static function generateMinimap correctly generates the minimap of a
     * non-accessible map.
     */
    @Test
    public void testGenerateMinimapAccessibleMapWithoutAccessibleSpaces() {
        Floor floor = Floor.newBuilder().addNavigableSpaces(NavigableSpace.newBuilder()).build();
        Building map = Building.newBuilder().addFloors(floor).build();
        Object[] data = {3.0, 0, 0, 0, Double.MAX_VALUE, Double.MAX_VALUE, 0};
        validateMinimap(map, data);
    }

    /**
     * Tests if the static function generateParticles correctly generates particles for every
     * landmark.
     */
    @Test
    public void testGenerateParticles() {
        Building map = MapBuilder.generateConvexMap();
        map = Preprocessing.generateParticles(map);
        for (Landmark landmark : map.getFloorsList().get(0).getLandmarksList())
            assertTrue("Landmark does not have 10 particles.", landmark.getParticlesCount() == 10);
    }

    /**
     * Tests if the static function isAccessible correctly identifies if a path is accessible.
     */
    @Test
    public void testAccessible() {
        Building map = MapBuilder.generateConvexMap();
        Path2D.Double space =
                Preprocessing.createAccessibleArea(map.getFloors(0).getNavigableSpacesList());
        assertTrue(Preprocessing.isAccessible(new Line2D.Double(1.1, 3.0, 4.0, 3.0), space));
        assertTrue(Preprocessing.isAccessible(new Line2D.Double(1.0, 3.0, 4.0, 3.0), space));
        assertFalse(Preprocessing.isAccessible(new Line2D.Double(4.0, 4.0, 4.0, 3.0), space));
        assertFalse(Preprocessing.isAccessible(new Line2D.Double(0.0, 0.0, 4.0, 3.0), space));
        assertFalse(Preprocessing.isAccessible(new Line2D.Double(2.0, 3.0, 7.0, 7.0), space));
        assertFalse(Preprocessing.isAccessible(new Line2D.Double(0.0, 0.0, 7.0, 7.0), space));
    }
}