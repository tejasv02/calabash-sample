package sh.calaba.calabashdriver;

import sh.calaba.driver.CalabashCapabilities;
import sh.calaba.driver.client.RemoteCalabashAndroidDriver;
import sh.calaba.driver.model.CalabashAndroidDriver;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	CalabashCapabilities cpb = CalabashCapabilities.android("greenhouse-android", "4.0.3",
				"de_DE", "emulator-5554", "emulator",
				"sh.calaba.demoproject",
				"sh.calaba.demoproject.WebViewDemo");
		String host= "localhost";
		Integer port= 4444;
		RemoteCalabashAndroidDriver driver = new RemoteCalabashAndroidDriver(host, port, cpb); 
    	assertTrue( true );
    }
}
