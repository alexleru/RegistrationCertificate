package rs.alexleru.registrationcertificate.pdfCreator.form

import rs.alexleru.registrationcertificate.pdfCreator.model.DocumentModelPdf
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.Template
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.TemplateTools
import rs.alexleru.registrationcertificate.pdfCreator.templateUtils.multiLine

class RegistrationOfPlaceOfStay(model: DocumentModelPdf) : Template {

    private val _arrayOfLines = ArrayList<TemplateTools.Line>()
    override fun arrayOfLines() = _arrayOfLines.toList()

    private val _arrayOfText = ArrayList<TemplateTools.Text>()
    override fun arrayOfText() = _arrayOfText.toList()

    init {
        //horizontal header
        _arrayOfLines.add(TemplateTools.LineHorizontal(145f, 200F, 26f))

        //horizontal left column bottom
        _arrayOfLines.add(TemplateTools.LineHorizontal(96f, 196F, 268f))

        //horizontal
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 30f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 42f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 54f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 66f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 78f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 90f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 102f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 118f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 142f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 154f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 176f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 206f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 218f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 232f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 252f))
        _arrayOfLines.add(TemplateTools.LineHorizontal(10f, 200F, 275f))

        //vertical
        _arrayOfLines.add(TemplateTools.LineVertical(10f, 30f, 275f))
        _arrayOfLines.add(TemplateTools.LineVertical(200f, 30f, 275f))

        _arrayOfLines.add(TemplateTools.LineVertical(53f, 30f, 54f))
        _arrayOfLines.add(TemplateTools.LineVertical(92f, 54f, 275f))

        //header text
        _arrayOfText.add(TemplateTools.Text("Образац 1.", 180f, 11f))

        _arrayOfText.add(TemplateTools.Text("Серијски број/Serial number:", 150f, 17f))

        _arrayOfText.add(TemplateTools.Text("ПРИЈАВА БОРАВИШТА СТРАНЦА", 74f, 18f))

        _arrayOfText.add(TemplateTools.Text("REGISTRATION OF PLACE OF STAY", 73f, 22f))

        //static text left column

        _arrayOfText.add(
            TemplateTools.Text(
                "(потпис овлашћеног лица - signature of the authorized person)",
                100f,
                273f
            )
        )

        //static text right column

        _arrayOfText.add(TemplateTools.Text("Презиме - Surname", 12f, 37f))

        _arrayOfText.add(TemplateTools.Text(model.surname, 55f, 37f))

        _arrayOfText.add(TemplateTools.Text("Име - Name", 12f, 49f))

        _arrayOfText.add(TemplateTools.Text(model.name, 55f, 49f))

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Датум рођења\n" +
                        "Date of birth",
                12f,
                59f
            )
                .multiLine()
        )

        _arrayOfText.add(TemplateTools.Text(model.dateOfBirth, 94f, 61f))

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Пол\n" +
                        "Sex",
                12f,
                71f
            ).multiLine()
        )

        _arrayOfText.add(TemplateTools.Text(model.sex, 94f, 73f))

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Место и држава рођења \n" +
                        "Place and country of birth",
                12f,
                83f
            ).multiLine()
        )

        _arrayOfText.add(TemplateTools.Text(model.placeOfBirth, 94f, 85f))

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Држављанство\n" +
                        "Nationality",
                12f,
                95f
            ).multiLine()
        )

        _arrayOfText.add(TemplateTools.Text(model.nationality, 94f, 97f))

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Врста и број путне или друге исправе о идентитету\n" +
                        "Type and number of travel document or other ID",
                12f,
                109f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                model.typeOfDocument + "\n" +
                        model.numberOfDocument,
                94f,
                109f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Врста и број визе и место издавња\n" +
                        "Type and number of visa and place of issuance",
                12f,
                129f
            ).multiLine()
        )
        _arrayOfText.addAll(
            TemplateTools.Text(
                "Датум и место уласка у Републику Србију\n" +
                        "Date and place of entry into the Republic of Serbia",
                12f,
                147f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                model.dateIn + "\n" +
                        model.entryInto,
                94f,
                147f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Адреса боравишта у Републици Србији\n" +
                        "Аddress of place of stay in the Republic of Serbia",
                12f,
                165f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                model.addressStay,
                94f,
                167f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Податак о станодавцу (презиме и име и ЈМБГ, \n" +
                        "односно назив правног лица или предузетника и \n" +
                        "ПИБ)\n" +
                        "Surname, given name and personal identification \n" +
                        "number of the landlord/host ie, name of legal \n" +
                        "entity or entrepreneur and tax ID number).",
                12f,
                182f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                model.nameOfHost + "\n" +
                        model.numberIdOfHost,
                94f,
                190f
            ).multiLine()
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Датум пријаве\n" +
                        "Date of registration",
                12f,
                211f
            ).multiLine()
        )

        _arrayOfText.add(
            TemplateTools.Text(
                model.dateOfRegistration,
                94f,
                213f
            )
        )

        _arrayOfText.addAll(
            TemplateTools.Text(
                "Напомена\n" +
                        "Note",
                12f,
                224f
            ).multiLine()
        )
        _arrayOfText.addAll(
            TemplateTools.Text(
                "Потпис подносиоца пријаве\n" +
                        "Signature of the person who registers",
                12f,
                241f
            ).multiLine()
        )


    }
}