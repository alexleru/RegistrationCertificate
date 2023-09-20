package rs.alexleru.registrationcertificate.pdfCreator

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.TextPaint
import androidx.core.content.res.ResourcesCompat
import rs.alexleru.registrationcertificate.R
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.Template
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.TemplateTools
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.converter
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.toPostScript
import java.io.File
import java.io.FileOutputStream

class PdfConstructor(private val template: Template, private val context: Context) : PdfDocument() {

    private fun createForm() {
        val pageInfo =
            PageInfo.Builder(
                PAGE_WIDTH_A4,
                PAGE_HEIGHT_A4,
                1
            ).create()
        val page = startPage(pageInfo)

        val pdfCanvas = page.canvas
        val paint = Paint().apply {
            //paint.pathEffect = DashPathEffect(floatArrayOf(2f, 2f), 0f)
            strokeWidth = 0.2f.toPostScript()
        }

        val textPaint = TextPaint().apply {
            textSize = 8f
            val typefaceRes = ResourcesCompat.getFont(context, R.font.merriweather)
            typeface = Typeface.create(typefaceRes, Typeface.NORMAL)
        }

        template.arrayOfLines().map { it.converter() as TemplateTools.Line }
            .forEach { pdfCanvas.drawLine(it.startX, it.startY, it.endX, it.endY, paint) }

        template.arrayOfText().map { it.converter() as TemplateTools.Text }
            .forEach { pdfCanvas.drawText(it.value, it.startX, it.startY, textPaint) }

        finishPage(page)
    }


    fun savePdf() {
        createForm()

        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val name = "$NAME_FILE.$TYPE_OF_FILE"
        var file = File(dir, name)
        var index = 2
        while (true) {
            val newNameFile = "$NAME_FILE($index).$TYPE_OF_FILE"
            if (file.exists()) {
                file = File(dir, newNameFile)
                index++
            } else {
                break
            }
        }
        val fileOutputStream = FileOutputStream(file)
        writeTo(fileOutputStream)
    }

    companion object {
        private val PAGE_WIDTH_A4 = 210.toPostScript()
        private val PAGE_HEIGHT_A4 = 297.toPostScript()

        private const val NAME_FILE = "registrationForm"
        private const val TYPE_OF_FILE = "pdf"
    }
}