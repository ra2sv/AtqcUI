# Autotest Framework Template
This repository contains an autotest framework template on Java.

Project structure description can be found here: [Framework Description](https://dev.yalantis.com/wiki/display/kb/Framework+Description).

The technology stack is described here: [Technology Stack](https://dev.yalantis.com/wiki/display/kb/Technology+Stack)


**Before testing make sure that you have already set JAVA_HOME, M2_HOME, ANDROID_HOME (for android app testing) environment variables.**

## Web testing in Chrome browser 

To run test in terminal:
1. Download the project
2. Open terminal and go to the project root directory
3. Run command 

`mvn clean -Dsuite=functional.xml -Dwebdriver.platform=CHROME test`

## RestApi testing

1. Create RestApi tests in specific directory. 
2. Add test classes to restApi.xml file.
3. Open terminal and go to the project directory
4. Run command 

`mvn clean -Dsuite=restApi.xml test`

## Mobile testing
Preconditions: 
1. Install and run Appium server with such parameters: 

  - address: 127.0.0.1, port: 4723

###### Android testing:
2. Run emulator or real device. 
3. Set required capabilities in /src/main/resources/config.properties (such as android.deviceName, android.appPath)
4. Create a test using Appium Inspector or another tool to search elements' locators.
5. Create required xml file with test suite. For example, mobile.xml
6. Open terminal and go to the project directory
7. Run command 

`mvn clean -Dsuite=mobile.xml -Dwebdriver.platform=ANDROID test`

###### iOS testing:
2. Run simulator or real device. 
3. Set required capabilities in /src/main/resources/config.properties (such as "deviceName" and "udid")
4. Create a test using Appium Inspector or another tool to search elements' locators.
5. Create required xml file with test suite. For example, mobile.xml
6. Open terminal and go to the project directory
7. Run command 

`mvn clean -Dsuite=mobile.xml -Dwebdriver.platform=iOS test`

## Reports
To generate Allure Reports run command:

`allure:serve` or `allure:report`

Make sure that all required packages are installed! 
