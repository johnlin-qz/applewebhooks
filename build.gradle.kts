import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.13.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
	kotlin("plugin.jpa") version "1.2.71"
	kotlin("kapt") version "1.2.71"
}

group = "com.quizlet.hack"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.squareup.moshi:moshi:1.8.0")
	kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.cloud:spring-cloud-gcp-starter-sql-mysql:1.2.2.RELEASE")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks {
	val bootRun by getting(org.springframework.boot.gradle.tasks.run.BootRun::class) {
		jvmArgs=listOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=32323")
		environment("GOOGLE_APPLICATION_CREDENTIALS", "/Users/johnlin/Downloads/applewebhooks/quizlet-development-dfc5b9305547.json")
	}
}
