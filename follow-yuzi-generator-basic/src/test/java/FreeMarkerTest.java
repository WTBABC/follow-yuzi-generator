import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {

    @Test
    public void test() throws IOException, TemplateException {
        // VERSION_2_3_32：FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在路径
        configuration.setDirectoryForTemplateLoading(new File("src\\main\\resources\\templates"));
        // 设置模板文件的字符集
        configuration.setDefaultEncoding("utf-8");
        // 0.# 代表最少1位整数，和最多一位小数
        // 此处用于去掉数字分割','，此处因为是只有年份，整数，所以 .# 去掉也是可以的
        configuration.setNumberFormat("0.#");

        // 加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 1000000);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://codefather.cn");
        menuItem1.put("label", "编程导航");
        Map<String, Object> menuItem2 = new HashMap<>();
        menuItem2.put("url", "https://laoyujianli.com");
        menuItem2.put("label", "老鱼简历");
        menuItems.add(menuItem1);
        menuItems.add(menuItem2);
        dataModel.put("menuItems", menuItems);

        // 指定生成的文件路径和名称
        Writer out = new FileWriter("myweb.html");

        // 根据模板和数据模型，生成文件到指定的目标
        template.process(dataModel, out);

        //关闭
        out.close();
    }
}
