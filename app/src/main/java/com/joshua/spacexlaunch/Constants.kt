package com.joshua.spacexlaunch

const val DB_NAME ="space_x_launch.db"
const val TABLE_LAUNCHES = "launches_table"

// Base URL for API
const val SPACE_X_BASE_URL = "https://api.spacexdata.com"
const val API_TIME_OUT = 100_000

//SCREEN_ROUTE
const val ROUTE_SPLASH = "splash"
const val ROUTE_LAUNCHES = "launches"
const val ROUTE_LAUNCHES_DETAIL = "info/{launchesId}"
const val ROUTE_LAUNCHES_KEY = "launchesId"
const val ROUTE_ABOUT = "about"