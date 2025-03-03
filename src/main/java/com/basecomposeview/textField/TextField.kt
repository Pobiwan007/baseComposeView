package com.basecomposeview.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.Container
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.basecomposeview.theme.BaseTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPaddingTextField(
    modifier: Modifier = Modifier,
    textState: MutableState<TextFieldValue>,
    onPostError: @Composable () -> Unit = {},
    placeholderValue: @Composable () -> Unit = {},
    onValueChanged: () -> Unit = {},
    textStyle: TextStyle = BaseTypography.bodySmall,
    backgroundColor: Color = Color.White,
    isError: Boolean = false,
    textFieldHeight: Dp = 50.dp,
    innerPadding: Dp = 8.dp,
    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyBoardActions: KeyboardActions = KeyboardActions.Default
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(textFieldHeight)
                .background(backgroundColor),
            value = textState.value,
            onValueChange = { newValue: TextFieldValue ->
                textState.value = newValue
                onValueChanged()
            },
            singleLine = true,
            keyboardOptions = keyBoardOptions,
            keyboardActions = keyBoardActions,
            textStyle = textStyle.copy(color = if (isError) Color.Red else Color.Black),
            decorationBox = @Composable { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    TextFieldDefaults.DecorationBox(
                        value = textState.value.text,
                        innerTextField = innerTextField,
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        placeholder = {
                            placeholderValue.invoke()
                        },
                        contentPadding = PaddingValues(innerPadding),
                        container = {
                            Container(
                                isError = false,
                                enabled = true,
                                interactionSource = interactionSource,
                                colors = colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedTextColor = if (isError) Color.Red else Color.Black
                                ),
                                shape = TextFieldDefaults.shape,
                                focusedIndicatorLineThickness = 0.dp,
                                unfocusedIndicatorLineThickness = 0.dp,
                            )
                        }
                    )
                }
            }
        )
        onPostError()
    }

}


@Preview
@Composable
fun PreviewTextField() {
    CustomPaddingTextField(textState = remember { mutableStateOf(TextFieldValue("eeee")) })
}