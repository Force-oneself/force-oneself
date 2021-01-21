package com.quan.uitl;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: IO流工具类
 * @Author heyq
 * @Date 2021-01-03
 **/
@Slf4j
public final class IOUtils {

    /**
     * 获取目录下符合条件的文件
     *
     * @param file   目录路径
     * @param filter 过滤条件
     * @return 符合条件的文件集合
     */
    public static List<File> matchFiles(File file, Predicate<File> filter) {
        return Arrays.stream(file.listFiles(File::isFile)).filter(filter).collect(Collectors.toList());
    }

    /**
     * 获取目录下符合后缀式的文件
     *
     * @param file     目录路径
     * @param endsWith 后缀式字符
     * @return 符合后缀式的文件集合
     */
    public static List<File> endsWithFiles(File file, String endsWith) {
        return matchFiles(file, endFile -> endFile.getName().endsWith(endsWith));
    }

    /**
     * 获取目录下符合前缀式的文件
     *
     * @param file      目录路径
     * @param startWith 前缀式字符
     * @return 符合前缀式的文件集合
     */
    public static List<File> startWithFiles(File file, String startWith) {
        return matchFiles(file, startFile -> startFile.getName().startsWith(startWith));
    }
}
