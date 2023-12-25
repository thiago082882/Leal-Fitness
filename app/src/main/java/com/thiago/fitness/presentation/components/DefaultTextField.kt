package com.thiago.fitness.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thiago.fitness.presentation.ui.theme.Blue200


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DefaultTextField(
    modifier: Modifier,
    value : String,
    onValueChange : (value: String) -> Unit,
    validateField: () -> Unit = {},
    label : String,
    icon : ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText : Boolean = false,
    errorMsg : String ="",

){
    Column() {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()


                            },
            keyboardOptions =  KeyboardOptions(keyboardType=keyboardType),
            label =
            {
                Text(label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White

                )
            },
            visualTransformation = if(hideText) PasswordVisualTransformation()else VisualTransformation.None
        )
        Text(
            modifier= Modifier.padding(top=2.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color= Blue200


        )
    }


}