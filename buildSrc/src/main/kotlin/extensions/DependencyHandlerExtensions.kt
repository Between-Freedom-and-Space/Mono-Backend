package extensions

import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.ProjectDependency
import org.gradle.api.artifacts.Dependency

fun DependencyHandler.implementation(dependency: ProjectDependency): Dependency? =
    add("implementation", dependency.fullPath)


fun DependencyHandler.runtimeOnly(dependency: ProjectDependency): Dependency? =
    add("runtimeOnly", dependency.fullPath)


fun DependencyHandler.developmentOnly(dependency: ProjectDependency): Dependency? =
    add("developmentOnly", dependency.fullPath)


fun DependencyHandler.api(dependency: ProjectDependency): Dependency? =
    add("api", dependency.fullPath)

fun DependencyHandler.kapt(dependency: ProjectDependency): Dependency? =
    add("kapt", dependency.fullPath)


fun DependencyHandler.testImplementation(dependency: ProjectDependency): Dependency? =
    add("testImplementation", dependency.fullPath)

fun DependencyHandler.annotationProcessor(dependency: ProjectDependency): Dependency? =
    add("annotationProcessor", dependency.fullPath)

private val ProjectDependency.fullPath: String
    get() = "$group:$artifact:$version"