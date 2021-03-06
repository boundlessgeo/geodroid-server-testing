package tests;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import support.BaseTest;
import static support.Fixture.VA_PLACES;
import static support.Fixture.VA_ROADS;
import support.Runner;

@RunWith(Runner.Block.class)
public class TestImagePerformance extends BaseTest {

    protected final int lowRunCount = 5;
    protected final int runCount = 10;

    @ClassRule
    public static final BaseTest.WithScenario scenario = new BaseTest.WithScenario();

    @ClassRule
    public static final BaseTest.CollectDeviceLogs logs = new BaseTest.CollectDeviceLogs(scenario);

    @Rule
    public BaseTest.CollectGCLogs testLogs = new BaseTest.CollectGCLogs(scenario);

    static {
        scenario.reporter().toggleCSVReportingMode();
    }

    @Test
    public void testPerformanceFeatureFullImage() throws Exception {
        for (int i = 0; i < runCount; i++) {
            tests.getFeatureAsImage(VA_ROADS, VA_ROADS.getAssociatedStyles().get(0));
        }
    }

    @Test
    public void testPerformanceFeaturePartialImage1() throws Exception {
        // 1023 features
        for (int i = 0; i < runCount; i++) {
            tests.getFeatureAsImage(VA_ROADS, VA_ROADS.getAssociatedStyles().get(0),
                "bbox", "-77.7759011555055,38.73029438222257,-74.64079463398436,41.865400903743705");
        }
    }

    @Test
    public void testPerformanceFeaturePartialImage2() throws Exception {
        // 742 features
        for (int i = 0; i < runCount; i++) {
            tests.getFeatureAsImage(VA_ROADS, VA_ROADS.getAssociatedStyles().get(0),
                "bbox", "-77.7759011555,38.7302943822,-76.2083478947,40.297847643");
        }
    }

    @Test
    public void testPerformanceFeaturePartialImage3() throws Exception {
        // 259
        for (int i = 0; i < runCount; i++) {
            tests.getFeatureAsImage(VA_ROADS, VA_ROADS.getAssociatedStyles().get(0),
                "bbox", "-77.7759011555,38.7302943822,-76.9921245251,39.5140710126");
        }
    }

    @Test
    public void testWMSRoads() throws Exception {
        for (int i = 0; i < runCount; i++) {
            tests.getWMSImage(null, "EPSG:4326", null, "35.071847116597,-84.845603303943,42.108590280659,-69.437278108631" , 1402, 640, VA_ROADS);
        }
    }

    @Test
    public void testWMSRoadsWithStyle() throws Exception {
        for (int i = 0; i < runCount; i++) {
            tests.getWMSImage(null, "EPSG:4326", "roads", "35.071847116597,-84.845603303943,42.108590280659,-69.437278108631" , 1402, 640, VA_ROADS);
        }
    }

    @Test
    public void testWMSLayersStyles() throws Exception {
        for (int i = 0; i < runCount; i++) {
            tests.getWMSImage(null, "EPSG:4326", "roads,places", "35.071847116597,-84.845603303943,42.108590280659,-69.437278108631" , 1402, 640, VA_ROADS, VA_PLACES);
        }
    }
}
