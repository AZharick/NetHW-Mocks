import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceTests {

   @Test
   void byCoordinatesTest() {
      GeoServiceImpl gsi = new GeoServiceImpl();
      assertThrows(RuntimeException.class, () -> gsi.byCoordinates(100,100));
   }

   @ParameterizedTest
   @CsvFileSource (resources = "/ipArgs.csv")
   void byIpTest(String ip, String expected) {
      GeoServiceImpl gsi = new GeoServiceImpl();
      assertEquals(expected, gsi.byIp(ip).toString());
   }
}