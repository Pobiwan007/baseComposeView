package com.basecomposeview.checkbox

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun ItemCheckbox(
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false,
    text: String,
    enableTextStyle: TextStyle = TextStyle.Default,
    disableTextStyle: TextStyle = TextStyle.Default,
    isEnabledTextField: Boolean = false,
    uncheckedColor: Color = Color.Gray,
    checkedColor: Color = Color.Green,
    textMaxLength: Int = 50,
    onDone: (String, Boolean) -> Unit = { _, _ -> },
    onChecked: () -> Unit = {}
) {
    val textField = remember { mutableStateOf(text) }
    val isChecked = remember { mutableStateOf(isCompleted) }
    Row(modifier = modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            modifier = Modifier.padding(start = 20.dp).border(1.dp, if(isChecked.value) checkedColor else uncheckedColor, RoundedCornerShape(14.dp)).size(40.dp),
            checked = isChecked.value,
            onCheckedChange = {
                onChecked()
                isChecked.value = !isChecked.value
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = checkedColor,
                checkedColor = Color.White,
                uncheckedColor = Color.Transparent,
            ),
        )
        TextField(
            modifier = Modifier,
            value = textField.value,
            enabled = isEnabledTextField,
            onValueChange = { newValue ->
                if(newValue.length <= textMaxLength)
                    textField.value = newValue
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onDone(
                    textField.value,
                    isChecked.value
                )
            }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            textStyle = if (isChecked.value) disableTextStyle else enableTextStyle
        )
    }
}