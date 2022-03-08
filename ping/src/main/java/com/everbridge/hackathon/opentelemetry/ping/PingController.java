package com.everbridge.hackathon.opentelemetry.ping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Value("${backend.url}")
    private String backendUrl;

    @GetMapping("/ping")
    public String ping() {
        String content = "";
        HttpURLConnection con = null;

        try {
            URL url = new URL(backendUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(500);
            con.setReadTimeout(500);
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            try (InputStream inputStream = con.getInputStream()) {
                content = IOUtils.readInputStreamToString(inputStream, StandardCharsets.UTF_8);
            }

            // Check HTTP code + message
            final int statusCode = con.getResponseCode();
            final String statusMessage = con.getResponseMessage();

            // Ensure 2xx status code
            if (statusCode > 299 || statusCode < 200) {
                throw new IOException("HTTP " + statusCode + ": " + statusMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return "ping " + content;
    }

}
