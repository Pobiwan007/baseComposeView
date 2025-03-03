package com.basecomposeview.list

sealed class NavigationAction {
    data object Previous : NavigationAction()
    data object Next : NavigationAction()
}