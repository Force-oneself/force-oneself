package com.quan.demo.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @Description ExcelDemo.java
 * @date 2021-06-07
 */
public class ExcelDemo {

    public static void main(String[] args) {
        List<Entity> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Entity entity = new Entity();
            entity.setAge(23);
            entity.setDesc("dkkkdkkkkkkkkdjakfjdkjfldkjlkfjdljflkdjksssssssssssssfdasfdsafdsafdsfewrewrqeqredfadfadjafkldjl;kfjd;klsajfkdjs;alfjd;laskfjdlk;sjf;ldksjaf;lkdjska;lfjdlk;safj;dlsjafk;dsjafdl;ksajf;dksja;fjdsaklfjd;lsakfjdl;kjafdk;ljsaf;lkdja;lfkjdl;kjf;lkdjas;fkljdlskfjldkasjfl;kdjal;sfkjd;kajf;lkdjskrewqrewrewqrewrqek" + i);
            entity.setName("zhangsan");
            data.add(entity);
        }

        EasyExcelUtil.write("C:\\Users\\Administrator\\Desktop\\demo.xlsx", Entity.class, "sheet", data);
    }
}
