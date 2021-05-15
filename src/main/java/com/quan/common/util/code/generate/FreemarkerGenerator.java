package com.quan.common.util.code.generate;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: Freemarker生成器
 * @Author heyq
 * @Date 2021-01-01
 **/
@Data
public class FreemarkerGenerator implements Generator {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerGenerator.class);

    private Map<String, Object> paramMap = new HashMap<>();

    public static final String DEFAULT_ENCODING_FORMAT = "UTF-8";

    private String templateFilePath;

    private String codeFilePath;

    private String encodingFormat;

    @Override
    public void generate() {
        doGenerator(templateFilePath, codeFilePath, encodingFormat, paramMap);
    }

    public void doGenerator(String templateFilePath, String codeFilePath,
                            String encodingFormat, Map<String, Object> paramMap) {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        try (Writer out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(codeFilePath),
                        Objects.isNull(encodingFormat) ? DEFAULT_ENCODING_FORMAT : encodingFormat))) {
            Template template = config.getTemplate(templateFilePath,
                    Objects.isNull(encodingFormat) ? DEFAULT_ENCODING_FORMAT : encodingFormat);
            template.process(paramMap, out);
            out.flush();
        } catch (Exception e) {
            logger.error("模板生成失败!", e);
        }
    }
}
