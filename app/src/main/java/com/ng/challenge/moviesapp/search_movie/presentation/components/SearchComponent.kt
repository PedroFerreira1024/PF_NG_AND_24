package com.ng.challenge.moviesapp.search_movie.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ng.challenge.moviesapp.search_movie.presentation.MovieSearchEvent

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it))
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query)
                    keyboardController?.hide()
                }
            ) {
              Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        },
        placeholder = {
            Text(
                text = "Search Movies here"
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.secondary,
            cursorColor = MaterialTheme.colors.secondary,
            placeholderColor = MaterialTheme.colors.onPrimary,
            trailingIconColor = MaterialTheme.colors.secondary
        ),
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
    )
}


@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(
        query = "Search",
        onSearch = {

        },
        onQueryChangeEvent = {

        })
}