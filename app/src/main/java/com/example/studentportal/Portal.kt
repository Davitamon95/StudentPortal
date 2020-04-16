package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *Parcialize zorgt ervoor dat data door meerdere activitys kan worden doorgegeven
 */
@Parcelize
data class Portal (
    var title: String,
    var url: String
) : Parcelable