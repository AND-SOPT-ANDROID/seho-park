package org.sopt.and.presentation.signup.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.util.Utils
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun SignUpGreetingText(fontSize: Int) {
    Text(
        AnnotatedString(
            text = stringResource(R.string.sign_up_welcome_text),
            spanStyles = listOf(
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    ),
                    start = Utils.GREETING_FIRST_LINE_FOCUS_START_INDEX,
                    end = Utils.GREETING_FIRST_LINE_FOCUS_END_INDEX
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    ),
                    start = Utils.GREETING_FIRST_LINE_FOCUS_END_INDEX,
                    end = Utils.GREETING_FIRST_LINE_END_INDEX
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = White100,
                        fontSize = fontSize.sp
                    ),
                    start = Utils.GREETING_SECOND_LINE_FOCUS_START_INDEX,
                    end = Utils.GREETING_SECOND_LINE_FOCUS_END_INDEX
                ),
                AnnotatedString.Range(
                    item = SpanStyle(
                        color = Grey200,
                        fontSize = fontSize.sp
                    ),
                    start = Utils.GREETING_SECOND_LINE_FOCUS_END_INDEX,
                    end = Utils.GREETING_SECOND_LINE_END_INDEX
                ),
            ),
            paragraphStyles = listOf(
                AnnotatedString.Range(
                    item = ParagraphStyle(
                        lineHeight = 2.em,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Top,
                            trim = LineHeightStyle.Trim.Both
                        )
                    ),
                    start = Utils.GREETING_FIRST_LINE_FOCUS_START_INDEX,
                    end = Utils.GREETING_SECOND_LINE_END_INDEX
                )
            )
        )
    )
}