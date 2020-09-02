package hu.netcode.devtools.service

import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MongoService {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun generateObjectId(size: Int = 1): List<String> {
        val objectIdList = mutableListOf<String>()
        for (i in 1..size) {
            objectIdList.add(ObjectId().toString())
        }
        return objectIdList
    }
}
