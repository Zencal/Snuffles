package net.zencal.snuffles;

import net.zencal.snuffles.service.InitialisationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
        basePackageClasses = { Application.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new SpringApplication(Application.class).run(args);
        InitialisationService initialisationService = (InitialisationService) applicationContext.getBean("initialisationService");
        initialisationService.init(args);
    }
}
