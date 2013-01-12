package com.listmovie.utils;

import java.io.BufferedInputStream ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.io.Reader ;
import java.io.StringWriter ;
import java.net.HttpURLConnection ;
import java.net.URL ;
public class HttpManager {
    
    public static String getResponseString(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream is;
        String body;
        try {
            is = new BufferedInputStream(urlConnection.getInputStream());
            Reader reader = null;
            StringWriter writer = null;
            String charset = "UTF-8"; // You should determine it based on response header.

            reader = new InputStreamReader(is, charset);
            writer = new StringWriter();

            char[] buffer = new char[10240];
            for (int length = 0; (length = reader.read(buffer)) > 0;) {
                writer.write(buffer, 0, length);
            }
            
            body = writer.toString();
        } finally {
          urlConnection.disconnect();
        }
        
        

        return body;
    }

}
