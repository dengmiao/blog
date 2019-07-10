package com.miao.boot.blog.toolkit;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @title: ByteUtil
 * @description: 对象序列化，反序列化（序列化对象转byte[],ByteBuffer, byte[]转object
 * @author: dengmiao
 * @create: 2019-07-09 13:58
 **/
public class ByteUtil {

    public static byte[] getBytes(Object obj) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(obj);
        out.flush();
        byte[] bytes = bout.toByteArray();
        bout.close();
        out.close();
        return bytes;
    }

    public static Object getObject(byte[] bytes) throws IOException
            , ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        Object obj = oi.readObject();
        bi.close();
        oi.close();
        return obj;
    }

    /*public static Object getObject(ByteBuffer byteBuffer)
            throws ClassNotFoundException, IOException {
        // 需要mina框架的IoBuffer
        IoBuffer buffer =
                IoBuffer.allocate(byteBuffer.capacity()).setAutoExpand(true); // 自动展开
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.position(i);
            buffer.put(byteBuffer.get());
        }
        buffer.position(0);
        InputStream input = buffer.asInputStream();
        ObjectInputStream oi = new ObjectInputStream(input);
        Object obj = oi.readObject();
        input.close();
        oi.close();
        return obj;
    }*/

    public static ByteBuffer getByteBuffer(Object obj) throws IOException {
        byte[] bytes = ByteUtil.getBytes(obj);
        ByteBuffer buff = ByteBuffer.wrap(bytes);
        return buff;
    }
}
