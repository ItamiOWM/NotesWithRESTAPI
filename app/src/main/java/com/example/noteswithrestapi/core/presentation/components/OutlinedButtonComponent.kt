package com.example.noteswithrestapi.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteswithrestapi.core.presentation.theme.ui.theme.NotesWithRESTAPITheme

@Composable
fun OutlinedButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    leadingIcon: ImageVector? = null,
    borderColor: Color = MaterialTheme.colorScheme.tertiary,
    contentColor: Color = MaterialTheme.colorScheme.tertiary,
    contentPadding: PaddingValues = PaddingValues(start = 32.dp, end = 40.dp, top = 14.dp, bottom = 14.dp),
    enabled: () -> Boolean,
    shape: Shape = MaterialTheme.shapes.large,
    onButtonClick: () -> Unit,
) {

    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor
        ),
        border = BorderStroke(2.dp, borderColor),
        shape = shape,
        enabled = enabled(),
        contentPadding = contentPadding,
        onClick = {
            onButtonClick()
        },
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = text,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.width(9.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun OutlinedButtonComponentPreview() {
    NotesWithRESTAPITheme(darkTheme = false) {
        OutlinedButtonComponent(
            text = "Login",
            enabled = { true },
            leadingIcon = Icons.Rounded.Login,
            onButtonClick = {}
        )
    }
}