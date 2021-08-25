

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StringDoctorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StringDoctorTest
{
    /**
     * Default constructor for test class StringDoctorTest
     */
    public StringDoctorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void TestEmpty()
    {
        StringDoctor stringDo1 = new StringDoctor();
        assertEquals(false, stringDo1.isEmpty("test String "));
        assertEquals(true, stringDo1.isEmpty(" "));
        assertEquals(true, stringDo1.isEmpty("           "));
        assertEquals(true, stringDo1.isEmpty("       \n"));
    }

    @Test
    public void TestEscapeHtml()
    {
        StringDoctor stringDo1 = new StringDoctor();
        assertEquals("&lt;testString&gt;", stringDo1.EscapeHtml("<testString>"));
    }

    @Test
    public void TestEscapeString()
    {
        StringDoctor stringDo1 = new StringDoctor();
        assertEquals("/\\*\\?\\+testString", stringDo1.EscapeString("/*?+testString"));
    }
}



