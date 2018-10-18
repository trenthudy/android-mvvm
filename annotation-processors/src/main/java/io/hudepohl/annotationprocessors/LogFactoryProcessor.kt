package io.hudepohl.annotationprocessors

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.hudepohl.annotations.LogFactory
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedOptions
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(LogFactoryProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
@SupportedAnnotationTypes(LogFactoryProcessor.SUPPORTED_ANNOTATION_TYPES)
class LogFactoryProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        val logFactories = roundEnvironment.getElementsAnnotatedWith(LogFactory::class.java)

        if (logFactories.any { it.kind != ElementKind.CLASS }) {
            logError("${LogFactory::class.java.simpleName} annotation can only be used on classes")
            return false
        }

        logFactories.forEach { generateLogFactory(it) }

        return false
    }

    private fun generateLogFactory(logFactoryElement: Element) {
        val packageName = getElementPackage(logFactoryElement)
        val factoryName = getFactoryName(logFactoryElement)
        val factoryId = getFactoryId(logFactoryElement)

        val logFun =
            FunSpec.builder("log")
                .addParameter("message", ClassName("kotlin", "String"))
                .addCode("""
                    val formattedMsg = message.trim()
                    if (formattedMsg.isNotEmpty()) {
                      println(formattedMsg)
                    }

                """.trimIndent())
                .build()

        val idProp =
            PropertySpec.builder("ID", Int::class)
                .initializer("%L", factoryId)
                .build()

        val companionObj =
            TypeSpec.companionObjectBuilder()
                .addProperty(idProp)
                .build()

        val genClazz =
            TypeSpec.classBuilder(factoryName)
                .addKdoc("""
                    Generated file. Do not fiddle...

                """.trimIndent())
                .addFunction(logFun)
                .addType(companionObj)
                .build()

        generateFile(packageName, factoryName, genClazz)
    }

    private fun generateFile(packageName: String, name: String, genClazz: TypeSpec) {
        val generatedRoot = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()

        if (generatedRoot.isEmpty()) {
            logError("Cannot find target dir for generated Kotlin Files")
            return
        }

        val dir = File(generatedRoot)
        dir.mkdir()

        FileSpec.builder(packageName, name)
            .addType(genClazz)
            .build()
            .writeTo(dir)
    }

    private fun logError(error: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, error)
    }

    private fun getElementPackage(clazzElement: Element) = processingEnv.elementUtils.getPackageOf(clazzElement).toString()
    private fun getFactoryName(clazzElement: Element) = "${clazzElement.simpleName}LogFactory"
    private fun getFactoryId(clazzElement: Element) = clazzElement.getAnnotation(LogFactory::class.java).id

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val SUPPORTED_ANNOTATION_TYPES = "io.hudepohl.annotations.LogFactory"
    }
}