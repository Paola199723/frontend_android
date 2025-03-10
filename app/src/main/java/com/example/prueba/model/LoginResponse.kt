package com.example.prueba.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse( val auth_token: String): Parcelable
