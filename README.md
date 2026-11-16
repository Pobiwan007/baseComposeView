# baseComposeView

[![CI](https://github.com/Pobiwan007/baseComposeView/actions/workflows/ci.yml/badge.svg)](https://github.com/Pobiwan007/baseComposeView/actions/workflows/ci.yml)
[![JitPack](https://jitpack.io/v/Pobiwan007/baseComposeView.svg)](https://jitpack.io/#Pobiwan007/baseComposeView)
![minSdk](https://img.shields.io/badge/minSdk-24-brightgreen)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202024.12-4285F4?logo=jetpackcompose&logoColor=white)

A set of reusable Jetpack Compose building blocks I use as the UI foundation of my apps:
layout shortcuts, form controls, image helpers, type-safe bottom navigation and a result-driven
screen wrapper.

## What's inside

| Area | Components |
|---|---|
| **State** | `BaseResultView` renders `Loading` / `Success` / `Error` of a [`Result`](https://github.com/Pobiwan007/common) |
| **Navigation** | `BaseNavHost`, `BaseBottomNavigation` (type-safe routes, Navigation 2.8), `BaseRoute`, `NavigationListView` |
| **Layout** | `SpaceBetweenRow`, `SpaceAroundRow`, `CenteredColumn`, `SpaceBetweenColumn`, `RoundedBorderBox`, `TransparentRoundedBox` |
| **Controls** | `CustomSwitch` (animated), `ItemCheckbox` (checkbox with an editable label), `CustomPaddingTextField` |
| **Images** | `BaseImageView` (resource or URL via Coil), `AsyncImageWithPlaceholder`, `AssetImage`, `BaseImage` + `NavType` for passing images between destinations |
| **Drawer** | `DrawerHandlerView`: modal drawer with a draggable edge handle that follows the drawer offset |
| **Indicators** | `LabeledCircularProgressIndicator`, `HorizontalViewPagerIndicator` |
| **Modifiers** | `noIndicationClickable`, `addBottomShadow` (rounded polygon shadow via `graphics-shapes`), `bitmapBackground` |
| **System UI** | `SystemUiController`: immersive mode that restores the bars when the screen leaves composition |
| **Typography** | `BaseTypography`, `Text.size(TextType)`: font size proportional to the screen width |

## Examples

**Screen driven by a typed result**

```kotlin
val state by viewModel.profile.collectAsStateWithLifecycle()

BaseResultView(
    response = state,
    onLoading = { CircularProgressIndicator() },
    onError = { Text(stringResource(it.error.message)) },
    onSuccessful = { ProfileContent(it.data) }
)
```

**Type-safe bottom navigation**

```kotlin
@Serializable object Home : BaseRoute(iconRes = R.drawable.ic_home, nameRes = R.string.home)
@Serializable object Settings : BaseRoute(iconRes = R.drawable.ic_settings, nameRes = R.string.settings)

Scaffold(
    bottomBar = {
        BaseBottomNavigation(navController = navController, routes = listOf(Home, Settings)) { route, onClick, selected ->
            NavigationBarItem(selected = selected, onClick = onClick, icon = { Icon(painterResource(route.iconRes!!), null) })
        }
    }
) { padding ->
    BaseNavHost(Modifier.padding(padding), startDestination = Home, navController = navController) {
        composable<Home> { HomeScreen() }
        composable<Settings> { SettingsScreen() }
    }
}
```

Re-selecting the current tab pops its back stack to the root; switching tabs saves and restores each tab's state.

## Installation

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories { maven("https://jitpack.io") }
}

// build.gradle.kts
dependencies {
    implementation("com.github.Pobiwan007:baseComposeView:v1.0.0")
}
```

## Build & test

```bash
./gradlew testReleaseUnitTest assembleRelease
```
