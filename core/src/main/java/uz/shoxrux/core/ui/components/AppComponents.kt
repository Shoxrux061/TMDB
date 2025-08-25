package uz.shoxrux.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.shoxrux.core.R

@Composable
fun AppTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.background),
        border = BorderStroke(
            width = 2.dp,
            color = if (isFocused) {
                colors.onSurfaceVariant
            } else if (isEmpty) {
                colors.errorContainer
            } else {
                colors.primary
            }
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            textStyle = typography.bodyMedium,
            singleLine = true,
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = colors.onSurfaceVariant
                    )
                )
            },
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colors.background,
                unfocusedContainerColor = colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            interactionSource = interactionSource
        )
    }

}

@Composable
fun AppTextFieldWithHide(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val isVisible = remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.background),
        border = BorderStroke(
            width = 2.dp,
            color = if (isFocused) {
                colors.onSurfaceVariant
            } else if (isEmpty) {
                colors.error
            } else {
                colors.primary
            }
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = typography.bodyMedium,
                singleLine = true,
                placeholder = {
                    Text(
                        text = hint,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = colors.onSurfaceVariant
                        )
                    )
                },
                onValueChange = {
                    onValueChange(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colors.background,
                    unfocusedContainerColor = colors.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                interactionSource = interactionSource,

                visualTransformation = if (isVisible.value) PasswordVisualTransformation() else VisualTransformation.None
            )

            IconButton(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterEnd)
                    .size(36.dp)
                    .clip(CircleShape),
                onClick = {
                    isVisible.value = !isVisible.value
                }
            ) {
                Icon(
                    tint = colors.onSurfaceVariant,
                    painter = if (isVisible.value) painterResource(R.drawable.ic_visibility)
                    else painterResource(R.drawable.ic_visibility_off),
                    contentDescription = null
                )

            }
        }

    }
}

@Composable
fun LargeAppButton(onClick: () -> Unit, text: String, modifier: Modifier = Modifier) {

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = {
            onClick.invoke()
        },
        colors = ButtonDefaults.buttonColors(colors.primary),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            style = typography.titleSmall
        )
    }

}