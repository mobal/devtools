package hu.netcode.devtools.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UUIDService {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun generate(size: Int = 1): List<String> {
        val uuidList = mutableListOf<String>()
        for (i in 1..size) {
            uuidList.add(UUID.randomUUID().toString())
        }
        return uuidList
    }
}
