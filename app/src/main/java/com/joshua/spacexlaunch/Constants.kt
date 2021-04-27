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

const val KEY_CAPSULES_LAST_REFRESH = "key_capsules_last_refresh"
const val KEY_CORES_LAST_REFRESH = "key_cores_last_refresh"
const val KEY_COMPANY_LAST_REFRESH = "key_company_last_refresh"
const val KEY_LAUNCHES_LAST_REFRESH = "key_upcoming_launches_last_refresh"
const val KEY_LAUNCH_PADS_LAST_REFRESH = "key_launch_pads_last_refresh"

const val PREFS_KEY_REFRESH_INTERVAL = "prefs_refresh_interval"
const val PREFS_KEY_DARK_MODE = "prefs_dark_theme"
const val PREFS_KEY_NOTIFICATIONS_UPCOMING_LAUNCHES = "prefs_notifications_upcoming_launches"
