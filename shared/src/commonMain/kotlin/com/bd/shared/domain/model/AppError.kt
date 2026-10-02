package com.bd.shared.domain.model

sealed interface AppError {

    data object Network :
        AppError

    data object NotFound :
        AppError

    data object Server :
        AppError

    data object Unknown :
        AppError
}