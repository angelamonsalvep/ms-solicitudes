package co.com.credilya.solicitudes.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCasesConfigTest {

    @Test
    void testUseCaseBeansExist() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class)) {
            String[] beanNames = context.getBeanDefinitionNames();

            boolean useCaseBeanFound = false;
            for (String beanName : beanNames) {
                if (beanName.endsWith("UseCase")) {
                    useCaseBeanFound = true;
                    break;
                }
            }

            assertTrue(useCaseBeanFound, "No beans ending with 'Use Case' were found");
        }
    }

    @Configuration
    @Import(UseCasesConfig.class)
    static class TestConfig {

        @Bean
        public MyUseCase myUseCase() {
            return new MyUseCase();
        }

        // Bean mock para SolicitudRepository
        @Bean
        public co.com.credilya.solicitudes.model.solicitud.gateways.SolicitudRepository solicitudRepository() {
            return org.mockito.Mockito.mock(co.com.credilya.solicitudes.model.solicitud.gateways.SolicitudRepository.class);
        }

        // Bean mock para TipoPrestamoRepository
        @Bean
        public co.com.credilya.solicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository tipoPrestamoRepository() {
            return org.mockito.Mockito.mock(co.com.credilya.solicitudes.model.tipoprestamo.gateways.TipoPrestamoRepository.class);
        }

        // Bean mock para AutenticacionGateway
        @Bean
        public co.com.credilya.solicitudes.model.autenticacion.AutenticacionGateway autenticacionGateway() {
            return org.mockito.Mockito.mock(co.com.credilya.solicitudes.model.autenticacion.AutenticacionGateway.class);
        }
    }

    static class MyUseCase {
        public String execute() {
            return "MyUseCase Test";
        }
    }
}