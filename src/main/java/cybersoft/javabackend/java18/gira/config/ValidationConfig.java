package cybersoft.javabackend.java18.gira.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
    @Bean
    public MessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource
                messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:/validation/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

        validator.setValidationMessageSource(this.getMessageSource());

        return validator;
    }
}
