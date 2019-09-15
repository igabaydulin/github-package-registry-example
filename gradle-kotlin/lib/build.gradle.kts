plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("maven-publish")
    id("net.linguica.maven-settings") version "0.5"
}

version = "1.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

publishing {
    publications {
        create<MavenPublication>("lib") {
            artifactId = "kotlin-example-ping"
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/igabaydulin/github-package-registry-example")
        }
    }
}
