package `in`.techrebounce.todonotes.clicklisteners

import `in`.techrebounce.todonotes.model.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
}