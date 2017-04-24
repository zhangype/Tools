package com.yyfax.cma.web.sys.controller;

import java.io.*;
import java.util.List;

/**
 * 深度克隆工具
 *
 * @author zhangype@yonyou.com
 * @version V1.0.0
 * @date 2017/4/24
 */
public class DeepCloneTool {
    /**
     * 使用序列化方法实现List深度克隆
     *
     * @param src 源集合
     * @param <T>
     * @return 拷贝集合
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static <T> List<T> deepCloneList(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
