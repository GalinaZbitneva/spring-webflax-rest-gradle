plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "me"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//testImplementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive-test")
	//testImplementation("org.springframework.boot:spring-boot-starter-webflux-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	//testImplementation ("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.11.0")
	testImplementation ("io.projectreactor:reactor-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
