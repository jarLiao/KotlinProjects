package com.eview.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import theme.ErrorColor
import theme.PrimaryColor
import theme.SurfaceColor
import theme.textBlack

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
//)

val bigWhiteBold = TextStyle(color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
val maxWhiteBold = TextStyle(color = Color.White, fontSize = 18.sp,fontWeight = FontWeight.Bold)
val normalWhiteBold = TextStyle(color = Color.White, fontSize = 16.sp,fontWeight = FontWeight.Bold)
val normalWhite = TextStyle(color = Color.White, fontSize = 20.sp)
val smallNormalWhite = TextStyle(color = Color.White, fontSize = 14.sp)
val maxWhite = TextStyle(color = Color.White, fontSize = 18.sp)
val minWhiteBold = TextStyle(color = Color.White, fontSize = 10.sp,fontWeight = FontWeight.Bold)


val smallBlack2 = TextStyle(color = textBlack, fontSize = 12.sp)
val smallBlack = TextStyle(color = textBlack, fontSize = 14.sp)
val normalBlack2 = TextStyle(color = textBlack, fontSize = 16.sp)
val normalBlack3 = TextStyle(color = textBlack, fontSize = 18.sp)
val normalBlack = TextStyle(color = textBlack, fontSize = 20.sp)
val smallBlackBold = TextStyle(color = textBlack, fontSize = 12.sp,fontWeight = FontWeight.Bold)
val smallBlackBold2 = TextStyle(color = textBlack, fontSize = 14.sp,fontWeight = FontWeight.Bold)
val blackBold = TextStyle(color = textBlack, fontSize = 18.sp,fontWeight = FontWeight.Bold)
val blackBold2 = TextStyle(color = textBlack, fontSize = 16.sp,fontWeight = FontWeight.Bold)
val bigBlackBold = TextStyle(color = textBlack, fontSize = 36.sp, fontWeight = FontWeight.Bold)
val MaxBlackBold = TextStyle(color = textBlack, fontSize = 48.sp, fontWeight = FontWeight.Bold)
//
//
//val smallGreen = TextStyle(color = switchOn, fontSize = 14.sp)

val smallGray = TextStyle(color = SurfaceColor, fontSize = 14.sp)
val normalGray = TextStyle(color = SurfaceColor, fontSize = 16.sp)
val MaxBlackGray= TextStyle(color = SurfaceColor, fontSize = 48.sp, fontWeight = FontWeight.Bold)


val maxThemeBold = TextStyle(color = PrimaryColor, fontSize = 18.sp,fontWeight = FontWeight.Bold)
val maxTheme = TextStyle(color = PrimaryColor, fontSize = 18.sp)
val normalTheme = TextStyle(color = PrimaryColor, fontSize = 16.sp)
val smallTheme = TextStyle(color = PrimaryColor, fontSize = 14.sp)
val normalThemeBold = TextStyle(color = PrimaryColor, fontSize = 16.sp,fontWeight = FontWeight.Bold)


val smallRed2 = TextStyle(color = ErrorColor, fontSize = 12.sp)
val smallRed = TextStyle(color = ErrorColor, fontSize = 14.sp)
val normalRed = TextStyle(color = ErrorColor, fontSize = 16.sp)





