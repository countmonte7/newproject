package com.newproject.web.config.spring;

import com.newproject.web.controller.MemberController;
import com.newproject.web.controller.StudyController;
import com.newproject.web.dao.MemberDao;
import com.newproject.web.dao.StudyDao;
import com.newproject.web.service.MemberService;
import com.newproject.web.service.StudyService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;


import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-default.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource(
            @Value("${jdbc.driver-name}") String driverClass,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password
    ) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClass);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public MemberDao dao(JdbcTemplate jdbcTemplate) {
        return new MemberDao(jdbcTemplate);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MemberService memberService(MemberDao memberDao) {
        return new MemberService(memberDao);
    }

    @Bean
    public MemberController memberController(MemberService service) {
        return new MemberController(service);
    }

    @Bean
    public StudyDao studyDao(JdbcTemplate jdbcTemplate) {return new StudyDao(jdbcTemplate);}

    @Bean
    public StudyService studyService(StudyDao studyDao) {return new StudyService(studyDao);}

    @Bean
    public StudyController studyController(StudyService studyService) {return new StudyController(studyService);}

}