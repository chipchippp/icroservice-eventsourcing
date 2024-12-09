package com.example.borrowingservice.query.controller;


import com.example.borrowingservice.command.model.BorrowingRequestModel;
import com.example.borrowingservice.query.queries.GetAllBorrowingQuery;
import com.example.borrowingservice.query.queries.GetBorrowingDetailQuery;
import com.example.commonservice.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingQueryController {
    private final QueryGateway queryGateway;
    private final KafkaService kafkaService;

    @GetMapping
    public List<BorrowingRequestModel> getAllBorrowings() {
        GetAllBorrowingQuery query = new GetAllBorrowingQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BorrowingRequestModel.class)).join();
    }

    @GetMapping("/{id}")
    public BorrowingRequestModel getBorrowingById(@PathVariable String id) {
        GetBorrowingDetailQuery query = new GetBorrowingDetailQuery(id);
        return queryGateway.query(query, ResponseTypes.instanceOf(BorrowingRequestModel.class)).join();
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message) {
        kafkaService.sendMessage("test", message);
    }
}
