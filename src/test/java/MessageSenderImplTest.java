import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

   @ParameterizedTest
   @CsvFileSource (resources = "/sendArgs.csv")
   void sendTest(String expected, String ip) {
      GeoService gsm = Mockito.mock(GeoService.class);
      Mockito.when(gsm.byIp("96.44.183.149"))
              .thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
      Mockito.when(gsm.byIp("172.0.32.11"))
              .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

      LocalizationService lsm = Mockito.mock(LocalizationService.class);
      Mockito.when(lsm.locale(Country.RUSSIA))
              .thenReturn("Добро пожаловать");
      Mockito.when(lsm.locale(Country.USA))
              .thenReturn("Welcome");

      MessageSenderImpl messageSender = new MessageSenderImpl(gsm, lsm);
      Map<String, String> map = new HashMap<>();
      map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
      String send = messageSender.send(map);
      Assertions.assertEquals(expected, send);
   }
}