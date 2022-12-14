package com.supplychainpoc.dropwizardapp.services

import com.supplychainpoc.dropwizardapp.api.OrderModel
import com.supplychainpoc.dropwizardapp.entities.OrderEntity
import com.supplychainpoc.dropwizardapp.enums.OrderStatus
import com.supplychainpoc.dropwizardapp.exceptions.CustomException
import com.supplychainpoc.dropwizardapp.exceptions.Errors
import java.util.UUID

object OrderService {
    private val orders = mutableListOf<OrderEntity>()

    fun create(order: OrderModel): OrderEntity {
        val entity = OrderEntity(
            id = UUID.randomUUID(),
            vendorId = order.vendorId,
            muid = order.muid,
            products = listOf(), // order.products,
            status = OrderStatus.RECEIVED
        )
        orders.add(entity)
        return entity
    }

    fun list(): List<OrderEntity> {
        return orders
    }

    fun get(id: UUID): OrderEntity? {
        return orders.find { it.id.toString() == id.toString() }
    }

    fun updateStatus(id: UUID, status: OrderStatus) {
        val index = orders.indexOfFirst { it.id == id }
        if (index > -1) {
            orders[index].status = status
        } else {
            throw Exception("Not Found")
        }
    }

    fun updateStatus2(id: UUID, status: OrderStatus): OrderEntity {
        val order = orders.find {
            it.id == id
        } ?: throw CustomException(Errors.ORDER_NOT_FOUND)
        order.status = status
        return order
    }
}
