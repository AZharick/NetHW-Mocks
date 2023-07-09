import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocServImplTest {

   @ParameterizedTest
   @CsvFileSource (resources = "localeArguments.csv")
   void localeTest(Country country, String expected) {
      LocalizationServiceImpl lsi = new LocalizationServiceImpl();
      Assertions.assertEquals(expected, lsi.locale(country));
   }
}