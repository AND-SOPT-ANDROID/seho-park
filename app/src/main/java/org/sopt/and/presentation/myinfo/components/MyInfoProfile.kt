package org.sopt.and.presentation.myinfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey100
import org.sopt.and.ui.theme.White100

@Composable
fun MyInfoProfile(
    myHobby: String,
    getMyHobby: () -> Unit,
    modifier: Modifier = Modifier
) {
    getMyHobby()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Grey100)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = stringResource(id = R.string.my_account_icon_description),
            modifier = Modifier.size(80.dp),
            tint = White100
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = myHobby.ifEmpty { "Loading..." },
            color = White100
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = stringResource(id = R.string.my_notification_icon_description),
            modifier = Modifier.size(30.dp),
            tint = White100
        )

        Spacer(modifier = Modifier.width(24.dp))

        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(id = R.string.my_setting_icon_description),
            modifier = Modifier.size(30.dp),
            tint = White100
        )
    }
}