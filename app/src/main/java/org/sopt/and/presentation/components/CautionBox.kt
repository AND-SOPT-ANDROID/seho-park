package org.sopt.and.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Grey200

@Composable
fun CautionBox(
    caution: Int,
    contentDescription: Int
) {
    Row {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(id = contentDescription),
            tint = Grey200
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = stringResource(id = caution),
            color = Grey200,
            style = TextStyle(fontSize = 11.sp)
        )
    }
}