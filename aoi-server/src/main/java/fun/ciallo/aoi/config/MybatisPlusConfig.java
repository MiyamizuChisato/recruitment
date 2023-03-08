package fun.ciallo.aoi.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("fun.ciallo.aoi.mapper")
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor pageInterceptor() {
        MybatisPlusInterceptor pageInterceptor = new MybatisPlusInterceptor();
        pageInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return pageInterceptor;
    }
}
