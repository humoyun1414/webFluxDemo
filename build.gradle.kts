apply(plugin = "org.springframework.boot")
plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "uz.humoyun"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


repositories {
    mavenCentral()
}

dependencies {
    // Spring Framework
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp:4.0.0")


    // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-config
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.0.0")


    // EUREKA CLIENT
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.0.0")

//    RabbitMQ
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp
    implementation("org.springframework.boot:spring-boot-starter-amqp:3.0.2")


    // Liquibase
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework:spring-jdbc")

    //Immutables
    annotationProcessor("org.immutables:value:2.9.3")
    compileOnly("org.immutables:builder:2.9.3")
    compileOnly("org.immutables:value-annotations:2.9.3")


    // Postgres
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.2.Final")
    // Test
    testImplementation("io.projectreactor:reactor-test")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mockserver")
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.mockito:mockito-inline")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mock-server:mockserver-junit-jupiter:5.13.2")
//       Faker
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    implementation("com.github.javafaker:javafaker:1.0.2")
//    implementation("com.github.javafaker:javafaker:${rootProject.extra.get("fakerVersion")}")


    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

dependencyManagement {
    imports {// https://github.com/testcontainers/testcontainers-java/releases
        mavenBom("org.testcontainers:testcontainers-bom:1.17.3")

    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
