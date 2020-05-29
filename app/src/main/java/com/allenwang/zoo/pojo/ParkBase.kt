package com.allenwang.zoo.pojo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParkBase(
    @SerializedName("result")
    val result: ParkResponse
)

data class ParkResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Park>,
    @SerializedName("sort")
    val sort: String
)

data class Park(
    @SerializedName("E_Category")
    val eCategory: String,
    @SerializedName("E_Geo")
    val eGeo: String,
    @SerializedName("E_Info")
    val eInfo: String,
    @SerializedName("E_Memo")
    val eMemo: String,
    @SerializedName("E_Name")
    val eName: String,
    @SerializedName("E_no")
    val eNo: String,
    @SerializedName("E_Pic_URL")
    val ePicURL: String,
    @SerializedName("E_URL")
    val eURL: String,
    @SerializedName("_id")
    val id: Int,

    var plants: List<Plant>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        mutableListOf<Plant>().apply { parcel.readTypedList(this, Plant.CREATOR)}
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eCategory)
        parcel.writeString(eGeo)
        parcel.writeString(eInfo)
        parcel.writeString(eMemo)
        parcel.writeString(eName)
        parcel.writeString(eNo)
        parcel.writeString(ePicURL)
        parcel.writeString(eURL)
        parcel.writeInt(id)
        parcel.writeTypedList(plants)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Park> {
        override fun createFromParcel(parcel: Parcel): Park {
            return Park(parcel)
        }

        override fun newArray(size: Int): Array<Park?> {
            return arrayOfNulls(size)
        }
    }
}