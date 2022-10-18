package com.studio.noodoeassignment.data

data class LogInResponse(
    var objectId: String? = null,
    var name: String? = null,
    var timezone: String? = null,
    var phone: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var sessionToken: String? = null
)
