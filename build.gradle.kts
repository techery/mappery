buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    from("gradle/plugins/maven-simple.gradle")
}

val kotlinVersion = "1.1.61"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.6)
    setTargetCompatibility(1.6)
}

repositories {
    gradleScriptKotlin()
    maven { setUrl("http://central.maven.org/maven2/") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.8.0")
    testImplementation("org.jetbrains.spek:spek-api:1.1.2")
    testImplementation("org.jetbrains.spek:spek-junit-platform-engine:1.1.2")
    testImplementation("org.junit.platform:junit-platform-runner:1.0.0-M4")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
}

fun kotlin(module: String) = "org.jetbrains.kotlin:kotlin-$module:$kotlinVersion"
