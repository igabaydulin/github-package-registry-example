# How to Use GitHub Package Registry Basics

_GitHub Package Registry integration examples for Java projects_

## GitHub Package Registry Public Beta
**Notice**: GitHub Package Registry is not yet released and to be able to publish package into GitHub Package Registry you must
have access to the public beta. To request to join the limited public beta, see the [GitHub Package Registry page](https://github.com/features/package-registry).
You will be notified by email when you've gained access to public beta.

_P.S. I don't know if there are requirements for public beta access to be able to download packages. Let me know it there are no requirements 🙂_

## Repository Structure
There are a few examples illustrating how to integrate GitHub Package Registry into your project:
* [Configuring Maven project](https://github.com/igabaydulin/github-package-registry-example/tree/master/maven)
  ```
  maven
  ├── pom.xml
  ├── lib
  │   ├── pom.xml
  │   └── src
  │       ├── main
  │       │   └── java
  │       │       └── com.github.igabaydulin.sample
  │       │           └── Ping.java
  │       └── test
  │           └── java
  │               └── com.github.igabaydulin.sample
  │                   └── PingTest.java
  └── sample
      ├── pom.xml
      └── src
          ├── main
          │   └── java
          │       └── com.github.igabaydulin.sample
          │           └── App.java
          └── test
              └── java
                  └── com.github.igabaydulin.sample
                      └── AppTest.java
  ```
* [Configuring Gradle project](https://github.com/igabaydulin/github-package-registry-example/tree/master/gradle-groovy)
  ```
  gradle-groovy
  ├── build.gradle
  ├── settings.gradle
  ├── gradle
  │   └── wrapper
  │       ├── gradle-wrapper.jar
  │       └── gradle-wrapper.properties
  ├── gradlew
  ├── gradlew.bat
  ├── lib
  │   ├── build.gradle
  │   └── src
  │       ├── main
  │       │   └── java
  │       │       └── com.github.igabaydulin.ping
  │       │           └── Ping.java
  │       └── test
  │           └── java
  │               └── com.github.igabaydulin.ping
  │                   └── PingTest.java
  └── sample
      ├── build.gradle
      └── src
          ├── main
          │   └── java
          │       └── com.github.igabaydulin.sample
          │           └── App.java
          └── test
              └── java
                  └── com.github.igabaydulin.sample
                      └── AppTest.java
  ```
* [Configuring Gradle project using Kotlin DSL](https://github.com/igabaydulin/github-package-registry-example/tree/master/gradle-kotlin)
  ```
  gradle-kotlin
  ├── build.gradle.kts
  ├── settings.gradle.kts
  ├── gradle
  │   └── wrapper
  │       ├── gradle-wrapper.jar
  │       └── gradle-wrapper.properties
  ├── gradlew
  ├── gradlew.bat
  ├── lib
  │   ├── build.gradle.kts
  │   └── src
  │       ├── main
  │       │   └── kotlin
  │       │       └── com.github.igabaydulin.ping
  │       │           └── Ping.kt
  │       └── test
  │           └── kotlin
  │               └── com.github.igabaydulin.ping
  │                   └── PingTest.kt
  └── sample
      ├── build.gradle.kts
      └── src
          ├── main
          │   └── kotlin
          │       └── com.github.igabaydulin.sample
          │           └── App.kt
          └── test
              └── kotlin
                  └── com.github.igabaydulin.sample
                      └── AppTest.kt
  ```

## Useful Links:
* [1] [About GitHub Package Registry](https://help.github.com/en/articles/about-github-package-registry)
* [2] [Managing packages with GitHub Package Registry](https://help.github.com/en/categories/managing-packages-with-github-package-registry)
* [3] [Configuring Apache Maven for use with GitHub Package Registry](https://help.github.com/en/articles/configuring-apache-maven-for-use-with-github-package-registry)

## Prerequisites
### Public Beta Access
As was mentioned before make sure you have an access to public beta 🙂 (you can use [this link](https://github.com/features/package-registry) to sign up). 
<br/><br/>
### Create GitHub Personal Access Token
You need to create a personal access token with the `read:packages` and `write:packages` scopes to publish and download packages. [This article](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line) explains how to create a personal access token.
<br/><br/>
### Configuring Apache Maven to Use Generated Token
As described in [Configuring Apache Maven for use with GitHub Package Registry](https://help.github.com/en/articles/configuring-apache-maven-for-use-with-github-package-registry) article
you need to provide Maven generated token (configuring Maven to use GitHub Registry as Maven repository along the way) by editing `~/.m2/settings.xml` (if it does not exists create a new one).

**Notice:** It doesn't matter if you're using Gradle instead, create or modify `~/.m2/settings.xml` as well.

`~/.m2/settings.xml` should look like this (example is taken from the article):
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>true</enabled></snapshots>
        </repository>
        <repository>
          <id>github</id>
          <name>GitHub OWNER Apache Maven Packages</name>
          <url>https://maven.pkg.github.com/OWNER</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```
where
* `OWNER`: the repository/packages owner (if you wanna try to download packages from this repository, use `igabaydulin`)
* `USERNAME`: your GitHub username
* `TOKEN`: personal access token generated on the previous step

## Project Configuration to Publish Packages
## Maven Project
Besides providing the necessary Maven plugins (maven-deploy-plugin as an example) or using the built-in plugins versions
you need to configure where you will publish the package.
Add these lines in your `pom.xml` file:
```xml
<distributionManagement>
  <repository>
    <id>github</id> <!-- this id must be equal to id in ~/.m2/settings.xml file! -->
    <name>GitHub igabaydulin Apache Maven Packages</name>
    <url>https://maven.pkg.github.com/igabaydulin/github-package-registry-example</url>
  </repository>
</distributionManagement>
```
### Package Publishing
```bash
mvn deploy
```
### Details
You can take a look at [maven](https://github.com/igabaydulin/github-package-registry-example/tree/master/maven) project for more details

## Gradle Project
By default Gradle does not use `~/.m2/settings.xml`. To fix this we can use one of the Gradle plugins. I recommend to use
[net.linguica.maven-settings](https://plugins.gradle.org/plugin/net.linguica.maven-settings) plugin:
```groovy
plugins {
  id "net.linguica.maven-settings" version "0.5"
}
```

To be able to publish packages you also must use `maven-publish` plugin:
```
  id "maven-publish"
```
You can read about current publishing mechanism in [this](https://docs.gradle.org/current/userguide/publishing_overview.html) Gradle article.
Your publishing configuration may look like this:
```groovy
publishing {
    publishing {
        publications {
            lib(MavenPublication) {
                artifactId = "groovy-example-ping" // not required if you project name is equal to artifactId (in my example project has "lib" name)
                from components.java // otherwise you will get an empty package and publishing step will be skipped
            }
        }

        repositories {
            maven {
                name = "github" // must be equal to id in ~/.m2/settings.xml file!
                url = uri("https://maven.pkg.github.com/igabaydulin/github-package-registry-example")
            }
        }
    }
}
```
### Package Publishing
```bash
./gradlew publish
```
### Details
You can take a look at [gradle-groovy](https://github.com/igabaydulin/github-package-registry-example/tree/master/gradle-groovy) project for more details

## Gradle Project with Kotlin DSL
The configuration is pretty the same as a previous one.

First, specify the necessery plugins:
```kotlin
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("maven-publish")
    id("net.linguica.maven-settings") version "0.5"
}
```

Second, configure publishing:
```kotlin
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
```
### Package Publishing
You can use the same command described in the previous section (obviously! 🤠):
```bash
./gradlew publish
```
### Details
And as always you can take a look at [gradle-kotlin](https://github.com/igabaydulin/github-package-registry-example/tree/master/gradle-kotlin) project for more details

## Few Words about Dependency Configuration
## Maven Project
Add the next dependency:
```xml
<dependency>
    <groupId>com.github.igabaydulin</groupId>
    <artifactId>maven-example-ping</artifactId>
    <version>1.0.0</version>
</dependency>
```
And... that's it! 😃

## Gradle Project
Add the next dependency:
```groovy
dependencies {
    implementation 'com.github.igabaydulin:groovy-example-ping:1.0.1'
}
```

And... and do not forget to provide maven-settings plugin:
```groovy
plugins {
  id("net.linguica.maven-settings") version "0.5"
}
```

And... and specify repository to download the package:
```groovy
repositories {
    maven {
        name = "github"
        url = uri("https://maven.pkg.github.com/igabaydulin/github-package-registry-example")
    }
}
```
_Note: I don't even know why this is required (maybe some lack of code in maven-settings plugin). If you know the reason, please, DM me :)_

And... that's it! 😫

## Gradle Project with Kotlin DSL
Pretty the same.

Add plugins:
```kotlin
plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.3.41"
    id("net.linguica.maven-settings") version "0.5"
}
```

Specify repository:
```kotlin
repositories {
    jcenter()
    maven {
        name = "github"
        url = uri("https://maven.pkg.github.com/igabaydulin/github-package-registry-example")
    }
}
```

Add dependency:
```kotlin
dependencies {
    implementation("com.github.igabaydulin:kotlin-example-ping:1.0.0")
}
```
