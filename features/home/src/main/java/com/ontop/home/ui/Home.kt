package com.ontop.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ontop.home.R

@Composable
fun Home(
    onNavigateToMoviesApp: () -> Unit,
    onNavigateToInput: () -> Unit,
    onNavigateToPasswordGenerator: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Column {
            Button(onNavigateToMoviesApp, stringResource(id = R.string.movies_title))
            Button(onNavigateToInput, stringResource(id = R.string.input_app_title))
            Button(
                onNavigateToPasswordGenerator,
                stringResource(id = R.string.password_generator)
            )

        }
    }
}

@Composable
fun Button(onclick: () -> Unit, buttonTitle: String, modifier: Modifier = Modifier) {
    ElevatedButton(
        onClick = onclick, modifier = modifier
            .fillMaxWidth()
    ) {
        Text(buttonTitle)
    }
    _gap()

}

@Composable
fun _gap() {
    Spacer(modifier = Modifier.height(10.dp))
}