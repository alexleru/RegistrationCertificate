package rs.alexleru.registrationcertificate.pdfCreator.templateUtils

interface Template {

    fun arrayOfLines(): List<TemplateTools>

    fun arrayOfText(): List<TemplateTools>

}