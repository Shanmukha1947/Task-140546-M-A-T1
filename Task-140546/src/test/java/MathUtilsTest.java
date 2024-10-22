import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.powermock.configuration.ConfigurationType.Mockito;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(org.junit.platform.engine.support.junit4.Junit4Engine.class)
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@PrepareForTest({MathUtilsTest.MathUtils.class})
public class MathUtilsTest {

    private static List<Object[]> testData() {
        return List.of(
                new Object[]{2, 3, 5},
                new Object[]{3, 4, 7},
                new Object[]{10, 11, 21}
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testAdd(int a, int b, int expectedResult) throws Exception {

        // Mocking static method
        PowerMockito.mockStatic(MathUtils.class);
        Mockito.when(MathUtils.isEven(a)).thenReturn(true);

        int actualResult = MathUtils.add(a, b);

        assertEquals(expectedResult, actualResult);
    }

    static class MathUtils {

        static int add(int a, int b) {
            return a + b;
        }

        static boolean isEven(int a) {
            return a % 2 == 0;
        }
    }
}