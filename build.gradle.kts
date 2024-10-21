@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.*
import java.util.*

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")
}

val current = libs.me.crypto.get()

group = current.group
version = current.version!!


kotlin {
    jvmToolchain(17)

    jvm()
    js(IR){
        browser()
    }
    wasmJs{
        browser()
    }
}

publishing {
    val rName = propertiesLocal["repsy.name"]
    val rKey = propertiesLocal["repsy.key"]
    repositories {
        mavenLocal()
        maven {
            name = "repsy.io"
            url = uri("https://repo.repsy.io/mvn/${rName}/public")
            credentials {
                username = rName
                password = rKey
            }
        }
    }
}

val Project.propertiesLocal: LocalProperties get() = LocalProperties.get(this)

class LocalProperties private constructor(private val project: Project) {
    val prop = Properties().apply {
        project.rootProject.file("local.properties").reader().use {
            load(it)
        }
    }

    companion object {
        private lateinit var instance: LocalProperties
        internal fun get(project: Project): LocalProperties {
            if (!::instance.isInitialized) {
                instance = LocalProperties(project)
            }
            return instance
        }
    }

    operator fun get(key: String): String? = prop[key] as? String
}
