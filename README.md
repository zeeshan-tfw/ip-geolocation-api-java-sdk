# IP Geolocation API Java SDK

## Introduction

[IPGeolocation API](https://ipgeolocation.io) is the solution to identify country code (ISO2 and ISO3 standard), country
name, continent code, continent name, country capital, state/province, district, city, zip code, latitude and longitude
of city, is country belongs to Europian Union, calling code, top level domain (TLD), languages, country flag, internet
service provider (ISP), connection type, organization, geoname ID, currency code, currency name, time zone ID, time zone
offset, current time in the time zone, is time zone in daylight saving time, total daylight savings and user agent
details. This document provides important information to help you get up to speed with IPGeolocation API using IP
Geolocation API Java SDK.

Developers can use this Java SDK for software and web projects related to, but not limited to:

1. Display native language and currency
2. Redirect based on the country
3. Digital rights management
4. Web log stats and analysis
5. Auto-selection of country, state/province and city on forms
6. Filter access from countries you do not do business with
7. Geo-targeting for increased sales and click-through

## Quick Start Guide

You need a valid 'IPGeolocation API key' to use this SDK. [Sign up](https://ipgeolocation.io/signup) here and get your
free API key if you don’t have one.

**Note:** Complete documentation to use this SDK is also available
at [IP Geolocation API JAVA SDK Documentation](https://ipgeolocation.io/documentation/ip-geolocation-api-java-sdk-20180807094025)
.

## System Requirements

IP Geolocation API Java SDK has been developed and tested on JDK version 8.  
Note: Internet connection is required to run this component.

## Installation

Our Java SDK can be installed by various methods given below:

### Maven

Add the following dependency in 'pom.xml' file to use the IP Geolocation API Java SDK.

```maven
<dependency>
    <groupId>io.ipgeolocation</groupId>
    <artifactId>ipgeolocation</artifactId>
    <version>1.0.13</version>
</dependency>
```

### Gradle

Add the following dependency in 'build.gradle' file to use the IP Geolocation API Java SDK.

```gradle
repositories {
    ...
    maven { url "http://dl.bintray.com/ipgeolocation/ipgeolocation" }
}

dependencies {
    compile 'io.ipgeolocation:ipgeolocation:1.0.13'
    ...
}
```

### Ivy

Add the following dependency code in 'ivy.xml' file to use the IP Geolocation API Java SDK.

```ivy
<dependency org='io.ipgeolocation' name='ipgeolocation' rev='1.0.13'>
    <artifact name='ipgeolocation' />
</dependency>
```

### JAR File

Use the following URL to download the latest JAR file for IP Geolocation API Java SDK.

* [https://ipgeolocation.io/downloads/ip-geolocation-api-java-1.0.13.jar](https://ipgeolocation.io/downloads/ip-geolocation-api-java-1.0.13.jar)

Documentation
-----------
Use the following URL to visit documentation

* [https://ipgeolocation.io/documentation.html](https://ipgeolocation.io/documentation.html)

Basic Usage
-----------

### Setup API

```java
// Create IPGeolocationAPI object, passing your valid API key
IPGeolocationAPI api=new IPGeolocationAPI("YOUR_API_KEY");
```

### Geolocation Lookup

```java
// Get geolocation for IP address (1.1.1.1) and fields (geo, time_zone and currency)
GeolocationParams geoParams = new GeolocationParams();
geoParams.setIPAddress("1.1.1.1");
geoParams.setFields("geo,time_zone,currency");
geoParams.setIncludeSecurity(true);
Geolocation geolocation = api.getGeolocation(geoParams);

// Check if geolocation lookup was successful
if (geolocation.getStatus() == 200) {
    System.out.println(geolocation.getCountryName());
    System.out.println(geolocation.getCurrency().getName());
    System.out.println(geolocation.getTimezone().getCurrentTime());
    System.out.println(geolocation.getGeolocationSecurity().getAnonymous());
    System.out.println(geolocation.getGeolocationSecurity().getKnownAttacker());
    System.out.println(geolocation.getGeolocationSecurity().getProxy());
    System.out.println(geolocation.getGeolocationSecurity().getProxyType());
    System.out.println(geolocation.getGeolocationSecurity().getAnonymous());
    System.out.println(geolocation.getGeolocationSecurity().getCloudProvider());
    System.out.println(geolocation.getUserAgent().getDevice().getName());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}

// Get geolocation in Russian** for IP address (1.1.1.1) and all fields
GeolocationParams geoParams = new GeolocationParams();
geoParams.setIPAddress("1.1.1.1");
geoParams.setLang("ru");

Geolocation geolocation = api.getGeolocation(geoParams);

// Check if geolocation lookup was successful
if (geolocation.getStatus() == 200) {
    System.out.println(geolocation.getIPAddress());
    System.out.println(geolocation.getCountryName());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}

// Get geolocation for the calling machine's IP address for all fields
Geolocation geolocation = api.getGeolocation();

if (geolocation.getStatus() == 200) {
    System.out.println(geolocation.getCountryCode2());
    System.out.println(geolocation.getTimezone().getCurrentTime());
} else {     
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}
```

### Bulk Geolocations Lookup

```java
// Query geolocation in German** for multiple IP addresses and all fields
String[] ips = new String[]{"1.1.1.1","2.2.2.2","3.3.3.3"};
GeolocationParams geoParams = new GeolocationParams();
geoParams.setIPAddresses(ips);
geoParams.setLang("de");

List<Geolocation> geolocations = api.getBulkGeolocation(geoParams);

System.out.println(geolocations.size());
System.out.println(geolocations.get(0).getCountryName());
System.out.println(geolocations.get(1).getLanguages());
System.out.println(geolocations.get(2).getTimezone().getCurrentTime());

// Query geolocations for multiple IP addresses but only geo field
String[] ips = new String[]{"1.1.1.1","2.2.2.2","3.3.3.3"};
GeolocationParams geoParams = new GeolocationParams();
geoParams.setIPAddresses(ips);
geoParams.setFields("geo");

List<Geolocation> geolocations = api.getBulkGeolocation(geoParams);

System.out.println(geolocations.size());
System.out.println(geolocations.get(0).getCountryCode2());
System.out.println(geolocations.get(1).getCountryName());
System.out.println(geolocations.get(2).getLatitude());
```

### Timezone API

```java
// Get time zone information by time zone ID
TimezoneParams tzParams = new TimezoneParams();
tzParams.setTimezone("America/New_York");

Timezone tz = api.getTimezone(tzParams);

if (tz.getStatus() == 200) {
    System.out.println(tz.getDateTimeWti());
    System.out.println(tz.getDateTimeTxt());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}

// Get time zone information by coordinates (latitude and longitude) of the location
TimezoneParams tzParams = new TimezoneParams();
tzParams.setCoordinates(37.1838139,-123.8105225);

Timezone tz = api.getTimezone(tzParams);

if (tz.getStatus() == 200) {
    System.out.println(tz.getTimezone());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}

// Get time zone information for IP address (1.1.1.1) and geolocation information Japanese**
TimezoneParams tzParams = new TimezoneParams();
tzParams.setIPAddress("1.1.1.1");
tzParams.setLang("ja");

Timezone tz = api.getTimezone(tzParams);

if (tz.getStatus() == 200) {
    System.out.println(tz.getTimezone());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}

// Query time zone information for calling machine's IP address
Timezone tz = api.getTimezone();

if(tz.getMessage()){
    System.out.println(tz.getTimezone());
    System.out.println(tz.getDateTimeYmd());
} else {
    System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
}
```

** IPGeolocation provides geolocation information in the following languages:

* English (en)
* German (de)
* Russian (ru)
* Japanese (ja)
* French (fr)
* Chinese Simplified (cn)
* Spanish (es)
* Czech (cs)
* Italian (it)

By default, geolocation information is returned into English. Response in a language other than English is available to
paid users only.

## IP Geolocation API Java SDK Objects Reference

IP Geolocation API Java SDK has the following classes that you can use to fully leverage it.

### Class: io.ipgeolocation.api.IPGeolocationAPI

| Method                                                          | Description                                                                                                                                    | Return Type         |
|:----------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------|:--------------------|
| IPGeolocationAPI(String apiKey) throws IllegalArgumentException | Constructs the IPGeolocationAPI object. It takes a valid apiKey as a parameter and throws IllegalArgumentException if apiKey is empty or null. |                     |
| getApiKey()                                                     | This function to get the API key that you set to query the IPGeolocation API.                                                                  | String              |
| getGeolocation()                                                | This function to query Geolocation API.                                                                                                        | Map<String, Object> |
| getGeolocation(GeolocationParams params)                        | This function to query Geolocation API based on the parameters passed.                                                                         | Map<String, Object> |
| getBulkGeolocation(GeolocationParams params)                    | This function to query Geolocation API to lookup multiple IP addresses (max. 50).                                                              | Map<String, Object> |
| getTimezone()                                                   | This function to query Timezone API based on calling machine's IP address.                                                                     | Map<String, Object> |
| getTimezone(TimezoneParams params)                              | This function to query Timezone API based on the parameters passed.                                                                            | Map<String, Object> |
| getUserAgent(String uaString)                                   | This function to query UserAgent API.                                                                                                          | Map<String, Object> |
| getBulkUserAgent(List<String> uaStrings)                        | This function to query UserAgent API to lookup multiple user-agent strings (max. 50).                                                          | Map<String, Object> |

### Class: io.ipgeolocation.api.GeolocationParams

| Method                                                              | Description                                                                                                                                                                                                                                                                                                                      | Return Type |
|:--------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------|
| setIPAddress(String ip)                                             | Sets IP address to lookup geolocation.                                                                                                                                                                                                                                                                                           | void        |
| getIPAddress()                                                      | Get IP address set to lookup geolocation.                                                                                                                                                                                                                                                                                        | String      |
| setIPAddresses(String[] ips) throws IllegalArgumentException        | Set IP addresses to lookup multiple geo-locations. Throws IllegalArgumentException if no. of IP addresses are more than 50. **
Note:** Multiple IP addresses lookup is only available for paid users.                                                                                                                             | void        |
| getIPAddresses()                                                    | Get IP addresses set to lookup bulk geolocations.                                                                                                                                                                                                                                                                                | String[]    |
| setLang(String lang)                                                | Set language parameter to lookup geolocation.                                                                                                                                                                                                                                                                                    | void        |
| getLang()                                                           | Get language set to lookup geolocation.                                                                                                                                                                                                                                                                                          | String      |
| setFields(String fields)                                            | Set fields to lookup geolocation.                                                                                                                                                                                                                                                                                                | void        |
| getFields()                                                         | Get fields set to lookup geolocation.                                                                                                                                                                                                                                                                                            | String      |
| setIncludeHostname(Boolean includeHostname)                         | This URL parameter enables the IPGeolocation API to lookup hostname from our IP-Hostname database and returns the same IP address if there is no hostname found for the queried IP address. Lookup thru IP-Hostname database is faster than other options but is experimental and under process and can produce unwanted output. | void        |
| isIncludeHostname()                                                 | Returns Boolean object whether hostname is included in response or not.                                                                                                                                                                                                                                                          | Boolean     |
| setIncludeHostnameFallbackLive(Boolean includeHostnameFallbackLive) | This URL parameter enables the IPGeolocation API to lookup hostname from our IP-Hostname database and if there is no hostname found for the queried IP address, then lookup thru the live sources. This option has been introduced for faster and accurate lookup.                                                               | void        |
| isIncludeHostnameFallbackLive()                                     | Returns Boolean object whether hostname with fall-back-live is included in response or not.                                                                                                                                                                                                                                      | Boolean     |
| setIncludeLiveHostname(Boolean includeLiveHostname)                 | This URL parameter enables the IPGeolocation API to lookup hostname from live sources. Lookup thru live sources is accurate but can introduce more latency to your query to IPGeolocation API.                                                                                                                                   | void        |
| isIncludeLiveHostname()                                             | Returns Boolean object whether live hostname is included in response or not.                                                                                                                                                                                                                                                     | Boolean     |
| setIncludeSecurity(Boolean includeSecurity)                         | Set includeSecurity to true to get Security object as well.                                                                                                                                                                                                                                                                      | void        |
| isIncludeSecurity()                                                 | Returns Boolean object whether Security object is included in response or not.                                                                                                                                                                                                                                                   | Boolean     |
| setIncludeUserAgentDetail(Boolean includeUserAgentDetail)           | Set includeUserAgentDetail to true to get UserAgent object as well.                                                                                                                                                                                                                                                              | void        |
| isIncludeUserAgentDetail()                                          | Returns Boolean object whether UserAgent object is included in response or not.                                                                                                                                                                                                                                                  | Boolean     |
| setExcludes(String excludes)                                        | Set fields (as a comma separated value) to exclude from response.                                                                                                                                                                                                                                                                | void        |
| getExcludes()                                                       | Get fields (as a comma separated value) that have been excluded from response.                                                                                                                                                                                                                                                   | String      |

### Class: io.ipgeolocation.api.Geolocation

| Method                   | Description                                                  | Return Type         |
|:-------------------------|:-------------------------------------------------------------|:--------------------|
| getDomain()              | Returns domain name if domain name is passed.                | String              |
| getHostname()            | Returns hostname for the IP address.                         | String              |
| getIPAddress()           | Returns IP address of the geolocation.                       | String              |
| getContinentCode()       | Returns 2-letters continent code.                            | String              |
| getContinentName()       | Returns continent name.                                      | String              |
| getCountryCode2()        | Returns 2-letters country code.                              | String              |
| getCountryCode3()        | Returns 3-letters country code.                              | String              |
| getCountryName()         | Returns country name.                                        | String              |
| getCountryCapital()      | Returns country capital.                                     | String              |
| getStateProvince()       | Returns state/province.                                      | String              |
| getDistrict()            | Returns district.                                            | String              |
| getCity()                | Returns city.                                                | String              |
| getZipCode()             | Returns zip code.                                            | String              |
| getLatitude()            | Returns latitude of the city.                                | Double              |
| getLongitude()           | Returns longitude of the city.                               | Double              |
| isEU()                   | Returns is the country in European Union.                    | Boolean             |
| getCallingCode()         | Returns country calling code.                                | String              |
| getCountryTLD()          | Returns country's top level domain like '.au' for Australia. | String              |
| getLanguages()           | Returns languages spoken in the country.                     | String              |
| getCountryFlag()         | Returns a URL to country's flag.                             | String              |
| getISP()                 | Returns ISP name.                                            | String              |
| getConnectionType()      | Returns connection type of the IP address.                   | String              |
| getOrganization()        | Returns organization of the IP address.                      | String              |
| getAsn()                 | Returns AS number of the IP address.                         | String              |
| getGeonameID()           | Returns geoname ID from geonames.org database.               | String              |
| getCurrency()            | Returns currency information of the country.                 | GeolocationCurrency |
| getTimezone()            | Returns time zone information of the country.                | GeolocationTimezone |
| getGeolocationSecurity() | Returns security details of the ip address.                  | GeolocationSecurity |
| getUserAgent()           | Returns user agent information of the country.               | UserAgent           |

### Class: io.ipgeolocation.api.GeolocationCurrency

| Method | Description                      | Return Type |
|:-------|:---------------------------------|:------------|
| getName() | Returns currency name.           | String      |
| getCode() | Returns 3-letters currency code. | String      |
| getSymbol() | Returns currency symbol.         | String      |

### Class: io.ipgeolocation.api.GeolocationTimezone

| Method | Description                                                                    | Return Type |
|:-------|:-------------------------------------------------------------------------------|:------------|
| getName() | Returns standard time zone ID like "America/New_York".                         | String      |
| getOffset() | Returns time zone offset from UTC.                                             | Integer     |
| getCurrentTime() | Returns current date-time string in the format "yyyy-MM-dd HH:mm:ss.SSSZ"      | String      |
| getCurrentTimeUnix() | Returns current date-time as a unix time                                       | BigDecimal  |
| isDST() | Returns is the country observing daylight saving time.                         | Boolean     |
| getDSTSavings() | Returns daylight savings time (in hours).                                      | Integer     |

### Class: io.ipgeolocation.api.GeolocationSecurity

| Method | Description                                                               | Return Type |
|:-------|:--------------------------------------------------------------------------|:------------|
| getThreatScore() | Returns threat score for the ip address                    | Integer      |
| isTor() | Returns Boolean object whether the ip is using tor or not.                 | Boolean      |
| isProxy() | Returns Boolean object whether the ip is using proxy or not.                 | Boolean      |
| getProxyType() | Returns the type of proxy used by ip address  | String      |
| isAnonymous() | Returns Boolean object whether the ip is anonymous or not.                 | Boolean      |
| isKnownAttacker() | Returns Boolean object whether the ip is known attacker or not.                 | Boolean  |
| isBot() | Returns Boolean object whether the ip is bot or not.                 | Boolean      |
| isSpam() | Returns Boolean object whether the ip is spam or not.                 | Boolean      |
| isCloudProvider() | Returns Boolean object whether the ip is cloud provider or not.                 | Boolean      |

### Class: io.ipgeolocation.api.TimezoneParams

| Method                                              | Description                                                               | Return Type |
|:----------------------------------------------------|:--------------------------------------------------------------------------|:------------|
| setTimezone(String timezone)                        | Sets time zone ID to query time zone information.                         | void        |
| getTimezone()                                       | Get time zone ID set to query time zone information.                      | String      |
| setIPAddress(String ip)                             | Sets IP address to query time zone information.                           | void        |
| getIPAddress()                                      | Get IP address set to query time zone information.                        | String      |
| setCoordinates(Double latitude, Double longitude)   | Sets latitude and longitude of a location to query time zone information. | void        |
| getLatitude()                                       | Returns latitude set to query time zone information.                      | Double      |
| getLongitude()                                      | Returns longitude set to query time zone information.                     | Double      |
| setLocation(String location)                        | Set location parameter to get timezone details.                           | void        |
| getLocation()                                       | Get location parameter value to get timezone details.                     | String      |
| setLang(String lang)                                | Set language parameter to lookup geolocation. Default is 'en'.            | void        |
| getLang()                                           | Get language set to lookup geolocation.                                   | String      |

### Class: io.ipgeolocation.api.Timezone

| Method                     | Description                                                                            | Return Type |
|:---------------------------|:---------------------------------------------------------------------------------------|:------------|
| getTimezone()              | Returns time zone ID like "America/New_York".                                          | String      |
| getTimezoneOffset()        | Returns time zone offset from UTC.                                                     | Integer     |
| getTimezoneOffsetWithDST() | Returns time zone offset with dst value from UTC.                                      | Integer     |
| getDate()                  | Returns current date in the format "yyyy-MM-dd".                                       | String      |
| getDateTime()              | Returns date-time string in the format "yyyy-MM-dd HH:mm:ss".                          | String      |
| getDateTimeTxt()           | Returns date-time string in the format "EEEE, MMMM dd, yyyy HH:mm:ss".                 | String      |
| getDateTimeWti()           | Returns date-time string in the format "EEE, dd MMM yyyy HH:mm:ss Z".                  | String      |
| getDateTimeYmd()           | Returns date-time string in the format "yyyy-MM-dd'T'HH:mm:ssZ".                       | String      |
| getDateTimeUnix()          | Returns current date-time as unix time.                                                | BigDecimal  |
| getTime24()                | Returns current time in the format "HH:mm:ss".                                         | String      |
| getTime12()                | Returns current time in the format "hh:mm:ss aa".                                      | String      |
| getWeek()                  | Returns current week of the year.                                                      | Integer     |
| getMonth()                 | Returns current month of the year.                                                     | Integer     |
| getYear()                  | Returns current year.                                                                  | Integer     |
| getYearAbbr()              | Returns 2-letters year abbreviation like "18".                                         | String      |
| isDST()                    | Returns is the country observing Daylight Saving time.                                 | Boolean     |
| getDSTSavings()            | Returns daylight savings time (in hours).                                              | Integer     |
| getTimezoneGeo()           | Returns geolocation of timezone if you lookup timezone information from an IP address. | TimezoneGeo |

### Class: io.ipgeolocation.api.TimezoneGeo

| Method | Description                      | Return Type |
|:-------|:---------------------------------|:------------|
| getCountryCode2() | Returns 2-letters country code.  | String |
| getCountryCode3() | Returns 3-letters country code.  | String |
| getCountryName() | Returns country name.            | String |
| getStateProvince() | Returns state/province.          | String |
| getDistrict() | Returns district.                | String |
| getCity() | Returns city.                    | String |
| getZipCode() | Returns zip code.                | String |
| getLatitude() | Returns latitude of the city.    | BigDecimal |
| getLongitude() | Returns longitude of the city.   | BigDecimal |

### Class: io.ipgeolocation.api.UserAgent

| Method | Description                                       | Return Type              |
|:-------|:--------------------------------------------------|:-------------------------|
| getUserAgentString() | Returns user-agent string.                        | String                   |
| getName() | Returns name of the user agent.                   | String                   |
| getType() | Returns type of the user agent.                   | String                   |
| getVersion() | Returns version of the user agent.                | String                   |
| getVersionMajor() | Returns version major of the user agent.          | String                   |
| getDevice() | Returns user-agent's device details.              | UserAgentDevice          |
| getEngine() | Returns user-agent's engine details.              | UserAgentEngine          |
| getOperatingSystem() | Returns user-agent's operating system details.    | UserAgentOperatingSystem |

### Class: io.ipgeolocation.api.UserAgentDevice

| Method | Description                       | Return Type |
|:-------|:----------------------------------|:------------|
| getName() | Returns user-agent's device name. | String |
| getType() | Returns user-agent's type name.   | String |
| getBrand() | Returns user-agent's brand name.  | String |
| getCpu() | Returns user-agent's CPU name.    | String |

### Class: io.ipgeolocation.api.UserAgentEngine

| Method | Description                                | Return Type |
|:-------|:-------------------------------------------|:------------|
| getName() | Returns user-agent's engine name.          | String |
| getType() | Returns user-agent's engine type.          | String |
| getVersion() | Returns user-agent's engine version.       | String |
| getVersionMajor() | Returns user-agent's engine version major. | String |

### Class: io.ipgeolocation.api.UserAgentOperatingSystem

| Method | Description                                 | Return Type |
|:-------|:--------------------------------------------|:------------|
| getName() | Returns user-agent's operating system name. | String |
| getType() | Returns user-agent's operating system type.           | String |
| getVersion() | Returns user-agent's operating system version.        | String |
| getVersionMajor() | Returns user-agent's operating system version major.  | String |
