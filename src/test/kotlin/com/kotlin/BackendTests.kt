package com.kotlin

import io.restassured.RestAssured.given
import io.restassured.path.json.JsonPath
import org.junit.Assert
import org.junit.jupiter.api.Test


class BackendTests {
    private val baseUri = "https://api.whisk-dev.com/"
    private val token = "Bearer 4PkSbMXhfI4xMmdWy9xpTLLHkvImTGkY84zW9xbbdR8Dm4Q8FH0BL3Sdy73mdkSo"

    private fun createList(): JsonPath? {
        val response = given().baseUri(baseUri)
            .header("Authorization", token)
            .header("Content-type", "application/json")
            .body("{\"name\":\"new shopping list\", \"primary\":false}").log()
            .all().`when`()
            .post("list/v2").then().log()
            .all().statusCode(200).extract().jsonPath()
        return response

    }

    @Test
    fun test1() {
        val newListResponseData = createList()
        val id = newListResponseData?.getString("list.id")

        val response = given().baseUri(baseUri)
            .header("Authorization", token)
            .header("Content-type", "application/json")
            .`when`()
            .get("list/v2/$id").then().log()
            .all().statusCode(200).extract().jsonPath()

        val responseId = newListResponseData?.getString("list.id")
        val shoppingList = response.getString("content")

        Assert.assertNotEquals( "", responseId);
        Assert.assertEquals( "[:]", shoppingList);
    }

    @Test
    fun test2() {
        val newListResponseData = createList()
        val id = newListResponseData?.getString("list.id")

        given().baseUri(baseUri)
            .header("Authorization", token)
            .`when`()
            .delete("list/v2/$id").then().log()
            .all().statusCode(200)

        given().baseUri(baseUri)
            .header("Authorization", token)
            .header("Content-type", "application/json")
            .`when`()
            .get("list/v2/$id").then().log()
            .all().statusCode(400).extract().jsonPath()
    }
}