
object versions  {
	var commons_io_version = "2.13.0"
	var springdoc_version = "2.1.0"
	var knife4j_version = "4.1.0"
}

plugins {
	java
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "cn.qingtangbaimian.kafka"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}


extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-api:${versions.springdoc_version}")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${versions.springdoc_version}")
	implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:${versions.knife4j_version}")
	implementation("org.apache.kafka:kafka-streams")
	implementation("commons-codec:commons-codec")
	implementation("org.apache.commons:commons-lang3")
	implementation("commons-io:commons-io:${versions.commons_io_version}")
	implementation("org.yaml:snakeyaml")
	implementation("io.r2dbc:r2dbc-h2")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
//
//tasks.test {
//	outputs.dir(snippetsDir)
//}
//
//tasks.asciidoctor {
//	inputs.dir(snippetsDir)
//	dependsOn(test)
//}
