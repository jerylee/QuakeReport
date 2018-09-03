package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String SAMPLE_JSON_RESPONSE = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1534636790000,\"url\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.8\",\"limit\":10,\"offset\":1,\"count\":10},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"14km N of Golfito, Costa Rica\",\"time\":1534548144900,\"updated\":1534628492279,\"tz\":-360,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000gc0k\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000gc0k&format=geojson\",\"felt\":114,\"cdi\":7.1,\"mmi\":5.72,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":653,\"net\":\"us\",\"code\":\"1000gc0k\",\"ids\":\",us1000gc0k,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.46,\"rms\":1.09,\"gap\":37,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.1 - 14km N of Golfito, Costa Rica\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-83.1531,8.7694,15]},\"id\":\"us1000gc0k\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.5,\"place\":\"109km NNW of Kampungbajo, Indonesia\",\"time\":1534520102070,\"updated\":1534601950338,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000gbi4\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000gbi4&format=geojson\",\"felt\":119,\"cdi\":3.8,\"mmi\":3.16,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":695,\"net\":\"us\",\"code\":\"1000gbi4\",\"ids\":\",us1000gbi4,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.385,\"rms\":0.98,\"gap\":54,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.5 - 109km NNW of Kampungbajo, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[119.819,-7.4139,538.68]},\"id\":\"us1000gbi4\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"250km SE of Iwo Jima, Japan\",\"time\":1534443773350,\"updated\":1534529080359,\"tz\":600,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000gaqv\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000gaqv&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":2.94,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":611,\"net\":\"us\",\"code\":\"1000gaqv\",\"ids\":\",us1000gaqv,\",\"sources\":\",us,\",\"types\":\",geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":3.785,\"rms\":0.61,\"gap\":17,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 250km SE of Iwo Jima, Japan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[143.3143,23.4356,20]},\"id\":\"us1000gaqv\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.6,\"place\":\"50km S of Tanaga Volcano, Alaska\",\"time\":1534370214780,\"updated\":1534525832652,\"tz\":-600,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000ga0z\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000ga0z&format=geojson\",\"felt\":4,\"cdi\":6.1,\"mmi\":6.24,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":673,\"net\":\"us\",\"code\":\"1000ga0z\",\"ids\":\",us1000ga0z,at00pdivmx,pt18227000,ak20105476,\",\"sources\":\",us,at,pt,ak,\",\"types\":\",dyfi,geoserve,impact-link,losspager,moment-tensor,oaf,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":0.399,\"rms\":1.19,\"gap\":41,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.6 - 50km S of Tanaga Volcano, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-178.0543,51.435,20]},\"id\":\"us1000ga0z\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"126km NE of Bristol Island, South Sandwich Islands\",\"time\":1534217393440,\"updated\":1534266018040,\"tz\":-120,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000g8kf\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000g8kf&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":3.87,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":572,\"net\":\"us\",\"code\":\"1000g8kf\",\"ids\":\",us1000g8kf,\",\"sources\":\",us,\",\"types\":\",geoserve,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":7.342,\"rms\":1.36,\"gap\":23,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.1 - 126km NE of Bristol Island, South Sandwich Islands\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-25.2588,-58.1107,35]},\"id\":\"us1000g8kf\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.1,\"place\":\"65km SSW of Kaktovik, Alaska\",\"time\":1534108501841,\"updated\":1534613685768,\"tz\":-540,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/ak20083478\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=ak20083478&format=geojson\",\"felt\":17,\"cdi\":5.5,\"mmi\":6.88,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":582,\"net\":\"ak\",\"code\":\"20083478\",\"ids\":\",ak20083478,at00pdd9p2,us1000g7ed,\",\"sources\":\",ak,at,us,\",\"types\":\",dyfi,geoserve,ground-failure,impact-link,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":null,\"rms\":0.85,\"gap\":null,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 6.1 - 65km SSW of Kaktovik, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-144.3602,69.5205,1.7]},\"id\":\"ak20083478\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.3,\"place\":\"84km SW of Kaktovik, Alaska\",\"time\":1534085934286,\"updated\":1534608509916,\"tz\":-540,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/ak20076877\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=ak20076877&format=geojson\",\"felt\":102,\"cdi\":5.4,\"mmi\":6.56,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":666,\"net\":\"ak\",\"code\":\"20076877\",\"ids\":\",at00pdcsa7,ak20076877,us1000g77a,\",\"sources\":\",at,ak,us,\",\"types\":\",dyfi,general-link,general-text,geoserve,ground-failure,impact-link,losspager,moment-tensor,oaf,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":null,\"rms\":0.72,\"gap\":null,\"magType\":\"ml\",\"type\":\"earthquake\",\"title\":\"M 6.3 - 84km SW of Kaktovik, Alaska\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-145.2998,69.5619,2.2]},\"id\":\"ak20076877\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.9,\"place\":\"0km SW of Loloan, Indonesia\",\"time\":1533469598190,\"updated\":1534568525735,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000g3ub\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000g3ub&format=geojson\",\"felt\":885,\"cdi\":8.2,\"mmi\":8.35,\"alert\":\"orange\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1726,\"net\":\"us\",\"code\":\"1000g3ub\",\"ids\":\",us1000g3ub,\",\"sources\":\",us,\",\"types\":\",dyfi,general-text,geoserve,ground-failure,impact-text,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.269,\"rms\":1.02,\"gap\":15,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.9 - 0km SW of Loloan, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[116.4363,-8.2597,31]},\"id\":\"us1000g3ub\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6.4,\"place\":\"5km WNW of Obelobel, Indonesia\",\"time\":1532818058740,\"updated\":1533937529529,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000ggbs\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000ggbs&format=geojson\",\"felt\":404,\"cdi\":5.5,\"mmi\":7.2,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":872,\"net\":\"us\",\"code\":\"2000ggbs\",\"ids\":\",us2000ggbs,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,impact-text,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":2.342,\"rms\":1.15,\"gap\":14,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.4 - 5km WNW of Obelobel, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[116.508,-8.2395,14]},\"id\":\"us2000ggbs\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":6,\"place\":\"149km N of Palue, Indonesia\",\"time\":1532797643370,\"updated\":1533049628043,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000gg76\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000gg76&format=geojson\",\"felt\":2,\"cdi\":4.1,\"mmi\":2.08,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":555,\"net\":\"us\",\"code\":\"2000gg76\",\"ids\":\",us2000gg76,\",\"sources\":\",us,\",\"types\":\",dyfi,geoserve,ground-failure,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.586,\"rms\":1.36,\"gap\":19,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - 149km N of Palue, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[122.7474,-7.1221,577.48]},\"id\":\"us2000gg76\"}],\"bbox\":[-178.0543,-58.1107,1.7,143.3143,69.5619,577.48]}";

    private QueryUtils() {
    }

    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making HTTP request", e);
        }

        List<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        return earthquakes;
    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Earthquake> extractFeatureFromJson(String earthquakeJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < earthquakeArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject properties = currentEarthquake.getJSONObject("properties");

                // Extract the value for the key called "mag"
                double magnitude = properties.getDouble("mag");

                // Extract the value for the key called "place"
                String location = properties.getString("place");

                // Extract the value for the key called "time"
                long time = properties.getLong("time");

                // Extract the value for the key called "url"
                String url = properties.getString("url");

                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                Earthquake earthquake = new Earthquake(magnitude, location, time, url);

                // Add the new {@link Earthquake} to the list of earthquakes.
                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }


}
