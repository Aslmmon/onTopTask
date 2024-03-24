package com.ontop.inputapp.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R


@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
    val startText by upperCaseInputViewModel.startText.collectAsState()
    val resultText by upperCaseInputViewModel.resultText.collectAsState()

    Column {
        BasicTextField(
            singleLine = true,
            value = startText,
            onValueChange = {
                upperCaseInputViewModel.updateStartText(it)
            },
            modifier = modifier.padding(16.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color(0XFF4c8acc),
                            shape = RoundedCornerShape(10.dp)
                        )

                ) {
                    Row(
                        modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Outlined.Edit, contentDescription = "")
                        if (startText.isEmpty()) {
                            Text(text = stringResource(id = R.string.enter_text))
                        }
                        innerTextField.invoke()
                    }
                }
            }
        )


        Text(text = resultText, modifier = Modifier.padding(16.dp))
    }
}
