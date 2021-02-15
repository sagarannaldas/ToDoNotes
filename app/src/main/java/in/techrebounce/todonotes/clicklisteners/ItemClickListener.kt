package `in`.techrebounce.todonotes.clicklisteners

import `in`.techrebounce.todonotes.db.Notes

interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}