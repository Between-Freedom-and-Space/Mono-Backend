package com.between_freedom_and_space.mono_backend.mailing.components.impl

import com.between_freedom_and_space.mono_backend.mailing.components.EmailBodyProvider
import com.between_freedom_and_space.mono_backend.mailing.components.exceptions.InvalidEmailHtmlTemplateException
import com.between_freedom_and_space.mono_backend.mailing.components.model.SendVerificationCodeBodyParams
import java.nio.charset.Charset

class ResourceEmailBodyProvider: EmailBodyProvider {

    companion object {

        private const val VERIFICATION_CODE_ALIAS = "{{VERIFICATION_CODE}}"

        private const val VERIFICATION_CODE_TEMPLATE_NAME = "send_verification_code_template.html"
    }

    override fun provideSendVerificationCodeBody(params: SendVerificationCodeBodyParams): String {
        var html = getHtml(VERIFICATION_CODE_TEMPLATE_NAME)

        html = html.replace(VERIFICATION_CODE_ALIAS, params.verificationCode)

        return html
    }

    private fun getHtml(name: String): String {
        return this.javaClass.classLoader
            .getResource("html/$name")
            ?.readText(Charset.defaultCharset())
            ?: throw InvalidEmailHtmlTemplateException("Email html template with name: $name not found")
    }
}