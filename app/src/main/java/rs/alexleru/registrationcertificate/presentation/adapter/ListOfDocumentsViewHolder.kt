package rs.alexleru.registrationcertificate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import rs.alexleru.registrationcertificate.R
import rs.alexleru.registrationcertificate.databinding.ItemOfDocumentBinding
import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.presentation.mapper.toStr

class ListOfDocumentsViewHolder(
    private val binding: ItemOfDocumentBinding,
    private val onClickItemListener: (Long) -> Unit
) :
    ViewHolder(binding.root) {

    fun bind(document: Document) {
        val context = binding.root.context
        with(binding) {
            val nameSurnameTemplates = context.resources.getString(R.string.name_surname)
            name.text = String.format(nameSurnameTemplates, document.surname, document.name)
            address.text = document.addressStay
            regDate.text = document.dateOfRegistration
            root.setOnClickListener {
                onClickItemListener.invoke(document.id)
            }
        }
    }

}