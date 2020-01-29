package com.example.projectnews.newstories

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
    data class News(
        val by: String? = null,
        val descendants: String? = null,
        val id: Int,
        val kids: List<Int>? = null,
        val score : Int,
        val time: Long,
        val title: String? = null,
        val type: String?= null,
        val url: String? = null
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createIntArray()?.toList(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(by)
        parcel.writeString(descendants)
        parcel.writeInt(id)
        parcel.writeIntArray(kids?.toIntArray())
        parcel.writeInt(score)
        parcel.writeLong(time)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}