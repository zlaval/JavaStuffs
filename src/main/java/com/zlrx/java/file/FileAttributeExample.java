package com.zlrx.java.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class FileAttributeExample {

    public static void main(String[] args) {
        try {
            var file = new File("tesztfile");
            Files.write(file.toPath(), "hello".getBytes());
            var faw = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            faw.write("s3Path", Charset.defaultCharset().encode("mypath"));
            faw.write("name", Charset.defaultCharset().encode("xyztre"));
            var f = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            var attributes = f.list();
            attributes.forEach(System.out::println);
            var attr = ByteBuffer.allocate(f.size("s3Path"));
            f.read("s3Path", attr);
            attr.flip();
            String attrValue = Charset.defaultCharset().decode(attr).toString();
            System.out.println(attrValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
