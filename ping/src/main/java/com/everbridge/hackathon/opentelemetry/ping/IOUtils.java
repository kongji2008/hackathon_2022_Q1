package com.everbridge.hackathon.opentelemetry.ping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class IOUtils {
    public static String readInputStreamToString(InputStream stream) throws IOException {
        return readInputStreamToString(stream, StandardCharsets.UTF_8);
    }

    public static String readInputStreamToString(InputStream stream, Charset charset) throws IOException {
        char[] buffer = new char[1024];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, charset);
        Throwable var6 = null;

        try {
            while (true) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0) {
                    String var18 = out.toString();
                    return var18;
                }

                out.append(buffer, 0, rsz);
            }
        } catch (Throwable var16) {
            var6 = var16;
            throw var16;
        } finally {
            if (in != null) {
                if (var6 != null) {
                    try {
                        in.close();
                    } catch (Throwable var15) {
                        var6.addSuppressed(var15);
                    }
                } else {
                    in.close();
                }
            }

        }
    }

    public static String readFileToString(File file) throws IOException {
        return readInputStreamToString(new FileInputStream(file));
    }

    public static String readFileToString(File file, Charset charset) throws IOException {
        return readInputStreamToString(new FileInputStream(file), charset);
    }

    private IOUtils() {
    }
}
