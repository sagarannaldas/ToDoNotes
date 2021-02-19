package `in`.techrebounce.todonotes.clicklisteners

import `in`.techrebounce.todonotes.db.Note

interface ItemClickListener {
    fun onClick(note: Note)
    fun onUpdate(note: Note)
}