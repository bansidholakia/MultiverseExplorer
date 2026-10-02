package com.bd.multiverseexplorer.presentation.common

import com.bd.shared.domain.model.AppError

fun AppError.toMessage(): String {
    return when (this) {
        AppError.Network ->
            "No internet connection"

        AppError.NotFound ->
            "Character not found"

        AppError.Server ->
            "Server error. Please try again."

        AppError.Unknown ->
            "Something went wrong"
    }
}