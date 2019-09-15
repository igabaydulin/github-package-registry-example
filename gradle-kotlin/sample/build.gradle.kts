plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("net.linguica.maven-settings") version "0.5"
}

repositories {
    jcenter()
    maven {
        name = "github"
        url = uri("https://maven.pkg.github.com/igabaydulin/github-package-registry-example")
    }
}

dependencies {
    implementation("com.github.igabaydulin:kotlin-example-ping:1.1.0")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "com.github.igabaydulin.sample.AppKt"
}
