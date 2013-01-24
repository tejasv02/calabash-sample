calabash-sample
===============

Discussion and help - https://groups.google.com/forum/?fromgroups=#!topic/calabash-android/Elm-HzlXULw

Calabash android installation:
https://github.com/calabash-driver/calabash-driver/wiki/Setup



Installation: 
Test ng pluing to eclipse
TestNG - http://beust.com/eclipse


STEPS

1) Clone my project from git using 'git clone https://github.com/tejasv02/calabash-sample.git' command.

2) Set path for - CALABASH_DRIVER_APPS enviroment variable.

3) Import project into eclipse.

4) create test server for apk file to be tested using command 'calabash-android build path-to-apk-file' and place the testserver apk into CALABASH_DRIVER_APPS folder.
Also add application apk (pickerviews.pak) in this case to CALABASH_DRIVER_APPS folder

5) Start emulator with AVD(android virtual device)  having SDK greater than or equal to ginger bread (2.3.3)

6) RUN SELENIUM GRID:
C:\Users\xxx\workspace\greenhouse-android-test\lib>java -Dfile.encoding=UTF-8 -cp "selenium-server-standalone-2.28.0.jar" org.openqa.grid.selenium.GridLauncher -role hub host 127.0.0.1 -port 4444 & sleep 8

7) Run Pickerjavatest.java as testng test
Test must be running fine !!

PROBLEM :
if this doesnt work verify calabash server installation and instrumentation is fine run this command from command line - It must launch application in emulator
application apk and test server apk must be installed before running below command 

'adb shell am instrument -e target_package com.sai.samples.views -e main_activity com.sai.samples.views.PickerViewSample -e class sh.calaba.instrumentationbackend.InstrumentationBackend sh.calaba.android.test/sh.calaba.instrumentationbackend.CalabashInstrumentationTestRunner'


REFERENCE:

This project is cloned from - https://github.com/calabash-driver/gdg-devfest-zrh


PROBLEMS THAT MIGHT NEED TO BE FIXED:
I will try to put some help on problems, I faced during installation in this blog - http://mytechlifez.blogspot.co.uk/2013/01/calabash-android-driver-java.html
Hope that  helps

