package com.erickhene.administrator.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@MapperScan(
        basePackages  = "com.erickhene.administrator.dao",
        sqlSessionFactoryRef = "erickheneSqlSessionFactory"
)
public class IbatisDataSource {

    private final Environment env;

    public IbatisDataSource(Environment env) {
        this.env = env;
    }

    @Bean(name = "erickheneDataSource")
    public DataSource erickheneDataSource(){
        final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));
        return driverManagerDataSource;
    }

    @Bean(name = "erickheneSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("erickheneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                "classpath*:mapper/*.xml"
        ));
        return sqlSessionFactoryBean.getObject();
    }

}
