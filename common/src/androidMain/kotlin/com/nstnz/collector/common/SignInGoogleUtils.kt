package com.nstnz.collector.common

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nstnz.collector.common.basic.di.SharedDI
import com.nstnz.collector.common.feature.core.domain.usecase.auth.SaveGoogleAuthTokenUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kodein.di.instance

private const val Token = "760863961066-vcsvurqf6a9cqqa6h7a8sejc42558ls4.apps.googleusercontent.com"

private val googleSignInOptions = GoogleSignInOptions
    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestIdToken(Token)
    .requestEmail()
    .build()

actual fun launchGoogleSignIn() {
    Android.activity.launchIntent(
        GoogleSignIn.getClient(Android.context, googleSignInOptions)
            .signInIntent
    )
}

fun saveGoogleAuthToken(data: Intent) {
    val result = GoogleSignIn.getSignedInAccountFromIntent(data).result
    val useCase: SaveGoogleAuthTokenUseCase by SharedDI.di.instance()
    val scope: CoroutineScope by SharedDI.di.instance()
    useCase.invoke(result.idToken, result.email, result.displayName, result.photoUrl.toString())
    Firebase.initialize(Android.context)

    if (!result.idToken.isNullOrEmpty()) {
        scope.launch {
            Firebase.auth.signInWithCredential(
                GoogleAuthProvider.credential(
                    accessToken = null,
                    idToken = result.idToken
                )
            )
        }
    }
}

actual fun logout() {
    val googleClient = GoogleSignIn.getClient(Android.context, googleSignInOptions)
    googleClient.signOut()
    val useCase: SaveGoogleAuthTokenUseCase by SharedDI.di.instance()
    useCase.invoke(null)
}
