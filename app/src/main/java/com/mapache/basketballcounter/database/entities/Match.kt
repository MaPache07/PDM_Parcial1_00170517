package com.mapache.basketballcounter.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_table")
data class Match (
    @PrimaryKey(autoGenerate = true) var id : Long,
    var teamA : String,
    var teamB : String,
    var scoreA : Int,
    var scoreB : Int,
    var winner : String,
    var date : String,
    var time : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(teamA)
        dest.writeString(teamB)
        dest.writeInt(scoreA)
        dest.writeInt(scoreB)
        dest.writeString(winner)
        dest.writeString(date)
        dest.writeString(time)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}