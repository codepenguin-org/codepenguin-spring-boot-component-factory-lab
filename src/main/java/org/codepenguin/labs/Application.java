/*
 * MIT License
 *
 * Copyright (c) 2022 CodePenguin.org - Jorge Alfonso Garcia Espinosa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package org.codepenguin.labs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Application.
 *
 * @author Jorge Garcia
 * @version 1.0.0
 * @since 17
 */
@SpringBootApplication
public class Application {

    /**
     * Main method.
     *
     * @param args the system arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI openAPI() {
        final var title = "codepenguin-spring-boot-component-factory-lab";
        final var description = "Spring Boot REST application which exemplifies how to use Qualifier to use the " +
                "Factory design pattern to obtain a service based on a country used as a parameter.";
        final var version = "v1.0.0";
        final var license = "MIT License";
        final var licenseFile = "LICENSE.md";
        final var urlPattern = "https://github.com/codepenguin-org/codepenguin-spring-boot-component-factory-lab/blob/main/%s";
        final var licenseUrl = urlPattern.formatted(licenseFile);
        final var readmeUrl = urlPattern.formatted(licenseFile);
        return new OpenAPI()
                .info(new Info().title(title).description(description).version(version)
                        .license(new License().name(license).url(licenseUrl)))
                .externalDocs(new ExternalDocumentation().description("README").url(readmeUrl));
    }
}
