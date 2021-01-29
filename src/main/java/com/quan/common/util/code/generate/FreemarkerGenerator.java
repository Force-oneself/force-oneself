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

/**
 * @Description: Freemarker生成器
 * @Author heyq
 * @Date 2021-01-01
 **/
@Data
public class FreemarkerGenerator implements Generator {

    private static final Logger logger = LoggerFactory.getLogger(FreemarkerGenerator.class);

    private Initializer initializer;

    private Map<String, Object> paramMap = new HashMap<>();

    private String templateFilePath;

    private String codeFilePath;

    private String encodingFormat = "UTF-8";

    public FreemarkerGenerator(Initializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void generate() {
        initializer.init(this);
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(codeFilePath), encodingFormat))) {
            Template template = config.getTemplate(templateFilePath, encodingFormat);
            template.process(paramMap, out);
            out.flush();
        } catch (Exception e) {
            logger.error("生成异常!", e);
        }
    }
}
