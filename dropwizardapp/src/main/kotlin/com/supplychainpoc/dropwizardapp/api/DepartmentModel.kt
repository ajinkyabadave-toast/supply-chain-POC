package com.supplychainpoc.dropwizardapp.api

import com.fasterxml.jackson.annotation.JsonProperty

data class DepartmentModel(
    @JsonProperty("name")
    val name: String
)
