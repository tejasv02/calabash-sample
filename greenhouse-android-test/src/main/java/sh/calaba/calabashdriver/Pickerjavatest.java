package sh.calaba.calabashdriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sh.calaba.driver.CalabashCapabilities;
import sh.calaba.driver.client.RemoteCalabashAndroidDriver;
import sh.calaba.driver.server.CalabashAndroidServer;
import sh.calaba.driver.server.CalabashNodeConfiguration;

public class Pickerjavatest {
  public String host = "localhost";
  public int port = 4446;
  protected CalabashCapabilities capabilities;
  protected CalabashAndroidServer calabashDriverServer;

  public Pickerjavatest() {
    this.capabilities =
        CalabashCapabilities.android("greenhouse-android", "4.0.3", "en_EN", "emulator-5554",
            "emulator", "com.sai.samples.views", "com.sai.samples.views.PickerViewSample");
  }

  @Test
  public void testPicker() {
    RemoteCalabashAndroidDriver driver = new RemoteCalabashAndroidDriver(host, port, capabilities);
    driver.findViewById("Button01").click();
    driver.waitForTextIsPresent("Done");
    driver.quit();
  }

  // ///////////////////////////////////////
  // Just lifecycle methods
  // ///////////////////////////////////////

  @BeforeMethod
  protected void startCalabashDriverServer() throws Exception {
    CalabashNodeConfiguration config = new CalabashLocalNodeConfiguration(capabilities, host, port);
    calabashDriverServer = new CalabashAndroidServer();
    System.out.println("starting local driver");
    new Thread(new DriverServerRunnable(calabashDriverServer, config)).run();
    Thread.sleep(2000);
    Lock lock = new ReentrantLock();
    lock.lock();

    boolean isReady = !calabashDriverServer.isReady();
    while (isReady) {
      System.out.println("waiting");
      lock.newCondition().await(15, TimeUnit.SECONDS);
      isReady = !calabashDriverServer.isReady();
    }
    lock.unlock();
  }

  public class DriverServerRunnable implements Runnable {
    private CalabashAndroidServer server = null;
    private CalabashNodeConfiguration config = null;

    DriverServerRunnable(CalabashAndroidServer server, CalabashNodeConfiguration config) {
      this.server = server;
      this.config = config;
    }

    public void run() {
      try {
        server.start(config);
      } catch (Exception e) {
        e.printStackTrace();
        Assert.fail("The local calabash-driver server cannot be started: ", e);
      }
    }
  }

  @AfterMethod
  public void shutdown() throws Exception {
    calabashDriverServer.stop();
  }

  public class CalabashLocalNodeConfiguration extends CalabashNodeConfiguration {
    public CalabashLocalNodeConfiguration(CalabashCapabilities capability, String driverHost,
        int driverPort) {
      super();
      this.capabilities = new ArrayList<CalabashCapabilities>();
      this.capabilities.add(capability);
      this.driverHost = driverHost;
      
      this.driverMaxSession = 5;
      this.driverPort = driverPort;
      this.cleanSavedUserDataEnabled = true;
      
      //Register with the Selenium Grid hub @ specified hubHost:hubPort
      this.driverRegistrationEnabled = true;
      this.hubHost="127.0.0.1";
      this.hubPort=4444;
      
      //This defines what needs to be installed and from where, if set to TRUE
      this.installApksEnabled = true;
      this.mobileAppPath = "PickerViews.apk";
      this.mobileTestAppPath = "af4a49f53f08c646fee75d46aff03476_0.3.8.apk";
    }
  }
}