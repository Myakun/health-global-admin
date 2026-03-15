buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-core:10.20.1")
        classpath("org.flywaydb:flyway-database-postgresql:10.20.1")
        classpath("org.postgresql:postgresql:42.7.5")
    }
}

plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.zanosov"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.flywaydb:flyway-database-postgresql")

    implementation("com.fasterxml.uuid:java-uuid-generator:4.3.0")
    implementation("me.paulschwarz:spring-dotenv:4.0.0")
    implementation("org.jspecify:jspecify:1.0.0")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}

fun flywayConfig(): org.flywaydb.core.Flyway {
    val host = System.getenv("APP_POSTGRES_HOST") ?: error("APP_POSTGRES_HOST not set")
    val port = System.getenv("APP_POSTGRES_PORT") ?: error("APP_POSTGRES_PORT not set")
    val db = System.getenv("APP_POSTGRES_DB") ?: error("APP_POSTGRES_DB not set")
    val user = System.getenv("APP_POSTGRES_USER") ?: error("APP_POSTGRES_USER not set")
    val password = System.getenv("APP_POSTGRES_PASS") ?: error("APP_POSTGRES_PASS not set")

    return org.flywaydb.core.Flyway.configure()
        .dataSource("jdbc:postgresql://$host:$port/$db", user, password)
        .locations("filesystem:${projectDir}/src/main/resources/db/migration")
        .cleanDisabled(false)
        .load()
}

tasks.register("flywayMigrate") {
    group = "flyway"
    doLast { flywayConfig().migrate() }
}

tasks.register("flywayClean") {
    group = "flyway"
    doLast { flywayConfig().clean() }
}

tasks.register("flywayInfo") {
    group = "flyway"
    doLast {
        val info = flywayConfig().info()
        info.all().forEach { m ->
            println("${m.version ?: "-"}\t${m.description}\t${m.state}")
        }
    }
}
