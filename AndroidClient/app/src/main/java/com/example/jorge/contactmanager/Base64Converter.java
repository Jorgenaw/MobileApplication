package com.example.jorge.contactmanager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Base64;

public class Base64Converter {


    public static String encodeFileToBase64(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return Base64.encodeToString(bytes, Base64.URL_SAFE);
    }

    public static File decodeStringToFile(String string) throws IOException {
        File file = File.createTempFile("tempfile", ".tmp");

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(Base64.decode(string, Base64.URL_SAFE));

        return file;

    }
}
