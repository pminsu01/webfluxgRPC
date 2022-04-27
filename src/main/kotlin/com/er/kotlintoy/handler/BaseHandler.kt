package com.er.kotlintoy.handler

import com.er.kotlintoy.document.BaseDocument
import com.er.kotlintoy.dto.BaseDTO
import com.er.kotlintoy.repository.BaseRepository
import com.er.kotlintoy.service.BaseService
import com.fasterxml.jackson.databind.ser.Serializers.Base
import com.mongodb.internal.connection.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
@Transactional
class BaseHandler(private val repo: BaseRepository) {

    @Autowired
    private lateinit var baseService: BaseService;


    // Router Handler 연결 테스트
    fun get(req: ServerRequest): Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body<String>(Mono.just("테스트 중입니다.."))


    // MongoDB 전체 조회 Test
    fun getAll(req: ServerRequest): Mono<ServerResponse> {
        val baseAllData: Flux<BaseDocument> = baseService.getAll()
        return ok().contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromProducer(baseAllData, BaseDocument::class.java))
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    // BaseId값에 따른 조회 Test
    fun findByBaseId(req: ServerRequest): Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.justOrEmpty(Optional.empty()))

}