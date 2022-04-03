package es.usj.androidapps.infrastructure

import org.glassfish.jersey.client.JerseyClientBuilder
import java.util.concurrent.TimeUnit
import javax.ws.rs.client.Client

internal const val LOCAL = "http://localhost:43005"

data class TestProperties(
    val client: Client = JerseyClientBuilder()
        .connectTimeout(30000, TimeUnit.MILLISECONDS)
        .readTimeout(30000, TimeUnit.MILLISECONDS)
        .build(),
    var baseUrl: String,
    var token: String,
    val environment: Environment = Environment.LOCAL
) {
    companion object {

        var MASTER_TENANT_TOKEN =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaGVybmFuZGV6IiwidGVuYW50SWQiOiI4MTc5NzU4Yy00YmVjLTQwM2UtODQ2Ny0wMzBkZmIyMjdjMjAiLCJpYXQiOjE2NDI0Mjc3MzIsImV4cCI6MTY0MjUxNDEzMn0.WmY3htLgmuewy4erfUonis9UO9LFZiBMp6a4r8d_M9NF6b5fJAv2N9fPGDpg4eCc5nj3_gGJ02LqGajfQBr1AQ"
        var SECONDARY_TENANT_TOKEN = MASTER_TENANT_TOKEN
        var PRIMARY_TENANT_TOKEN = MASTER_TENANT_TOKEN
        var TOKEN_WITHOUT_TENANT = ""

        fun local() = TestProperties(
            environment = Environment.LOCAL,
            baseUrl = LOCAL,
            token = PRIMARY_TENANT_TOKEN
        )

        fun mock() = TestProperties(
            environment = Environment.LOCAL,
            baseUrl = LOCAL,
            token = TOKEN_WITHOUT_TENANT
        )

        fun qa() = TestProperties(environment = Environment.QA, baseUrl = LOCAL, token = "")
        fun develop() = TestProperties(environment = Environment.TEST, baseUrl = LOCAL, token = "")
        fun production() = TestProperties(environment = Environment.PRODUCTION, baseUrl = LOCAL, token = "")
    }
}
