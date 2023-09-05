package rs.alexleru.registrationcertificate.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import rs.alexleru.registrationcertificate.domain.model.Document

object ListOfDocumentsDiffUtils: DiffUtil.ItemCallback<Document>() {
    override fun areItemsTheSame(oldItem: Document, newItem: Document)
    = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Document, newItem: Document)
    = oldItem == newItem
}