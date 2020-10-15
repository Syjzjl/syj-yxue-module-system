package com.baizhi.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Created by 
 */
public class CodeGeneration {

    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir("F:\\")// 设置代码生成输出的位置
                .setAuthor("syj") // 设置作者
                .setBaseResultMap(true) // 设置mapper文件中是否生成resultMap
                .setBaseColumnList(true) // 设置mapper文件中是否生成sql片段
                .setFileOverride(true) // 设置如果指定的位置已经生成过代码，是否进行覆盖
                .setControllerName("%sController") // 设置控制器类名规则
                .setServiceName("%sService") // 设置service接口类名规则
                .setServiceImplName("%sServiceImpl")  // 设置service实现类 类名规则
                .setMapperName("%sMapper") // 设置dao接口名规则
                .setXmlName("%sMapper") // 设置mapper文件名规则
                ;
        //2. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/yx")
                .setUsername("root")
                .setPassword("syjzjl");

        //3. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig()
                .setTablePrefix(new String[]{"yx_"}) // 设置表名的前缀，未来生成的实体类的类名会自动去掉前缀
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(new String[]{ // 指定要生成哪些表对应的实体类
                        "yx_log"
                })
                .setEntityLombokModel(true); // 设置生成lombok风格的实体类

        //4. 包结构配置
        PackageConfig packageConfig = new PackageConfig()
                .setParent("com.baizhi")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setEntity("entity")
                .setMapper("dao") // dao接口的包
                .setXml("mapper"); // mapper文件的包

        // 整合配置到代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig);

        autoGenerator.execute();

    }
}